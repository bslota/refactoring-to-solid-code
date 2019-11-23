package com.bslota.refactoring.library;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * @author bslota on 18/11/2019
 */
class BookIdTest {

    private static final int SOME_VALUE = 1;

    @Test
    void shouldBeCreatedFromInteger() {
        //when
        BookId bookId = BookId.of(SOME_VALUE);

        //then
        assertNotNull(bookId);
    }

    @Test
    void shouldConvertToInteger() {
        //when
        BookId bookId = BookId.of(SOME_VALUE);

        //then
        assertEquals(SOME_VALUE, bookId.asInt());
    }

    @Test
    void twoBookIdsWithSameValueShouldBeEqual() {
        //given
        BookId firstId = BookId.of(SOME_VALUE);
        BookId secondId = BookId.of(SOME_VALUE);

        //expect
        assertEquals(firstId, secondId);
    }
}