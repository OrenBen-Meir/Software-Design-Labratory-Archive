package Oren_BenMeir_Assignment2;
import java.util.Scanner;

public class Tester {

    public static void main(String[] args) {
        int maxTime;
        int upProbability;
        double startPrice;
        Scanner input = new Scanner(System.in);

        System.out.print("Number of time periods for Tree :");
        maxTime = input.nextInt();
        System.out.print("Probability to move up :");
        upProbability = input.nextInt();
        System.out.print("Starting price at time=0 :");
        startPrice = input.nextDouble();
        System.out.print("Navigate for how many iterations :");
        int numberOfIterations = input.nextInt();

        BinomialTreeFactory.setNewStartPrice(startPrice);
        Navigator myNavigator = new Navigator(numberOfIterations);


        System.out.println();
        myNavigator.Navigate(BinomialTreeFactory.create(maxTime, upProbability));
    }
}
