package de.wiedehoeft.librarykata;

public class Customer {

    private double totalAmount;

    public double rentMovie(int days) {
        totalAmount += 3.00;

        if (days > 3) {
            totalAmount += 1.50 * (days - 3);
        }

        return totalAmount;
    }
}
