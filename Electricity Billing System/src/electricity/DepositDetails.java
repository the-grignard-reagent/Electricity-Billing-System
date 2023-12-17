import java.awt.*;
import javax.swing.*;
import java.sql.*;
import net.proteanit.sql.DbUtils;
import java.awt.event.*;

public class DepositDetails extends JFrame implements ActionListener {

    // Declare Swing components
    Choice meternumber, cmonth;
    JTable table;
    JButton search, print;

    // Constructor for the DepositDetails class
    DepositDetails() {
        // Call the constructor of the superclass with the title
        super("Deposit Details");

        // Set the size and location of the JFrame
        setSize(700, 700);
        setLocation(400, 100);

        // Set layout to null and background color to white
        setLayout(null);
        getContentPane().setBackground(Color.WHITE);

        // Create and configure components for searching by meter number
        JLabel lblmeternumber = new JLabel("Search By Meter Number");
        lblmeternumber.setBounds(20, 20, 150, 20);
        add(lblmeternumber);

        meternumber = new Choice();
        meternumber.setBounds(180, 20, 150, 20);
        add(meternumber);

        // Populate the meter number Choice component from the database
        try {
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("select * from customer");
            while (rs.next()) {
                meternumber.add(rs.getString("meter_no"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Create and configure components for searching by month
        JLabel lblmonth = new JLabel("Search By Month");
        lblmonth.setBounds(400, 20, 100, 20);
        add(lblmonth);

        cmonth = new Choice();
        cmonth.setBounds(520, 20, 150, 20);
        cmonth.add("January");
        cmonth.add("February");
        // ... (add the rest of the months)
        add(cmonth);

        // Create a JTable for displaying deposit details
        table = new JTable();

        // Fetch deposit details from the database and set them as the data source for the JTable
        try {
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("select * from bill");
            table.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Create a JScrollPane to add scroll functionality to the JTable
        JScrollPane sp = new JScrollPane(table);
        sp.setBounds(0, 100, 700, 600);
        add(sp);

        // Create and configure buttons for searching and printing
        search = new JButton("Search");
        search.setBounds(20, 70, 80, 20);
        search.addActionListener(this);
        add(search);

        print = new JButton("Print");
        print.setBounds(120, 70, 80, 20);
        print.addActionListener(this);
        add(print);

        // Make the JFrame visible
        setVisible(true);
    }

    // ActionListener implementation for button actions
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == search) {
            // When the "Search" button is clicked, execute a query based on selected meter number and month
            String query = "select * from bill where meter_no = '" + meternumber.getSelectedItem() + "' and month = '" + cmonth.getSelectedItem() + "'";

            try {
                Conn c = new Conn();
                ResultSet rs = c.s.executeQuery(query);
                table.setModel(DbUtils.resultSetToTableModel(rs));
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            // When the "Print" button is clicked, print the contents of the JTable
            try {
                table.print();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    // Main method to test the DepositDetails class
    public static void main(String[] args) {
        new DepositDetails();
    }
}
