import javax.swing.*;
import java.awt.*;

/**
 * Class for the sign-up panel.
 */

public class SignUpPanel extends JPanel {

    /**
     * Constructor of the sign-up panel.
     * @param frame the main frame/window of the program.
     * @param formpane panel to choose between customer and provider for creating a new account.
     * @param giannitsa must be a platform for the existing platform of the application.
     */
    SignUpPanel(JFrame frame, JLayeredPane formpane, Platform giannitsa){
        setBackground(Color.CYAN);
        setBounds(0, 0, 360, 420);
        setLayout(new FlowLayout(FlowLayout.CENTER, 300, 10));

        JLabel label1 = new JLabel("Sign Up");
        add(label1);
        JTextField username = new JTextField();
        username.setPreferredSize(new Dimension(100, 25));
        JPasswordField passwordField = new JPasswordField();
        passwordField.setPreferredSize(new Dimension(100, 25));
        String[] ar = {"Provider", "Customer"};
        JComboBox<String> list = new JComboBox<>(ar);
        list.setPreferredSize(new Dimension(100, 35));
        JLabel label2 = new JLabel("Username:");
        JLabel label3 = new JLabel("Password:");
        JLabel label4 = new JLabel("Role:");
        JButton button1 = new JButton("Sign Up");

        button1.addActionListener(e -> {
            if(!username.getText().isBlank() && !String.valueOf(passwordField.getPassword()).isBlank() && (list.getSelectedItem()=="Provider" || list.getSelectedItem()=="Customer")){
                if(SignUp.signUp(giannitsa, username.getText(), String.valueOf(passwordField.getPassword()), (String) list.getSelectedItem())) {
                    System.out.println("Signed Up successfully!");
                    MainWindow.savePlatform();
                    if (((String) list.getSelectedItem()).equalsIgnoreCase("Customer")) {
                        new CustomerMainPage(giannitsa, username.getText());
                    } else if (((String) list.getSelectedItem()).equalsIgnoreCase("Provider")) {
                        new ProviderMainPage(giannitsa, username.getText());
                    }
                    frame.dispose();
                }
                else{
                    JOptionPane.showMessageDialog(null, "This user already exists!", "Oops!", JOptionPane.WARNING_MESSAGE);
                }
            }
            else if(username.getText().isBlank()){
                JOptionPane.showMessageDialog(null, "Please enter a username!", "Oops!", JOptionPane.WARNING_MESSAGE);
            }
            else if(String.valueOf(passwordField.getPassword()).isBlank()){
                JOptionPane.showMessageDialog(null, "Please enter a password", "Oops!", JOptionPane.WARNING_MESSAGE);
            }
            else{
                JOptionPane.showMessageDialog(null, "Please select a role", "Oops!", JOptionPane.WARNING_MESSAGE);
            }
        });
        JButton button2 = new JButton("Already have an account?");
        button2.addActionListener(e -> {
            JPanel signInPanel = new SignInPanel(frame, formpane, giannitsa);
            formpane.removeAll();
            formpane.add(signInPanel, Integer.valueOf(0));
            frame.add(formpane);
            frame.invalidate();
            frame.validate();
        });
        this.add(label2);
        this.add(username);
        this.add(label3);
        this.add(passwordField);
        this.add(label4);
        this.add(list);
        this.add(button1);
        this.add(button2);
    }
}
