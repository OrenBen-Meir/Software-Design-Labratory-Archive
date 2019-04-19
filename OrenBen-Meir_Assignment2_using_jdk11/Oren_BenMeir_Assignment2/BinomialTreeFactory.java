package Oren_BenMeir_Assignment2;


public final class BinomialTreeFactory {

    public static double startPrice = 100;

    //Precondition: maxTime>-0 and 0<=upProbability<=100
    //Postcondition, returns the root binary tree that predict stock price movements with a given probability P,
    //length T which is also the the number of time periods
    public static Node create(int T, int P){
        return buildTree(T, P, startPrice);
    }

    //Precondition: numberOfTimePeriods>-0 and 0<=upProbability<=100
    //Postcondition, creates a binary tree that predict stock price movements with a given probability upProbability,
    //a starting price labeled startPrice, and the number of time periods of the tree labeled numberOfTimePeriods which
    // is also its length.
    private static Node buildTree(int numberOfTimePeriods, int upProbability, double startPrice){

        if(numberOfTimePeriods<0)
            throw new IllegalArgumentException("Maximum time/height for a binary tree can't be negative");
        if(upProbability<0||upProbability>100)
            throw new IllegalArgumentException("upProbability needs to be in the range [0,100]");

        //Base case
        if(numberOfTimePeriods == 0){
            return new Node(startPrice ,upProbability);
        }

        //Recursive case
        return new Node(startPrice, upProbability,
                buildTree(numberOfTimePeriods-1, upProbability, startPrice*.8),
                buildTree(numberOfTimePeriods-1, upProbability, startPrice*1.2));
    }

    public static void setNewStartPrice(double new_startPrice){
        startPrice = new_startPrice;
    }

    private BinomialTreeFactory(){
    }
}
