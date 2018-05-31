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
}
