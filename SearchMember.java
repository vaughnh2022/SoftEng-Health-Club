import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SearchMember extends Staff {


    /**
     * Finds all members by their member ID, first name, or last name and returns their details in a table-compatible format.
     */
    public static DefaultTableModel findMembers(String text) {
        String sql = "SELECT member_id, first_name, last_name, email, phone, membership_length, last_check_in " +
                "FROM members " +
                "WHERE member_id = ? OR first_name = ? OR last_name = ?";

        String[] columnNames = {"Member ID", "First Name", "Last Name", "Email", "Phone", "Membership Length", "Last Check-in"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, text);
            pstmt.setString(2, text);
            pstmt.setString(3, text);

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Object[] memberDetails = extractMemberDetails(rs);
                    tableModel.addRow(memberDetails);
                }
            }

        } catch (SQLException e) {
            System.out.println("Error while searching for members.");
            e.printStackTrace();
        }

        return tableModel.getRowCount() > 0 ? tableModel : null;
    }

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
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Padding around components
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Title
        JLabel title = new JLabel("Member Search", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 18));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2; // Span across two columns
        panel.add(title, gbc);

        // Subtitle
        JLabel subTitle = new JLabel("Please enter either member ID, first name, or last name:", SwingConstants.CENTER);
        subTitle.setFont(new Font("Arial", Font.PLAIN, 12));
        gbc.gridy = 1;
        panel.add(subTitle, gbc);

        // Input Label
        JLabel inputLabel = new JLabel("Search Input:");
        inputLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1; // Single column
        panel.add(inputLabel, gbc);

        // Input Field
        JTextField inputField = new JTextField(20); // Set a consistent size
        gbc.gridx = 1;
        gbc.gridy = 2;
        panel.add(inputField, gbc);

        // Buttons
        JButton backB = new JButton("Back");
        gbc.gridx = 0;
        gbc.gridy = 3;
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

        BootLoader.panelContainer.add(panel, "search panel");
        BootLoader.cardLayout.show(BootLoader.panelContainer, "search panel");
        BootLoader.loginFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);

        // Action listeners
        enterB.addActionListener(e -> {
            DefaultTableModel memberTableModel = findMembers(inputField.getText().trim());
            if (memberTableModel == null) {
                errorTitle.setText("No members found for the given input.");
            } else {
                showMemberDetails(memberTableModel);
            }
        });

        backB.addActionListener(e -> MainPage.backToMain());
    }

    /**
     * Displays all matching members' information in a JTable in a new panel.
     */
    public static void showMemberDetails(DefaultTableModel memberTableModel) {
        JPanel panel = new JPanel(new BorderLayout());

        // Create the table with the model
        JTable table = new JTable(memberTableModel);
        JScrollPane scrollPane = new JScrollPane(table);

        // Back Button
        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> MainPage.backToMain());

        // Add components to the panel
        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(backButton, BorderLayout.SOUTH);

        BootLoader.panelContainer.add(panel, "details panel");
        BootLoader.cardLayout.show(BootLoader.panelContainer, "details panel");
        BootLoader.loginFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);

    }
}
