import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import static org.junit.Assert.*;

public class BookingTest {

    Booking booking;
    Listing listing;
    Date from;
    Date to;
    HashMap<Integer, Booking> bookings;

    @Before
    public void setUp(){
        from = Calendar.getInstance().getTime();
        to = new Date();
        long time = from.getTime() + 24 * 60 * 60 * 1000;
        to.setTime(time);
        listing = new Listing("listing", "takis", 45, false, true);
        bookings = new HashMap<>();
        booking = new Booking(bookings, "makis", listing, from, to);
        bookings.put(booking.getNum(), booking);
    }

    @Test
    public void getNum(){
        assertEquals(1, booking.getNum());
        Booking booking2 = new Booking(bookings, "makis", listing, from, to);
        bookings.put(bookings.size()+1,booking2);
        assertEquals(2, booking2.getNum());
    }

    @Test
    public void getFrom(){
        assertEquals(from, booking.getFrom());
    }

    @Test
    public void getTo(){
        assertEquals(to, booking.getTo());
    }

    @Test
    public void getCustomer(){assertEquals("makis", booking.getCustomer());}

    @Test
    public void setActive(){
        assertTrue(booking.isActive());
        booking.setActive(false);
        assertFalse(booking.isActive());
    }
}