import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class SearchMember extends Staff {
    /**
     * Finds a member by their member ID or name and returns their details as an Object array.
     *
     * @param text The member ID or name to search for.
     * @return An Object array containing the member's details, or null if not found.
     */
    /**
     * Finds a member by their member ID or name and returns their details as an Object array.
     *
     * @param text The member ID or name to search for.
     * @return An Object array containing the member's details, or null if not found.
     */
    public static Object[] findMember(String text) {
        String sqlByID = "SELECT member_id, first_name, last_name, email, phone, membership_length, last_check_in FROM members WHERE member_id = ?";
        String sqlByName = "SELECT member_id, first_name, last_name, email, phone, membership_length, last_check_in FROM members WHERE first_name = ? OR last_name = ?";

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmtByID = conn.prepareStatement(sqlByID);
             PreparedStatement pstmtByName = conn.prepareStatement(sqlByName)) {

            // Search by Member ID
            pstmtByID.setString(1, text);
            try (ResultSet rsByID = pstmtByID.executeQuery()) {
                if (rsByID.next()) {
                    return extractMemberDetails(rsByID);
                }
            }

            // Search by Name
            pstmtByName.setString(1, text);
            pstmtByName.setString(2, text);
            try (ResultSet rsByName = pstmtByName.executeQuery()) {
                if (rsByName.next()) {
                    return extractMemberDetails(rsByName);
                }
            }

        } catch (SQLException e) {
            System.out.println("Error while searching for member.");
            e.printStackTrace();
        }

        return null; // Return null if no member found
    }

    /**
     * Extracts the member details from the ResultSet.
     *
     * @param rs The ResultSet containing the member's details.
     * @return An Object array of member details.
     * @throws SQLException If an error occurs while reading the ResultSet.
     */
    private static Object[] extractMemberDetails(ResultSet rs) throws SQLException {
        return new Object[]{
                rs.getString("member_id"),
                rs.getString("first_name"),
                rs.getString("last_name"),
                rs.getString("email"),
                rs.getString("phone"),
                rs.getString("membership_length"),
                rs.getString("last_check_in")
        };
    }

    /**
     * Initializes the search member GUI.
     */
    public static void searchMember_init() {
        JPanel panel = new JPanel();
        panel.setLayout(null);

        JLabel title = new JLabel("Member Search");
        JLabel subTitle = new JLabel("Please enter either member ID, first name, or last name:");
        JLabel errorTitle = new JLabel();
        JButton enterB = new JButton("Enter");
        JButton backB = new JButton("Back");
        JTextArea inputArea = new JTextArea();

        // Set bounds for components
        title.setBounds(120, 30, 200, 25);
        subTitle.setBounds(35, 70, 300, 25);
        subTitle.setFont(new Font("Arial", Font.PLAIN, 10));
        inputArea.setBounds(50, 110, 250, 30);
        backB.setBounds(50, 160, 100, 30);
        enterB.setBounds(170, 160, 100, 30);
        errorTitle.setBounds(35, 200, 300, 25);

        // Add components to the panel
        panel.add(title);
        panel.add(subTitle);
        panel.add(errorTitle);
        panel.add(inputArea);
        panel.add(enterB);
        panel.add(backB);

        BootLoader.panelContainer.add(panel, "search panel");
        BootLoader.cardLayout.show(BootLoader.panelContainer, "search panel");
        BootLoader.loginFrame.setSize(400, 400);

        // Action listeners
        enterB.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        Object[] memberDetails = findMember(inputArea.getText());
                        if (memberDetails == null) {
                            errorTitle.setText("Incorrect input or member not found.");
                        } else {
                            showMemberDetails(memberDetails);
                        }
                    }
                }
        );

        backB.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        MainPage.backToMain();
                    }
                }
        );
    }

    /**
     * Displays the member's information in a JTable in a new panel.
     *
     * @param memberDetails The details of the member to display.
     */
    public static void showMemberDetails(Object[] memberDetails) {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        // Table headers
        String[] columnNames = {"Member ID", "First Name", "Last Name", "Email", "Phone", "Membership Length", "Last Check-in"};

        // Table model
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        tableModel.addRow(memberDetails); // Add the member's details as a row

        JTable table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);

        // Button to go back
        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> MainPage.backToMain());

        // Add components to the panel
        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(backButton, BorderLayout.SOUTH);

        // Update the GUI
        BootLoader.panelContainer.add(panel, "details panel");
        BootLoader.cardLayout.show(BootLoader.panelContainer, "details panel");
        BootLoader.loginFrame.setSize(700, 250); // Adjust the frame size for the table
    }
}
