import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;
import javax.swing.*;

public class Staff {

    public static String addMemberCheck(String firstName, String lastName, String street, String city, String state, String zip, String email, String phone, String membershipLength) {
        if (firstName.isEmpty()) return "First name is missing.";
        if (lastName.isEmpty()) return "Last name is missing.";
        if (street.isEmpty() || city.isEmpty() || state.isEmpty() || zip.isEmpty()) return "One or more address fields are missing.";
        if (!email.contains("@")) return "Email format is incorrect.";
        if (!phone.matches("\\(\\d{3}\\)-\\d{3}-\\d{4}")) return "Phone number format is incorrect.";
        if (!membershipLength.matches("\\d+(\\.\\d{1})?") || Double.parseDouble(membershipLength) <= 0) return "Membership length is invalid.";
        return "";
    }

    public static void addNewMember(String firstName, String lastName, String street, String city, String state, String zip, String memberID, String email, String phone, String membershipLength) {
        String sql = "INSERT INTO members (member_id, first_name, last_name, email, phone, street, city, state, zip, membership_length) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, memberID);
            pstmt.setString(2, firstName);
            pstmt.setString(3, lastName);
            pstmt.setString(4, email);
            pstmt.setString(5, phone);
            pstmt.setString(6, street);
            pstmt.setString(7, city);
            pstmt.setString(8, state);
            pstmt.setString(9, zip);
            pstmt.setString(10, membershipLength);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void addMember_init() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Padding around components
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Title
        JLabel titleLabel = new JLabel("Add Member", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(titleLabel, gbc);

        // First Name
        gbc.gridwidth = 1;
        gbc.gridy = 1;
        gbc.gridx = 0;
        panel.add(new JLabel("First Name:"), gbc);

        JTextField firstNameField = new JTextField(20);
        gbc.gridx = 1;
        panel.add(firstNameField, gbc);

        // Last Name
        gbc.gridy = 2;
        gbc.gridx = 0;
        panel.add(new JLabel("Last Name:"), gbc);

        JTextField lastNameField = new JTextField(20);
        gbc.gridx = 1;
        panel.add(lastNameField, gbc);

        // Email
        gbc.gridy = 3;
        gbc.gridx = 0;
        panel.add(new JLabel("Email:"), gbc);

        JTextField emailField = new JTextField(20);
        gbc.gridx = 1;
        panel.add(emailField, gbc);

        // Phone
        gbc.gridy = 4;
        gbc.gridx = 0;
        panel.add(new JLabel("Phone (e.g., (xxx)-xxx-xxxx):"), gbc);

        JTextField phoneField = new JTextField(20);
        gbc.gridx = 1;
        panel.add(phoneField, gbc);

        // Address
        gbc.gridy = 5;
        gbc.gridx = 0;
        panel.add(new JLabel("Street:"), gbc);

        JTextField streetField = new JTextField(20);
        gbc.gridx = 1;
        panel.add(streetField, gbc);

        gbc.gridy = 6;
        gbc.gridx = 0;
        panel.add(new JLabel("City:"), gbc);

        JTextField cityField = new JTextField(20);
        gbc.gridx = 1;
        panel.add(cityField, gbc);

        gbc.gridy = 7;
        gbc.gridx = 0;
        panel.add(new JLabel("State (e.g., New York): "), gbc);

        JTextField stateField = new JTextField(20);
        gbc.gridx = 1;
        panel.add(stateField, gbc);

        gbc.gridy = 8;
        gbc.gridx = 0;
        panel.add(new JLabel("ZIP Code (e.g., 60659): "), gbc);

        JTextField zipField = new JTextField(20);
        gbc.gridx = 1;
        panel.add(zipField, gbc);

        // Membership Length
        gbc.gridy = 9;
        gbc.gridx = 0;
        panel.add(new JLabel("Membership Length:"), gbc);

        JComboBox<String> membershipDropdown = new JComboBox<>(new String[]{"0.5 (Half Year)", "1 (One Year)", "2 (Two Years)"});
        gbc.gridx = 1;
        panel.add(membershipDropdown, gbc);

        // Buttons
        gbc.gridy = 10;
        gbc.gridx = 0;
        JButton backButton = new JButton("Back");
        panel.add(backButton, gbc);

        gbc.gridx = 1;
        JButton enterButton = new JButton("Enter");
        panel.add(enterButton, gbc);

        // Error Label
        JLabel errorLabel = new JLabel("", SwingConstants.CENTER);
        errorLabel.setForeground(Color.RED);
        gbc.gridy = 11;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        panel.add(errorLabel, gbc);

        BootLoader.panelContainer.add(panel, "addMember");
        BootLoader.cardLayout.show(BootLoader.panelContainer, "addMember");
        BootLoader.loginFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);


        // Action listeners
        backButton.addActionListener(e -> MainPage.backToMain());
        enterButton.addActionListener(e -> {
            String firstName = firstNameField.getText().trim();
            String lastName = lastNameField.getText().trim();
            String email = emailField.getText().trim();
            String phone = phoneField.getText().trim();
            String street = streetField.getText().trim();
            String city = cityField.getText().trim();
            String state = stateField.getText().trim();
            String zip = zipField.getText().trim();
            String membershipLength = membershipDropdown.getSelectedItem().toString().split(" ")[0];

            String validationError = addMemberCheck(firstName, lastName, street, city, state, zip, email, phone, membershipLength);
            if (!validationError.isEmpty()) {
                errorLabel.setText(validationError);
                return;
            }

            String memberID;
            do {
                memberID = String.format("%05d", new Random().nextInt(50000));
            } while (!isMemberIDUnique(memberID));

            addNewMember(firstName, lastName, street, city, state, zip, memberID, email, phone, membershipLength);
            alert_init(memberID);
        });
    }

    public static boolean isMemberIDUnique(String memberID) {
        String sql = "SELECT COUNT(*) FROM members WHERE member_id = ?";

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, memberID);

            try (ResultSet rs = pstmt.executeQuery()) {
                return rs.next() && rs.getInt(1) == 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public static void alert_init(String memberID) {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel textLabel = new JLabel("This member's memberID is " + memberID);
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(textLabel, gbc);

        JButton nextButton = new JButton("Next");
        gbc.gridy = 1;
        panel.add(nextButton, gbc);

        BootLoader.panelContainer.add(panel, "alert panel");
        BootLoader.cardLayout.show(BootLoader.panelContainer, "alert panel");
        BootLoader.loginFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);


        nextButton.addActionListener(e -> MainPage.backToMain());
    }
}
