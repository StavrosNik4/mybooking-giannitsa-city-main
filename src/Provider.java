import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Objects;

/**
 * This class represents the providers of the application.
 * It extends the general user class.
 */

public class Provider extends User {

    /**
     * Constructor to set nickname and password and rank of the provider.
     * It uses the super method for the nickname and password params.
     * @param nickname must be a string for the provider's nickname.
     * @param password must be a string for the provider's password.
     */
    Provider(String nickname, String password) {
        super(nickname, password);
        rank = "Provider";
    }

    /**
     * A method to return the panel of all the listings of a provider.
     * @param giannitsa must be a platform for the existing platform of the application.
     * @param frame must be a JFrame of the main frame of the program.
     * @return the JPanel with the listings.
     */
    public JPanel listingsPanel(Platform giannitsa, JFrame frame){
        JPanel panel = new JPanel();
        panel.setBackground(Color.CYAN);
        panel.setLayout(new FlowLayout(FlowLayout.LEFT,30,20));

        for (Listing a: giannitsa.getListings().values()) {
            if(a.getOwner().equalsIgnoreCase(getNickname())) {
                JPanel panel1 = a.toPanel();
                JButton button = new DeleteButton(frame, giannitsa, a.getName());
                panel1.add(button);
                panel.add(panel1);
            }
        }
        return panel;
    }

    /**
     * A method to return a panel for the addListingPanel method.
     * @param giannitsa must be a platform for the existing platform of the application.
     * @param frame must be a JFrame of the main frame of the program.
     * @return the JPanel of the new listing.
     */
    public JPanel addListingPanel(Platform giannitsa, JFrame frame){
        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(Color.CYAN);
        JPanel panel = new JPanel();
        panel.setBackground(Color.yellow);
        panel.setPreferredSize(new Dimension(380, 420));
        panel.setLayout(null);
        JLabel label = new JLabel("Listing name:");
        JTextField text = new JTextField();
        text.setPreferredSize(new Dimension(100, 50));

        JLabel label1 = new JLabel("Wifi: ");
        JRadioButton yes1 = new JRadioButton("yes");
        JRadioButton no1 = new JRadioButton("no");
        ButtonGroup wifi = new ButtonGroup();
        wifi.add(yes1);
        wifi.add(no1);

        JLabel label2 = new JLabel("Parking: ");
        JRadioButton yes2 = new JRadioButton("yes");
        JRadioButton no2 = new JRadioButton("no");
        ButtonGroup parking = new ButtonGroup();
        parking.add(yes2);
        parking.add(no2);

        JLabel label3 = new JLabel("Price: ");
        JSlider slider = new JSlider();
        slider.setPreferredSize(new Dimension(100, 40));
        slider.setPaintTicks(true);
        slider.setMinorTickSpacing(10);
        slider.setPaintTrack(true);
        slider.setMajorTickSpacing(25);
        slider.setPaintLabels(true);

        JButton button = new JButton("Confirm");
        button.setPreferredSize(new Dimension(100, 50));

        label.setBounds(20, 20, 90,30);
        text.setBounds(150, 20, 150, 30);
        label1.setBounds(50, 80, 90,30);
        yes1.setBounds(150, 80, 50, 30);
        no1.setBounds(250, 80, 50, 30);
        label2.setBounds(50, 140, 90, 30);
        yes2.setBounds(150, 140, 50, 30);
        no2.setBounds(250, 140, 50, 30);
        label3.setBounds(50, 200, 90, 30);
        slider.setBounds(150, 200, 150, 50);
        button.setBounds(140, 300, 100, 30);

        button.addActionListener(e -> {
            if(!text.getText().isBlank()){
                Listing a = new Listing(text.getText(), getNickname(), slider.getValue(), yes1.isSelected(), yes2.isSelected());
                giannitsa.addListing(a);
                JOptionPane.showMessageDialog(null, "Listing added successfully!", "Success!", JOptionPane.INFORMATION_MESSAGE);
            }
            else
                JOptionPane.showMessageDialog(null, "Please enter a listing name!", "Oops!", JOptionPane.WARNING_MESSAGE);
        });

        panel.add(label);
        panel.add(text);
        panel.add(label1);
        panel.add(yes1);
        panel.add(no1);
        panel.add(label2);
        panel.add(yes2);
        panel.add(no2);
        panel.add(label3);
        panel.add(slider);
        panel.add(button);
        mainPanel.add(panel);

        return mainPanel;
    }

    /**
     * A method to return a panel of listing that has been edited.
     * @param giannitsa must be a platform for the existing platform of the application.
     * @param frame must be a JFrame of the main frame of the program.
     * @return the JPanel with the edited listing.
     */
    public JPanel EditListing(Platform giannitsa, JFrame frame){
        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(Color.CYAN);
        JPanel panel = new JPanel();
        panel.setBackground(Color.yellow);
        panel.setPreferredSize(new Dimension(380, 420));
        panel.setLayout(null);
        JLabel label = new JLabel("Listing name:");
        JTextField text = new JTextField();
        text.setPreferredSize(new Dimension(100, 50));
        text.setEditable(false);

        JLabel label1 = new JLabel("Wifi: ");
        JRadioButton yes1 = new JRadioButton("yes");
        JRadioButton no1 = new JRadioButton("no");
        ButtonGroup wifi = new ButtonGroup();
        wifi.add(yes1);
        wifi.add(no1);

        JLabel label2 = new JLabel("Parking: ");
        JRadioButton yes2 = new JRadioButton("yes");
        JRadioButton no2 = new JRadioButton("no");
        ButtonGroup parking = new ButtonGroup();
        parking.add(yes2);
        parking.add(no2);

        JLabel label3 = new JLabel("Price: ");
        JSlider slider = new JSlider();
        slider.setPreferredSize(new Dimension(100, 40));
        slider.setPaintTicks(true);
        slider.setMinorTickSpacing(10);
        slider.setPaintTrack(true);
        slider.setMajorTickSpacing(25);
        slider.setPaintLabels(true);

        JButton button = new JButton("Confirm");
        button.setPreferredSize(new Dimension(100, 50));


        ArrayList<String> tmp = new ArrayList<>();

        for (Listing a: giannitsa.getListings().values()) {
            if(Objects.equals(a.getOwner(), getNickname()))
                tmp.add(a.getName());
        }

        String[] listings = tmp.toArray(new String[0]);
        JComboBox<String> box = new JComboBox<>(listings);
        JButton select = new JButton("Select");

        box.setBounds(100, 20, 100, 30);
        select.setBounds(250, 20, 100, 30);
        label.setBounds(20, 80, 90,30);
        text.setBounds(150, 80, 150, 30);
        label1.setBounds(50, 140, 90,30);
        yes1.setBounds(150, 140, 50, 30);
        no1.setBounds(250, 140, 50, 30);
        label2.setBounds(50, 200, 90, 30);
        yes2.setBounds(150, 200, 50, 30);
        no2.setBounds(250, 200, 50, 30);
        label3.setBounds(50, 260, 90, 30);
        slider.setBounds(150, 260, 150, 50);
        button.setBounds(140, 350, 100, 30);  //confirm
        button.setEnabled(false);

        select.addActionListener(e -> {
            Listing a = giannitsa.getListings().get((String) box.getSelectedItem());
            text.setText(a.getName());
            if (a.isWifi())
                yes1.setSelected(true);
            else
                no1.setSelected(true);
            if (a.isParking())
                yes2.setSelected(true);
            else
                no2.setSelected(true);
            slider.setValue((int) a.getPrice());
            button.setEnabled(true);
            frame.invalidate();
            frame.validate();
        });

        button.addActionListener(e -> {

            Listing a = new Listing(text.getText(), getNickname(), slider.getValue(), yes1.isSelected(), yes2.isSelected());
            giannitsa.deleteListing((String) box.getSelectedItem());
            giannitsa.addListing(a);
            JOptionPane.showMessageDialog(null, "Listing edited successfully!", "Success!", JOptionPane.INFORMATION_MESSAGE);
        });

        panel.add(label);
        panel.add(text);
        panel.add(label1);
        panel.add(yes1);
        panel.add(no1);
        panel.add(label2);
        panel.add(yes2);
        panel.add(no2);
        panel.add(label3);
        panel.add(slider);
        panel.add(button);
        panel.add(box);
        panel.add(select);

        mainPanel.add(panel);
        return mainPanel;
    }

    /**
     * A method to return a panel with the bookings.
     * @param giannitsa must be a platform for the existing platform of the application.
     * @return JPanel of the bookings.
     */
    public JPanel bookings(Platform giannitsa) {
        JPanel panel = new JPanel();
        panel.setSize(980, 720);
        panel.setBackground(Color.CYAN);
        panel.setLayout(new FlowLayout());
        for (Booking a : giannitsa.getBookings().values()) {
            if(a.getOwner().equals(getNickname()))
                panel.add(a.toPanel());
        }
        return panel;
    }
}