import java.awt.Font;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.*;
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
    public static void checkin_init() {
        JPanel panel = new JPanel();
        panel.setLayout(null);

        JLabel title = new JLabel("Member Check-In");
        JLabel subTitle = new JLabel("Please enter member ID. Use Search Member if ID is unknown.");
        JLabel errorTitle = new JLabel();
        JButton enterB = new JButton("Enter");
        JButton backB = new JButton("Back");
        JTextArea inputArea = new JTextArea();

        // Set bounds
        title.setBounds(120, 30, 200, 25);
        subTitle.setBounds(35, 70, 300, 25);
        subTitle.setFont(new Font("Arial", Font.PLAIN, 10));
        inputArea.setBounds(50, 110, 250, 30);
        backB.setBounds(50, 160, 100, 30);
        enterB.setBounds(170, 160, 100, 30);
        errorTitle.setBounds(35, 200, 300, 25);

        // Add components
        panel.add(title);
        panel.add(subTitle);
        panel.add(errorTitle);
        panel.add(inputArea);
        panel.add(enterB);
        panel.add(backB);

        BootLoader.panelContainer.add(panel, "checkin panel");
        BootLoader.cardLayout.show(BootLoader.panelContainer, "checkin panel");
        BootLoader.loginFrame.setSize(400, 400);

        // Action listeners
        backB.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        inputArea.setText("");
                        errorTitle.setText("");
                        MainPage.backToMain();
                    }
                }
        );

        enterB.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String response = checkin(inputArea.getText());
                        if (response.equals("success")) {
                            inputArea.setText("");
                            showConfirmation("Check-in complete! Have a great workout.");
                        } else if (response.equals("alert")) {
                            alert_init();
                        } else {
                            errorTitle.setText(response);
                        }
                    }
                }
        );
    }

    /**
     * Displays the alert GUI for memberships expiring within 30 days.
     */
    public static void alert_init() {
        JPanel panel = new JPanel();
        panel.setLayout(null);

        JLabel textLabel = new JLabel("ALERT: This member's membership expires within 30 days.");
        textLabel.setBounds(25, 50, 350, 25);

        JButton nextButton = new JButton("Next");
        nextButton.setBounds(150, 100, 100, 30);

        panel.add(textLabel);
        panel.add(nextButton);
        BootLoader.panelContainer.add(panel, "alert panel");
        BootLoader.cardLayout.show(BootLoader.panelContainer, "alert panel");
        BootLoader.loginFrame.setSize(400, 400);

        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainPage.backToMain();
            }
        });
    }

    /**
     * Displays a confirmation message after a successful check-in.
     *
     * @param message The confirmation message to display.
     */
    public static void showConfirmation(String message) {
        JPanel panel = new JPanel();
        panel.setLayout(null);

        JLabel textLabel = new JLabel(message);
        textLabel.setBounds(25, 50, 350, 25);

        JButton nextButton = new JButton("Next");
        nextButton.setBounds(150, 100, 100, 30);

        panel.add(textLabel);
        panel.add(nextButton);
        BootLoader.panelContainer.add(panel, "confirmation panel");
        BootLoader.cardLayout.show(BootLoader.panelContainer, "confirmation panel");
        BootLoader.loginFrame.setSize(400, 400);

        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainPage.backToMain();
            }
        });
    }
}
