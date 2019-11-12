package com.bslota.refactoring.library;

import java.util.Objects;

/**
 * @author bslota on 12/11/2019
 */
final class PatronId {
    private final int value;

    private PatronId(int value) {
        this.value = value;
    }

    static PatronId of(int value) {
        return new PatronId(value);
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

    public int asInt() {
        return value;
    }
}
