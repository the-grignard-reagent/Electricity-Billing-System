import java.sql.*;


public class Conn {
    
    Connection c;
    Statement  s;
    Conn() {
        //JDBC
        // Class.forName("/com.mysql.cj.jdbc.Driver");
        try {
            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/ebs", "RMgX", "example-password");
            s = c.createStatement();
        }catch(Exception e){
            e.printStackTrace();
        }
        
    }
    
}
