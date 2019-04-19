package OrenBenMeirAssignment1;

import java.time.DateTimeException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class CommandPromptMain {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        HeartRates yourHeartInfo = new HeartRates();

        boolean noValidInput = true;

        do {
            try {
                System.out.println("\nYou will be prompted below to enter your information.\n");

                System.out.print("Enter your first name: ");
                yourHeartInfo.setFirstName(input.nextLine());

                System.out.print("Enter your last name: ");
                yourHeartInfo.setLastName(input.nextLine());

                System.out.println("Enter your date of birth below");

                System.out.print("Month: ");
                int month = input.nextInt();//Throws InputMismatchException

                System.out.print("Day: ");
                int day = input.nextInt();//Throws InputMismatchException

                System.out.print("Year: ");
                int year = input.nextInt();//Throws InputMismatchException

                yourHeartInfo.setDateOfBirth(month, day, year);//Throws DateTimeException and IllegalArgumentException

                System.out.printf("%n%s", yourHeartInfo);

                noValidInput = false;

            } catch (InputMismatchException e) {
                System.err.printf("%n%s%n%s%n", e, "Month, day, and year need to be integers");
                System.out.println("\nTry again with valid input.\n");
            } catch (DateTimeException | IllegalArgumentException e) {
                System.err.println('\n'+e.toString());
                System.out.println("\nTry again with a valid input\n");
            }
            input = new Scanner(System.in);

        } while (noValidInput);
    }
}