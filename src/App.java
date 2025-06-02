import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;



public class App {
    public static void main(String[] args) throws Exception {
        try ( Connection connect = DatabaseManager.getConnection()) {
           
if (connect != null){
     Statement stmt = connect.createStatement();

            ResultSet res = stmt.executeQuery("SELECT * FROM customers LIMIT 5");

            while (res.next()) {
                String firstname = res.getString("firstname"); 
                System.out.println(firstname);
             
    
            }
            res.close();
            stmt.close();
            connect.close();
}
           
        } catch (SQLException e) {
            System.out.print("SQL error" + e.getMessage());
        }
    }
}
