package com.bslota.refactoring.library;

import java.time.Instant;

public class Book {
    private int bookId;
    private Instant reservationDate;
    private Instant reservationEndDate;
    private int patronId;

    public Book(int bookId, Instant reservationDate, Instant reservationEndDate, int patronId) {
        this.bookId = bookId;
        this.reservationDate = reservationDate;
        this.reservationEndDate = reservationEndDate;
        this.patronId = patronId;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public void setReservationDate(Instant now) {
        this.reservationDate = now;
    }

    public void setReservationEndDate(Instant date) {
        this.reservationEndDate = date;
    }

    public Instant getReservationDate() {
        return this.reservationDate;
    }

    public void setPatronId(int patronId) {
        this.patronId = patronId;
    }
}
