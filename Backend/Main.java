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

// teacherdao all methods testing:
        // TeacherDao dao = new TeacherDao();


        // // Har baar naya email, taake "unique" wali error na aaye
        // String email = "teacher" + System.currentTimeMillis() + "@test.com";

        // // 1. registerTeacher
        // System.out.println("===== 1. registerTeacher =====");
        // boolean result = dao.registerTeacher("Daniyal", "03001234567", email, "Test123");

        // if (result) {
        //     System.out.println("Teacher Data Saved Successfully!");
        // } else {
        //     System.out.println("Teacher Registration Failed!");
        //     return;   // register nahi hua to aage test nahi chalega
        // }

        // // 2. getAllTeachers
        // System.out.println("\n===== 2. getAllTeachers =====");
        // List<Teacher> list = dao.getAllTeachers();

        // if (list.isEmpty()) {
        //     System.out.println("Koi teacher nahi mila");
        //     return;
        // }

        // for (Teacher t : list) {
        //     t.display();
        //     System.out.println("-------------------");
        // }

        // // Ab jo naya teacher bana (list ka aakhri), uski id le lo
        // int teacherId = list.get(list.size() - 1).getId();
        // System.out.println("Test ke liye teacher id: " + teacherId);

        // // 3. getTeacherById
        // System.out.println("\n===== 3. getTeacherById =====");
        // Teacher t1 = dao.getTeacherById(teacherId);

        // if (t1 != null) {
        //     t1.display();
        // } else {
        //     System.out.println("Teacher nahi mila");
        // }

        // // 3b. getTeacherById (galat id, null aana chahiye)
        // Teacher notFound = dao.getTeacherById(99999);
        // if (notFound == null) {
        //     System.out.println("Galat id par null aaya (sahi hai)");
        // }

        // // 4. getTeacherByUserId
        // // Yahan user_id apne database se dekh kar likho (users table mein is teacher ki user_id)
        // System.out.println("\n===== 4. getTeacherByUserId =====");
        // int userId = 1;   // <-- apni asli user_id likho
        // Teacher t2 = dao.getTeacherByUserId(userId);

        // if (t2 != null) {
        //     t2.display();
        // } else {
        //     System.out.println("Is user_id ka teacher nahi mila");
        // }

        // // 5. getAssignedClasses
        // System.out.println("\n===== 5. getAssignedClasses =====");
        // List<String> classes = dao.getAssignedClasses(teacherId);

        // if (classes.isEmpty()) {
        //     System.out.println("Is teacher ko abhi koi class assign nahi hui");
        // } else {
        //     for (String c : classes) {
        //         System.out.println(c);
        //     }
        // }

        // // 6. removeTeacher
        // System.out.println("\n===== 6. removeTeacher =====");
        // boolean removed = dao.removeTeacher(teacherId);

        // if (removed) {
        //     System.out.println("Teacher remove hua (status LEFT)");
        // } else {
        //     System.out.println("Teacher remove nahi hua");
        // }

        // // Dobara dekho, ab status "Not Active" aana chahiye
        // Teacher after = dao.getTeacherById(teacherId);
        // if (after != null) {
        //     after.display();


        

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

        // =====testing student dao=====

    //     StudentDAO st = new StudentDAO();

    //     // Har baar unique email generate hoga
    //     String email = "student" + System.currentTimeMillis() + "@test.com";

    //     // 1. registerStudent
    //     System.out.println("===== 1. registerStudent =====");

    //     boolean result = st.registerStudent(
    //     "Daniyal",
    //     "Male",
    //     "06-11-2007",
    //     1,                  // class id from database
    //     email,
    //     "Daniyal123"
    //     );

    // if (result) {
    //     System.out.println("Student Data Saved Successfully!");
    // } else {
    //     System.out.println("Student Registration Failed!");
    //     return;  
    //     // Register nahi hua to aage testing nahi chalegi
    // }


    // // 2. getAllStudents
    // System.out.println("\n===== 2. getAllStudents =====");

    // List<Student> list = st.getAllStudents();

    // if (list.isEmpty()) {
    //     System.out.println("No student found");
    //     return;
    // }

    // for (Student s : list) {
    //     s.displayStudent();
    //     System.out.println("--------------------------");
    // }


    // // Ab jo naya student bana (list ka aakhri), uski Student ID le lo
    // int studentId = list.get(list.size() - 1).getStudentId();

    // System.out.println("Student ID: " + studentId);


    // // 3. getStudentById
    // System.out.println("\n===== 3. getStudentById =====");

    // Student s1 = st.getStudentById(studentId);

    // if (s1 != null) {
    //     s1.displayStudent();
    // } else {
    //     System.out.println("Student Not found");
    // }


    // // 3. getStudentById (galat ID to null aana chahiye)

    // Student notFound = st.getStudentById(523);

    // if (notFound == null) {
    //     System.out.println("null on invalid ID ");
    //     // Galat ID par null aaya (sahi hai)
    // }

    }
}
