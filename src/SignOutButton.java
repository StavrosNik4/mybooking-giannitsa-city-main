import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Class for the sign-out button.
 */

public class SignOutButton extends JButton implements ActionListener{
    JFrame frame;
    /**
     * Constructor of the SignOutButton
     * @param frame the main frame/window of the program.
     */
    SignOutButton(JFrame frame){
        setText("Sign Out");
        setPreferredSize(new Dimension(200, 50));
        this.frame = frame;
        addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        MainWindow.savePlatform();
        new MainWindow();
        frame.dispose();
    }
}
