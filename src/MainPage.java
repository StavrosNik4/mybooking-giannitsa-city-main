import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Interface for the Main Page of the programm.
 * It is implemented later from the 3 types of user separately to make the main page GUI for each user depending on their role.
 */

public interface MainPage{

    static JPanel welcomePanel(String username){
        JPanel menu = new JPanel();
        menu.setPreferredSize(new Dimension(980, 720));
        menu.setBackground(Color.CYAN);
        menu.setLayout(new FlowLayout(FlowLayout.CENTER, 300, 100));

        JLabel message = new JLabel("Welcome " + username + "!");
        message.setFont(new Font(null, Font.PLAIN, 36));

        JButton button = new JButton("Call Technical Support!");
        button.setPreferredSize(new Dimension(200, 70));
        button.addActionListener(e -> {
            MusicOrganizer m = new MusicOrganizer();
            m.addFile("audio/call.mp3");
            m.listFile(0);
            m.startPlaying(0);
            JOptionPane.showMessageDialog(null, "Calling Technical Support... Please wait...", "Calling...", JOptionPane.PLAIN_MESSAGE);
            m.stopPlaying();
        });

        menu.add(message);
        menu.add(button);
        return menu;
    }
    JPanel makeMenu();
    default JPanel infocard(User a){
        JPanel info = new JPanel();
        info.setPreferredSize(new Dimension(680, 480));
        info.setBackground(Color.ORANGE);
        info.setLayout(null);

        JLabel label = new JLabel("Info Card");
        label.setFont(new Font(null, Font.PLAIN, 30));
        label.setBounds(300, 30, 300, 30);

        JLabel label1 = new JLabel("Name: ");
        label1.setBounds(30, 80, 100, 30);
        label1.setFont(new Font(null, Font.PLAIN, 18));

        JTextField text = new JTextField(a.getNickname());
        text.setEditable(false);
        text.setBounds(200, 80, 200, 30);

        JLabel label2 = new JLabel("Password: ");
        label2.setBounds(30, 120, 100, 30);
        label2.setFont(new Font(null, Font.PLAIN, 18));

        JPasswordField text1 = new JPasswordField();
        text1.setText(a.getPassword());
        text1.setBounds(200, 120, 200, 30);

        JButton button = new JButton("Update");
        button.setBounds(500, 120, 100, 30);
        button.addActionListener(e -> {
            a.setPassword(new String(text1.getPassword()));
            JOptionPane.showMessageDialog(null, "Password updated successfully!", "Success!", JOptionPane.INFORMATION_MESSAGE);
        });

        JLabel label3 = new JLabel("Email: ");
        label3.setBounds(30, 160, 200, 30);
        label2.setFont(new Font(null, Font.PLAIN, 18));

        JTextField email = new JTextField(a.getEmail());
        email.setBounds(200, 160, 200, 30);
        JButton button1 = new JButton("Update");
        button1.setBounds(500, 160, 100, 30);
        button1.addActionListener(e -> {
            a.setEmail(email.getText());
            new SendEmail(a.getEmail());

        });

        JLabel label5 = new JLabel("Address: ");
        label5.setBounds(30, 200, 200, 30);
        label2.setFont(new Font(null, Font.PLAIN, 18));
        JTextField text3 = new JTextField(a.getAddress());
        text3.setBounds(200, 200, 200, 30);
        JButton button3 = new JButton("Update");
        button3.setBounds(500, 200, 100, 30);
        button3.addActionListener(e -> {
            a.setAddress(text3.getText());
            JOptionPane.showMessageDialog(null, "Address updated successfully!", "Success!", JOptionPane.INFORMATION_MESSAGE);
        });

        JLabel label4 = new JLabel("Messages");
        label4.setBounds(300, 250, 200, 50);
        label4.setFont(new Font(null, Font.PLAIN, 24));

        ArrayList<Integer> tmp = new ArrayList<>();

        for (int i = 0; i < a.getMessages().size(); i++) {
            //String t = a.getMessages().get(i);
            tmp.add(i);
        }

        Integer[] listings = tmp.toArray(new Integer[0]);
        JComboBox<Integer> box = new JComboBox<>(listings);

        box.setBounds(250, 300, 100, 30);
        JButton button2 = new JButton("Select");
        button2.setBounds(400, 300, 100, 30);


        JTextArea text2 = new JTextArea();
        text2.setBounds(160, 350, 350, 120);
        text2.setEditable(false);
        text2.setFont(new Font(null, Font.PLAIN, 16));

        button2.addActionListener(e -> {
            try {
                text2.setText(a.getMessages().get(box.getSelectedIndex()).toString());
            }
            catch (Exception ignored){

            }
        });

        info.add(label);
        info.add(label1);
        info.add(text);
        info.add(label2);
        info.add(text1);
        info.add(button);
        info.add(label3);
        info.add(email);
        info.add(button1);
        info.add(label4);
        info.add(box);
        info.add(button2);
        info.add(text2);
        info.add(label5);
        info.add(text3);
        info.add(button3);

        return info;
    }
}

