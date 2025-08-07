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
            File file = new File(filename);
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();

                // Skip empty or comment-like lines
                if (line.isEmpty()) continue;

                String[] parts = line.split("\\s+");
                if (parts.length != 6) {
                    System.out.println("Skipping line (not a valid student record): " + line);
                    continue;
                }

                String lastName = parts[0];
                String firstName = parts[1];
                String studentId = parts[2];
                int[] marks = new int[3];

                try {
                    marks[0] = Integer.parseInt(parts[3]);
                    marks[1] = Integer.parseInt(parts[4]);
                    marks[2] = Integer.parseInt(parts[5]);
                } catch (NumberFormatException e) {
                    System.out.println("Skipping line (invalid mark entry): " + line);
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
        System.out.println("\nStudents with total marks below " + threshold + ":");
        for (Student student : students) {
            if (student.calculateTotalMarks() < threshold) {
                System.out.println(student.getDetails());
            }
        }
    }
}
