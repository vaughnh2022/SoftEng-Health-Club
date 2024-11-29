import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CheckIn {

    /**
     * Method to check if a member exists in the database by their member_id.
     *
     * @param memberID The ID of the member to check.
     * @return true if the member exists, false otherwise.
     */
    public static boolean isMemberPresent(String memberID) {
        String sql = "SELECT COUNT(*) FROM members WHERE member_id = ?";

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
}
