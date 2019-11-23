package com.bslota.refactoring.library;

import org.junit.jupiter.api.Test;

import static com.bslota.refactoring.library.PatronFixture.PatronBuilder.newPatron;
import static org.junit.jupiter.api.Assertions.*;

/**
 * @author bslota on 23/11/2019
 */
class PatronTest {

    private static final int PATRON_ID_VALUE = 10;

    @Test
    void shouldCreateWithGivenId() {
        //given
        PatronId id = PatronId.of(PATRON_ID_VALUE);

        //when
        Patron patron = newPatron().withId(id).build();

        //then
        assertEquals(PATRON_ID_VALUE, patron.getPatronIdValue());
        assertEquals(id, patron.getPatronId());
    }

}