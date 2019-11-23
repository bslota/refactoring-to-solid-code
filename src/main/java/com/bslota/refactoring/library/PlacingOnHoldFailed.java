package com.bslota.refactoring.library;

/**
 * @author bslota on 23/11/2019
 */
class PlacingOnHoldFailed implements PlaceOnHoldResult {

    private final BookId bookId;
    private final PatronId patronId;

    private PlacingOnHoldFailed(BookId bookId, PatronId patronId) {
        this.bookId = bookId;
        this.patronId = patronId;
    }

    static PlacingOnHoldFailed of(BookId bookId, PatronId patronId) {
        return new PlacingOnHoldFailed(bookId, patronId);
    }

    @Override
    public BookId bookId() {
        return this.bookId;
    }

    @Override
    public PatronId patronId() {
        return this.patronId;
    }
}
