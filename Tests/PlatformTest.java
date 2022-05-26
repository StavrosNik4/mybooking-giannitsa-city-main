import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import static org.junit.Assert.*;

public class PlatformTest {
    Platform platform;
    Admin admin;
    Listing listing;
    Booking booking;
    Customer customer;
    Provider provider;
    HashMap<Integer, Booking> bookings;
    Date from;
    Date to;

    @Before
    public void setUp(){
        platform = new Platform();
        admin = new Admin("admin", "2310");
        customer = new Customer("customer", "123");
        provider = new Provider("provider", "58100");
        listing = new Listing("hotel", provider.getNickname(), 69, false, true);
        platform.addListing(listing);
        from = Calendar.getInstance().getTime();
        long time = from.getTime() + 24 * 60 * 60 * 1000;
        to = new Date();
        to.setTime(time);
        bookings = platform.getBookings();
        booking = new Booking(bookings, customer.getNickname(), listing, from, to);
        platform.addBooking(booking);
    }

    @Test
    public void addUsers(){
        assertEquals(2, platform.getNewUsers().size());
        assertEquals(6, platform.getUsers().size());
        platform.addUser(admin);
        platform.addUser(customer);
        platform.addUser(provider);

        assertEquals(5, platform.getNewUsers().size()); // 5 because we have 3 new users (admin, customer, provider) + 2 already new users.
        assertEquals(9, platform.getUsers().size()); // 9 because we have the 6 users that we create in the start of the program.
    }

    @Test
    public void addListings(){
        assertEquals(2, platform.getListings().size()); //two because there is one default listing in Platform called "Takis Hotel"
        Listing listing2 = new Listing("Hotel 2", provider.getNickname(), 69, true, true);
        platform.addListing(listing2);
        assertEquals(3, platform.getListings().size());
    }

    @Test
    public void deleteListingTest(){
        assertEquals(2, platform.getListings().size());
        assertTrue(platform.deleteListing("hotel"));
        assertEquals(1, platform.getListings().size());
        assertFalse(platform.deleteListing("random"));
    }

    @Test
    public void addBookingTest(){
        assertEquals(1, platform.getBookings().size());
        from = Calendar.getInstance().getTime();
        long time = from.getTime() + 3 * (24 * 60 * 60 * 1000);
        from.setTime(time);
        time = from.getTime() + 4 * (24 * 60 * 60 * 1000);
        to.setTime(time);
        Booking booking2 = new Booking(bookings, customer.getNickname(), listing, from, to);
        platform.addBooking(booking2);
        assertEquals(2, platform.getBookings().size());
    }

    @Test
    public void cancelBookingTest(){
        assertTrue(platform.getBookings().get(booking.getNum()).isActive());
        platform.cancel(platform.getBookings().get(booking.getNum()));
        assertFalse(platform.getBookings().get(booking.getNum()).isActive());
    }

    @Test
    public void searchTest(){
        assertEquals(1, platform.search(from, to, 0, 100, true, false).size()); // only one because the booking we made here is already booked.
        assertEquals(0, platform.search(from, to, 100, 50, false, true).size()); // 0 because wrong mix and max
        long time = from.getTime() + 3 * (24 * 60 * 60 * 1000);
        from.setTime(time);
        time = from.getTime() + 4 * (24 * 60 * 60 * 1000);
        to.setTime(time);
        assertEquals(2, platform.search(from, to, 0, 100, false, false).size());
    }
}