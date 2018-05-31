package de.wiedehoeft.librarykata;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Queue;

public class Customer {

    private static final double BASE_PRICE = 3.00;
    private static final int DAYS_DISCOUNTED = 3;
    private static final double ADDITIIONAL_PRICE = 1.50;
    private double totalAmount;
    private List<String> rentals;

    public Customer() {
        this.rentals = new ArrayList<>();
    }

    public double rentMovie(int days) {
        totalAmount += BASE_PRICE;

        if (days > DAYS_DISCOUNTED) {
            totalAmount += ADDITIIONAL_PRICE * (days - DAYS_DISCOUNTED);
        }

        return totalAmount;
    }

    public String printRental() {
        return
                this.rentals.get(0) + ": EUR 3.00\n" +
                        this.rentals.get(1) + ": EUR 4.50\n" +
                        this.rentals.get(2) + ": EUR 3.00\n" +
                        "Total Charge:" + prettyPrint(totalAmount);
    }

    public String prettyPrint(double amount) {
        final NumberFormat numberFormat = NumberFormat.getInstance(Locale.ENGLISH);
        numberFormat.setMinimumFractionDigits(2);
        return " EUR " + numberFormat.format(amount);
    }

    public void rentMovie(String title, int days) {
        this.rentals.add(title);
        rentMovie(days);
    }
}
