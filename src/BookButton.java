import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

public class BookButton extends JButton implements ActionListener{
    JFrame frame;
    Customer a;
    Platform giannitsa;
    Listing listing;
    Date from;
    Date to;


    BookButton(JFrame frame, Customer a, Platform giannitsa, Listing listing, Date from, Date to){
        this.setText("Book Now!");
        this.setSize(new Dimension(100, 50));
        this.frame = frame;
        this.a = a;
        this.giannitsa = giannitsa;
        this.listing = listing;
        this.from = from;
        this.to = to;
        this.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Booking booking = new Booking(giannitsa.getBookings(), a.getNickname(), listing, from, to);
        giannitsa.addBooking(booking);
        listing.addBooking(booking);
        JOptionPane.showMessageDialog(null, "Booked Successfully", "Success!", JOptionPane.INFORMATION_MESSAGE);
        setEnabled(false);
        frame.invalidate();
        frame.validate();
    }
}
