import javax.swing.*;
import java.awt.*;
import java.util.Calendar;
import java.util.Date;

/**
 * This class represents the admins of the application.
 * It extends the general user abstract class.
 */

public class Admin extends User{
    /**
     * Constructor to set nickname and password and rank of the admin.
     * It uses the super method for the nickname and password params.
     * @param nickname must be a string for the admins' nickname.
     * @param password must be a string for the admins' password.
     */
    Admin(String nickname, String password) {
        super(nickname, password);
        rank = "Admin";
    }

    /**
     * Method to send messages to users (providers or customers).
     * @param giannitsa must be a platform for the existing platform of the application.
     * @param user must be a string of the username of the user to send the message.
     * @param content must be a string with the message for the {@code user}.
     */
    protected boolean sendMessage(Platform giannitsa, String user, String content){
        if(giannitsa.getUsers().containsKey(user)) {
            Date date = Calendar.getInstance().getTime();
            Message message = new Message(this.getNickname(), user, content, date);
            giannitsa.getUsers().get(user).addMessage(message);
            return true;
        }
        else
            return false;
    }

    /**
     * A method to display all the users of the platform.
     * @param giannitsa must be a platform for the existing platform of the application.
     * @return the panel of all the users of the platform.
     */
    public JPanel usersPanel(Platform giannitsa){
        JPanel panel = new JPanel();
        JPanel users = new JPanel();
        users.setBackground(Color.CYAN);
        users.setPreferredSize(new Dimension(980, 720));
        users.setLayout(new GridLayout(1,1));
        panel.setBackground(Color.CYAN);
        panel.setPreferredSize(new Dimension(980, 720));
        panel.setLayout(new GridLayout(1,3));
        JPanel tmp = new JPanel();
        tmp.setBackground(Color.CYAN);
        tmp.setLayout(new FlowLayout(FlowLayout.CENTER, 300, 20));
        for (User a: giannitsa.getUsers().values()) {
            JLabel t = new JLabel("Username: " + a.getNickname() + ", Role: " + a.getRank());
            t.setFont(new Font(null, Font.PLAIN, 26));
            tmp.add(t);
        }
        panel.add(tmp, 0, 0);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        JScrollPane scrollPane = new JScrollPane(panel);
        users.add(scrollPane);
        return users;
    }

    /**
     * A method to display all the new users of the platform.
     * @param giannitsa must be a platform for the existing platform of the application.
     * @return the panel of all new users of the platform.
     */
    public JPanel newUsersPanel(Platform giannitsa){
        JPanel users = new JPanel();
        users.setBackground(Color.CYAN);
        users.setLayout(new FlowLayout(FlowLayout.CENTER, 100, 20));
        for (User a: giannitsa.getNewUsers().values()) {
            JLabel t = new JLabel("Username: " + a.getNickname() + ", Role: " + a.getRank());
            t.setFont(new Font(null, Font.PLAIN, 24));
            users.add(t);
            JButton tmp = new JButton(a.getNickname());
            tmp.addActionListener(e -> {
                giannitsa.getNewUsers().remove(tmp.getText());
                tmp.setEnabled(false);
            });
            users.add(tmp);
        }
        return users;
    }

    /**
     * A method to display the admins' menu to send a message to the users.
     * @param giannitsa must be a platform for the existing platform of the application.
     * @return the admins' menu to send a message to the users.
     */
    public JPanel sendMessagePanel(Platform giannitsa){
        JPanel panel = new JPanel();
        panel.setBackground(Color.CYAN);
        panel.setLayout(new FlowLayout(FlowLayout.CENTER, 500, 20));

        JLabel label = new JLabel("Type your message here: ");
        panel.add(label);

        JTextField textField = new JTextField();
        textField.setPreferredSize(new Dimension(480, 50));
        panel.add(textField);

        JLabel label1 = new JLabel("To: ");
        panel.add(label1);

        String[] users = giannitsa.getUsers().keySet().toArray(new String[0]);
        JComboBox<String> list = new JComboBox<>(users);
        panel.add(list);

        JButton button = new JButton("Send");
        button.addActionListener(e -> {
            sendMessage(giannitsa, (String) list.getSelectedItem(), textField.getText());
                JOptionPane.showMessageDialog(null, "Message sent successfully!", "Success!", JOptionPane.INFORMATION_MESSAGE);
        });
        panel.add(button);
        return panel;
    }

    /**
     * A method to display all the bookings of the platform.
     * @param giannitsa must be a platform for the existing platform of the application.
     * @return the panel of all bookings of the platform.
     */
    public JPanel bookings(Platform giannitsa) {
        JPanel panel1 = new JPanel();
        panel1.setBackground(Color.CYAN);
        panel1.setSize(980, 720);
        panel1.setLayout(new FlowLayout());
        for (Booking a : giannitsa.getBookings().values()) {
            panel1.add(a.toPanel());
        }
        if(giannitsa.getBookings().isEmpty())
        {
            panel1.setLayout(null);
            JLabel t = new JLabel("There are no bookings right now!");
            t.setFont(new Font(null, Font.PLAIN, 32));
            t.setBounds(300, 270, 500, 40);
            panel1.add(t);

        }
        return panel1;
    }
}
