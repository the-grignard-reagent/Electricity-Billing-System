import java.awt.*;
import javax.swing.*;
import java.sql.*;
import net.proteanit.sql.DbUtils;
import java.awt.event.*;

public class CustomerDetails extends JFrame implements ActionListener {

    // Declare Swing components
    Choice meternumber, cmonth;
    JTable table;
    JButton search, print;

    // Constructor for the CustomerDetails class
    CustomerDetails() {
        // Call the constructor of the superclass with the title
        super("Customer Details");

        // Set the size and location of the JFrame
        setSize(1200, 650);
        setLocation(200, 150);

        // Create a JTable for displaying customer details
        table = new JTable();

        // Fetch customer details from the database and set them as the data source for the JTable
        try {
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("select * from customer");
            table.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Create a JScrollPane to add scroll functionality to the JTable
        JScrollPane sp = new JScrollPane(table);
        add(sp);

        // Create a "Print" button and add an ActionListener to it
        print = new JButton("Print");
        print.addActionListener(this);
        add(print, "South");

        // Make the JFrame visible
        setVisible(true);
    }

    // ActionListener implementation for button actions
    public void actionPerformed(ActionEvent ae) {
        try {
            // Print the contents of the JTable when the "Print" button is clicked
            table.print();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Main method to test the CustomerDetails class
    public static void main(String[] args) {
        new CustomerDetails();
    }
}
