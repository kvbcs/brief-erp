import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseManager {
      private static final String URL = "jdbc:postgresql://localhost:5432/STORE";
    private static final String USER = "postgres";
    private static final String PASSWORD = "root";

        public static Connection getConnection() {
            try {
                Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
                System.out.println("✅ Connexion réussie !");
                return connection;
            } catch (SQLException e) {
                System.err.println("❌ Erreur de connexion : " + e.getMessage());
                return null;
            }
        }
        
       
    }

