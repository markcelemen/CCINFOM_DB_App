import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/RestaurantManagementSystem";
    private static final String USER = "root";
    private static final String PASSWORD = "yourpassword"; // change this based on ur password for mysql community

    public static Connection getConnection() throws SQLException {
        try {
            // Establish connection
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            System.out.println("Database connection failed: " + e.getMessage());
            throw e;
        }
    }
}