package com.bslota.refactoring.library;

import java.time.Instant;

public class Book {
    private int bookId;
    private String author;
    private String title;
    private Instant reservationDate;
    private Instant reservationEndDate;
    private int patronId;

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setReservationDate(Instant now) {
        this.reservationDate = now;
    }

    public void setReservationEndDate(Instant date) {
        this.reservationEndDate = date;
    }

    public Instant getReservationEndDate() {
        return this.reservationEndDate;
    }

    public void setPatronId(int patronId) {
        this.patronId = patronId;
    }
}
