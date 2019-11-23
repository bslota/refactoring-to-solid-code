package com.bslota.refactoring.library;

import com.bslota.refactoring.util.DatabaseNotChosenYetException;
import org.springframework.stereotype.Repository;

@Repository
public class PatronDAO implements PatronRepository {

    @Override
    public Patron findBy(PatronId patronId) {
        return getPatronFromDatabase(patronId.asInt());
    }

    @Override
    public void save(Patron patron) {
        update(patron);
    }

    public Patron getPatronFromDatabase(int patronId) {
        throw new DatabaseNotChosenYetException();
    }

    public void update(Patron patron) {
        throw new DatabaseNotChosenYetException();
    }
}
