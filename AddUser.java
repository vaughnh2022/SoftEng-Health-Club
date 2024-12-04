import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
            System.out.println("Error adding new user: " + e.getMessage());
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

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        /*
         * Title
         */
        JLabel titleLabel = new JLabel("Add Staff", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        gbc.gridwidth = 2;
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(titleLabel, gbc);

        gbc.gridwidth = 1;

        /*
         * First Name
         */
        JLabel firstNameLabel = new JLabel("First Name:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(firstNameLabel, gbc);

        JTextField firstNameField = new JTextField();
        firstNameField.setPreferredSize(new Dimension(300, 30));
        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(firstNameField, gbc);

        /*
         * Last Name
         */
        JLabel lastNameLabel = new JLabel("Last Name:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(lastNameLabel, gbc);

        JTextField lastNameField = new JTextField();
        lastNameField.setPreferredSize(new Dimension(300, 30));
        gbc.gridx = 1;
        gbc.gridy = 2;
        panel.add(lastNameField, gbc);

        /*
         * Email
         */
        JLabel emailLabel = new JLabel("Email:");
        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(emailLabel, gbc);

        JTextField emailField = new JTextField();
        emailField.setPreferredSize(new Dimension(300, 30));
        gbc.gridx = 1;
        gbc.gridy = 3;
        panel.add(emailField, gbc);

        /*
         * Phone Number
         */
        JLabel phoneLabel = new JLabel("Phone (e.g., (xxx)-xxx-xxxx):");
        gbc.gridx = 0;
        gbc.gridy = 4;
        panel.add(phoneLabel, gbc);

        JTextField phoneField = new JTextField();
        phoneField.setPreferredSize(new Dimension(300, 30));
        gbc.gridx = 1;
        gbc.gridy = 4;
        panel.add(phoneField, gbc);

        /*
         * Address
         */
        JLabel streetLabel = new JLabel("Street:");
        gbc.gridx = 0;
        gbc.gridy = 5;
        panel.add(streetLabel, gbc);

        JTextField streetField = new JTextField();
        streetField.setPreferredSize(new Dimension(300, 30));
        gbc.gridx = 1;
        gbc.gridy = 5;
        panel.add(streetField, gbc);

        JLabel cityLabel = new JLabel("City:");
        gbc.gridx = 0;
        gbc.gridy = 6;
        panel.add(cityLabel, gbc);

        JTextField cityField = new JTextField();
        cityField.setPreferredSize(new Dimension(300, 30));
        gbc.gridx = 1;
        gbc.gridy = 6;
        panel.add(cityField, gbc);

        JLabel stateLabel = new JLabel("State:");
        gbc.gridx = 0;
        gbc.gridy = 7;
        panel.add(stateLabel, gbc);

        JTextField stateField = new JTextField();
        stateField.setPreferredSize(new Dimension(300, 30));
        gbc.gridx = 1;
        gbc.gridy = 7;
        panel.add(stateField, gbc);

        JLabel zipLabel = new JLabel("ZIP Code (e.g., 60626):");
        gbc.gridx = 0;
        gbc.gridy = 8;
        panel.add(zipLabel, gbc);

        JTextField zipField = new JTextField();
        zipField.setPreferredSize(new Dimension(300, 30));
        gbc.gridx = 1;
        gbc.gridy = 8;
        panel.add(zipField, gbc);

        /*
         * Role Selector
         */
        JLabel roleLabel = new JLabel("Role:");
        gbc.gridx = 0;
        gbc.gridy = 9;
        panel.add(roleLabel, gbc);

        JComboBox<String> roleComboBox = new JComboBox<>(new String[]{"Admin", "Staff"});
        gbc.gridx = 1;
        gbc.gridy = 9;
        panel.add(roleComboBox, gbc);

        /*
         * Buttons
         */
        JButton enterButton = new JButton("Enter");
        gbc.gridx = 0;
        gbc.gridy = 10;
        panel.add(enterButton, gbc);

        JButton backButton = new JButton("Back");
        gbc.gridx = 1;
        gbc.gridy = 10;
        panel.add(backButton, gbc);

        BootLoader.panelContainer.add(panel, "add user");
        BootLoader.cardLayout.show(BootLoader.panelContainer, "add user");
        BootLoader.loginFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);

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
                JOptionPane.showMessageDialog(null, "Error: All fields must be filled.");
                return;
            }

            String memberID = addNewUser(new Object[]{firstName, lastName, email, phone, street, city, state, zip, role});
            if (memberID != null) {
                alert_init(memberID);
            } else {
                JOptionPane.showMessageDialog(null, "Error: Failed to add user. Please check database connectivity.");
            }
        });

        backButton.addActionListener(e -> MainPage.backToMain());
    }


    public static void alert_init(String memberID) {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel textLabel = new JLabel("This user's Member ID is " + memberID);
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(textLabel, gbc);

        JButton nextButton = new JButton("Next");
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(nextButton, gbc);

        BootLoader.panelContainer.add(panel, "user alert panel");
        BootLoader.cardLayout.show(BootLoader.panelContainer, "user alert panel");
        BootLoader.loginFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);

        nextButton.addActionListener(e -> MainPage.backToMain());
        nextButton.addActionListener(e -> MainPage.backToMain());
    }
}
