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
    
}
