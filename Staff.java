import java.awt.event.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;
import javax.swing.*;

public class Staff {

    /*
     *
     *
     *
     *
     * team code
     *
     * return "" if correct, else return a string describing error
     *
     */

    public static String addMemberCheck(String firstName, String lastName, String street, String city, String state, String zip, String email, String phone, String membershipLength) {
        // Print values to trace input
        System.out.println("Validating input...");
        System.out.println("First Name: " + firstName);
        System.out.println("Last Name: " + lastName);
        System.out.println("Street: " + street);
        System.out.println("City: " + city);
        System.out.println("State: " + state);
        System.out.println("Zip: " + zip);
        System.out.println("Email: " + email);
        System.out.println("Phone: " + phone);
        System.out.println("Membership Length: " + membershipLength);

        // Validate inputs
        if (firstName.isEmpty()) {
            return "First name is missing.";
        }
        if (lastName.isEmpty()) {
            return "Last name is missing.";
        }
        if (street.isEmpty() || city.isEmpty() || state.isEmpty() || zip.isEmpty()) {
            return "One or more address fields are missing.";
        }

        // Additional validations
        if (!email.contains("@")) {
            return "Email format is incorrect.";
        }
        if (!phone.matches("\\(\\d{3}\\)-\\d{3}-\\d{4}")) {
            return "Phone number format is incorrect.";
        }
        if (!membershipLength.matches("\\d+(\\.\\d{1})?") || Double.parseDouble(membershipLength) <= 0) {
            return "Membership length is invalid.";
        }

        // If everything is valid, return an empty string
        return "";
    }




    /*
     *
     * team code
     *
     * add new member given this information
     *
     *
     */
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
            System.out.println("New member added successfully!");

        } catch (SQLException e) {
            System.out.println("Error adding new member.");
            e.printStackTrace();
        }
    }


    /*
     *
     *
     *
     *
     * GUI code (final)
     *
     *
     *
     */
    public static void addMember_init() {
        /*
         * Main panel
         */
        JPanel panel = new JPanel();
        panel.setLayout(null); // Using absolute positioning with setBounds()

        /*
         * Title
         */
        JLabel titleLabel = new JLabel("Add Member");
        titleLabel.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 20));
        titleLabel.setBounds(175, 10, 200, 30); // Position and size
        panel.add(titleLabel);

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
        JLabel phoneLabel = new JLabel("Phone Number (xxx)-xxx-xxxx:");
        phoneLabel.setBounds(20, 170, 250, 25); // Adjusted to avoid overlap
        panel.add(phoneLabel);

        JTextField phoneField = new JTextField();
        phoneField.setBounds(230, 170, 200, 25); // Adjusted to avoid overlap
        panel.add(phoneField);

        /*
         * Address
         */


        JLabel streetLabel = new JLabel("Address :");
        streetLabel.setBounds(20, 250, 200, 25);
        panel.add(streetLabel);

        JTextField streetField = new JTextField();
        streetField.setBounds(230, 250, 200, 25);
        panel.add(streetField);

        JLabel cityLabel = new JLabel("City, EX. Chicago:");
        cityLabel.setBounds(20, 290, 200, 25);
        panel.add(cityLabel);

        JTextField cityField = new JTextField();
        cityField.setBounds(230, 290, 200, 25);
        panel.add(cityField);

        JLabel stateLabel = new JLabel("State, EX: IL:");
        stateLabel.setBounds(20, 330, 200, 25);
        panel.add(stateLabel);

        JTextField stateField = new JTextField();
        stateField.setBounds(230, 330, 200, 25);
        panel.add(stateField);

        JLabel zipLabel = new JLabel("ZIP Code, EX. 60626:");
        zipLabel.setBounds(20, 370, 200, 25);
        panel.add(zipLabel);

        JTextField zipField = new JTextField();
        zipField.setBounds(230, 370, 200, 25);
        panel.add(zipField);

        JLabel errorLabel = new JLabel("");
        errorLabel.setBounds(200, 450, 200, 25);
        panel.add(errorLabel);

        /*
         * Membership Length
         */
        JLabel membershipLabel = new JLabel("Membership length by year, {0.5,1,2}:");
        membershipLabel.setBounds(20, 410, 200, 25);
        panel.add(membershipLabel);

        JTextField membershipField = new JTextField();
        membershipField.setBounds(230, 410, 200, 25);
        panel.add(membershipField);

        /*
         * Buttons
         */
        JButton backButton = new JButton("Back");
        backButton.setBounds(100, 500, 100, 30);
        panel.add(backButton);

        JButton enterButton = new JButton("Enter");
        enterButton.setBounds(250, 500, 100, 30);
        panel.add(enterButton);

        /*
         * Load panel
         */
        BootLoader.panelContainer.add(panel, "addMember");
        BootLoader.cardLayout.show(BootLoader.panelContainer, "addMember");
        BootLoader.loginFrame.setSize(500, 650); // Adjusted height for new fields

        /*
         * Action listeners
         */
        backButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        zipField.setText("");
                        stateField.setText("");
                        streetField.setText("");
                        cityField.setText("");
                        errorLabel.setText("");
                        MainPage.backToMain();
                    }
                }
        );

        enterButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        // Get user input
                        String firstName = firstNameField.getText();
                        String lastName = lastNameField.getText();
                        String email = emailField.getText();
                        String phone = phoneField.getText();
                        String zip = zipField.getText();
                        String state = stateField.getText();
                        String street = streetField.getText();
                        String city = cityField.getText();
                        String membershipLength = membershipField.getText();

                        // Validate inputs
                        String checkBol = addMemberCheck(firstName, lastName, street, city, state, zip, email, phone, membershipLength);

                        if (checkBol.equals("")) {
                            // Generate random member ID
                            Random rand = new Random();
                            int userID = rand.nextInt(50000);
                            String memberID = String.format("%05d", userID); // Zero-pad to 5 digits

                            // Add new member to the database
                            addNewMember(firstName, lastName, street, city, state, zip, memberID, email, phone, membershipLength);

                            // Show confirmation
                            alert_init(memberID);
                        } else {
                            // Display error message
                            errorLabel.setText(checkBol);
                        }
                    }
                }
        );

    }


    public static void alert_init(String memberID) {
        /*
         * JPanel
         */
        JPanel panel = new JPanel();
        panel.setLayout(null);

        // Add a label with text
        JLabel textLabel = new JLabel("This member's memberID is " + memberID);
        textLabel.setBounds(25, 50, 350, 25);
        /*
         * button
         */
        JButton nextButton = new JButton("Next");
        nextButton.setBounds(150, 100, 100, 30);
        /*
         * add
         */
        panel.add(textLabel);
        panel.add(nextButton);
        BootLoader.panelContainer.add(panel, "alert panel");
        BootLoader.cardLayout.show(BootLoader.panelContainer, "alert panel");
        BootLoader.loginFrame.setSize(400, 400);
        /*
         * action listeners
         */
        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainPage.backToMain();
            }
        });

    }
    public static boolean isMemberPresent(String memberID) {
        String sql = "SELECT COUNT(*) FROM members WHERE member_id = ?";
        /*
         * 
         * 
         * This is the only code i changed in Staff to give me admin perms to login and work with frontend feel free to change
         * 
         * 
         */
        if (memberID.equals("admin")) {//checks for admin login
            return true; //run as admin
        }

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, memberID);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    int count = rs.getInt(1);
                    return count > 0; // Return true if at least one match is found
                }
            }
        } catch (SQLException e) {
            System.out.println("Error while checking member presence.");
            e.printStackTrace();
        }

        return false; // Default to false if there's an error
    }
    public static boolean isMemberPresentName(String name) {
        String sql = "SELECT COUNT(*) FROM members WHERE name = ?";

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, name);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    int count = rs.getInt(1);
                    return count > 0; // Return true if at least one match is found
                }
            }
        } catch (SQLException e) {
            System.out.println("Error while checking member presence.");
            e.printStackTrace();
        }

        return false; // Default to false if there's an error
    }


}
