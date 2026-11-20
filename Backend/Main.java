package Backend;

public class Main {
    public static void main(String[] args) {
        Admin admin1 = new Admin(1, "Rayyan", 101);
        admin1.displayAdmin();
        admin1.setFullName("Daniyal");

        System.out.println("\nAfter Updating Name:");
        admin1.displayAdmin();


        Teacher t1 = new Teacher(1, "LEO", "03001234567", new String[]{"Active"});
        Teacher t2 = new Teacher(2, "MUDA", "03111234567", new String[]{"Not Active"});

        t1.display();
        System.out.println("-------------------");
        t2.display();


        Student s1 = new Student(1, "Daniyal", new String[]{"Male"}, "06-November-2007", "15-august-2025");
  s1.displayStudent();

  System.out.print("");
  System.out.println("==================================================================================================");
  System.out.print("");

  Student s2 = new Student(2, "Aisha", new String[]{"Female"}, "15-October-2005", "15-may-2023");
  s2.displayStudent();
    }
}
