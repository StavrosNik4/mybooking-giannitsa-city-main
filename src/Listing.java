import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.io.Serializable;
import java.util.HashMap;

/**
 *  This class represents the listing of a provider.
 *  Every listing has 2 string fields, one for the name of the listing and one for the owner's name.
 *  There is one double field for the price of the listing.
 *  There are 2 boolean fields for the existence of wifi and parking in the building.
 *  Finally, there is a hash map for the bookings of the Listing.
 */

public class Listing implements Serializable {

    private final String name;
    private final String owner;
    private final double price;
    private final boolean wifi;
    private final boolean parking;
    private final HashMap<Integer, Booking> bookings;

    /**
     * Listing constructor. It initializes the fields of the class.
     * @param name must be a String for the name of the listing.
     * @param owner sets the name of the owner
     * @param price sets the listing's price
     * @param wifi sets the wifi existence
     * @param parking sets the parking existence
     */

    Listing(String name, String owner,  double price, boolean wifi, boolean parking){
        this.name = name;
        this.owner = owner;
        this.price = price;
        this.wifi = wifi;
        this.parking = parking;
        this.bookings = new HashMap<>();
    }

    //getters

    /**
     * Getter for the listing's name.
     * @return the name of the listing.
     */
    public String getName() {
        return name;
    }

    /**
     * Getter for the wifi.
     * @return true if there is wifi in the listing, false if there is not.
     */
    public boolean isWifi() {
        return wifi;
    }

    /**
     * Getter for the parking.
     * @return true if there is parking in the listing, false if there is not.
     */
    public boolean isParking() {
        return parking;
    }

    /**
     * Getter for the listing's price.
     * @return the listing's price.
     */
    public double getPrice() {
        return price;
    }

    /**
     * Getter for the name of the listing's owner.
     * @return the name of the listing's owner.
     */
    public String getOwner() {
        return owner;
    }

    /**
     * Getter for the bookings.
     * @return the bookings hash map
     */
    public HashMap<Integer, Booking> getBookings() {
        return bookings;
    }

    /**
     * Method to add a booking.
     * @param booking the booking that is going to be added.
     */
    public void addBooking(Booking booking){
        bookings.put(booking.getNum(), booking);
    }

    /**
     * Method to return a Listing as a JPanel for the GUI.
     * @return the JPanel with information about the listing.
     */
    public JPanel toPanel(){
        Border blackline = BorderFactory.createLineBorder(Color.black);
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(250, 200));
        panel.setBackground(Color.WHITE);
        panel.setLayout(new FlowLayout(FlowLayout.CENTER, 300, 10));

        JLabel label1 = new JLabel(this.getName());
        panel.add(label1);

        JLabel label2 = new JLabel("Owner: " + this.getOwner());
        panel.add(label2);

        String wifi;
        if(isWifi())
            wifi = "yes";
        else
            wifi = "no";
        JLabel label4 = new JLabel("WIFI: " + wifi);
        panel.add(label4);

        String parking;
        if(isParking())
            parking = "yes";
        else
            parking = "no";
        JLabel label5 = new JLabel("Parking: " + parking);
        panel.add(label5);

        JLabel label6 = new JLabel("Price: " + price + "/day");
        panel.add(label6);

        panel.setBorder(blackline);
        return panel;
    }
}
