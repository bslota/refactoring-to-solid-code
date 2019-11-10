package com.bslota.refactoring.library;

import com.bslota.refactoring.util.DatabaseNotChosenYetException;
import org.springframework.stereotype.Repository;

@Repository
public class PatronDAO {
    public Patron getPatronFromDatabase(int patronId) {
        throw new DatabaseNotChosenYetException();
    }
}
