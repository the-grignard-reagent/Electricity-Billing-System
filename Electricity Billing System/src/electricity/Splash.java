import javax.swing.*;
import java.awt.*;

// Splash class extending JFrame and implementing Runnable
public class Splash extends JFrame implements Runnable {
    // Thread for controlling animation and splash screen duration
    Thread t;

    // Constructor for Splash class
    Splash() {
        // Creating an ImageIcon and resizing the image
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/elect.jpg"));
        Image i2 = i1.getImage().getScaledInstance(730, 550, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);

        // Creating a JLabel to display the image and adding it to the frame
        JLabel image = new JLabel(i3);
        add(image);

        // Setting the frame to be visible
        setVisible(true);

        // Gradually increasing the size of the frame for animation
        int x = 1;
        for (int i = 2; i < 600; i += 4, x += 1) {
            setSize(i + x, i);
            setLocation(700 - ((i + x) / 2), 400 - (i / 2));
            try {
                // Adding a short delay for the animation effect
                Thread.sleep(5);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        // Creating a new thread for the automatic closing of the splash screen
        t = new Thread(this);
        t.start();

        // Setting the frame to be visible
        setVisible(true);
    }

    // Run method for the thread
    public void run() {
        try {
            // Adding a delay of 7 seconds before closing the splash screen
            Thread.sleep(7000);
            setVisible(false);

            // Creating a new instance of the Login class (main application)
            new Login();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Main method to create an instance of the Splash class
    public static void main(String[] args) {
        new Splash();
    }
}
