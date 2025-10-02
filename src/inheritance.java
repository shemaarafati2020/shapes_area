class IPRCKarongi{
    int StudentID;
    String StudentName;
    String staffName;
    String position;

    void StudentDetails(int StudentID, String StudentName){
        this.StudentID = StudentID;
         this.StudentName = StudentName;
    }
    void StaffDetails(String staffName, String position){
        this.staffName = staffName;
        this.position = position;
    }
}
class staff extends IPRCKarongi{
    void DisplayStaffDetails(){
        System.out.println("Staff Name: " + this.staffName);
         System.out.println("Staff Position: " +this. position);
    }
}
class student extends IPRCKarongi{
    void DisplayStudentDetails(){
        System.out.println("Student ID: " + this.StudentID);
        System.out.println("Student Name: " + this.StudentName);
    }
}
public class inheritance {
    public static void main(String[] args) {
        int StudentID = 101;
        String StudentName = "Kenny";
        String staffName = "sophonie";
        String position = "Teacher";
        staff staff = new staff();
        staff.StaffDetails(staffName, position);
        staff.DisplayStaffDetails();
        student student = new student();
        student.StudentDetails(StudentID, StudentName);
        student.DisplayStudentDetails();

    }

}
