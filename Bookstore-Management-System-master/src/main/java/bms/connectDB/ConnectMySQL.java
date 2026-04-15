package bms.connectDB;
// DONE

import java.sql.*;

public class ConnectMySQL {

    private static String url = "jdbc:mysql://localhost:3306/bms?autoReconnect=true&useSSL=false";
    private static String username = "hung";
    private static String password = "gay";

    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        //Nap Driver
        Class.forName("com.mysql.jdbc.Driver");
        return (Connection) DriverManager.getConnection(url, username, password);
    }
}
