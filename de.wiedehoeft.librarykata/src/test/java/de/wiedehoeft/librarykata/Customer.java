package de.wiedehoeft.librarykata;

public class Customer {

    private static final double BASE_PRICE = 3.00;
    private static final int DAYS_DISCOUNTED = 3;
    private static final double ADDITIIONAL_PRICE = 1.50;
    private double totalAmount;

    public double rentMovie(int days) {
        totalAmount += BASE_PRICE;

        if (days > DAYS_DISCOUNTED) {
            totalAmount += ADDITIIONAL_PRICE * (days - DAYS_DISCOUNTED);
        }

        return totalAmount;
    }
}
