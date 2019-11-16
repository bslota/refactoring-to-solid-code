package com.bslota.refactoring.library;

import java.util.List;

public class Patron {
    private int patronId;
    private int type;
    private int points;
    private String email;
    private boolean qualifiesForFreeBook;
    private List<Integer> holds;

    public int getType() {
        return this.type;
    }

    public int getPoints() {
        return this.points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public void setQualifiesForFreeBook(boolean flag) {
        this.qualifiesForFreeBook = flag;
    }

    public boolean isQualifiesForFreeBook() {
        return this.qualifiesForFreeBook;
    }

    public String getEmail() {
        return this.email;
    }

    public List<Integer> getHolds() {
        return this.holds;
    }

    public void setHolds(List<Integer> holds) {
        this.holds = holds;
    }
}
