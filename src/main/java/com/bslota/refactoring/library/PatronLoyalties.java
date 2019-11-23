package com.bslota.refactoring.library;

/**
 * @author bslota on 23/11/2019
 */
class PatronLoyalties {
    private PatronId patronId;
    private int type;
    private int points;
    private boolean qualifiesForFreeBook;

    PatronLoyalties(PatronId patronId, int type, int points, boolean qualifiesForFreeBook) {
        this.patronId = patronId;
        this.type = type;
        this.points = points;
        this.qualifiesForFreeBook = qualifiesForFreeBook;
    }

    PatronId getPatronId() {
        return patronId;
    }

    int getType() {
        return type;
    }

    void setType(int type) {
        this.type = type;
    }

    int getPoints() {
        return points;
    }

    void setPoints(int points) {
        this.points = points;
    }

    boolean isQualifiesForFreeBook() {
        return qualifiesForFreeBook;
    }

    void setQualifiesForFreeBook(boolean qualifiesForFreeBook) {
        this.qualifiesForFreeBook = qualifiesForFreeBook;
    }
}
