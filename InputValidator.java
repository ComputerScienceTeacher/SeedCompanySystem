import java.util.Scanner;

public class InputValidator{
    public static int getValidInteger(String prompt, int min, int max) {
        System.out.print(prompt);
        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.next();
        try {
            int value = Integer.parseInt(userInput);
            if (value < min || value > max) {
                throw new IllegalArgumentException("Input out of range.");
            }
            return value;
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            System.out.println("Please try again.");
            return getValidInteger(prompt, min, max); // Recursive call
        }
    }
}