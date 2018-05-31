package de.wiedehoeft.librarykata;

public class Movie {
    private final String title;
    private double amount;

    public Movie(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public double getAmount() {
        return this.amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
