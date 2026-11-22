package Backend;

import java.sql.Connection;

public class Main {

    public static void main(String[] args) {
        // UserDAO
            UserDAO userDAO = new UserDAO();

            User user = userDAO.login(
                "rayyan@test.com",
             "Test123"
                                    );

    if (user != null) {

    System.out.println("Login Successful!");
    System.out.println("User ID: " + user.getUserId());
    System.out.println("Email: " + user.getEmail());
    System.out.println("Role: " + user.getUserRole());

                } 
        else  {
    System.out.println("Invalid Email or Password!");
        }


        // // Admin Testing
        // Admin admin1 = new Admin(1, "Rayyan", 101);
        // admin1.displayAdmin();

        // admin1.setFullName("Daniyal");

        // System.out.println("\nAfter Updating Name:");
        // admin1.displayAdmin();

        // // Admin Registration Testing

        // AdminDao adminDAO = new AdminDao();

        // boolean result = adminDAO.registerAdmin(
        //             "Rayyan",
        //             "rayyan@test.com",
        //             "Test123"
        //                                     );

        // if (result) {
        //     System.out.println("Admin Registration Test Successful!");
        //             } 
        // else {
        //     System.out.println("Admin Registration Test Failed!");
        //     }

        // // Teacher Testing
        // Teacher t1 = new Teacher(1, "LEO", "03001234567", new String[]{"Active"});
        // Teacher t2 = new Teacher(2, "MUDA", "03111234567", new String[]{"Not Active"});

        // t1.display();
        // System.out.println("-------------------");
        // t2.display();

        // // Student Testing
        // Student s1 = new Student(1, "Daniyal", new String[]{"Male"},
        //         "06-November-2007", "15-august-2025");

        // s1.displayStudent();

        // System.out.println("==================================================");

        // Student s2 = new Student(2, "Aisha", new String[]{"Female"},
        //         "15-October-2005", "15-may-2023");

        // s2.displayStudent();
        //teacher testing
        // TeacherDao dao = new TeacherDao();

        // // 1. Teacher register karo
        // dao.registerTeacher("Ali Khan", "03001234567", "ali@ahss.com", "1234");

        // // 2. Sab teachers dekho
        // List<Teacher> list = dao.getAllTeachers();
        // for (Teacher t : list) {
        //     t.display();
        //     System.out.println("-------------------");
        // }

        // // 3. Ek teacher id se dekho (id 1 maan lo)
        // Teacher t1 = dao.getTeacherById(1);
        // if (t1 != null) {
        //     t1.display();
        // }

        // // 4. Assigned classes dekho
        // List<String> classes = dao.getAssignedClasses(1);
        // for (String c : classes) {
        //     System.out.println(c);
        // }

        // // 5. Teacher remove karo (status LEFT)
        // dao.removeTeacher(1);
        // dao.getTeacherById(1).display(); 

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
