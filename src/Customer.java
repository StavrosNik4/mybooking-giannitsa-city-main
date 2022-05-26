import org.jdesktop.swingx.JXDatePicker;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * This class represents the customers of the application.
 * It extends the general user class.
 */

public class Customer extends User {

    /**
     * Constructor to set nickname and password and rank of the customer.
     * It uses the super method for the nickname and password params.
     * @param nickname must be a string for the provider's nickname.
     * @param password must be a string for the provider's password.
     */

    Customer(String nickname, String password) {
        super(nickname, password);
        rank = "Customer";
    }

    /**
     * A method to return a panel with the booking's of a customer.
     * @param giannitsa must be a platform for the existing platform of the application.
     * @param frame must be a JFrame of the main frame of the program.
     * @return JPanel of the bookings of the customer.
     */
    public JPanel bookings(Platform giannitsa, JFrame frame) {
        JPanel panel = new JPanel();
        panel.setSize(980, 720);
        panel.setBackground(Color.CYAN);
        panel.setLayout(new FlowLayout());
        boolean flag = true;
        for (Booking a : giannitsa.getBookings().values()) {
            if(a.getCustomer().equals(getNickname())) {
                JPanel panel1 = a.toPanel();
                JButton button = new CancelButton(frame, this, giannitsa, a);
                flag = false;
                if(!a.isActive())
                    button.setEnabled(false);
                panel1.add(button);
                panel.add(panel1);
            }
        }
        if(flag)
        {
            panel.setLayout(null);
            JLabel t = new JLabel("You haven't booked any listing yet!");
            t.setFont(new Font(null, Font.PLAIN, 32));
            t.setBounds(300, 270, 500, 40);
            panel.add(t);

        }
        return panel;
    }

    /**
     * A method to return the panel of the search method with all the criteria and buttons in GUI format.
     * @param giannitsa must be a platform for the existing platform of the application.
     * @param frame must be a JFrame of the main frame of the program.
     * @return the JPanel of the search method.
     */
    public JPanel search(Platform giannitsa, JFrame frame){
        JPanel panel = new JPanel();
        panel.setSize(980, 720);
        panel.setLayout(new BorderLayout());

        JPanel panel1 = new JPanel();
        panel1.setBackground(Color.GREEN);
        panel1.setPreferredSize(new Dimension(300, 720));
        panel1.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 10));

        panel1.add(new JLabel("Min Price: "));

        JSlider min = new JSlider();
        min.setPreferredSize(new Dimension(200, 50));
        min.setPaintTicks(true);
        min.setMinorTickSpacing(10);
        min.setPaintTrack(true);
        min.setMajorTickSpacing(25);
        min.setPaintLabels(true);
        panel1.add(min);

        panel1.add(new JLabel("Max Price: "));

        JSlider max = new JSlider();
        max.setPreferredSize(new Dimension(200, 50));
        max.setPaintTicks(true);
        max.setMinorTickSpacing(10);
        max.setPaintTrack(true);
        max.setMajorTickSpacing(25);
        max.setPaintLabels(true);
        panel1.add(max);

        panel1.add(new JLabel("WIFI: "));
        JRadioButton yes1 = new JRadioButton("yes");
        JRadioButton no1 = new JRadioButton("no");
        ButtonGroup wifi = new ButtonGroup();
        wifi.add(yes1);
        wifi.add(no1);
        panel1.add(yes1);
        panel1.add(no1);

        panel1.add(new JLabel("Parking: "));
        JRadioButton yes2 = new JRadioButton("yes");
        JRadioButton no2 = new JRadioButton("no");
        ButtonGroup parking = new ButtonGroup();
        parking.add(yes2);
        parking.add(no2);
        panel1.add(yes2);
        panel1.add(no2);

        panel1.add(new JLabel("Starting day: "));

        JXDatePicker picker = new JXDatePicker();
        picker.setDate(Calendar.getInstance().getTime());
        picker.setFormats(new SimpleDateFormat("dd.MM.yyyy"));
        panel1.add(picker);


        panel1.add(new JLabel("Ending day: "));
        JXDatePicker picker2 = new JXDatePicker();
        picker2.setDate(Calendar.getInstance().getTime());
        picker2.setFormats(new SimpleDateFormat("dd.MM.yyyy"));
        panel1.add(picker2);

        JButton sr = new JButton("Search now!");
        panel1.add(sr);
        sr.addActionListener(e -> {

            if(picker.getDate().getTime() == picker2.getDate().getTime() || picker.getDate().after(picker2.getDate()) || picker.getDate().before(Calendar.getInstance().getTime()) || picker2.getDate().before(Calendar.getInstance().getTime())){
                JOptionPane.showMessageDialog(null, "Please enter valid dates!", "Oops!", JOptionPane.WARNING_MESSAGE);
                return;
            }
            boolean w = yes1.isSelected();
            boolean p = yes2.isSelected();

            ArrayList<Listing> listings = giannitsa.search(picker.getDate(), picker2.getDate(), min.getValue(), max.getValue(), w, p);
            JPanel r = results(listings, picker.getDate(), picker2.getDate(), giannitsa, frame);
            panel.removeAll();
            panel.add(panel1, BorderLayout.WEST);
            panel.add(r, BorderLayout.CENTER);
            frame.add(panel);
            frame.invalidate();
            frame.validate();
        });

        panel.add(panel1, BorderLayout.WEST);
        return panel;
    }

    /**
     * A private method to return the results of a user's search.
     * @param listings must be an ArrayList with the resulted listings by the Platform's {@code search} method (check line 140 of this class)
     * @param from must be a Date with the selected, by the user, starting day of the booking.
     * @param to must be a Date with the selected, by the user, last day of the booking.
     * @param giannitsa must be a platform for the existing platform of the application.
     * @param frame must be a JFrame of the main frame of the program.
     * @return the JPanel of all the resulted Listings.
     */
    private JPanel results(ArrayList<Listing> listings, Date from, Date to, Platform giannitsa, JFrame frame){
        JPanel panel = new JPanel();
        panel.setBackground(Color.CYAN);
        panel.setPreferredSize(new Dimension(680, 720));
        panel.setLayout(new GridLayout(1,1));
        JPanel panel2 = new JPanel();
        panel2.setBackground(Color.CYAN);
        panel2.setPreferredSize(new Dimension(680, 720));
        panel2.setLayout(new GridLayout(1,3));
        JPanel tmp = new JPanel();
        tmp.setBackground(Color.CYAN);
        tmp.setLayout(new FlowLayout(FlowLayout.CENTER, 300, 20));
        for (Listing a: listings) {
            JPanel t = a.toPanel();
            t.add(new BookButton(frame, this, giannitsa, a, from, to));
            tmp.add(t);
        }
        panel2.add(tmp, 0,0);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        JScrollPane scrollPane = new JScrollPane(panel2);
        panel.add(scrollPane);
        return panel;
    }
}