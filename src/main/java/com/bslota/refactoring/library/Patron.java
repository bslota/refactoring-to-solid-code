package com.bslota.refactoring.library;

import java.util.List;

public class Patron {
    private PatronId patronId;
    private int type;
    private int points;
    private boolean qualifiesForFreeBook;
    private List<Integer> holds;

    public Patron(PatronId patronId, int type, int points, boolean qualifiesForFreeBook, List<Integer> holds) {
        this.patronId = patronId;
        this.type = type;
        this.points = points;
        this.qualifiesForFreeBook = qualifiesForFreeBook;
        this.holds = holds;
    }

    PlaceOnHoldResult placeOnHold(Book book) {
        if (hasNotReachedMaximumNumberOfHolds()) {
            this.holds.add(book.getBookIdValue());
            return BookPlacedOnHold.of(book.getBookId(), this.patronId);
        } else {
            return PlacingOnHoldFailed.of(book.getBookId(), this.patronId);
        }
    }

    public int getPatronIdValue() {
        return patronId.asInt();
    }

    public int getType() {
        return this.type;
    }

    public void setType(int type) {
        this.type = type;
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

    public boolean isQualifiesForFreeBook() {
        return this.qualifiesForFreeBook;
    }

    public List<Integer> getHolds() {
        return this.holds;
    }

    public void setHolds(List<Integer> holds) {
        this.holds = holds;
    }

    public PatronId getPatronId() {
        return this.patronId;
    }

    boolean hasNotReachedMaximumNumberOfHolds() {
        return this.holds.size() < 5;
    }
}
