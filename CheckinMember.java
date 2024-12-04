import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class CheckinMember {

    /**
     * Checks in a member by their ID.
     *
     * @param memberID The ID of the member checking in.
     * @return An appropriate message:
     *         - "success" if check-in is successful.
     *         - "alert" if the membership expires within 30 days.
     *         - A descriptive error message if the input is invalid or the member is not found.
     */
    public static String checkin(String memberID) {
        String sqlFind = "SELECT membership_length, last_check_in FROM members WHERE member_id = ?";
        String sqlUpdate = "UPDATE members SET last_check_in = CURRENT_TIMESTAMP WHERE member_id = ?";

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmtFind = conn.prepareStatement(sqlFind);
             PreparedStatement pstmtUpdate = conn.prepareStatement(sqlUpdate)) {

            // Check if the member exists and fetch their details
            pstmtFind.setString(1, memberID);
            try (ResultSet rs = pstmtFind.executeQuery()) {
                if (rs.next()) {
                    String membershipLength = rs.getString("membership_length");
                    LocalDate today = LocalDate.now();

                    // Update last check-in
                    pstmtUpdate.setString(1, memberID);
                    pstmtUpdate.executeUpdate();

                    // Check if membership expires within 30 days
                    LocalDate expirationDate = today.plusMonths((long) (Double.parseDouble(membershipLength) * 12));
                    if (!expirationDate.isAfter(today.plusDays(30))) {
                        return "alert"; // Membership expires within 30 days
                    }

                    return "success"; // Successful check-in
                } else {
                    return "Member not found.";
                }
            }

        } catch (SQLException e) {
            System.out.println("Error while checking in member.");
            e.printStackTrace();
            return "An error occurred while checking in.";
        } catch (NumberFormatException e) {
            System.out.println("Invalid membership length.");
            return "Invalid membership length in database.";
        }
    }

    /**
     * Initializes the member check-in GUI.
     */
    /**
     * Initializes the member check-in GUI with proper alignment.
     */
    public static void checkin_init() {
        // Create a panel with GridBagLayout for precise alignment
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Padding around components
        gbc.fill = GridBagConstraints.HORIZONTAL; // Fill horizontally

        // Title
        JLabel title = new JLabel("Member Check-In", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 18));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2; // Span across two columns
        panel.add(title, gbc);

        // Subtitle
        JLabel subTitle = new JLabel("Please enter member ID. Use Search Member if ID is unknown.", SwingConstants.CENTER);
        subTitle.setFont(new Font("Arial", Font.PLAIN, 12));
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        panel.add(subTitle, gbc);

        // Input Label
        JLabel inputLabel = new JLabel("Member ID:");
        inputLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1; // Reset to single column
        panel.add(inputLabel, gbc);

        // Input Field
        JTextField inputField = new JTextField(20); // Set consistent size
        gbc.gridx = 1;
        gbc.gridy = 2;
        panel.add(inputField, gbc);

        // Buttons
        JButton backB = new JButton("Back");
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        panel.add(backB, gbc);

        JButton enterB = new JButton("Enter");
        gbc.gridx = 1;
        gbc.gridy = 3;
        panel.add(enterB, gbc);

        // Error Label
        JLabel errorTitle = new JLabel("", SwingConstants.CENTER);
        errorTitle.setFont(new Font("Arial", Font.ITALIC, 12));
        errorTitle.setForeground(Color.RED);
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2; // Span across two columns
        panel.add(errorTitle, gbc);

        // Add panel to BootLoader
        BootLoader.panelContainer.add(panel, "checkin panel");
        BootLoader.cardLayout.show(BootLoader.panelContainer, "checkin panel");
        BootLoader.loginFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);

        // Action listeners
        backB.addActionListener(e -> {
            inputField.setText("");
            errorTitle.setText("");
            MainPage.backToMain();
        });

        enterB.addActionListener(e -> {
            String response = checkin(inputField.getText().trim());
            if (response.equals("success")) {
                inputField.setText("");
                showConfirmation("Check-in complete! Have a great workout.");
            } else if (response.equals("alert")) {
                alert_init();
            } else {
                errorTitle.setText(response);
            }
        });
    }


    /**
     * Displays the alert GUI for memberships expiring within 30 days.
     */
    public static void alert_init() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        // Alert Label
        JLabel textLabel = new JLabel("ALERT: This member's membership expires within 30 days.", SwingConstants.CENTER);
        textLabel.setFont(new Font("Arial", Font.BOLD, 14));
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(textLabel, gbc);

        // Next Button
        JButton nextButton = new JButton("Next");
        gbc.gridy = 1;
        panel.add(nextButton, gbc);

        BootLoader.panelContainer.add(panel, "alert panel");
        BootLoader.cardLayout.show(BootLoader.panelContainer, "alert panel");
        BootLoader.loginFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);

        nextButton.addActionListener(e -> MainPage.backToMain());
    }

    /**
     * Displays a confirmation message after a successful check-in.
     *
     * @param message The confirmation message to display.
     */
    public static void showConfirmation(String message) {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        // Confirmation Label
        JLabel textLabel = new JLabel(message, SwingConstants.CENTER);
        textLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(textLabel, gbc);

        // Next Button
        JButton nextButton = new JButton("Next");
        gbc.gridy = 1;
        panel.add(nextButton, gbc);

        BootLoader.panelContainer.add(panel, "confirmation panel");
        BootLoader.cardLayout.show(BootLoader.panelContainer, "confirmation panel");
        BootLoader.loginFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);


        nextButton.addActionListener(e -> MainPage.backToMain());
    }
}
