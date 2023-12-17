import javax.swing.*;
import java.awt.event.*;

// Paytm class extending JFrame and implementing ActionListener
public class Paytm extends JFrame implements ActionListener {

    // Declaration of String and JButton variables
    String meter;
    JButton back;

    // Constructor for Paytm class taking the meter number as a parameter
    Paytm(String meter) {
        this.meter = meter;

        // Creating an editable JEditorPane
        JEditorPane j = new JEditorPane();
        j.setEditable(false);

        try {
            // Setting the page URL for Paytm online payments
            j.setPage("https://paytm.com/online-payments");
        } catch (Exception e) {
            // Handling exceptions by setting content type to text/html and displaying an error message
            j.setContentType("text/html");
            j.setText("<html>Could not load<html>");
        }

        // Creating a JScrollPane to hold the JEditorPane
        JScrollPane pane = new JScrollPane(j);
        add(pane);

        // Creating and configuring JButton for Back
        back = new JButton("Back");
        back.setBounds(640, 20, 80, 30);
        back.addActionListener(this);
        j.add(back);

        // Setting the size, location, and visibility of the JFrame
        setSize(800, 600);
        setLocation(400, 150);
        setVisible(true);
    }

    // ActionListener method for handling button clicks
    public void actionPerformed(ActionEvent ae) {
        // Setting the current frame as not visible and creating a new PayBill frame
        setVisible(false);
        new PayBill(meter);
    }

    // Main method to create an instance of the Paytm class
    public static void main(String[] args) {
        new Paytm("");
    }
}
