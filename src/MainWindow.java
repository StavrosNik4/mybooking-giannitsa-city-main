import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;

public class MainWindow {

    static Platform giannitsa;
    JFrame frame;
    JLayeredPane  formpane;

    public MainWindow(){
        giannitsa = new Platform();
        loadPlatform();
        makeFrame();
    }

    private void makeFrame(){

        frame = new JFrame();
        frame.setTitle("Giannitsa City Booking Application");
        frame.setSize(1280, 720);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        String bg = "images/Giannitsa.JPG";
        URL bgURL = getClass().getResource(bg);
        BufferedImage myImage = null;
        try {
            assert bgURL != null;
            myImage = ImageIO.read(bgURL);
        } catch (IOException e) {
            e.printStackTrace();
        }
        frame.setContentPane(new ImagePanel(myImage));

        formpane = new JLayeredPane();
        formpane.setSize(new Dimension(360, 420));
        formpane.setLocation((1280 - 360)/2, (720 - 420)/2);
        JPanel login = new SignInPanel(frame, formpane, giannitsa);
        formpane.add(login, Integer.valueOf(0));
        frame.add(formpane);
        frame.setVisible(true);
    }

    public static void savePlatform() {
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("database.bin"))) {
            oos.writeObject(giannitsa);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadPlatform() {
        try(ObjectInputStream oos = new ObjectInputStream(new FileInputStream("database.bin"))) {
            giannitsa = (Platform) oos.readObject();
        }
        catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
