package com.bslota.refactoring.library;

/**
 * @author bslota on 23/11/2019
 */
class BookPlacedOnHold implements PlaceOnHoldResult {

    private final BookId bookId;
    private final PatronId patronId;

    private BookPlacedOnHold(BookId bookId, PatronId patronId) {
        this.bookId = bookId;
        this.patronId = patronId;
    }

    static BookPlacedOnHold of(BookId bookId, PatronId patronId) {
        return new BookPlacedOnHold(bookId, patronId);
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
