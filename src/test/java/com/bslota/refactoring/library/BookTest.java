package com.bslota.refactoring.library;

import org.junit.jupiter.api.Test;

import static com.bslota.refactoring.library.BookFixture.BookBuilder.newBook;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author bslota on 19/11/2019
 */
class BookTest {

    private static final int SOME_INT_VALUE = 123;

    @Test
    void shouldCreatePatronWithGivenId() {
        //given
        BookId id = BookId.of(SOME_INT_VALUE);

        //when
        Book book = newBook().withId(id).build();

        //then
        assertEquals(id.asInt(), book.getBookIdValue());
        assertEquals(id, book.getBookId());
    }

}
