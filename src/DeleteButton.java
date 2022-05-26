import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DeleteButton extends JButton implements ActionListener{
    JFrame frame;
    Platform giannitsa;
    String name;

    DeleteButton(JFrame frame, Platform giannitsa, String name){
        this.setText("Delete");
        this.setSize(new Dimension(100, 50));
        this.frame = frame;
        this.giannitsa = giannitsa;
        this.name = name;
        this.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        giannitsa.deleteListing(name);
        this.setEnabled(false);
        //JOptionPane.showMessageDialog(null, "Removed Successfully", "Removed!", JOptionPane.WARNING_MESSAGE);
        frame.invalidate();
        frame.validate();
    }
}
