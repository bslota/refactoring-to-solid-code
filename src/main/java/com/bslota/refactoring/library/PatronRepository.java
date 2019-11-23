package com.bslota.refactoring.library;

/**
 * @author bslota on 20/11/2019
 */
public interface PatronRepository {

    Patron findBy(PatronId patronId);

    void save(Patron patron);
}
