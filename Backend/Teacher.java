package Backend;
public class Teacher {
    // Private attributes
    private int id;
    private String name;
    private String phone;
    private String[] status;   

    // Constructor
    public Teacher(int id, String name, String phone, String[] status) {
        this.id = id;
        this.name = name;
        this.phone = phone;

        this.status = status;

        if (status[0].equals("Active")) {
            this.status[0] = "Active";
        } else {
            this.status[0] = "Not Active";
        }
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String[] getStatus() {
        return status;
    }

    // Display
    public void display() {
        System.out.println("Teacher ID : " + id);
        System.out.println("Name       : " + name);
        System.out.println("Phone      : " + phone);
        System.out.println("Status     : " + status[0]);
    }
}

