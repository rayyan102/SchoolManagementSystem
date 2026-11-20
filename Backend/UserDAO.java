package Backend;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {
  
public User login(String email, String password) {

    String sql = "SELECT * FROM users WHERE email = ? AND pass = ?";

    try (Connection con = DataBaseConnection.getConnection()) {

        if (con == null) {
            return null;
        }

        try (PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, email);
            ps.setString(2, password);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {

                    int userId = rs.getInt("USER_ID");
                    String userEmail = rs.getString("EMAIL");
                    String userPassword = rs.getString("PASS");
                    String userRole = rs.getString("USER_ROLE");

                    return new User(
                        userId,
                        userEmail,
                        userPassword,
                        userRole
                    );
                }
            }
        }

    } catch (SQLException e) {

        System.out.println("Login Failed!");
        System.out.println(e.getMessage());
    }

    return null;
}

}
