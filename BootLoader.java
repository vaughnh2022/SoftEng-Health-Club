import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class BootLoader {

    static final CardLayout cardLayout = new CardLayout();
    public static JPanel panelContainer = new JPanel(cardLayout);
    static JFrame loginFrame;

    // Store the current user's role globally
    public static String currentUserRole = ""; // Will be set during login

    public static void main(String[] args) {
        loginFrame = new JFrame("SoftHealth Login");
        loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loginFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        loginFrame.add(panelContainer);
        bootup_init();
        loginFrame.setVisible(true);
    }

    /**
     * Retrieves the role of the logged-in user from the database.
     *
     * @param memberID The member ID of the user logging in.
     * @return The role of the user (e.g., "Admin", "Staff"), or null if the user doesn't exist.
     */
    public static String getUserRole(String memberID) {
        String sql = "SELECT role FROM Staff WHERE member_id = ?";
        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, memberID);
            System.out.println("Executing query: " + pstmt.toString());

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    String role = rs.getString("role");
                    System.out.println("Role found: " + role);
                    return role; // Return the role if found
                } else {
                    System.out.println("No role found for member ID: " + memberID);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error while retrieving user role.");
            e.printStackTrace();
        }
        return null; // Return null if no role found
    }


    /**
     * Verifies if the member_id exists in the Staff table.
     *
     * @param memberID The member ID to check.
     * @return True if the member ID exists, false otherwise.
     */
    public static boolean isStaffMemberValid(String memberID) {
        return getUserRole(memberID) != null; // Check if a role exists for this member ID
    }

    /**
     * Initializes the login screen.
     */
    public static void bootup_init() {

        JPanel loginCard = new JPanel(new GridBagLayout()); // Use GridBagLayout for centering
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Add padding around components

        JLabel softengTitle = new JLabel("Softeng Health Club Login");
        JLabel subTitle = new JLabel("Please enter memberID:");
        JTextField inputArea = new JTextField(); // Create the text box
        JButton enterB = new JButton("Enter");
        JLabel errorTitle = new JLabel();
        loginFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);

        // Set preferred size for the text box to make it visually balanced
        inputArea.setPreferredSize(new Dimension(200, 30)); // Width = 200, Height = 30

        // Add components to the panel with GridBagConstraints
        gbc.gridx = 0; // Center horizontally
        gbc.gridy = 0; // First row
        loginCard.add(softengTitle, gbc);

        gbc.gridy = 1; // Second row
        loginCard.add(subTitle, gbc);

        gbc.gridy = 2; // Third row
        loginCard.add(inputArea, gbc);

        gbc.gridy = 3; // Fourth row
        loginCard.add(enterB, gbc);

        gbc.gridy = 4; // Fifth row
        loginCard.add(errorTitle, gbc);

        // Add the loginCard panel to the BootLoader container
        panelContainer.add(loginCard, "login panel");
        cardLayout.show(panelContainer, "login panel");

        // Action listener for login button
        enterB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String userID = inputArea.getText();

                // Retrieve the user's role
                String role = getUserRole(userID);

                if (role != null) {
                    currentUserRole = role; // Set the global role for this session
                    MainPage.main_init(userID); // Proceed to the main menu
                } else {
                    errorTitle.setText("Invalid Member ID. Access denied.");
                }
            }
        });
    }
}
