package com.bslota.refactoring.library;

/**
 * @author bslota on 20/11/2019
 */
class InMemoryPatronRepository extends InMemoryRepository<PatronId, Patron> implements PatronRepository {

    InMemoryPatronRepository() {
        super(Patron::getPatronId);
    }
}
