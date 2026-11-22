import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class StudentDAO {

    // converting M/F from database to Male/Female from java  
    private String[] toGenderArray(String dbGender) {

        if ("M".equalsIgnoreCase(dbGender)) {
            return new String[]{"Male"};
        }

        return new String[]{"Female"};
    }

    // creating student object from row 
    private Student mapRow(ResultSet rs) throws SQLException {

        return new Student(
                rs.getInt("student_id"),
                rs.getString("full_name"),
                toGenderArray(rs.getString("gender")),
                rs.getDate("date_of_birth") != null
                    ? rs.getDate("date_of_birth").toString() : "",
                rs.getDate("admission_date") != null
                    ? rs.getDate("admission_date").toString() : ""
        );
    }

    //  student registration (admin)

    public boolean registerStudent(String fullName, String gender, String dob, int classId, String email, String password) {

        String userSQL = "INSERT INTO users (email, pass, user_role) " + "VALUES (?, ?, 'STUDENT')";

        String studentSQL = "INSERT INTO student " + "(full_name, gender, date_of_birth, class_id, user_id) "
                          + "VALUES (?, ?, ?, ?, ?)";

        try (Connection con = DataBaseConnection.getConnection()) {

            if (con == null) {
                return false;
            }

            con.setAutoCommit(false);

            try {

                int userId;

                //insertion in user
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

                // insertion in student
                try (PreparedStatement ps = con.prepareStatement(studentSQL)) {

                    ps.setString(1, fullName);

                    // Gender M/F conversion
                    if (gender.equalsIgnoreCase("Male")) {
                        ps.setString(2, "M");
                    } else if (gender.equalsIgnoreCase("Female")) {
                        ps.setString(2, "F");
                    } else {
                        throw new SQLException("Invalid Gender!");
                    }

                    ps.setDate(3, java.sql.Date.valueOf(dob));
                    ps.setInt(4, classId);
                    ps.setInt(5, userId);

                    ps.executeUpdate();
                }

                con.commit();

                System.out.println("Student Registered Successfully!");

                return true;

            } catch (SQLException e) {

                con.rollback();
                throw e;
            }
        }

        catch (SQLException e) {

            System.out.println("Student Registration Failed!");
            System.out.println(e.getMessage());

            return false;
        }
    }


    // searching stdent through student id

    public Student getStudentById(int studentId) {

        String sql = "SELECT student_id, full_name, gender, " + "date_of_birth, admission_date "
                   + "FROM student WHERE student_id = ?";

        try (Connection con = DataBaseConnection.getConnection()) {

            if (con == null) {
                return null;
            }

            try (PreparedStatement ps = con.prepareStatement(sql)) {

                ps.setInt(1, studentId);

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


    // seraching student through user id after login

    public Student getStudentByUserId(int userId) {

        String sql = "SELECT student_id, full_name, gender, " + "date_of_birth, admission_date "
                   + "FROM student WHERE user_id = ?";

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


    // viewing all students (only admin)

    public List<Student> getAllStudents() {

        List<Student> list = new ArrayList<>();

        String sql = "SELECT student_id, full_name, gender, " + "date_of_birth, admission_date "
                   + "FROM student ORDER BY student_id";

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
}
