import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.awt.event.*;

// PayBill class extending JFrame and implementing ActionListener
public class PayBill extends JFrame implements ActionListener {

    // Declaration of Choice, JButton, and String variables
    Choice cmonth;
    JButton pay, back;
    String meter;

    // Constructor for PayBill class taking the meter number as a parameter
    PayBill(String meter) {
        this.meter = meter;

        // Setting layout and bounds for the JFrame
        setLayout(null);
        setBounds(300, 150, 900, 600);

        // Creating and configuring JLabel for heading
        JLabel heading = new JLabel("Electricity Bill");
        heading.setFont(new Font("Tahoma", Font.BOLD, 24));
        heading.setBounds(120, 5, 400, 30);
        add(heading);

        // Creating and configuring JLabel for Meter Number
        JLabel lblmeternumber = new JLabel("Meter Number");
        lblmeternumber.setBounds(35, 80, 200, 20);
        add(lblmeternumber);

        // Creating and configuring JLabel to display the Meter Number
        JLabel meternumber = new JLabel("");
        meternumber.setBounds(300, 80, 200, 20);
        add(meternumber);

        // Creating and configuring JLabel for Name
        JLabel lblname = new JLabel("Name");
        lblname.setBounds(35, 140, 200, 20);
        add(lblname);

        // Creating and configuring JLabel to display the customer's name
        JLabel labelname = new JLabel("");
        labelname.setBounds(300, 140, 200, 20);
        add(labelname);

        // Creating and configuring JLabel for Month
        JLabel lblmonth = new JLabel("Month");
        lblmonth.setBounds(35, 200, 200, 20);
        add(lblmonth);

        // Creating and configuring Choice for selecting the month
        cmonth = new Choice();
        cmonth.setBounds(300, 200, 200, 20);
        cmonth.add("January");
        cmonth.add("February");
        cmonth.add("March");
        cmonth.add("April");
        cmonth.add("May");
        cmonth.add("June");
        cmonth.add("July");
        cmonth.add("August");
        cmonth.add("September");
        cmonth.add("October");
        cmonth.add("November");
        cmonth.add("December");
        add(cmonth);

        // Creating and configuring JLabel for Units
        JLabel lblunits = new JLabel("Units");
        lblunits.setBounds(35, 260, 200, 20);
        add(lblunits);

        // Creating and configuring JLabel to display the consumed units
        JLabel labelunits = new JLabel("");
        labelunits.setBounds(300, 260, 200, 20);
        add(labelunits);

        // Creating and configuring JLabel for Total Bill
        JLabel lbltotalbill = new JLabel("Total Bill");
        lbltotalbill.setBounds(35, 320, 200, 20);
        add(lbltotalbill);

        // Creating and configuring JLabel to display the total bill amount
        JLabel labeltotalbill = new JLabel("");
        labeltotalbill.setBounds(300, 320, 200, 20);
        add(labeltotalbill);

        // Creating and configuring JLabel for Status
        JLabel lblstatus = new JLabel("Status");
        lblstatus.setBounds(35, 380, 200, 20);
        add(lblstatus);

        // Creating and configuring JLabel to display the payment status
        JLabel labelstatus = new JLabel("");
        labelstatus.setBounds(300, 380, 200, 20);
        labelstatus.setForeground(Color.RED);
        add(labelstatus);

        try {
            // Establishing a database connection
            Conn c = new Conn();
            // Retrieving customer information based on the meter number
            ResultSet rs = c.s.executeQuery("select * from customer where meter_no = '" + meter + "'");
            while (rs.next()) {
                meternumber.setText(meter);
                labelname.setText(rs.getString("name"));
            }

            // Retrieving bill information for the month of January
            rs = c.s.executeQuery("select * from bill where meter_no = '" + meter + "' AND month = 'January'");
            while (rs.next()) {
                labelunits.setText(rs.getString("units"));
                labeltotalbill.setText(rs.getString("totalbill"));
                labelstatus.setText(rs.getString("status"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Adding an item listener to the Choice component for month selection
        cmonth.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent ae) {
                try {
                    Conn c = new Conn();
                    // Retrieving bill information based on the selected month
                    ResultSet rs = c.s.executeQuery("select * from bill where meter_no = '" + meter + "' AND month = '"
                            + cmonth.getSelectedItem() + "'");
                    while (rs.next()) {
                        labelunits.setText(rs.getString("units"));
                        labeltotalbill.setText(rs.getString("totalbill"));
                        labelstatus.setText(rs.getString("status"));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        // Creating and configuring JButton for Pay
        pay = new JButton("Pay");
        pay.setBackground(Color.BLACK);
        pay.setForeground(Color.WHITE);
        pay.setBounds(100, 460, 100, 25);
        pay.addActionListener(this);
        add(pay);

        // Creating and configuring JButton for Back
        back = new JButton("Back");
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);
        back.setBounds(230, 460, 100, 25);
        back.addActionListener(this);
        add(back);

        // Setting the background color of the content pane
        getContentPane().setBackground(Color.WHITE);

        // Creating and configuring JLabel for an image
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/bill.png"));
        Image i2 = i1.getImage().getScaledInstance(600, 300, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(400, 120, 600, 300);
        add(image);

        // Setting the JFrame as visible
        setVisible(true);
    }

    // ActionListener method for handling button clicks
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == pay) {
            try {
                // Establishing a database connection
                Conn c = new Conn();
                // Updating the status to 'Paid' for the selected month
                c.s.executeUpdate(
                        "update bill set status = 'Paid' where meter_no = '" + meter + "' AND month='" + cmonth.getSelectedItem() + "'");
            } catch (Exception e) {
                e.printStackTrace();
            }
            // Closing the current frame and opening the Paytm frame
            setVisible(false);
            new Paytm(meter);
        } else {
            // Closing the current frame if the 'Back' button is clicked
            setVisible(false);
        }
    }

    // Main method for testing the PayBill class
    public static void main(String[] args) {
        new PayBill("");
    }
}
