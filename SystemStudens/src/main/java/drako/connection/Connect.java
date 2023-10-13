package drako.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connect {
    public static Connection getConnection() {
        Connection connection = null;

        var dataBase = "students_db";
        var url = "jdbc:mysql://localhost:3306/" + dataBase;
        var user = "drako";
        var password = "password";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Error in: " + e.getMessage());
        }

        return connection;
    }

    public static void main(String[] args) {
        var connection = Connect.getConnection();
        if (connection != null) {
            System.out.println("Connection successfully " + connection);

        } else {
            System.out.println("Error in connection");
        }
    }
}
