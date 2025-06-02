import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAO {

    public List<Customer> getAllCustomers() {
        List<Customer> customers = new ArrayList<>();

        try (Connection connect = DatabaseManager.getConnection();
                Statement stmt = connect.createStatement();
                ResultSet res = stmt.executeQuery("SELECT customerid, firstname, email FROM customers")) {

            while (res.next()) {
                int id = res.getInt("customerid");
                String name = res.getString("firstname");
                String email = res.getString("email");

                customers.add(new Customer(id, name, email));
            }
            System.out.print("Requête 1 réussie");

        } catch (Exception e) {
            System.err.println("❌ Erreur lors de la récupération des clients : " + e.getMessage());
        }

        return customers;
    }
    
    public boolean addCustomer(
        int customerid, String firstname, String lastname, String address1, String address2,
        String city, String state, int zip, String country, int region,
        String email, String phone, int creditcardtype, String creditcard,
        String creditcardexpiration, String username, String password,
        int age, int income, String gender) {
    
        String sql = "INSERT INTO customers (customerid, firstname, lastname, address1, address2, city, state, zip, country, region, email, phone, creditcardtype, creditcard, creditcardexpiration, username, password, age, income, gender) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
    
            stmt.setInt(1, customerid);
            stmt.setString(2, firstname);
            stmt.setString(3, lastname);
            stmt.setString(4, address1);
            stmt.setString(5, address2);
            stmt.setString(6, city);
            stmt.setString(7, state);
            stmt.setInt(8, zip);
            stmt.setString(9, country);
            stmt.setInt(10, region);
            stmt.setString(11, email);
            stmt.setString(12, phone);
            stmt.setInt(13, creditcardtype);
            stmt.setString(14, creditcard);
            stmt.setString(15, creditcardexpiration);
            stmt.setString(16, username);
            stmt.setString(17, password);
            stmt.setInt(18, age);
            stmt.setInt(19, income);
            stmt.setString(20, gender);
    
            stmt.executeUpdate();
            return true;
    
        } catch (Exception e) {
            System.err.println("❌ Erreur lors de l’ajout : " + e.getMessage());
            return false;
        }
    }
    
}

