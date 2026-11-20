package Backend;
public class User {

    private int userId;
    private String email;
    private String password;
    private String userRole;

    // User Constructor
    public User(int userId, String email, String password, String userRole) {
        this.userId = userId;
        this.email = email;
        this.password = password;
        this.userRole = userRole;
    }

    // User Getters
    public int getUserId() {
        return userId;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getUserRole() {
        return userRole;
    }
}
