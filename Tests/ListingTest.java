import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import static org.junit.Assert.*;

public class ListingTest {
    Listing listing;
    Booking booking;
    HashMap<Integer, Booking> bookings;

    @Before
    public void setUp(){
        Date from = Calendar.getInstance().getTime();
        Date to = new Date();
        long time = from.getTime() + 24 * 60 * 60 * 1000;
        to.setTime(time);
        listing = new Listing("listing", "takis", 45, false, true);
        bookings = new HashMap<>();
        booking = new Booking(bookings, "takis", listing, from, to);
        bookings.put(booking.getNum(), booking);
        listing.addBooking(booking);
    }


    @Test
    public void getName() {
        assertEquals("listing", listing.getName());
    }

    @Test
    public void isWifi() {
        assertFalse(listing.isWifi());
    }

    @Test
    public void isParking() {
        assertTrue(listing.isParking());
    }

    @Test
    public void getPrice() {
        assertEquals(45.0, listing.getPrice(), 0.01);
    }

    @Test
    public void getOwner() {
        assertEquals("takis", listing.getOwner());
    }

    @Test
    public void getBookings() {
        assertEquals(bookings, listing.getBookings());
    }

    @Test
    public void addBooking() {
        assertEquals(1, listing.getBookings().size());
        Date from = Calendar.getInstance().getTime();
        long time = from.getTime() + 3 * (24 * 60 * 60 * 1000);
        from.setTime(time);
        Date to = Calendar.getInstance().getTime();
        time = to.getTime() + 4 * (24 * 60 * 60 * 1000);
        to.setTime(time);

        Booking test = new Booking(bookings, "takis", listing, from, to);
        listing.addBooking(test);
        assertEquals(2, listing.getBookings().size());
    }
}
