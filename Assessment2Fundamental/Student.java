
/**
 * Write a description of class Student here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
import java.util.Arrays;
public class Student
{
    private String firstName;
    private String lastName;
    private String studentId;
    private int[] marks;
    private int totalMarks;
    
    public Student(String lastName, String firstName, String studentId, int[] marks) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.studentId = studentId;
        this.marks = marks;
        this.totalMarks = calculateTotalMarks();
    }
        
    private int calculateTotalMarks() {
        int sum = 0;
        for (int mark : marks) {
            sum += mark;
        }
        return sum;
    }
    
    public int getTotalMarks() {
        return totalMarks;
    }
    
     public String getDetails() {
        return "Name       : " + firstName + " " + lastName +
             "\nStudent ID : " + studentId +
             "\nMarks      : " + Arrays.toString(marks) +
             "\nTotal Marks: " + totalMarks + "\n";
    }


}