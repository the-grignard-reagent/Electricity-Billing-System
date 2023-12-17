import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class GenerateBill extends JFrame implements ActionListener {

    // Declare instance variables
    String meter;
    JButton bill;
    Choice cmonth;
    JTextArea area;

    // Constructor for the GenerateBill class
    GenerateBill(String meter) {
        // Initialize instance variables
        this.meter = meter;

        // Set the size and location of the JFrame
        setSize(500, 800);
        setLocation(550, 30);

        // Set the layout to BorderLayout
        setLayout(new BorderLayout());

        // Create a JPanel for organizing components
        JPanel panel = new JPanel();

        // Create and configure labels, choice, and text area
        JLabel heading = new JLabel("Generate Bill");
        JLabel meternumber = new JLabel(meter);

        cmonth = new Choice();
        // Add months to the choice component
        cmonth.add("January");
        cmonth.add("February");
        // ... (add the rest of the months)

        area = new JTextArea(50, 15);
        area.setText("\n\n\t--------Click on the---------\n\t Generate Bill Button to get\n\tthe bill of the Selected Month");
        area.setFont(new Font("Senserif", Font.ITALIC, 18));

        // Create a JScrollPane for the text area
        JScrollPane pane = new JScrollPane(area);

        // Create a "Generate Bill" button and add an ActionListener to it
        bill = new JButton("Generate Bill");
        bill.addActionListener(this);

        // Add components to the panel and set its location to the north
        panel.add(heading);
        panel.add(meternumber);
        panel.add(cmonth);
        add(panel, "North");

        // Add the JScrollPane to the center
        add(pane, "Center");
        // Add the "Generate Bill" button to the south
        add(bill, "South");

        // Make the JFrame visible
        setVisible(true);
    }

    // ActionListener implementation for button actions
    public void actionPerformed(ActionEvent ae) {
        try {
            // Create a connection to the database
            Conn c = new Conn();

            // Get the selected month from the Choice component
            String month = cmonth.getSelectedItem();

            // Set initial text in the JTextArea
            area.setText("\tReliance Power Limited\nELECTRICITY BILL GENERATED FOR THE MONTH\n\tOF " + month + ", 2022\n\n\n");

            // Fetch customer details based on the meter number
            ResultSet rs = c.s.executeQuery("select * from customer where meter_no = '" + meter + "'");

            if (rs.next()) {
                // Append customer details to the JTextArea
                area.append("\n    Customer Name: " + rs.getString("name"));
                area.append("\n    Meter Number   : " + rs.getString("meter_no"));
                area.append("\n    Address             : " + rs.getString("address"));
                // ... (add the rest of the customer details)
                area.append("\n---------------------------------------------------");
                area.append("\n");
            }

            // Fetch meter information based on the meter number
            rs = c.s.executeQuery("select * from meter_info where meter_no = '" + meter + "'");

            if (rs.next()) {
                // Append meter information to the JTextArea
                area.append("\n    Meter Location: " + rs.getString("meter_location"));
                // ... (add the rest of the meter information)
                area.append("\n---------------------------------------------------");
                area.append("\n");
            }

            // Fetch tax details from the database
            rs = c.s.executeQuery("select * from tax");

            if (rs.next()) {
                // Append tax details to the JTextArea
                area.append("\n");
                area.append("\n    Cost Per Unit: " + rs.getString("cost_per_unit"));
                // ... (add the rest of the tax details)
                area.append("\n");
            }

            // Fetch bill details for the selected meter and month
            rs = c.s.executeQuery("select * from bill where meter_no = '" + meter + "' and month='" + month + "'");

            if (rs.next()) {
                // Append bill details to the JTextArea
                area.append("\n");
                area.append("\n    Current Month: " + rs.getString("month"));
                area.append("\n    Units Consumed:     " + rs.getString("units"));
                area.append("\n    Total Charges:        " + rs.getString("totalbill"));
                area.append("\n-------------------------------------------------------");
                area.append("\n    Total Payable: " + rs.getString("totalbill"));
                area.append("\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Main method to test the GenerateBill class
    public static void main(String[] args) {
        new GenerateBill("");
    }
}
