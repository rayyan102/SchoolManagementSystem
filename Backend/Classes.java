package Backend;
public class Classes {
    // Private attributes 
    private int classId;
    private String className;
    private int classTeacherId;   // class teacher ki teacher_id (FK)

    // Constructor
    public Classes(int classId, String className, int classTeacherId) {
        this.classId = classId;
        this.className = className;
        this.classTeacherId = classTeacherId;
    }
     // Getters
    public int getClassId() {
        return classId;
    }

    public String getClassName() {
        return className;
    }

    public int getClassTeacherId() {
        return classTeacherId;
    }

    // Display
    public void display() {
        System.out.println("Class ID         : " + classId);
        System.out.println("Class Name       : " + className);
        System.out.println("Class Teacher ID : " + classTeacherId);
    }
}
