package de.wiedehoeft.librarykata;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class RentalTest {

    private Customer customer;
    private Movie movie;

    @Before
    public void setUp() throws Exception {
        customer = new Customer();
        movie = new Movie("The Hunger Games");
    }

    @Test
    public void rentMovieForFirstDayWithBasePrice() {
        // When
        customer.rentMovie(movie, 1);

        // Then
        assertThat(customer.getTotalAmount()).isEqualTo(3.00);
    }

    @Test
    public void rentMovieForMaxDaysWithBasePrice() {
        // When
        customer.rentMovie(movie, 3);

        // Then
        assertThat(customer.getTotalAmount()).isEqualTo(3.00);
    }


    @Test
    public void rentMovieForFirstDayWithExtraCosts() {
        // When
        customer.rentMovie(movie, 4);

        // Then
        assertThat(customer.getTotalAmount()).isEqualTo(4.50);
    }


    @Test
    public void rentMovieForSecondDayWithExtraCosts() {
        // When
        customer.rentMovie(movie, 5);

        // Then
        assertThat(customer.getTotalAmount()).isEqualTo(6.00);
    }

    @Test
    public void printRental() {
        // Given
        customer.rentMovie(new Movie("Kill Bill"), 3);
        customer.rentMovie(new Movie("Star Wars"), 4);
        customer.rentMovie(new Movie("The Minions"), 2);

        // When
        String rental = customer.printRental();

        // Then
        assertThat(rental).isEqualTo("" +
                "Kill Bill\tEUR\t3.00\n" +
                "Star Wars\tEUR\t4.50\n" +
                "The Minions\tEUR\t3.00\n" +
                "Total Charge\tEUR\t10.50");

    }

    @Test
    public void prettyPrintAmount() {
        // Given
        customer.rentMovie(movie, 1);

        // Expect
        assertThat(customer.prettyPrint(customer.getTotalAmount())).isEqualTo("EUR\t3.00");
    }
}
