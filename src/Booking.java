import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

/**
 * This class represents the bookings of the application.
 * It saves the days that the customer booked,
 * the name of the customer,
 * the listing that he booked,
 * the owner of the listing
 * and if it's active or cancelled.
 */

public class Booking implements Serializable {
    private final int num;
    private final Date from;
    private final Date to;
    private final String customer;
    private final String owner;
    private final Listing listing;
    private boolean active;
    /**
     * Booking constructor.
     * @param nickname must be String for the customer that booked the application nickname.
     * @param listing must be a Listing for the listing of the booking.
     * @param from must be a Date for the day that the customer starts the booking.
     * @param to must be a Dare for the dat that the customer stops the booking.
     */
    Booking(HashMap<Integer, Booking> bookings, String nickname, Listing listing, Date from, Date to){
        this.customer = nickname;
        this.owner = listing.getOwner();
        this.listing = listing;
        this.from = from;
        this.to = to;
        this.num = bookings.size() + 1;
        this.active = true;
    }

    //getters

    /**
     * Getter for the number of the booking.
     * @return the numerical index of the booking.
     */
    public int getNum() {
        return num;
    }

    /**
     * Getter for the first day of the booking.
     * @return the first day of the booking.
     */
    public Date getFrom() {
        return from;
    }

    /**
     * Getter for the last day of the booking.
     * @return the last day of the booking.
     */
    public Date getTo() {
        return to;
    }

    /**
     * Getter for the customer's name.
     * @return the name of the customer of the booking.
     */
    public String getCustomer() {
        return customer;
    }

    /**
     * Status getter.
     * @return true if booking is active, if the booking is canceled it returns false.
     */
    public boolean isActive() {
        return active;
    }

    /**
     * Owner's getter.
     * @return the listing's owner's name.
     */
    public String getOwner() {
        return owner;
    }

    //setter

    /**
     * Status setter.
     * @param status must be a boolean for the status of the booking. True if active / False if canceled.
     */
    public void setActive(boolean status) {
        this.active = status;
    }

    /**
     * Method to return a panel for a booking.
     * @return the booking panel.
     */

    public JPanel toPanel() {
        JPanel panel = new JPanel();
        Border blackline = BorderFactory.createLineBorder(Color.black);
        panel.setBorder(blackline);
        panel.setBackground(Color.WHITE);
        panel.setPreferredSize(new Dimension(420, 130));
        panel.setLayout(new FlowLayout());

        int s = 18;
        JLabel label = new JLabel("Booking Number: " + this.getNum() + " ");
        label.setFont(new Font(null, Font.PLAIN, s));
        JLabel label1 = new JLabel("Listing Name: " + listing.getName() + " ");
        label1.setFont(new Font(null, Font.PLAIN, s));
        JLabel label2 = new JLabel("Owner: " + listing.getOwner() + " ");
        label2.setFont(new Font(null, Font.PLAIN, s));
        JLabel label3 = new JLabel("Customer: " + customer + " ");
        label3.setFont(new Font(null, Font.PLAIN, s));
        DateFormat outputFormat = new SimpleDateFormat("dd/MM/yyyy");
        JLabel label4 = new JLabel("Days: " + outputFormat.format(this.getFrom()) + " - " + outputFormat.format(this.getTo()) + " ");
        label4.setFont(new Font(null, Font.PLAIN, s));
        JLabel label5;
        if (isActive())
            label5 = new JLabel("Status: Active");
        else
            label5 = new JLabel("Status: Canceled");
        label5.setFont(new Font(null, Font.PLAIN, s));

        panel.add(label);
        panel.add(label1);
        panel.add(label2);
        panel.add(label3);
        panel.add(label4);
        panel.add(label5);

        return panel;
    }
}
