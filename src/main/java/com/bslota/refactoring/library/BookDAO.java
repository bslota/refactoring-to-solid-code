package com.bslota.refactoring.library;

import com.bslota.refactoring.util.DatabaseNotChosenYetException;
import org.springframework.stereotype.Repository;

@Repository
public class BookDAO implements BookRepository {

    @Override
    public Book findBy(BookId id) {
        return getBookFromDatabase(id.asInt());
    }

    @Override
    public void save(Book book) {
        update(book);
    }

    public Book getBookFromDatabase(int bookId) {
        throw new DatabaseNotChosenYetException();
    }

    public void update(Book book) {
        throw new DatabaseNotChosenYetException();
    }
}
