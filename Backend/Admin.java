package Backend;
public class Admin{
  private int adminId;
  private String fullName;
  private int userId;

  // Admin constructor
  
  public Admin(int a_id, String name, int u_id){
      this.adminId = a_id;
      this.fullName = name;
      this.userId = u_id;
  }

  // get value
  public int getAdminId(){
    return adminId; 
  }

  
  public String getFullName(){
    return fullName; 
  }
 
  public int getUserId(){
    return userId; 
  }

  public void setFullName(String fullName) {
    this.fullName = fullName;
}

  // Display Admin Information
  public void displayAdmin(){
    System.out.println("Admin ID: " + adminId);
    System.out.println("Admin Name: " + fullName);
    System.out.println("User ID: " + userId);
}

} 
  

