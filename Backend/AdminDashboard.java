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

        switch (choice) {

    case 1:
        System.out.println("Add Student Selected");
        break;

    case 2:
        System.out.println("Remove Student Selected");
        break;

    case 3:
        System.out.println("View All Students Selected");
        break;

    case 4:
        System.out.println("Add Teacher Selected");
        break;

    case 5:
        System.out.println("Remove Teacher Selected");
        break;

    case 6:
        System.out.println("View All Teachers Selected");
        break;

    case 7:
        System.out.println("Manage Classes Selected");
        break;

    case 8:
        System.out.println("Mark Teacher Attendance Selected");
        break;

    case 9:
        System.out.println("Logging Out...");
        break;

    default:
        System.out.println("Invalid Choice!");
}

    }
}