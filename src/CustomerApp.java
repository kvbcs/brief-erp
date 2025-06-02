import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class CustomerApp extends JFrame {

    private final JTable table;
    private final DefaultTableModel model;

    public CustomerApp() {
        setTitle("Liste des Clients");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLayout(new BorderLayout());

        // Tableau vide au départ
        model = new DefaultTableModel(new String[]{"ID", "Nom", "Email"}, 0);
        table = new JTable(model);

        JButton loadButton = new JButton("Charger clients");
        loadButton.addActionListener(e -> loadCustomers());

        add(new JScrollPane(table), BorderLayout.CENTER);
        add(loadButton, BorderLayout.SOUTH);
    }

    private void loadCustomers() {
        model.setRowCount(0); // Clear table
        CustomerDAO dao = new CustomerDAO();
        List<Customer> customers = dao.getAllCustomers();

        for (Customer c : customers) {
            model.addRow(new Object[]{c.getId(), c.getName(), c.getEmail()});
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new CustomerApp().setVisible(true);
        });
    }
}
