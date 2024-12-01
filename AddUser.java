import java.awt.event.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.*;

public class AddUser {

    public static boolean isAdmin() {
        return "Admin".equalsIgnoreCase(BootLoader.currentUserRole);
    }

    public static String addNewUser(Object[] userDetails) {
        String sql = "INSERT INTO Staff (member_id, name, last_name, email, phone, street, city, state, zip, role) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        int memberID = (int) (Math.random() * 100000);

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, String.format("%05d", memberID));
            pstmt.setString(2, (String) userDetails[0]); // First Name
            pstmt.setString(3, (String) userDetails[1]); // Last Name
            pstmt.setString(4, (String) userDetails[2]); // Email
            pstmt.setString(5, (String) userDetails[3]); // Phone
            pstmt.setString(6, (String) userDetails[4]); // Street
            pstmt.setString(7, (String) userDetails[5]); // City
            pstmt.setString(8, (String) userDetails[6]); // State
            pstmt.setString(9, (String) userDetails[7]); // Zip
            pstmt.setString(10, (String) userDetails[8]); // Role

            pstmt.executeUpdate();
            System.out.println("New user added successfully!");
            return String.format("%05d", memberID);

        } catch (SQLException e) {
            System.out.println("Error adding new user.");
            e.printStackTrace();
            return null;
        }
    }

    public static void addUserInit() {
        if (!isAdmin()) {
            JOptionPane.showMessageDialog(null, "Access Denied: Only Admins can add new users.");
            MainPage.backToMain();
            return;
        }

        JPanel panel = new JPanel();
        panel.setLayout(null);

        JLabel addUserTitle = new JLabel("Add User");
        addUserTitle.setBounds(150, 10, 200, 25);
        panel.add(addUserTitle);

        /*
         * First Name
         */
        JLabel firstNameLabel = new JLabel("First Name:");
        firstNameLabel.setBounds(20, 50, 200, 25);
        panel.add(firstNameLabel);

        JTextField firstNameField = new JTextField();
        firstNameField.setBounds(230, 50, 200, 25);
        panel.add(firstNameField);

        /*
         * Last Name
         */
        JLabel lastNameLabel = new JLabel("Last Name:");
        lastNameLabel.setBounds(20, 90, 200, 25);
        panel.add(lastNameLabel);

        JTextField lastNameField = new JTextField();
        lastNameField.setBounds(230, 90, 200, 25);
        panel.add(lastNameField);

        /*
         * Email
         */
        JLabel emailLabel = new JLabel("Email, EX. name@domain.com:");
        emailLabel.setBounds(20, 130, 200, 25);
        panel.add(emailLabel);

        JTextField emailField = new JTextField();
        emailField.setBounds(230, 130, 200, 25);
        panel.add(emailField);

        /*
         * Phone Number
         */
        JLabel phoneLabel = new JLabel("Phone (xxx-xxx-xxxx):");
        phoneLabel.setBounds(20, 170, 200, 25);
        panel.add(phoneLabel);

        JTextField phoneField = new JTextField();
        phoneField.setBounds(230, 170, 200, 25);
        panel.add(phoneField);

        /*
         * Address
         */
        JLabel streetLabel = new JLabel("Address:");
        streetLabel.setBounds(20, 210, 200, 25);
        panel.add(streetLabel);

        JTextField streetField = new JTextField();
        streetField.setBounds(230, 210, 200, 25);
        panel.add(streetField);

        JLabel cityLabel = new JLabel("City, EX. Chicago:");
        cityLabel.setBounds(20, 250, 200, 25);
        panel.add(cityLabel);

        JTextField cityField = new JTextField();
        cityField.setBounds(230, 250, 200, 25);
        panel.add(cityField);

        JLabel stateLabel = new JLabel("State, EX: IL:");
        stateLabel.setBounds(20, 290, 200, 25);
        panel.add(stateLabel);

        JTextField stateField = new JTextField();
        stateField.setBounds(230, 290, 200, 25);
        panel.add(stateField);

        JLabel zipLabel = new JLabel("ZIP Code, EX. 60626:");
        zipLabel.setBounds(20, 330, 200, 25);
        panel.add(zipLabel);

        JTextField zipField = new JTextField();
        zipField.setBounds(230, 330, 200, 25);
        panel.add(zipField);

        /*
         * Role Selector
         */
        JLabel roleLabel = new JLabel("Role:");
        roleLabel.setBounds(20, 370, 200, 25);
        panel.add(roleLabel);

        JComboBox<String> roleComboBox = new JComboBox<>(new String[]{"Admin", "Staff"});
        roleComboBox.setBounds(230, 370, 200, 25);
        panel.add(roleComboBox);

        /*
         * Error Label
         */
        JLabel errorLabel = new JLabel("");
        errorLabel.setBounds(20, 450, 200, 25);
        panel.add(errorLabel);

        /*
         * Buttons
         */
        JButton enterButton = new JButton("Enter");
        enterButton.setBounds(150, 500, 100, 30);
        panel.add(enterButton);

        JButton backButton = new JButton("Back");
        backButton.setBounds(280, 500, 100, 30);
        panel.add(backButton);

        BootLoader.panelContainer.add(panel, "add user");
        BootLoader.cardLayout.show(BootLoader.panelContainer, "add user");
        BootLoader.loginFrame.setSize(500, 600);

        enterButton.addActionListener(e -> {
            String firstName = firstNameField.getText().trim();
            String lastName = lastNameField.getText().trim();
            String email = emailField.getText().trim();
            String phone = phoneField.getText().trim();
            String street = streetField.getText().trim();
            String city = cityField.getText().trim();
            String state = stateField.getText().trim();
            String zip = zipField.getText().trim();
            String role = (String) roleComboBox.getSelectedItem();

            if (firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() || phone.isEmpty() ||
                    street.isEmpty() || city.isEmpty() || state.isEmpty() || zip.isEmpty()) {
                errorLabel.setText("Error: All fields must be filled.");
                return;
            }

            String memberID = addNewUser(new Object[]{firstName, lastName, email, phone, street, city, state, zip, role});
            if (memberID != null) {
                alert_init(memberID);
            } else {
                errorLabel.setText("Error: Failed to add user.");
            }
        });

        backButton.addActionListener(e -> MainPage.backToMain());
    }

    public static void alert_init(String memberID) {
        JPanel panel = new JPanel();
        panel.setLayout(null);

        JLabel textLabel = new JLabel("This user's Member ID is " + memberID);
        textLabel.setBounds(25, 50, 350, 25);

        JButton nextButton = new JButton("Next");
        nextButton.setBounds(150, 100, 100, 30);

        panel.add(textLabel);
        panel.add(nextButton);

        BootLoader.panelContainer.add(panel, "user alert panel");
        BootLoader.cardLayout.show(BootLoader.panelContainer, "user alert panel");
        BootLoader.loginFrame.setSize(400, 200);

        nextButton.addActionListener(e -> MainPage.backToMain());
    }
}
