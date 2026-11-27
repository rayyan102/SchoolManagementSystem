package Backend;

import java.util.Scanner;

public class AdminDashboard {

    private Admin admin;
    private Scanner sc = new Scanner(System.in);

    // Constructor
    public AdminDashboard(Admin admin) {
        this.admin = admin;
    }

    // Dashboard menu
    public void showDashboard() {

        System.out.println("\n================================");
        System.out.println("       AHSS ADMIN DASHBOARD");
        System.out.println("================================");

        System.out.println("Welcome, " + admin.getFullName());
        System.out.println("Admin ID: " + admin.getAdminId());

        System.out.println("\n1. Add Student");
        System.out.println("2. Remove Student");
        System.out.println("3. View All Students");
        System.out.println("4. Add Teacher");
        System.out.println("5. Remove Teacher");
        System.out.println("6. View All Teachers");
        System.out.println("7. Manage Classes");
        System.out.println("8. Mark Teacher Attendance");
        System.out.println("9. Logout");

        System.out.print("\nEnter your choice: ");

        int choice = sc.nextInt();

    }
}