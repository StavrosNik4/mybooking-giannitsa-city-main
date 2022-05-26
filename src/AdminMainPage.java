import javax.swing.*;
import java.awt.*;

public class AdminMainPage implements MainPage{
    Platform giannitsa;
    Admin admin;
    JFrame frame;
    public AdminMainPage(Platform x, String username) {
        giannitsa = x;
        admin = (Admin) giannitsa.getUsers().get(username);
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

        JButton button1 = new JButton("Display Bookings");
        button1.setPreferredSize(new Dimension(200, 50));

        JButton button2 = new JButton("Display Users");
        button2.setPreferredSize(new Dimension(200, 50));

        JButton button3 = new JButton("Confirm New Users");
        button3.setPreferredSize(new Dimension(200, 50));

        JButton button4 = new JButton("Send Message");
        button4.setPreferredSize(new Dimension(200, 50));

        JButton button5 = new SignOutButton(frame);

        button1.addActionListener(e -> {
            frame.getContentPane().remove(1);
            JPanel mainPanel = admin.bookings(giannitsa);
            frame.add(mainPanel, BorderLayout.CENTER);

            button1.setEnabled(false);
            button2.setEnabled(true);
            button3.setEnabled(true);
            button4.setEnabled(true);

            frame.invalidate();
            frame.validate();
        });

        button2.addActionListener(e -> {
            frame.getContentPane().remove(1);
            JPanel mainPanel = admin.usersPanel(giannitsa);
            frame.add(mainPanel, BorderLayout.CENTER);

            button1.setEnabled(true);
            button2.setEnabled(false);
            button3.setEnabled(true);
            button4.setEnabled(true);

            frame.invalidate();
            frame.validate();
        });




        button3.addActionListener(e -> {
            frame.getContentPane().remove(1);
            JPanel mainPanel = admin.newUsersPanel(giannitsa);
            frame.add(mainPanel, BorderLayout.CENTER);

            button1.setEnabled(true);
            button2.setEnabled(true);
            button3.setEnabled(false);
            button4.setEnabled(true);

            frame.invalidate();
            frame.validate();
        });

        button4.addActionListener(e -> {
            frame.getContentPane().remove(1);
            JPanel mainPanel = admin.sendMessagePanel(giannitsa);
            frame.add(mainPanel, BorderLayout.CENTER);

            button1.setEnabled(true);
            button2.setEnabled(true);
            button3.setEnabled(true);
            button4.setEnabled(false);

            frame.invalidate();
            frame.validate();
        });

        menu.add(button1);
        menu.add(button2);
        menu.add(button3);
        menu.add(button4);
        menu.add(button5);

        return menu;
    }
}
