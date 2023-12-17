import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

// Project class extending JFrame and implementing ActionListener
public class Project extends JFrame implements ActionListener {

    // Declaration of String variables for account type and meter number
    String atype, meter;

    // Constructor for Project class taking account type and meter number as parameters
    Project(String atype, String meter) {
        this.atype = atype;
        this.meter = meter;

        // Setting the frame to maximized state
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        // Creating an ImageIcon for the background image
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/elect1.jpg"));
        Image i2 = i1.getImage().getScaledInstance(1550, 850, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);

        // Creating a JLabel with the background image
        JLabel image = new JLabel(i3);
        add(image);

        // Creating a JMenuBar
        JMenuBar mb = new JMenuBar();
        setJMenuBar(mb);

        // Creating master menu
        JMenu master = new JMenu("Master");
        master.setForeground(Color.BLUE);

        // Creating menu items under Master
        JMenuItem newcustomer = new JMenuItem("New Customer");
        // Setting properties for the menu item
        newcustomer.setFont(new Font("monospaced", Font.PLAIN, 12));
        newcustomer.setBackground(Color.WHITE);
        // Creating and setting icon for the menu item
        ImageIcon icon1 = new ImageIcon(ClassLoader.getSystemResource("icon/icon1.png"));
        Image image1 = icon1.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
        newcustomer.setIcon(new ImageIcon(image1));
        newcustomer.setMnemonic('D');
        newcustomer.addActionListener(this);
        newcustomer.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, ActionEvent.CTRL_MASK));
        // Adding menu item to the Master menu
        master.add(newcustomer);

        // Similar steps for other menu items under Master
        JMenuItem customerdetails = new JMenuItem("Customer Details");
        // ... (repeated pattern for other menu items)
        
        JMenu info = new JMenu("Information");
        info.setForeground(Color.RED);

        // Similar steps for menu items under Information
        JMenuItem updateinformation = new JMenuItem("Update Information");
        // ...

        JMenu user = new JMenu("User");
        user.setForeground(Color.BLUE);

        // Similar steps for menu items under User
        JMenuItem paybill = new JMenuItem("Pay Bill");
        // ...

        JMenu report = new JMenu("Report");
        report.setForeground(Color.RED);

        // Similar steps for menu items under Report
        JMenuItem generatebill = new JMenuItem("Generate Bill");
        // ...

        JMenu utility = new JMenu("Utility");
        utility.setForeground(Color.BLUE);

        // Similar steps for menu items under Utility
        JMenuItem notepad = new JMenuItem("Notepad");
        // ...
        
        // Creating Exit menu
        JMenu mexit = new JMenu("Exit");
        mexit.setForeground(Color.RED);

        // Creating Exit menu item
        JMenuItem exit = new JMenuItem("Exit");
        // ... (repeated pattern for Exit menu item)

        // Adding menus to the JMenuBar based on the account type
        if (atype.equals("Admin")) {
            mb.add(master);
        } else {
            mb.add(info);
            mb.add(user);
            mb.add(report);
        }

        // Adding remaining menus to the JMenuBar
        mb.add(utility);
        mb.add(mexit);

        // Setting the layout and making the frame visible
        setLayout(new FlowLayout());
        setVisible(true);
    }

    // ActionListener method for handling menu item clicks
    public void actionPerformed(ActionEvent ae) {
        String msg = ae.getActionCommand();
        // Handling different menu item clicks and opening corresponding frames
        if (msg.equals("New Customer")) {
            new NewCustomer();
        } else if (msg.equals("Customer Details")) {
            new CustomerDetails();
        } else if (msg.equals("Deposit Details")) {
            new DepositDetails();
        } else if (msg.equals("Calculate Bill")) {
            new CalculateBill();
        } else if (msg.equals("View Information")) {
            new ViewInformation(meter);
        } else if (msg.equals("Update Information")) {
            new UpdateInformation(meter);
        } else if (msg.equals("Bill Details")) {
            new BillDetails(meter);
        } else if (msg.equals("Notepad")) {
            // Opening Notepad using Runtime.getRuntime().exec()
            try {
                Runtime.getRuntime().exec("notepad.exe");
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (msg.equals("Calculator")) {
            // Opening Calculator using Runtime.getRuntime().exec()
            try {
                Runtime.getRuntime().exec("calc.exe");
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (msg.equals("Exit")) {
            // Closing the current frame and opening the Login frame
            setVisible(false);
            new Login();
        } else if (msg.equals("Pay Bill")) {
            new PayBill(meter);
        } else if (msg.equals("Generate Bill")) {
            new GenerateBill(meter);
        }
    }

    // Main method to create an instance of the Project class
    public static void main(String[] args) {
        new Project("", "");
    }
}
