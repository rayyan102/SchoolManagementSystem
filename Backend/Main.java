package Backend;

import java.sql.Connection;

public class Main {

    public static void main(String[] args) {

        // Admin Login Testing
        UserDAO userDAO = new UserDAO();

        User user = userDAO.login(
            "rayyan@test.com",
            "Test123"
        );

        if (user != null) {

            System.out.println("Login Successful!");

            if (user.getUserRole().equals("ADMIN")) {

                AdminDao adminDAO = new AdminDao();

                Admin admin = adminDAO.getAdminByUserId(user.getUserId());

                if (admin != null) {

                    System.out.println("\n===== ADMIN DASHBOARD =====");
                    System.out.println("Admin ID: " + admin.getAdminId());
                    System.out.println("Admin Name: " + admin.getFullName());
                    System.out.println("User ID: " + admin.getUserId());

                } else {
                    System.out.println("Admin Details Not Found!");
                }

            } else {
                System.out.println("This account is not an Admin!");
            }

        } else {
            System.out.println("Invalid Email or Password!");
        }


        // // Teacher DAO Testing
        // TeacherDao teacherDao = new TeacherDao();

        // boolean result = teacherDao.registerTeacher(
        //     "Mudassir",
        //     "0300129874",
        //     "Mudassir@test.com",
        //     "Test321"
        // );

        // if (result) {
        //     System.out.println("Teacher Data Saved Successfully!");
        // } else {
        //     System.out.println("Teacher Registration Failed!");
        // }

        // Student TESTING
        StudentDAO studentDAO = new StudentDAO();
        boolean studentResult  = studentDAO.registerStudent(
        "Bilal",
        "Male",
        "2010-05-16",
        21,
        "Bilal@test.com",
        "Test4556"
                                                            );

        if (studentResult ) {
            System.out.println("Student Data Saved Successfully!");
                            } 
        else {
            System.out.println("Student Registration Failed!");
            }

        // Database Connection Testing
        Connection con = DataBaseConnection.getConnection();

        if (con != null) {

            System.out.println("\nAHSS Database is Ready!");

            try {
                con.close();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
