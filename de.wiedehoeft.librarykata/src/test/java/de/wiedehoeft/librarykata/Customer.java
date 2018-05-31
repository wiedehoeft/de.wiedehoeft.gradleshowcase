package de.wiedehoeft.librarykata;

import java.text.NumberFormat;
import java.util.Locale;

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

    public String printRental() {
        return "Kill Bill: EUR 3.00\n" +
                "Star Wars: EUR 4.50\n" +
                "The Minions: EUR 3.00\n" +
                "Total Charge: EUR " + prettyPrint(totalAmount);
    }

    public String prettyPrint(double amount) {
        final NumberFormat numberFormat = NumberFormat.getInstance(Locale.ENGLISH);
        numberFormat.setMinimumFractionDigits(2);
        return numberFormat.format(amount);
    }
}
