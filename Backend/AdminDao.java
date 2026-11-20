package Backend;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
public class AdminDao {
  
public boolean registerAdmin(String fullName, String email, String password) {

    String userSQL = "INSERT INTO users (email, pass, user_role) VALUES (?, ?, 'ADMIN')";

    String adminSQL = "INSERT INTO admin (full_name, user_id) VALUES (?, ?)";

    try (Connection con = DataBaseConnection.getConnection()) {

        if (con == null) {
            return false;
        }

        con.setAutoCommit(false);

        try {

            int userId;

            // Step 1: Insert into users table
            try (PreparedStatement ps = con.prepareStatement(
                    userSQL, new String[]{"USER_ID"})) {

                ps.setString(1, email);
                ps.setString(2, password);

                ps.executeUpdate();

                try (ResultSet rs = ps.getGeneratedKeys()) {

                    if (rs.next()) {
                        userId = rs.getInt(1);
                    } else {
                        throw new SQLException("User ID not generated!");
                    }
                }
            }

            // Step 2: Insert into admin table
            try (PreparedStatement ps = con.prepareStatement(adminSQL)) {

                ps.setString(1, fullName);
                ps.setInt(2, userId);

                ps.executeUpdate();
            }

            // Step 3: Save both records
            con.commit();

            System.out.println("Admin Registered Successfully!");

            return true;

        } catch (SQLException e) {

            con.rollback();
            throw e;
        }

    } catch (SQLException e) {

        System.out.println("Admin Registration Failed!");
        System.out.println(e.getMessage());

        return false;
    }
}

}
