package com.bslota.refactoring.library;

import java.util.Objects;

/**
 * @author bslota on 23/11/2019
 */
class PatronId {

    private final int value;

    private PatronId(int value) {
        this.value = value;
    }

    static PatronId of(int someValue) {
        return new PatronId(someValue);
    }

    int asInt() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PatronId patronId = (PatronId) o;
        return value == patronId.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
