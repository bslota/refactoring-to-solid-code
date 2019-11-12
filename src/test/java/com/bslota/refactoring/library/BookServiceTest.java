package com.bslota.refactoring.library;

import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * @author bslota on 11/11/2019
 */
class BookServiceTest {

    private static final int NOT_EXISTING_BOOK_ID = 1;
    private static final int EXISTING_BOOK_ID = 2;
    private static final int NOT_EXISTING_PATRON_ID = 10;
    private static final int EXISTING_PATRON_ID = 20;
    private static final int MAX_HOLDS_PATRON_ID = 30;
    private static final int HOLDING_PERIOD = 100;

    private BookDAO bookDAOStub = mock(BookDAO.class);
    private PatronDAO patronDAOStub = mock(PatronDAO.class);
    private NotificationSender notificationSender = new NotificationSender();
    private BookService bookService = new BookService(
            bookDAOStub,
            patronDAOStub,
            notificationSender
    );

    @Test
    void shouldFailToPlaceTheBookOnHoldWhenBookAndPatronDoNotExist() {
        //given:
        bookThatDoesNotExist();
        patronThatDoesNotExist();

        //when:
        boolean bookWasPlacedOnHold = bookService.placeOnHold(NOT_EXISTING_BOOK_ID, NOT_EXISTING_PATRON_ID, HOLDING_PERIOD);

        //then
        assertFalse(bookWasPlacedOnHold);
    }

    @Test
    void shouldFailToPlaceTheBookOnHoldWhenBookDoesNotExist() {
        //given:
        bookThatDoesNotExist();

        //and
        thereIsAPatron();

        //when:
        boolean bookWasPlacedOnHold = bookService.placeOnHold(NOT_EXISTING_BOOK_ID, EXISTING_PATRON_ID, HOLDING_PERIOD);

        //then
        assertFalse(bookWasPlacedOnHold);
    }

    @Test
    void shouldFailToPlaceTheBookOnHoldWhenPatronDoesNotExist() {
        //given:
        patronThatDoesNotExist();

        //and
        thereIsABook();

        //when:
        boolean bookWasPlacedOnHold = bookService.placeOnHold(NOT_EXISTING_BOOK_ID, EXISTING_PATRON_ID, HOLDING_PERIOD);

        //then
        assertFalse(bookWasPlacedOnHold);
    }

    @Test
    void shouldFailToPlaceTheBookOnHoldWhenPatronHasToManyHolds() {
        //given
        int maxAcceptableNumberOfHolds = 5;
        thereIsAPatronWith(maxAcceptableNumberOfHolds);

        //and
        thereIsABook();

        //when:
        boolean bookWasPlacedOnHold = bookService.placeOnHold(EXISTING_BOOK_ID, MAX_HOLDS_PATRON_ID, HOLDING_PERIOD);

        //then:
        assertFalse(bookWasPlacedOnHold);
    }

    @Test
    void shouldPlaceTheBookOnHold() {
        //given
        int specifiedNumberOfHolds = 4;
        thereIsAPatronWith(specifiedNumberOfHolds);

        //and
        thereIsABook();

        //when:
        boolean bookWasPlacedOnHold = bookService.placeOnHold(EXISTING_BOOK_ID, MAX_HOLDS_PATRON_ID, HOLDING_PERIOD);

        //then:
        assertTrue(bookWasPlacedOnHold);
    }

    private void thereIsAPatronWith(int specifiedNumberOfHolds) {
        Patron patron = new Patron();
//        patron.setPatronId(MAX_HOLDS_PATRON_ID);
//        patron.setHolds(IntStream.range(0, specifiedNumberOfHolds)
//                .boxed().collect(toList()));
//        when(patronDAOStub.getPatronFromDatabase(patron.getPatronId())).thenReturn(patron);
    }

    private void patronThatDoesNotExist() {
        when(patronDAOStub.getPatronFromDatabase(NOT_EXISTING_PATRON_ID)).thenReturn(null);
    }

    private void thereIsAPatron() {
        when(patronDAOStub.getPatronFromDatabase(EXISTING_PATRON_ID)).thenReturn(new Patron());
    }

    private void bookThatDoesNotExist() {
        when(bookDAOStub.getBookFromDatabase(NOT_EXISTING_BOOK_ID)).thenReturn(null);
    }

    private void thereIsABook() {
        when(bookDAOStub.getBookFromDatabase(EXISTING_BOOK_ID)).thenReturn(new Book());
    }

}