import javax.swing.*;
import java.awt.*;

public class CustomerMainPage implements MainPage{

    Platform giannitsa;
    Customer customer;
    //JPanel mainPanel;
    JFrame frame;

    public CustomerMainPage(Platform x, String username) {
        giannitsa = x;
        customer = (Customer) giannitsa.getUsers().get(username);
        frame = new JFrame();
        frame.setTitle("Giannitsa City Booking Application");
        frame.setSize(1280, 720);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLayout(new BorderLayout());

        JPanel menu = makeMenu();
        frame.add(menu, BorderLayout.WEST);

        JPanel mainPanel = MainPage.welcomePanel(username);
        frame.add(mainPanel, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    @Override
    public JPanel makeMenu() {
        JPanel menu = new JPanel();
        menu.setPreferredSize(new Dimension(300, 720));
        menu.setBackground(Color.PINK);
        menu.setLayout(new FlowLayout(FlowLayout.CENTER, 300, 10));
        JButton button1 = new JButton("Search");
        button1.setPreferredSize(new Dimension(200, 50));
        JButton button2 = new JButton("View Your Bookings");
        button2.setPreferredSize(new Dimension(200, 50));
        JButton button3 = new JButton("Info Card");
        button3.setPreferredSize(new Dimension(200, 50));
        JButton button4 = new SignOutButton(frame);
        button4.setPreferredSize(new Dimension(200, 50));

        if(giannitsa.getNewUsers().containsKey(customer.getNickname()))
        {
            button1.setEnabled(false);
            button2.setEnabled(false);
        }

        button1.addActionListener(e -> {
            frame.getContentPane().remove(1);
            JPanel newpanel = customer.search(giannitsa, frame);
            button1.setEnabled(false);
            button2.setEnabled(true);
            button3.setEnabled(true);
            frame.add(newpanel, BorderLayout.CENTER);
            frame.invalidate();
            frame.validate();
        });
        button2.addActionListener(e -> {
            frame.getContentPane().remove(1);
            JPanel newpanel = customer.bookings(giannitsa, frame);
            button1.setEnabled(true);
            button2.setEnabled(false);
            button3.setEnabled(true);
            frame.add(newpanel, BorderLayout.CENTER);
            frame.invalidate();
            frame.validate();
        });
        button3.addActionListener(e -> {
            frame.getContentPane().remove(1);
            JPanel mainPanel = new JPanel();
            mainPanel.setBackground(Color.CYAN);
            JPanel newpanel = infocard(customer);
            button3.setEnabled(false);
            if(giannitsa.getNewUsers().containsKey(customer.getNickname()))
            {
                button1.setEnabled(false);
                button2.setEnabled(false);
            }
            else{
                button1.setEnabled(true);
                button2.setEnabled(true);
            }
            mainPanel.add(newpanel);
            frame.add(mainPanel, BorderLayout.CENTER);
            frame.invalidate();
            frame.validate();
        });
        menu.add(button1);
        menu.add(button2);
        menu.add(button3);
        menu.add(button4);
        return menu;
    }
}
