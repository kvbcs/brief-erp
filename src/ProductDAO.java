import java.sql.*;
import java.util.*;

public class ProductDAO {

    private Connection conn;

    public ProductDAO() {
        try {
            conn = DatabaseManager.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Product> getAllProducts() {
        List<Product> list = new ArrayList<>();
        String sql = "SELECT * FROM products";

        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                list.add(new Product(
                        rs.getInt("id"),
                        rs.getString("actor"),
                        rs.getInt("special"),
                        rs.getInt("common_prod_id"),
                                                    
                    rs.getString("title"),
                    rs.getDouble("price"),
                    rs.getString("category")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    public List<String> getAllCategories() {
        List<String> categories = new ArrayList<>();
        String sql = "SELECT DISTINCT category FROM product";

        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                categories.add(rs.getString("category"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return categories;
    }

    public List<Product> getProductsByCategory(String category) {
        List<Product> list = new ArrayList<>();
        String sql = "SELECT * FROM product WHERE category = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, category);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                list.add(new Product(
                    rs.getInt("id"),
                    rs.getString("actor"),
                    rs.getInt("special"),
                    rs.getInt("common_prod_id"),
                                                
                rs.getString("title"),
                rs.getDouble("price"),
                rs.getString("category")
            ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }
}
