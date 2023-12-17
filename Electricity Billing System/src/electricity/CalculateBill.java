import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class CalculateBill extends JFrame implements ActionListener {

    // Declare Swing components
    JTextField tfname, tfaddress, tfstate, tfunits, tfemail, tfphone;
    JButton next, cancel;
    JLabel lblname, labeladdress;
    Choice meternumber, cmonth;

    // Constructor for the CalculateBill class
    CalculateBill() {
        // Set the size and location of the JFrame
        setSize(700, 500);
        setLocation(400, 150);

        // Create a JPanel with null layout and background color
        JPanel p = new JPanel();
        p.setLayout(null);
        p.setBackground(new Color(173, 216, 230));
        add(p);

        // Create and configure the heading label
        JLabel heading = new JLabel("Calculate Electricity Bill");
        heading.setBounds(100, 10, 400, 25);
        heading.setFont(new Font("Tahoma", Font.PLAIN, 24));
        p.add(heading);

        // Create and configure components for meter number
        JLabel lblmeternumber = new JLabel("Meter Number");
        lblmeternumber.setBounds(100, 80, 100, 20);
        p.add(lblmeternumber);

        meternumber = new Choice();

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

        meternumber.setBounds(240, 80, 200, 20);
        p.add(meternumber);

        // Create and configure components for displaying customer name and address
        JLabel lblmeterno = new JLabel("Name");
        lblmeterno.setBounds(100, 120, 100, 20);
        p.add(lblmeterno);

        lblname = new JLabel("");
        lblname.setBounds(240, 120, 100, 20);
        p.add(lblname);

        JLabel lbladdress = new JLabel("Address");
        lbladdress.setBounds(100, 160, 100, 20);
        p.add(lbladdress);

        labeladdress = new JLabel();
        labeladdress.setBounds(240, 160, 200, 20);
        p.add(labeladdress);

        // Fetch and display customer name and address based on the selected meter number
        try {
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("select * from customer where meter_no = '" + meternumber.getSelectedItem() + "'");
            while (rs.next()) {
                lblname.setText(rs.getString("name"));
                labeladdress.setText(rs.getString("address"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Add an ItemListener to update customer name and address when meter number changes
        meternumber.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent ie) {
                try {
                    Conn c = new Conn();
                    ResultSet rs = c.s.executeQuery("select * from customer where meter_no = '" + meternumber.getSelectedItem() + "'");
                    while (rs.next()) {
                        lblname.setText(rs.getString("name"));
                        labeladdress.setText(rs.getString("address"));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        // Create and configure components for units consumed
        JLabel lblcity = new JLabel("Units Consumed");
        lblcity.setBounds(100, 200, 100, 20);
        p.add(lblcity);

        tfunits = new JTextField();
        tfunits.setBounds(240, 200, 200, 20);
        p.add(tfunits);

        // Create and configure components for selecting the month
        JLabel lblstate = new JLabel("Month");
        lblstate.setBounds(100, 240, 100, 20);
        p.add(lblstate);

        cmonth = new Choice();
        cmonth.setBounds(240, 240, 200, 20);
        cmonth.add("January");
        cmonth.add("February");
        cmonth.add("March");
        // ... (add the rest of the months)
        p.add(cmonth);

        // Create and configure buttons for submission and cancellation
        next = new JButton("Submit");
        next.setBounds(120, 350, 100, 25);
        next.setBackground(Color.BLACK);
        next.setForeground(Color.WHITE);
        next.addActionListener(this);
        p.add(next);

        cancel = new JButton("Cancel");
        cancel.setBounds(250, 350, 100, 25);
        cancel.setBackground(Color.BLACK);
        cancel.setForeground(Color.WHITE);
        cancel.addActionListener(this);
        p.add(cancel);

        // Set the layout and add components to the JFrame
        setLayout(new BorderLayout());
        add(p, "Center");

        // Add an image to the JFrame
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/hicon2.jpg"));
        Image i2 = i1.getImage().getScaledInstance(150, 300, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        add(image, "West");

        // Set the background color of the JFrame
        getContentPane().setBackground(Color.WHITE);

        // Make the JFrame visible
        setVisible(true);
    }

    // ActionListener implementation for button actions
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == next) {
            // Get input values for meter, units, and month
            String meter = meternumber.getSelectedItem();
            String units = tfunits.getText();
            String month = cmonth.getSelectedItem();

            // Initialize variables for calculating the total bill
            int totalbill = 0;
            int unit_consumed = Integer.parseInt(units);

            // SQL query to fetch tax details
            String query = "select * from tax";

            try {
                Conn c = new Conn();
                ResultSet rs = c.s.executeQuery(query);

                // Calculate total bill based on tax details
                while (rs.next()) {
                    totalbill += unit_consumed * Integer.parseInt(rs.getString("cost_per_unit"));
                    totalbill += Integer.parseInt(rs.getString("meter_rent"));
                    totalbill += Integer.parseInt(rs.getString("service_charge"));
                    totalbill += Integer.parseInt(rs.getString("service_tax"));
                    totalbill += Integer.parseInt(rs.getString("swacch_bharat_cess"));
                    totalbill += Integer.parseInt(rs.getString("fixed_tax"));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            // Insert the bill details into the database
            String query2 = "insert into bill values('" + meter + "', '" + month + "', '" + units + "', '" + totalbill + "', 'Not Paid')";

            try {
                Conn c = new Conn();
                c.s.executeUpdate(query2);

                // Show a dialog indicating successful bill update
                JOptionPane.showMessageDialog(null, "Customer Bill Updated Successfully");
                setVisible(false);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            // If cancel button is clicked, hide the JFrame
            setVisible(false);
        }
    }

    // Main method to test the CalculateBill class
    public static void main(String[] args) {
        new CalculateBill();
    }
}
