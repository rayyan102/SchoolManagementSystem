package Backend;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TeacherDao {
    
    // Database mein status ACTIVE / LEFT hota hai, Teacher class mein Active / Not Active
    private String[] toStatusArray(String dbStatus) {
        if ("ACTIVE".equalsIgnoreCase(dbStatus)) {
            return new String[]{"Active"};
        }
        return new String[]{"Not Active"};
    }

    // Row se Teacher object banana
    private Teacher mapRow(ResultSet rs) throws SQLException {
        return new Teacher(
                rs.getInt("teacher_id"),
                rs.getString("full_name"),
                rs.getString("phone"),
                toStatusArray(rs.getString("status"))
        );
    }

    // 1. Teacher register karna (Admin): users table + teacher table
    public boolean registerTeacher(String fullName, String phone, String email, String password) {

        String userSQL = "INSERT INTO users (email, pass, user_role) VALUES (?, ?, 'TEACHER')";

        String teacherSQL = "INSERT INTO teacher (full_name, phone, status, user_id) "
                          + "VALUES (?, ?, 'ACTIVE', ?)";

        try (Connection con = DataBaseConnection.getConnection()) {

            if (con == null) {
                return false;
            }

            con.setAutoCommit(false);

            try {

                int userId;

                // Step 1: users table mein insert
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

                // Step 2: teacher table mein insert
                try (PreparedStatement ps = con.prepareStatement(teacherSQL)) {

                    ps.setString(1, fullName);
                    ps.setString(2, phone);
                    ps.setInt(3, userId);

                    ps.executeUpdate();
                }

                // Step 3: dono records save
                con.commit();

                System.out.println("Teacher Registered Successfully!");

                return true;

            } catch (SQLException e) {

                con.rollback();
                throw e;
            }

        } catch (SQLException e) {

            System.out.println("Teacher Registration Failed!");
            System.out.println(e.getMessage());

            return false;
        }
    }

    // 2. Teacher remove karna (delete nahi, status LEFT)
    public boolean removeTeacher(int teacherId) {

        String sql = "UPDATE teacher SET status = 'LEFT' WHERE teacher_id = ?";

        try (Connection con = DataBaseConnection.getConnection()) {

            if (con == null) {
                return false;
            }

            try (PreparedStatement ps = con.prepareStatement(sql)) {

                ps.setInt(1, teacherId);

                boolean done = ps.executeUpdate() > 0;

                if (done) {
                    System.out.println("Teacher Removed Successfully!");
                }

                return done;
            }

        } catch (SQLException e) {

            System.out.println("Teacher Remove Failed!");
            System.out.println(e.getMessage());

            return false;
        }
    }

    // 3. Teacher ko id se dhundna
    public Teacher getTeacherById(int teacherId) {

        String sql = "SELECT teacher_id, full_name, phone, status FROM teacher WHERE teacher_id = ?";

        try (Connection con = DataBaseConnection.getConnection()) {

            if (con == null) {
                return null;
            }

            try (PreparedStatement ps = con.prepareStatement(sql)) {

                ps.setInt(1, teacherId);

                try (ResultSet rs = ps.executeQuery()) {

                    if (rs.next()) {
                        return mapRow(rs);
                    }
                }
            }

        } catch (SQLException e) {

            System.out.println(e.getMessage());
        }

        return null;
    }

    // 4. Login ke baad user_id se teacher dhundna (dashboard ke liye)
    public Teacher getTeacherByUserId(int userId) {

        String sql = "SELECT teacher_id, full_name, phone, status FROM teacher WHERE user_id = ?";

        try (Connection con = DataBaseConnection.getConnection()) {

            if (con == null) {
                return null;
            }

            try (PreparedStatement ps = con.prepareStatement(sql)) {

                ps.setInt(1, userId);

                try (ResultSet rs = ps.executeQuery()) {

                    if (rs.next()) {
                        return mapRow(rs);
                    }
                }
            }

        } catch (SQLException e) {

            System.out.println(e.getMessage());
        }

        return null;
    }

    // 5. Sab teachers dekhna (Admin)
    public List<Teacher> getAllTeachers() {

        List<Teacher> list = new ArrayList<>();

        String sql = "SELECT teacher_id, full_name, phone, status FROM teacher ORDER BY teacher_id";

        try (Connection con = DataBaseConnection.getConnection()) {

            if (con == null) {
                return list;
            }

            try (PreparedStatement ps = con.prepareStatement(sql);
                 ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {
                    list.add(mapRow(rs));
                }
            }

        } catch (SQLException e) {

            System.out.println(e.getMessage());
        }

        return list;
    }

    // 6. Teacher ki assigned classes aur subjects (Teacher dashboard)
    public List<String> getAssignedClasses(int teacherId) {

        List<String> list = new ArrayList<>();

        String sql = "SELECT c.class_name, s.subject_name "
                   + "FROM teacher_assignment ta "
                   + "JOIN classes c ON ta.class_id = c.class_id "
                   + "JOIN subject s ON ta.subject_id = s.subject_id "
                   + "WHERE ta.teacher_id = ?";

        try (Connection con = DataBaseConnection.getConnection()) {

            if (con == null) {
                return list;
            }

            try (PreparedStatement ps = con.prepareStatement(sql)) {

                ps.setInt(1, teacherId);

                try (ResultSet rs = ps.executeQuery()) {

                    while (rs.next()) {
                        list.add(rs.getString("class_name") + " - " + rs.getString("subject_name"));
                    }
                }
            }

        } catch (SQLException e) {

            System.out.println(e.getMessage());
        }

        return list;
    }

}
