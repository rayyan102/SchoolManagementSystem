package Backend;

public class Main {
    public static void main(String[] args) {
        Admin admin1 = new Admin(1, "Rayyan", 101);
        admin1.displayAdmin();
        admin1.setFullName("Daniyal");

        System.out.println("\nAfter Updating Name:");
        admin1.displayAdmin();
    }
}
