import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Login extends JFrame implements ActionListener {

    // Declare Swing components
    JButton login, cancel, signup;
    JTextField username, password;
    Choice logginin;

    // Constructor for the Login class
    Login() {
        // Set JFrame title, background color, and layout
        super("Login Page");
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        // Create and configure labels, text fields, and choice component
        JLabel lblusername = new JLabel("Username");
        lblusername.setBounds(300, 20, 100, 20);
        add(lblusername);

        username = new JTextField();
        username.setBounds(400, 20, 150, 20);
        add(username);

        JLabel lblpassword = new JLabel("Password");
        lblpassword.setBounds(300, 60, 100, 20);
        add(lblpassword);

        password = new JTextField();
        password.setBounds(400, 60, 150, 20);
        add(password);

        JLabel loggininas = new JLabel("Loggin in as");
        loggininas.setBounds(300, 100, 100, 20);
        add(loggininas);

        logginin = new Choice();
        logginin.add("Admin");
        logginin.add("Customer");
        logginin.setBounds(400, 100, 150, 20);
        add(logginin);

        // Create and configure login, cancel, and signup buttons with icons
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/login.png"));
        Image i2 = i1.getImage().getScaledInstance(16, 16, Image.SCALE_DEFAULT);
        login = new JButton("Login", new ImageIcon(i2));
        login.setBounds(330, 160, 100, 20);
        login.addActionListener(this);
        add(login);

        ImageIcon i3 = new ImageIcon(ClassLoader.getSystemResource("icon/cancel.jpg"));
        Image i4 = i3.getImage().getScaledInstance(16, 16, Image.SCALE_DEFAULT);
        cancel = new JButton("Cancel", new ImageIcon(i4));
        cancel.setBounds(450, 160, 100, 20);
        cancel.addActionListener(this);
        add(cancel);

        ImageIcon i5 = new ImageIcon(ClassLoader.getSystemResource("icon/signup.png"));
        Image i6 = i5.getImage().getScaledInstance(16, 16, Image.SCALE_DEFAULT);
        signup = new JButton("Signup", new ImageIcon(i6));
        signup.setBounds(380, 200, 100, 20);
        signup.addActionListener(this);
        add(signup);

        // Create and configure an image label for the background
        ImageIcon i7 = new ImageIcon(ClassLoader.getSystemResource("icon/second.jpg"));
        Image i8 = i7.getImage().getScaledInstance(250, 250, Image.SCALE_DEFAULT);
        ImageIcon i9 = new ImageIcon(i8);
        JLabel image = new JLabel(i9);
        image.setBounds(0, 0, 250, 250);
        add(image);

        // Set JFrame size, location, and visibility
        setSize(640, 300);
        setLocation(400, 200);
        setVisible(true);
    }

    // ActionListener implementation for button actions
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == login) {
            // Get entered username, password, and user type
            String susername = username.getText();
            String spassword = password.getText();
            String user = logginin.getSelectedItem();

            try {
                // Create a connection to the database
                Conn c = new Conn();
                // Create a query to check login credentials
                String query = "select * from login where username = '" + susername + "' and password = '" + spassword + "' and user = '" + user + "'";

                ResultSet rs = c.s.executeQuery(query);

                if (rs.next()) {
                    // If login is successful, fetch the meter number and open the Project window
                    String meter = rs.getString("meter_no");
                    setVisible(false);
                    new Project(user, meter);
                } else {
                    // If login fails, show an error message and clear the text fields
                    JOptionPane.showMessageDialog(null, "Invalid Login");
                    username.setText("");
                    password.setText("");
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (ae.getSource() == cancel) {
            // If cancel button is clicked, close the login window
            setVisible(false);
        } else if (ae.getSource() == signup) {
            // If signup button is clicked, close the login window and open the Signup window
            setVisible(false);
            new Signup();
        }
    }

    // Main method to test the Login class
    public static void main(String[] args) {
        new Login();
    }
}
