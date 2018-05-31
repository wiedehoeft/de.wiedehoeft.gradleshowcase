package de.wiedehoeft.librarykata;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class RentalTest {

    @Test
    public void rentMovieForOneDay() {
        // Given
        Customer customer = new Customer();

        // When
        final double amount = customer.rentMovie(1);

        // Then
        assertThat(amount).isEqualTo(3.00);
    }

    @Test
    public void rentMovieForThreeDays() {
        // Given
        Customer customer = new Customer();

        // When
        final double amount = customer.rentMovie(2);

        // Then
        assertThat(amount).isEqualTo(3.00);
    }


    @Test
    public void rentMovieForFourDays() {
        // Given
        Customer customer = new Customer();

        // When
        final double amount = customer.rentMovie(4);

        // Then
        assertThat(amount).isEqualTo(4.50);
    }


    @Test
    public void rentMovieForFiveDays() {
        // Given
        Customer customer = new Customer();

        // When
        final double amount = customer.rentMovie(5);

        // Then
        assertThat(amount).isEqualTo(6.00);
    }
}
