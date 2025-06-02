import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ProductViewer extends JFrame {

    private final JTable table;
    private final JComboBox<String> categoryFilter;
    private final ProductDAO dao;

    public ProductViewer() {
        dao = new ProductDAO();
        setTitle("🎬 Produits disponibles");
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Table setup
        table = new JTable();
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        // Category filter setup
        categoryFilter = new JComboBox<>();
        categoryFilter.addItem("Tous");

        List<String> categories = dao.getAllCategories();
        for (String cat : categories) {
            categoryFilter.addItem(cat);
        }

        categoryFilter.addActionListener(e -> updateTable());

        JPanel topPanel = new JPanel();
        topPanel.add(new JLabel("Filtrer par catégorie :"));
        topPanel.add(categoryFilter);
        add(topPanel, BorderLayout.NORTH);

        updateTable(); // Charge les données dès le démarrage
    }

    private void updateTable() {
        List<Product> products;
        String selectedCategory = (String) categoryFilter.getSelectedItem();

        if ("Tous".equals(selectedCategory)) {
            products = dao.getAllProducts();
        } else {
            products = dao.getProductsByCategory(selectedCategory);
        }

        String[] columns = {"ID", "Acteur", "Special", "Common Prod ID", "Titre", "Prix", "Catégorie"};
        DefaultTableModel model = new DefaultTableModel(columns, 0);

        for (Product p : products) {
            Object[] row = {
                p.getId(),
                p.getActor(),
                p.getSpecial(),
                p.getCommonProdId(),
                p.getTitle(),
                p.getPrice(),
                p.getCategory()
            };
            model.addRow(row);
        }

        table.setModel(model);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ProductViewer().setVisible(true));
    }
}
