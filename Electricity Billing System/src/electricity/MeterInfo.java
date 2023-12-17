import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

// MeterInfo class extending JFrame and implementing ActionListener
public class MeterInfo extends JFrame implements ActionListener {

    // Declaration of Swing components
    JTextField tfname, tfaddress, tfstate, tfcity, tfemail, tfphone;
    JButton next, cancel;
    JLabel lblmeter;
    Choice meterlocation, metertype, phasecode, billtype;
    String meternumber;

    // Constructor for MeterInfo class
    MeterInfo(String meternumber) {
        this.meternumber = meternumber;

        // Setting JFrame properties
        setSize(700, 500);
        setLocation(400, 200);

        // Creating and configuring JPanel
        JPanel p = new JPanel();
        p.setLayout(null);
        p.setBackground(new Color(173, 216, 230));
        add(p);

        // Creating and configuring JLabel for heading
        JLabel heading = new JLabel("Meter Information");
        heading.setBounds(180, 10, 200, 25);
        heading.setFont(new Font("Tahoma", Font.PLAIN, 24));
        p.add(heading);

        // Creating and configuring JLabel for Meter Number
        JLabel lblname = new JLabel("Meter Number");
        lblname.setBounds(100, 80, 100, 20);
        p.add(lblname);

        // Displaying the provided meter number
        JLabel lblmeternumber = new JLabel(meternumber);
        lblmeternumber.setBounds(240, 80, 100, 20);
        p.add(lblmeternumber);

        // Creating and configuring JLabel for Meter Location
        JLabel lblmeterno = new JLabel("Meter Location");
        lblmeterno.setBounds(100, 120, 100, 20);
        p.add(lblmeterno);

        // Creating and configuring Choice for Meter Location
        meterlocation = new Choice();
        meterlocation.add("Outside");
        meterlocation.add("Inside");
        meterlocation.setBounds(240, 120, 200, 20);
        p.add(meterlocation);

        // Creating and configuring JLabel for Meter Type
        JLabel lbladdress = new JLabel("Meter Type");
        lbladdress.setBounds(100, 160, 100, 20);
        p.add(lbladdress);

        // Creating and configuring Choice for Meter Type
        metertype = new Choice();
        metertype.add("Electric Meter");
        metertype.add("Solar Meter");
        metertype.add("Smart Meter");
        metertype.setBounds(240, 160, 200, 20);
        p.add(metertype);

        // Creating and configuring JLabel for Phase Code
        JLabel lblcity = new JLabel("Phase Code");
        lblcity.setBounds(100, 200, 100, 20);
        p.add(lblcity);

        // Creating and configuring Choice for Phase Code
        phasecode = new Choice();
        phasecode.add("011");
        phasecode.add("022");
        phasecode.add("033");
        phasecode.add("044");
        phasecode.add("055");
        phasecode.add("066");
        phasecode.add("077");
        phasecode.add("088");
        phasecode.add("099");
        phasecode.setBounds(240, 200, 200, 20);
        p.add(phasecode);

        // Creating and configuring JLabel for Bill Type
        JLabel lblstate = new JLabel("Bill Type");
        lblstate.setBounds(100, 240, 100, 20);
        p.add(lblstate);

        // Creating and configuring Choice for Bill Type
        billtype = new Choice();
        billtype.add("Normal");
        billtype.add("Industrial");
        billtype.setBounds(240, 240, 200, 20);
        p.add(billtype);

        // Creating and configuring JLabel for Days
        JLabel lblemail = new JLabel("Days");
        lblemail.setBounds(100, 280, 100, 20);
        p.add(lblemail);

        // Displaying the default number of days (30 days)
        JLabel lblemails = new JLabel("30 Days");
        lblemails.setBounds(240, 280, 100, 20);
        p.add(lblemails);

        // Creating and configuring JLabel for Note
        JLabel lblphone = new JLabel("Note");
        lblphone.setBounds(100, 320, 100, 20);
        p.add(lblphone);

        // Displaying a note regarding the default calculation for 30 days
        JLabel lblphones = new JLabel("By Default Bill is calculated for 30 days only");
        lblphones.setBounds(240, 320, 500, 20);
        p.add(lblphones);

        // Creating and configuring JButton for Submit
        next = new JButton("Submit");
        next.setBounds(220, 390, 100, 25);
        next.setBackground(Color.BLACK);
        next.setForeground(Color.WHITE);
        next.addActionListener(this);
        p.add(next);

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
            // Retrieving selected values from Choice components
            String meter = meternumber;
            String location = meterlocation.getSelectedItem();
            String type = metertype.getSelectedItem();
            String code = phasecode.getSelectedItem();
            String typebill = billtype.getSelectedItem();
            String days = "30";  // Default value for days

            // Creating the SQL query to insert data into the meter_info table
            String query = "insert into meter_info values('" + meter + "', '" + location + "', '" + type + "', '"
                    + code + "', '" + typebill + "', '" + days + "')";

            try {
                // Establishing a database connection
                Conn c = new Conn();
                // Executing the SQL query to insert data
                c.s.executeUpdate(query);

                // Displaying a success message
                JOptionPane.showMessageDialog(null, "Meter Information Added Successfully");
                // Closing the current JFrame
                setVisible(false);

            } catch (Exception e) {
                // Printing the stack trace if an exception occurs
                e.printStackTrace();
            }
        } else {
            // If cancel button is clicked, close the current JFrame
            setVisible(false);
        }
    }

    // Main method to test the MeterInfo class
    public static void main(String[] args) {
        new MeterInfo("");
    }
}
