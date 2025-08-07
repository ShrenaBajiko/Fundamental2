
/**
 * Write a description of class StudentFilter here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
import java.util.Scanner;
public class StudentFilter
{
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        StudentProcessor processor = new StudentManager();

        int threshold = -1;

        // Input validation for threshold
        while (threshold < 0) {
            System.out.print("Enter a valid threshold for total marks (positive integer): ");
            if (input.hasNextInt()) {
                threshold = input.nextInt();
                if (threshold < 0) {
                    System.out.println("Threshold must be a non-negative number.");
                }
            } else {
                System.out.println("Invalid input. Please enter a numeric value.");
                input.next(); // consume invalid input
            }
        }

        processor.readStudentsFromFile("Student.TXT");
        processor.StudentsDetailBelowThreshold(threshold);

        input.close();
    }
}