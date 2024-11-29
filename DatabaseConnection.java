import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/*
 * The purpose behind this class is to test the system's connection to the postgres database. 
 */
public class DatabaseConnection {

    // Method to connect to PostgreSQL
    public static Connection connect() {
        String url = "jdbc:postgresql://localhost:5432/softHealthClub"; // Your database name
        String user = "postgres";  // Your PostgreSQL username
        String password = "JChicagoo1618$$";  // Your PostgreSQL password

        try {
            // Load the PostgreSQL JDBC driver (this is important if not using Maven)
            Class.forName("org.postgresql.Driver");

            // Establish the connection
            Connection conn = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to the database successfully!");
            return conn;
        } catch (SQLException e) {
            System.out.println("Connection to database failed!");
            e.printStackTrace();
            return null;
        } catch (ClassNotFoundException e) {
            System.out.println("PostgreSQL JDBC Driver not found!");
            e.printStackTrace();
            return null;
        }
    }

    // Main method to test the connection
    public static void main(String[] args) {
        connect();  // Call the connect method to test the database connection
    }
}
