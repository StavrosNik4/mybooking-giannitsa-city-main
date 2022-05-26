import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CancelButton extends JButton implements ActionListener{
    JFrame frame;
    Customer a;
    Platform giannitsa;
    Booking b;
    CancelButton(JFrame frame, Customer a, Platform giannitsa, Booking b){
        this.setText("Cancel");
        this.setSize(new Dimension(100, 50));
        this.frame = frame;
        this.a = a;
        this.giannitsa = giannitsa;
        this.b = b;
        this.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        giannitsa.cancel(b);
        this.setEnabled(false);
        JOptionPane.showMessageDialog(null, "Cancelled Successfully", "Cancelled!", JOptionPane.INFORMATION_MESSAGE);
        frame.invalidate();
        frame.validate();
    }
}
