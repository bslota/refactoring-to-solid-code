package com.bslota.refactoring.library;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author bslota on 23/11/2019
 */
class PatronIdTest {

    private static final int SOME_VALUE = 10;

    @Test
    void shouldBeCreatedFromInt() {
        //when
        PatronId id = PatronId.of(SOME_VALUE);

        //then
        assertNotNull(id);
    }

    @Test
    void shouldConvertToInt() {
        //given
        PatronId id = PatronId.of(SOME_VALUE);

        //expect
        assertEquals(SOME_VALUE, id.asInt());
    }

    @Test
    void twoPatronIdsWithTheSameValueShouldBeEqual() {
        //given
        PatronId firstId = PatronId.of(SOME_VALUE);
        PatronId secondID = PatronId.of(SOME_VALUE);

        //expect
        assertEquals(firstId, secondID);
    }
}