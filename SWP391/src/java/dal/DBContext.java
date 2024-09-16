package database;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DBContext {
    
    public static Connection getConnection() {
        Connection c = null; 
        try {
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver()); 
            String url = "jdbc:mySQL://localhost:3306/swp391";
            String username = "root";
            String password = "123456789?TM";
                     
            c = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return c;
    }
    public static void closeConnection(Connection c) {
        
    }
}
