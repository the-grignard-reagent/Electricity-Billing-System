import javax.swing.*;
import java.awt.*;
import java.sql.*;
import net.proteanit.sql.DbUtils;

public class BillDetails extends JFrame {

    // Constructor for the BillDetails class, takes a meter number as a parameter
    BillDetails(String meter) {

        // Set the size and location of the JFrame
        setSize(700, 650);
        setLocation(400, 150);

        // Set the background color of the JFrame
        getContentPane().setBackground(Color.WHITE);

        // Create a JTable to display the bill details
        JTable table = new JTable();

        try {
            // Create a connection to the database using the Conn class
            Conn c = new Conn();

            // Construct the SQL query to retrieve bill details for the given meter number
            String query = "select * from bill where meter_no = '" + meter + "'";

            // Execute the query and retrieve the ResultSet
            ResultSet rs = c.s.executeQuery(query);

            // Set the ResultSet as the data source for the JTable using DbUtils
            table.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            // Print the stack trace if an exception occurs
            e.printStackTrace();
        }

        // Create a JScrollPane to add scroll functionality to the JTable
        JScrollPane sp = new JScrollPane(table);

        // Set the bounds of the JScrollPane
        sp.setBounds(0, 0, 700, 650);

        // Add the JScrollPane to the JFrame
        add(sp);

        // Make the JFrame visible
        setVisible(true);
    }

    // Main method to test the BillDetails class
    public static void main(String[] args) {
        // Create an instance of BillDetails with an empty meter number
        new BillDetails("");
    }
}
