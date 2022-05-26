import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

/**
 * This class represents the platform that holds all the listings, users, new users and the search listings method.
 * We use 4 hash maps to store the users, the listings, the new users, the bookings and the cancellations (values).
 * As keys, we use the users' nicknames, the listings' names, the new users' nicknames and for the bookings hash map we use an integer value
 * which is the number of the booking that happened in the plartform in chronological order.
 */

public class Platform implements Serializable {

    //finals were suggested by IntelliJ IDEA
    private final HashMap<String,User> users;
    private final HashMap<String,Listing> listings;
    private final HashMap<String, User> newUsers;
    private final HashMap<Integer,Booking> bookings; //for the bookings and the cancellations.

    /**
     * Platform constructor.
     */
    Platform()
    {
        //initializing Hash Maps
        users = new HashMap<>();
        listings = new HashMap<>();
        newUsers = new HashMap<>();
        bookings = new HashMap<>();

        //creating user objects

        Admin babis = new Admin("babis", "2310");
        users.put(babis.getNickname(), babis);

        Admin pakis = new Admin("pakis","12345");
        users.put(pakis.getNickname(), pakis);

        Provider takis = new Provider("takis", "58100");
        users.put(takis.getNickname(), takis);
        Listing takisHotel = new Listing("Takis Hotel", takis.getNickname(), 50, true, false);
        listings.put("Takis Hotel", takisHotel);

        Customer makis = new Customer("makis", "123");
        users.put(makis.getNickname(), makis);

        Customer xristos = new Customer("xristos", "54321");
        users.put(xristos.getNickname(), xristos);
        newUsers.put(xristos.getNickname(), xristos);

        Provider akis = new Provider("akis", "135");
        users.put(akis.getNickname(), akis);
        newUsers.put(akis.getNickname(), akis);
    }

    //getters

    /**
     * Getter for the platform's users hash map.
     * @return users hash map.
     */
    public HashMap<String, User> getUsers() {
        return users;
    }

    /**
     * Getter for the platform's listings hash map.
     * @return listings hash map.
     */
    public HashMap<String,Listing> getListings() {
        return listings;
    }

    /**
     * Getter for the new users hash map.
     * @return platform's new users hash map.
     */
    public HashMap<String,User> getNewUsers() {
        return newUsers;
    }

    /**
     * Getter for the bookings of the platform.
     * @return the platform's bookings hash map.
     */
    public HashMap<Integer, Booking> getBookings() {
        return bookings;
    }

    //methods

    /**
     * Method that adds a new user in the users hash map of the platform with key the user's nickname and value the user object.
     * @param a the user object to add to the hash map.
     */
    public void addUser(User a){
        users.put(a.getNickname(), a);
        newUsers.put(a.getNickname(), a);
    }

    /**
     * Method that adds a new listing in the listings hash map of the platform with key the listing's name and value the listing object.
     * @param tmp must be a Listing to be added to the listings hash map.
     */
    public void addListing(Listing tmp) {
        listings.put(tmp.getName(), tmp);
    }

    /**
     * Method to delete a listing from the platform.
     * @param name must be a String with the name of the listing that we want to delete
     */
    public boolean deleteListing(String name) {
        if (listings.containsKey(name)) {
            listings.remove(name);
            return true;
        }
        else
            return false;
    }

    /**
     * Search method that uses the customer's preferences to find all the listings that match.
     * @param start must be a Date for the first day of the booking
     * @param end must be a Date for the last day of the booking
     * @param min must be an int for the minimum price of the search
     * @param max must be an int for the maximum price of the search
     * @param wifi must be a boolean for the existence of wifi
     * @param parking must be a boolean for the existence of parking
     * @return an array list with all the matching listings of the customer's search
     */
    public ArrayList<Listing> search(Date start, Date end, int min, int max, boolean wifi, boolean parking) {
        ArrayList<Listing> match = new ArrayList<>();

        for (Listing a : listings.values()) {
            if (a.getBookings().isEmpty()) {
                match.add(a);
                continue;
            }
            int it = a.getBookings().size();
            int it2 = 0;
            for (Booking b : a.getBookings().values()) {
                if (!b.isActive() || (start.before(b.getFrom()) && end.before(b.getFrom())) || (start.after(b.getTo()) && end.after(b.getTo()))) {
                    it2 = it2 + 1;
                } else
                    break;
            }
            if (it == it2) {
                match.add(a);
            }
        }

        match.removeIf(a -> a.getPrice() < min || a.getPrice() > max);

        if(wifi)
            match.removeIf(a -> !a.isWifi());

        if(parking)
            match.removeIf(a -> !a.isParking());

        return match;
    }

    /**
     * Method to add a booking.
     * @param a the booking that's going to be added.
     */
    public void addBooking(Booking a){
        bookings.put(a.getNum(), a);
    }

    /**
     * Method for the customer to cancel any booking.
     * @param a the booking that the customer wants to cancel.
     */
    public void cancel(Booking a) {
        a.setActive(false);
        bookings.remove(a.getNum());
        bookings.put(a.getNum(), a);
    }
}
