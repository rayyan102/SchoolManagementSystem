package Backend;
public class Student{
  
  private int studentId;
  private String fullName;
  private String[] Gender = {"Male","Female"};
  private String dob;
  private String admissionDate;

  // constructor
  public Student(int s_id, String name, String[] Gender, String dob, String a_date){
      this.studentId = s_id;
      this.fullName = name;
      this.dob = dob;
      this.admissionDate = a_date;
      this.Gender = Gender;

        if (Gender[0].equals("Male")) {
            this.Gender[0] = "Male";
        } 
        else if(Gender[0].equals("Female")){
          this.Gender[0] = "Female";
        }
        else {
          System.out.print("Invalid Choice!");
        }
  }

  // getting values
  public int getStudentId(){
    return studentId; 
  }

  
  public String getFullName(){
    return fullName; 
  }

  public String[] getGender() {
        return Gender;
    }


  public String getDob(){
    return dob; 
  }

  public String getAdmissionDate(){
    return admissionDate; 
  }

  // Display Student Information
  public void displayStudent(){
    System.out.println("Student ID: " + studentId);
    System.out.println("Student Name: " + fullName);
    System.out.println("Gender: " + Gender[0]);
    System.out.println("DOB: " + dob);
    System.out.println("Admission Date: " + admissionDate);
}
public static void main(String[] args) {
  Student s1 = new Student(1, "Daniyal", new String[]{"Male"}, "06-November-2007", "15-august-2025");
  s1.displayStudent();


  System.out.println("==================================================================================================");


  Student s2 = new Student(2, "Aisha", new String[]{"Female"}, "15-October-2005", "15-may-2023");
  s2.displayStudent();
}
} 
