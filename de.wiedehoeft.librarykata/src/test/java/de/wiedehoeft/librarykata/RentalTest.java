package de.wiedehoeft.librarykata;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class RentalTest {

    private Customer customer;

    @Before
    public void setUp() throws Exception {
        customer = new Customer();
    }

    @Test
    public void rentMovieForFirstDayWithBasePrice() {

        // Expect
        assertThat(customer.rentMovie(1)).isEqualTo(3.00);
    }

    @Test
    public void rentMovieForMaxDaysWithBasePrice() {

        // Expect
        assertThat(customer.rentMovie(3)).isEqualTo(3.00);
    }


    @Test
    public void rentMovieForFirstDayWithExtraCosts() {

        // Expect
        assertThat(customer.rentMovie(4)).isEqualTo(4.50);
    }


    @Test
    public void rentMovieForSecondDayWithExtraCosts() {

        // Expect
        assertThat(customer.rentMovie(5)).isEqualTo(6.00);
    }

    @Test
    public void printRental() {
        customer.rentMovie(new Movie("Kill Bill"), 3);
        customer.rentMovie(new Movie("Star Wars"), 4);
        customer.rentMovie(new Movie("The Minions"), 2);

        // When
        String rental = customer.printRental();

        // Then
        assertThat(rental).isEqualTo("" +
                "Kill Bill: EUR 3.00\n" +
                "Star Wars: EUR 4.50\n" +
                "The Minions: EUR 3.00\n" +
                "Total Charge: EUR 10.50");

    }

    @Test
    public void prettyPrintAmount() {
        final double amount = customer.rentMovie(1);

        assertThat(customer.prettyPrint(amount)).isEqualTo(" EUR 3.00");
    }
}
