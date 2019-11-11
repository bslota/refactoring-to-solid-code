package com.bslota.refactoring.library;

import org.junit.jupiter.api.Test;
import org.mockito.stubbing.OngoingStubbing;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * @author bslota on 11/11/2019
 */
class BookServiceTest {

    private static final int NOT_EXISTING_BOOK_ID = 1;
    private static final int NOT_EXISTING_PATRON_ID = 10;
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
    void shouldFailToPlaceTheBookOnHold() {
        //given:
        bookThatDoesNotExist();
        patronThatDoesNotExist();

        //when:
        boolean bookWasPlacedOnHold = bookService.placeOnHold(NOT_EXISTING_BOOK_ID, NOT_EXISTING_PATRON_ID, HOLDING_PERIOD);

        //then
        assertFalse(bookWasPlacedOnHold);
    }

    private void patronThatDoesNotExist() {
        when(patronDAOStub.getPatronFromDatabase(NOT_EXISTING_PATRON_ID)).thenReturn(null);
    }

    private void bookThatDoesNotExist() {
        when(bookDAOStub.getBookFromDatabase(NOT_EXISTING_BOOK_ID)).thenReturn(null);
    }

}