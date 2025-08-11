import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StudentManager implements StudentProcessor {
    private List<Student> students = new ArrayList<>();

@Override
public void readStudentsFromFile(String filename) {
    try {
        Scanner scanner = new Scanner(new File(filename));
        int lineNumber = 0;

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine().trim();
                             lineNumber++;

            if (lineNumber <= 2) {
                    continue;
            }

        

            // Skip empty or invalid lines
            if (line.isEmpty() || !line.contains(",")) continue;

            String[] parts = line.split(",");

            if (parts.length < 6) continue;

            String lastName = parts[0].trim();
            String firstName = parts[1].trim();
            String studentId = parts[2].trim();
            String fullName = firstName + " " + lastName;
            boolean invalidMarkFound = false;

            double[] marks = new double[3];
            boolean valid = true;

            for (int i = 0; i < 3; i++) {
                try {
                    double mark = Double.parseDouble(parts[3 + i].trim());
                    if (mark < 0) {
                        System.out.println("Negative mark for student " + firstName + " " + lastName + " (ID: " + studentId + "): " + mark + " at position " + (i + 1));
                        valid = false;
                        break;
                    }
                     if (mark > 100) {
                        System.out.println("Invalid mark for " + fullName + ": " + mark + " (must be between 0 and 100)");
                        invalidMarkFound = true;
                        break; // Stop checking further marks for this student
                    }
                    marks[i] = mark;
                } catch (NumberFormatException e) {
                    System.out.println("Invalid mark for student " + firstName + " " + lastName + " (ID: " + studentId + "): \"" + parts[3 + i] + "\" at position " + (i + 1));
                    valid = false;
                    break;
                }
            }

            if (!valid) {
                System.out.println("Skipping student due to invalid mark(s).");
                continue;
            }

            students.add(new Student(lastName, firstName, studentId, marks));
        }

        scanner.close();
    } catch (FileNotFoundException e) {
        System.out.println("File not found: " + filename);
    }
}


    @Override
    public void StudentsDetailBelowThreshold(int threshold) {
        boolean found = false;
        System.out.println("\nStudents with total marks below " + threshold + ":");
        for (Student student : students) {
            if (student.calculateTotalMarks() < threshold) {
                System.out.println(student.getDetails());
                found = true;
            }
        }
        if (!found) {
        System.out.println("No students found with total marks less than " + threshold);
    }
    }
    
      @Override
    public void displayTotalMarksForAll() {
        System.out.println("\nTotal marks for all students:");
        for (Student student : students) {
            System.out.println(student.getDetails());
        }
    }
}