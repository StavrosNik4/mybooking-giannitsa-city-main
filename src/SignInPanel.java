import javax.swing.*;
import java.awt.*;

public class SignInPanel extends JPanel {
    SignInPanel(JFrame frame, JLayeredPane formpane, Platform giannitsa){
        setBackground(Color.CYAN);
        setBounds(0, 0, 360, 420);
        setLayout(new FlowLayout(FlowLayout.CENTER, 300, 10));

        JLabel label1 = new JLabel("Sign In");
        add(label1);
        JTextField username = new JTextField();
        username.setPreferredSize(new Dimension(100, 25));
        JPasswordField passwordField = new JPasswordField();
        passwordField.setPreferredSize(new Dimension(100, 25));

        JLabel label2 = new JLabel("Username:");
        JLabel label3 = new JLabel("Password:");
        JButton button1 = new JButton("Sign In");

        button1.addActionListener(e -> {
            if(SignIn.signIn(giannitsa, username.getText(), new String(passwordField.getPassword()))){
                System.out.println("Signed In successfully!");
                if (giannitsa.getUsers().get(username.getText()).getRank().equalsIgnoreCase("Admin")){
                    new AdminMainPage(giannitsa, username.getText());
                }
                else if (giannitsa.getUsers().get(username.getText()).getRank().equalsIgnoreCase("Customer")) {
                    new CustomerMainPage(giannitsa, username.getText());
                }
                else if (giannitsa.getUsers().get(username.getText()).getRank().equalsIgnoreCase("Provider")) {
                    new ProviderMainPage(giannitsa, username.getText());
                }
                frame.dispose();
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Wrong username or password!", "Oops!", JOptionPane.WARNING_MESSAGE);
            }
        });
        JButton button2 = new JButton("New here?");
        button2.addActionListener(e -> {
            JPanel signUpPanel = new SignUpPanel(frame, formpane, giannitsa);
            formpane.removeAll();
            formpane.add(signUpPanel, Integer.valueOf(0));
            frame.add(formpane);
            frame.invalidate();
            frame.validate();
        });
        this.add(label2);
        this.add(username);
        this.add(label3);
        this.add(passwordField);
        this.add(button1);
        this.add(button2);
    }
}
