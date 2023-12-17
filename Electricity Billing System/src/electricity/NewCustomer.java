import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.event.*;

// NewCustomer class extending JFrame and implementing ActionListener
public class NewCustomer extends JFrame implements ActionListener {

    // Declaration of Swing components
    JTextField tfname, tfaddress, tfstate, tfcity, tfemail, tfphone;
    JButton next, cancel;
    JLabel lblmeter;

    // Constructor for NewCustomer class
    NewCustomer() {
        // Setting JFrame properties
        setSize(700, 500);
        setLocation(400, 200);

        // Creating and configuring JPanel
        JPanel p = new JPanel();
        p.setLayout(null);
        p.setBackground(new Color(173, 216, 230));
        add(p);

        // Creating and configuring JLabel for heading
        JLabel heading = new JLabel("New Customer");
        heading.setBounds(180, 10, 200, 25);
        heading.setFont(new Font("Tahoma", Font.PLAIN, 24));
        p.add(heading);

        // Creating and configuring JLabel for Customer Name
        JLabel lblname = new JLabel("Customer Name");
        lblname.setBounds(100, 80, 100, 20);
        p.add(lblname);

        // Creating and configuring JTextField for Customer Name
        tfname = new JTextField();
        tfname.setBounds(240, 80, 200, 20);
        p.add(tfname);

        // Creating and configuring JLabel for Meter Number
        JLabel lblmeterno = new JLabel("Meter Number");
        lblmeterno.setBounds(100, 120, 100, 20);
        p.add(lblmeterno);

        // Creating and configuring JLabel to display a generated random Meter Number
        lblmeter = new JLabel("");
        lblmeter.setBounds(240, 120, 100, 20);
        p.add(lblmeter);

        // Generating a random long number and displaying it as a Meter Number
        Random ran = new Random();
        long number = ran.nextLong() % 1000000;
        lblmeter.setText("" + Math.abs(number));

        // Creating and configuring JLabel for Address
        JLabel lbladdress = new JLabel("Address");
        lbladdress.setBounds(100, 160, 100, 20);
        p.add(lbladdress);

        // Creating and configuring JTextField for Address
        tfaddress = new JTextField();
        tfaddress.setBounds(240, 160, 200, 20);
        p.add(tfaddress);

        // Creating and configuring JLabel for City
        JLabel lblcity = new JLabel("City");
        lblcity.setBounds(100, 200, 100, 20);
        p.add(lblcity);

        // Creating and configuring JTextField for City
        tfcity = new JTextField();
        tfcity.setBounds(240, 200, 200, 20);
        p.add(tfcity);

        // Creating and configuring JLabel for State
        JLabel lblstate = new JLabel("State");
        lblstate.setBounds(100, 240, 100, 20);
        p.add(lblstate);

        // Creating and configuring JTextField for State
        tfstate = new JTextField();
        tfstate.setBounds(240, 240, 200, 20);
        p.add(tfstate);

        // Creating and configuring JLabel for Email
        JLabel lblemail = new JLabel("Email");
        lblemail.setBounds(100, 280, 100, 20);
        p.add(lblemail);

        // Creating and configuring JTextField for Email
        tfemail = new JTextField();
        tfemail.setBounds(240, 280, 200, 20);
        p.add(tfemail);

        // Creating and configuring JLabel for Phone Number
        JLabel lblphone = new JLabel("Phone Number");
        lblphone.setBounds(100, 320, 100, 20);
        p.add(lblphone);

        // Creating and configuring JTextField for Phone Number
        tfphone = new JTextField();
        tfphone.setBounds(240, 320, 200, 20);
        p.add(tfphone);

        // Creating and configuring JButton for Next
        next = new JButton("Next");
        next.setBounds(120, 390, 100, 25);
        next.setBackground(Color.BLACK);
        next.setForeground(Color.WHITE);
        next.addActionListener(this);
        p.add(next);

        // Creating and configuring JButton for Cancel
        cancel = new JButton("Cancel");
        cancel.setBounds(250, 390, 100, 25);
        cancel.setBackground(Color.BLACK);
        cancel.setForeground(Color.WHITE);
        cancel.addActionListener(this);
        p.add(cancel);

        // Configuring layout for the JFrame
        setLayout(new BorderLayout());

        // Adding the JPanel to the center of the JFrame
        add(p, "Center");

        // Creating and configuring JLabel for an image (West side of the JFrame)
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/hicon1.jpg"));
        Image i2 = i1.getImage().getScaledInstance(150, 300, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        add(image, "West");

        // Setting the background color of the content pane
        getContentPane().setBackground(Color.WHITE);

        // Setting the JFrame as visible
        setVisible(true);
    }

    // ActionListener method for handling button clicks
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == next) {
            // Retrieving values entered by the user
            String name = tfname.getText();
            String meter = lblmeter.getText();
            String address = tfaddress.getText();
            String city = tfcity.getText();
            String state = tfstate.getText();
            String email = tfemail.getText();
            String phone = tfphone.getText();

            // Creating SQL queries to insert data into the customer and login tables
            String query1 = "insert into customer values('" + name + "', '" + meter + "', '" + address + "', '" + city
                    + "', '" + state + "', '" + email + "', '" + phone + "')";
            String query2 = "insert into login values('" + meter + "', '', '" + name + "', '', '')";

            try {
                // Establishing a database connection
                Conn c = new Conn();
                // Executing SQL queries to insert data
                c.s.executeUpdate(query1);
                c.s.executeUpdate(query2);

                // Displaying a success message
                JOptionPane.showMessageDialog(null, "Customer Details Added Successfully");
                // Closing the current JFrame
                setVisible(false);

                // Opening a new frame (MeterInfo) for further details
                new MeterInfo(meter);
            } catch (Exception e) {
                // Printing the stack trace if an exception occurs
                e.printStackTrace();
            }
        } else {
            // If cancel button is clicked, close the current JFrame
            setVisible(false);
        }
    }

    // Main method to test the NewCustomer class
    public static void main(String[] args) {
        new NewCustomer();
    }
}
