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
     * Finds all members by their member ID, first name, or last name and returns their details in a table-compatible format.
     *
     * @param text The member ID, first name, or last name to search for.
     * @return A DefaultTableModel containing all matching members' details, or null if no matches are found.
     */
    public static DefaultTableModel findMembers(String text) {
        String sql = "SELECT member_id, first_name, last_name, email, phone, membership_length, last_check_in " +
                "FROM members " +
                "WHERE member_id = ? OR first_name = ? OR last_name = ?";

        String[] columnNames = {"Member ID", "First Name", "Last Name", "Email", "Phone", "Membership Length", "Last Check-in"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // Set the parameters for the query
            pstmt.setString(1, text);
            pstmt.setString(2, text);
            pstmt.setString(3, text);

            try (ResultSet rs = pstmt.executeQuery()) {
                // Add all matching rows to the table model
                while (rs.next()) {
                    Object[] memberDetails = extractMemberDetails(rs);
                    tableModel.addRow(memberDetails);
                }
            }

        } catch (SQLException e) {
            System.out.println("Error while searching for members.");
            e.printStackTrace();
        }

        // If the table model has no rows, return null to indicate no results
        return tableModel.getRowCount() > 0 ? tableModel : null;
    }

    /**
     * Extracts a single member's details from the ResultSet.
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
        BootLoader.loginFrame.setSize(600, 400);

        // Action listeners
        enterB.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        DefaultTableModel memberTableModel = findMembers(inputArea.getText());
                        if (memberTableModel == null) {
                            errorTitle.setText("No members found for the given input.");
                        } else {
                            showMemberDetails(memberTableModel);
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
     * Displays all matching members' information in a JTable in a new panel.
     *
     * @param memberTableModel The table model containing all matching members' details.
     */
    public static void showMemberDetails(DefaultTableModel memberTableModel) {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        // Create the table with the model
        JTable table = new JTable(memberTableModel);
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
        BootLoader.loginFrame.setSize(800, 300); // Adjust the frame size for the table
    }
}
