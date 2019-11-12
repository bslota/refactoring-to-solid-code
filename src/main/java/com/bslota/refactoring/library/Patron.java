package com.bslota.refactoring.library;

import java.time.Instant;
import java.util.List;

import static java.time.temporal.ChronoUnit.DAYS;

public class Patron {
    private PatronId patronId;
    private int type;
    private int points;
    private String email;
    private boolean qualifiesForFreeBook;
    private List<Integer> holds;

    public Patron() {

    }

    public Patron(PatronId patronId) {
        this.patronId = patronId;
    }

    public PatronId getPatronId() {
        return patronId;
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
            book.setPatronId(patronId.asInt());
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

    public void setHolds(List<Integer> holds) {
        this.holds = holds;
    }
}
