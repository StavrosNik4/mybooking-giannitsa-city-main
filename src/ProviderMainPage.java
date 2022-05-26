import javax.swing.*;
import java.awt.*;

public class ProviderMainPage implements MainPage{
    Platform giannitsa;
    Provider provider;
    JPanel mainPanel;
    JFrame frame;
    ProviderMainPage(Platform x, String username){
        giannitsa = x;
        provider = (Provider) giannitsa.getUsers().get(username);
        frame = new JFrame();
        frame.setTitle("Giannitsa City Booking Application");
        frame.setSize(1280, 720);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLayout(new BorderLayout());

        JPanel menu = makeMenu();
        frame.add(menu, BorderLayout.WEST);

        mainPanel = new JPanel();
        mainPanel = MainPage.welcomePanel(username);
        frame.add(mainPanel, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    @Override
    public JPanel makeMenu() {
        JPanel menu = new JPanel();
        menu.setPreferredSize(new Dimension(300, 720));
        menu.setBackground(Color.PINK);
        menu.setLayout(new FlowLayout(FlowLayout.CENTER, 300, 10));

        JButton button1 = new JButton("Add New Listing");
        button1.setPreferredSize(new Dimension(200, 50));

        JButton button2 = new JButton("Edit Listing");
        button2.setPreferredSize(new Dimension(200, 50));

        JButton button6 = new JButton("View Your Listings");
        button6.setPreferredSize(new Dimension(200, 50));

        JButton button3 = new JButton("View Bookings");
        button3.setPreferredSize(new Dimension(200, 50));

        JButton button4 = new JButton("Info Card");
        button4.setPreferredSize(new Dimension(200, 50));

        button1.addActionListener(e -> {
            frame.getContentPane().remove(1);
            mainPanel = new JPanel();
            mainPanel = provider.addListingPanel(giannitsa, frame);
            frame.add(mainPanel, BorderLayout.CENTER);
            button1.setEnabled(false);
            button2.setEnabled(true);
            button3.setEnabled(true);
            button4.setEnabled(true);
            button6.setEnabled(true);
            frame.invalidate();
            frame.validate();
        });

        button2.addActionListener(e -> {
            frame.getContentPane().remove(1);
            mainPanel = new JPanel();
            mainPanel = provider.EditListing(giannitsa, frame);
            frame.add(mainPanel, BorderLayout.CENTER);
            button1.setEnabled(true);
            button2.setEnabled(false);
            button3.setEnabled(true);
            button4.setEnabled(true);
            button6.setEnabled(true);
            frame.invalidate();
            frame.validate();
        });

        button6.addActionListener(e -> {
            frame.getContentPane().remove(1);
            mainPanel = new JPanel();
            mainPanel = provider.listingsPanel(giannitsa, frame);
            frame.add(mainPanel, BorderLayout.CENTER);
            button1.setEnabled(true);
            button2.setEnabled(true);
            button3.setEnabled(true);
            button4.setEnabled(true);
            button6.setEnabled(false);
            frame.invalidate();
            frame.validate();
        });

        button3.addActionListener(e -> {
            frame.getContentPane().remove(1);
            mainPanel = new JPanel();
            mainPanel = provider.bookings(giannitsa);
            frame.add(mainPanel, BorderLayout.CENTER);
            button1.setEnabled(true);
            button2.setEnabled(true);
            button3.setEnabled(false);
            button4.setEnabled(true);
            button6.setEnabled(true);
            frame.invalidate();
            frame.validate();
        });

        button4.addActionListener(e -> {
            frame.getContentPane().remove(1);
            JPanel mainPanel = new JPanel();
            mainPanel.setBackground(Color.CYAN);
            mainPanel.add(infocard(provider));
            frame.add(mainPanel, BorderLayout.CENTER);
            button4.setEnabled(false);
            if(giannitsa.getNewUsers().containsKey(provider.getNickname()))
            {
                button1.setEnabled(false);
                button2.setEnabled(false);
                button3.setEnabled(false);
                button6.setEnabled(false);
            }
            else{
                button1.setEnabled(true);
                button2.setEnabled(true);
                button3.setEnabled(true);
                button6.setEnabled(true);
            }
            frame.invalidate();
            frame.validate();
        });

        menu.add(button1);
        menu.add(button2);
        menu.add(button6);
        menu.add(button3);
        menu.add(button4);

        if(giannitsa.getNewUsers().containsKey(provider.getNickname()))
        {
            button1.setEnabled(false);
            button2.setEnabled(false);
            button3.setEnabled(false);
            button6.setEnabled(false);
        }

        JButton button5 = new SignOutButton(frame);
        menu.add(button5);

        return menu;
    }
}
