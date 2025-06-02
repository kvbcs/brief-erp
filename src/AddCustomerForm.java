import javax.swing.*;
import java.awt.*;

public class AddCustomerForm extends JFrame {

    private final JTextField firstnameField;
    private final JTextField lastnameField;
    private final JTextField address1Field;
    private final JTextField address2Field;
    private final JTextField cityField;
    private final JTextField stateField;
    private final JTextField zipField;
    private final JTextField countryField;
    private final JTextField regionField;
    private final JTextField emailField;
    private final JTextField phoneField;
    private final JTextField creditCardTypeField;
    private final JTextField creditCardField;
    private final JTextField creditCardExpirationField;
    private final JTextField usernameField;
    private final JTextField passwordField;
    private final JTextField ageField;
    private final JTextField incomeField;
    private final JTextField genderField;

    private final JButton submitButton;

    public AddCustomerForm() {
        setTitle("Ajouter un client");
        setSize(600, 800);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(21, 2, 10, 10));

        firstnameField = new JTextField();
        lastnameField = new JTextField();
        address1Field = new JTextField();
        address2Field = new JTextField();
        cityField = new JTextField();
        stateField = new JTextField();
        zipField = new JTextField();
        countryField = new JTextField();
        regionField = new JTextField();
        emailField = new JTextField();
        phoneField = new JTextField();
        creditCardTypeField = new JTextField();
        creditCardField = new JTextField();
        creditCardExpirationField = new JTextField();
        usernameField = new JTextField();
        passwordField = new JTextField();
        ageField = new JTextField();
        incomeField = new JTextField();
        genderField = new JTextField();

        submitButton = new JButton("Ajouter");

        add(new JLabel("Prénom :")); add(firstnameField);
        add(new JLabel("Nom :")); add(lastnameField);
        add(new JLabel("Adresse 1 :")); add(address1Field);
        add(new JLabel("Adresse 2 :")); add(address2Field);
        add(new JLabel("Ville :")); add(cityField);
        add(new JLabel("État :")); add(stateField);
        add(new JLabel("ZIP :")); add(zipField);
        add(new JLabel("Pays :")); add(countryField);
        add(new JLabel("Région :")); add(regionField);
        add(new JLabel("Email :")); add(emailField);
        add(new JLabel("Téléphone :")); add(phoneField);
        add(new JLabel("Type CB :")); add(creditCardTypeField);
        add(new JLabel("CB :")); add(creditCardField);
        add(new JLabel("Expiration CB :")); add(creditCardExpirationField);
        add(new JLabel("Utilisateur :")); add(usernameField);
        add(new JLabel("Mot de passe :")); add(passwordField);
        add(new JLabel("Âge :")); add(ageField);
        add(new JLabel("Revenu :")); add(incomeField);
        add(new JLabel("Genre :")); add(genderField);

        add(new JLabel());
        add(submitButton);

        submitButton.addActionListener(e -> handleSubmit());
    }

    private void handleSubmit() {
        try {
            String firstname = firstnameField.getText().trim();
            String lastname = lastnameField.getText().trim();
            String address1 = address1Field.getText().trim();
            String address2 = address2Field.getText().trim();
            String city = cityField.getText().trim();
            String state = stateField.getText().trim();
            int zip = Integer.parseInt(zipField.getText().trim()); // ⬅️ zip redeviens int
            String country = countryField.getText().trim();
            int region = Integer.parseInt(regionField.getText().trim());
            String email = emailField.getText().trim();
            String phone = phoneField.getText().trim();
            int creditCardType = Integer.parseInt(creditCardTypeField.getText().trim());
            String creditCard = creditCardField.getText().trim();
            String creditCardExpiration = creditCardExpirationField.getText().trim();
            String username = usernameField.getText().trim();
            String password = passwordField.getText().trim();
            int age = Integer.parseInt(ageField.getText().trim()); // ✅ age reste en int
            int income = Integer.parseInt(incomeField.getText().trim()); // ✅ income as String
            String gender = genderField.getText().trim();
    
            if (firstname.isEmpty() || email.isEmpty() || username.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Champs obligatoires manquants.", "Erreur", JOptionPane.ERROR_MESSAGE);
                return;
            }
    
            CustomerDAO dao = new CustomerDAO();
            boolean success = dao.addCustomer(
                0, firstname, lastname, address1, address2, city, state, zip,
                country, region, email, phone, creditCardType,
                creditCard, creditCardExpiration, username, password,
                age, income, gender
            );
    
            if (success) {
                JOptionPane.showMessageDialog(this, "✅ Client ajouté avec succès !");
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "❌ Échec de l'ajout.", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
    
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "⚠️ Format invalide de nombre", "Erreur", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "❌ Erreur : " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }
    

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new AddCustomerForm().setVisible(true));
    }
}
