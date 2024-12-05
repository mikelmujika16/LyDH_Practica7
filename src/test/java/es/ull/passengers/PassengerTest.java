package es.ull.passengers;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import es.ull.flights.Flight;
import es.ull.passengers.Passenger;

public class PassengerTest {

    private Passenger passenger;
    private Flight flight;

    @BeforeEach
    public void setUp() {
        passenger = new Passenger("12345", "John Doe", "US");
        flight = new Flight("AA1234", 100);
    }

    @Test
    public void testPassengerCreation() {
        assertEquals("12345", passenger.getIdentifier());
        assertEquals("John Doe", passenger.getName());
        assertEquals("US", passenger.getCountryCode());
    }

    @Test
    public void testJoinFlight() {
        passenger.joinFlight(flight);
        assertEquals(flight, passenger.getFlight());
        assertTrue(flight.getNumberOfPassengers() == 1);
    }

    @Test
    public void testLeaveFlight() {
        passenger.joinFlight(flight);
        passenger.joinFlight(null);
        assertNull(passenger.getFlight());
        assertTrue(flight.getNumberOfPassengers() == 0);
    }

    @Test
    public void testInvalidCountryCode() {
        assertThrows(RuntimeException.class, () -> {
            new Passenger("12345", "John Doe", "XX");
        });
    }
}