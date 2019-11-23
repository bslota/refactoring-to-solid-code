package com.bslota.refactoring.library;

/**
 * @author bslota on 20/11/2019
 */
public interface BookRepository {

    Book findBy(BookId id);

    void save(Book book);
}
