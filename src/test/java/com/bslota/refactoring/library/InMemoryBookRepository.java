package com.bslota.refactoring.library;

/**
 * @author bslota on 20/11/2019
 */
class InMemoryBookRepository extends InMemoryRepository<BookId, Book> implements BookRepository {

    InMemoryBookRepository() {
        super(Book::getBookId);
    }
}
