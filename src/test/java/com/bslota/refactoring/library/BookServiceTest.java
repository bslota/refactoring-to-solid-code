package com.bslota.refactoring.library;

import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.IntStream;

import static java.time.temporal.ChronoUnit.DAYS;
import static java.util.stream.Collectors.toList;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author bslota on 23/11/2019
 */
class BookServiceTest {

    private static final int ID_OF_NOT_EXISTING_BOOK = 1;
    private static final int ID_OF_NOT_EXISTING_PATRON = 10;
    private static final int PERIOD_IN_DAYS = 100;
    private static final int ID_OF_AVAILABLE_BOOK = 2;
    private static final int ID_OF_PATRON_WITH_MAX_NUMBER_OF_HOLDS = 20;
    private static final int ID_OF_UNAVAILABLE_BOOK = 3;

    private BookDAO bookDAO = mock(BookDAO.class);
    private PatronDAO patronDAO = mock(PatronDAO.class);
    private NotificationSender notificationSender = mock(NotificationSender.class);

    private BookService bookService = new BookService(bookDAO, patronDAO, notificationSender);

    @Test
    void shouldFailToPlaceNotExistingBookOnHold() {
        //when
        boolean result = bookService.placeOnHold(ID_OF_NOT_EXISTING_BOOK, ID_OF_NOT_EXISTING_PATRON, PERIOD_IN_DAYS);

        //then
        assertFalse(false);
    }

    @Test
    void shouldNotSAveBookPatronOrSendNotificationFailToPlaceNotExistingBookOnHold() {
        //when
        bookService.placeOnHold(ID_OF_NOT_EXISTING_BOOK, ID_OF_NOT_EXISTING_PATRON, PERIOD_IN_DAYS);

        //then
        verify(bookDAO, never()).update(any());
        verify(patronDAO, never()).update(any());
        verify(notificationSender, never()).sendMail(any(), any(), any(), any());
    }

    @Test
    void shouldFailToPlaceBookOnHoldWhenPAtronHasMaxNumberOfHolds() {
        //given
        Book book = availableBook();
        Patron patron = patronWithMaxNumberOfHolds();

        //when
        boolean result = bookService.placeOnHold(book.getBookId(), patron.getPatronId(), PERIOD_IN_DAYS);

        //then
        assertFalse(result);
    }

    @Test
    void shouldFailToPlaceBookOnHoldWhenItIsNotAvailable() {
        //given
        Patron patron = patronWithMaxNumberOfHolds();
        Book book = unavailableBook();

        //when:
        boolean result = bookService.placeOnHold(book.getBookId(), patron.getPatronId(), PERIOD_IN_DAYS);

        //then
        assertFalse(result);
    }

    @Test
    void shouldSucceedToPlaceBookOnHold() {
        //given
        Book book = availableBook();
        Patron patron = patronWithoutHolds();

        //when
        boolean result = bookService.placeOnHold(book.getBookId(), patron.getPatronId(), PERIOD_IN_DAYS);

        //then
        assertTrue(result);
    }

    @Test
    void shouldSaveBookPatronAndSendNotificationSucceedToPlaceBookOnHold() {
        //given
        Book book = availableBook();
        Patron patron = patronWithoutHolds();

        //when
        bookService.placeOnHold(book.getBookId(), patron.getPatronId(), PERIOD_IN_DAYS);

        //then
        verify(bookDAO, atLeastOnce()).update(any());
        verify(patronDAO, atLeastOnce()).update(any());
        verify(notificationSender, atLeastOnce()).sendMail(any(), any(), any(), any());
    }

    private Patron patronWithoutHolds() {
        Patron patron = new Patron(ID_OF_PATRON_WITH_MAX_NUMBER_OF_HOLDS, 0, 10000, false, new LinkedList<>());
        when(patronDAO.getPatronFromDatabase(patron.getPatronId())).thenReturn(patron);
        return patron;
    }

    private Patron patronWithMaxNumberOfHolds() {
        List<Integer> holds = IntStream.of(1, 2, 3, 4, 5).boxed().collect(toList());
        Patron patron = new Patron(ID_OF_PATRON_WITH_MAX_NUMBER_OF_HOLDS, 0, 0, false, holds);
        when(patronDAO.getPatronFromDatabase(patron.getPatronId())).thenReturn(patron);
        return patron;
    }

    private Book availableBook() {
        Book book = new Book(ID_OF_AVAILABLE_BOOK, null, null, 0);
        when(bookDAO.getBookFromDatabase(book.getBookId())).thenReturn(book);
        return book;
    }

    private Book unavailableBook() {
        Instant reservationDate = Instant.now();
        Book book = new Book(ID_OF_UNAVAILABLE_BOOK, reservationDate, reservationDate.plus(PERIOD_IN_DAYS, DAYS), 0);
        when(bookDAO.getBookFromDatabase(book.getBookId())).thenReturn(book);
        return book;
    }

}