package com.bslota.refactoring.library;

import java.time.Instant;

import static java.time.temporal.ChronoUnit.DAYS;

public class Book {
    private BookId bookId;
    private Instant reservationDate;
    private Instant reservationEndDate;
    private int patronId;

    public Book(BookId bookId, Instant reservationDate, Instant reservationEndDate, int patronId) {
        this.bookId = bookId;
        this.reservationDate = reservationDate;
        this.reservationEndDate = reservationEndDate;
        this.patronId = patronId;
    }

    public int getBookIdValue() {
        return bookId.asInt();
    }

    public BookId getBookId() {
        return bookId;
    }

    public Instant getReservationDate() {
        return this.reservationDate;
    }

    void placedOnHold(PatronId patronId, int days) {
        this.reservationDate = Instant.now();
        this.reservationEndDate = Instant.now().plus(days, DAYS);
        this.patronId = patronId.asInt();
    }

    public boolean isAvailable() {
        return reservationDate == null;
    }
}
