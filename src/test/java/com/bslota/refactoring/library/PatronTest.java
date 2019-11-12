package com.bslota.refactoring.library;

import org.junit.jupiter.api.Test;

import static com.bslota.refactoring.library.PatronTest.PatronBuilder.newPatron;
import static org.junit.jupiter.api.Assertions.*;

/**
 * @author bslota on 12/11/2019
 */
class PatronTest {

    @Test
    void shouldCreateAPatronWithId() {
        //given
        PatronId patronId = PatronId.of(1);
        //when
        Patron patron = newPatron()
                .with(patronId)
                .build();

        //then
        assertEquals(patronId, patron.getPatronId());
    }

    static class PatronBuilder {

        private PatronId patronId;

        static PatronBuilder newPatron() {
            return new PatronBuilder();
        }

        PatronBuilder with(PatronId id) {
            this.patronId = id;
            return this;
        }

        Patron build() {
            return new Patron(patronId);
        }
    }
}