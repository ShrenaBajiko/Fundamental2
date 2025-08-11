import java.util.Scanner;

public class StudentFilter {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        StudentProcessor processor = new StudentManager();

        // Read student data from file once
        processor.readStudentsFromFile("Student.txt");

        int choice;
        do {
            // Display menu
            System.out.println("\n=== Student Menu ===");
            System.out.println("1. Display total marks for all students");
            System.out.println("2. Display students below a threshold");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");

            // Validate input
            while (!input.hasNextInt()) {
                System.out.print("Invalid input. Please enter a number: ");
                input.next();
            }

            choice = input.nextInt();

            switch (choice) {
                case 1:
                    processor.displayTotalMarksForAll();
                    break;

                case 2:
                    int threshold = -1;
                    while (threshold < 0) {
                        System.out.print("Enter a threshold (non-negative): ");
                        if (input.hasNextInt()) {
                            threshold = input.nextInt();
                            if (threshold < 0) {
                                System.out.println("Threshold must be non-negative.");
                            }
                        } else {
                            System.out.println("Invalid input. Please enter a number.");
                            input.next();
                        }
                    }
                    processor.StudentsDetailBelowThreshold(threshold);
                    break;

                case 3:
                    System.out.println("Exiting program.");
                    break;

                default:
                    System.out.println("Invalid choice. Please select 1, 2 or 3.");
            }

        } while (choice != 3);

        input.close();
    }
}
