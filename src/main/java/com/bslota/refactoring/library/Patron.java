package com.bslota.refactoring.library;

import java.time.Instant;
import java.util.List;

import static java.time.temporal.ChronoUnit.DAYS;

public class Patron {
    private int patronId;
    private String name;
    private int type;
    private int points;
    private String email;
    private boolean qualifiesForFreeBook;
    private List<Integer> holds;

    public int getPatronId() {
        return patronId;
    }

    public void setPatronId(int patronId) {
        this.patronId = patronId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getType() {
        return this.type;
    }

    public int getPoints() {
        return this.points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public void setQualifiesForFreeBook(boolean flag) {
        this.qualifiesForFreeBook = flag;
    }

    public void placeOnHold(Book book, int days) {
        Instant reservationEndDate = book.getReservationEndDate();
        if (reservationEndDate != null) {
            book.setReservationDate(Instant.now());
            book.setReservationEndDate(Instant.now().plus(days, DAYS));
            book.setPatronId(patronId);
            holds.add(book.getBookId());
        }
    }

    public boolean isQualifiesForFreeBook() {
        return this.qualifiesForFreeBook;
    }

    public String getEmail() {
        return this.email;
    }

    public List<Integer> getHolds() {
        return this.holds;
    }
}
