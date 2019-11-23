package com.bslota.refactoring.library;

import java.util.List;

public class Patron {
    private PatronId patronId;
    private List<Integer> holds;
    private PatronLoyalties loyalties;

    public Patron(PatronId patronId, int type, int points, boolean qualifiesForFreeBook, List<Integer> holds) {
        this.patronId = patronId;
        this.loyalties = new PatronLoyalties(patronId, type, points, qualifiesForFreeBook);
        this.holds = holds;
    }

    PlaceOnHoldResult placeOnHold(Book book) {
        if (hasNotReachedMaximumNumberOfHolds() && book.isAvailable()) {
            this.holds.add(book.getBookIdValue());
            return BookPlacedOnHold.of(book.getBookId(), this.patronId);
        } else {
            return PlacingOnHoldFailed.of(book.getBookId(), this.patronId);
        }
    }

    public int getPatronIdValue() {
        return patronId.asInt();
    }

    public List<Integer> getHolds() {
        return this.holds;
    }

    public PatronId getPatronId() {
        return this.patronId;
    }

    private boolean hasNotReachedMaximumNumberOfHolds() {
        return this.holds.size() < 5;
    }

    public PatronLoyalties getLoyalties() {
        return loyalties;
    }
}
