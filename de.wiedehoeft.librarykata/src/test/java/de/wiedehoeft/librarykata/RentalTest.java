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
}
