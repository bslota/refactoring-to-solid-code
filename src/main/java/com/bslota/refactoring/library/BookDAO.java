package com.bslota.refactoring.library;

import com.bslota.refactoring.util.DatabaseNotChosenYetException;
import org.springframework.stereotype.Repository;

@Repository
public class BookDAO {
    public Book getBookFromDatabase(int bookId) {
        throw new DatabaseNotChosenYetException();
    }

    public void update(Book book) {
    }
}
