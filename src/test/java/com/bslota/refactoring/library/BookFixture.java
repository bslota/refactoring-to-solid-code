package com.bslota.refactoring.library;

import java.time.Instant;

import static com.bslota.refactoring.library.BookFixture.BookBuilder.newBook;
import static java.time.temporal.ChronoUnit.DAYS;

/**
 * @author bslota on 15/11/2019
 */
class BookFixture {

    private static final int EXISTING_BOOK_ID = 2;
    private static final int SOME_RESERVATION_PERIOD = 50;

    static Book availableBook() {
        return newBook().build();
    }

    static Book unavailableBook() {
        Instant reservationDate = Instant.now();
        return newBook()
                .withReservationDate(reservationDate)
                .withReservationEndDate(reservationDate.plus(SOME_RESERVATION_PERIOD, DAYS))
                .build();
    }

    static class BookBuilder {
        private int bookId = EXISTING_BOOK_ID;
        private Instant reservationDate;
        private Instant reservationEndDate;
        private int patronId;

        static BookBuilder newBook() {
            return new BookBuilder();
        }

        BookBuilder withReservationDate(Instant reservationDate) {
            this.reservationDate = reservationDate;
            return this;
        }

        BookBuilder withReservationEndDate(Instant reservationEndDate) {
            this.reservationEndDate = reservationEndDate;
            return this;
        }

        BookBuilder withPatronId(int patronId) {
            this.patronId = patronId;
            return this;
        }

        Book build() {
            return new Book(bookId, reservationDate, reservationEndDate, patronId);
        }
    }
}

