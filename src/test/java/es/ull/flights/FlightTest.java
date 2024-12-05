package es.ull.flights;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import es.ull.flights.Flight;
import es.ull.passengers.Passenger;

public class FlightTest {

    private Flight flight;
    private Passenger passenger;

    @BeforeEach
    public void setUp() {
        flight = new Flight("AA1234", 100);
        passenger = new Passenger("12345", "John Doe", "US");
    }

    @Test
    public void testFlightCreation() {
        assertEquals("AA1234", flight.getFlightNumber());
        assertEquals(100, flight.getSeats()); // Corrected assertion
    }

    @Test
    public void testAddPassenger() {
        flight.addPassenger(passenger);
        assertEquals(1, flight.getNumberOfPassengers());
        assertEquals(flight, passenger.getFlight());
    }

    @Test
    public void testRemovePassenger() {
        flight.addPassenger(passenger);
        flight.removePassenger(passenger);
        assertEquals(0, flight.getNumberOfPassengers());
        assertNull(passenger.getFlight());
    }

    @Test
    public void testInvalidFlightNumber() {
        assertThrows(RuntimeException.class, () -> {
            new Flight("1234", 100);
        });
    }

    @Test
    public void testNotEnoughSeats() {
        Flight smallFlight = new Flight("AA1234", 1);
        smallFlight.addPassenger(passenger);
        Passenger anotherPassenger = new Passenger("67890", "Jane Doe", "US");
        assertThrows(RuntimeException.class, () -> {
            smallFlight.addPassenger(anotherPassenger);
        });
    }
}