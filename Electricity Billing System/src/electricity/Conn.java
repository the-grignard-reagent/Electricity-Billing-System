import java.sql.*;

// Conn class for database connection
public class Conn {

    // Connection and Statement objects for database interaction
    Connection c;
    Statement s;

    // Constructor to initialize the database connection
    Conn() {
        // JDBC driver loading is not required in recent JDBC versions
        // Class.forName("/com.mysql.cj.jdbc.Driver");

        try {
            // Establish a connection to the MySQL database
            // Replace "localhost" with the actual database server address
            // "3306" is the default port for MySQL, and "ebs" is the database name
            // Replace "RMgX" and "example-password" with the actual username and password
            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/ebs", "RMgX", "example-password");

            // Create a Statement object for executing SQL queries
            s = c.createStatement();
        } catch (Exception e) {
            // Print the stack trace if there's an exception during the database connection setup
            e.printStackTrace();
        }
    }
}
