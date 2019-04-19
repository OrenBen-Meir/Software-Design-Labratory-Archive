package Oren_BenMeir_Assignment2;
import java.security.SecureRandom;

public final class Navigator {//Ths class is immutable

    private int numberOfIterations;

    //Precondition: N>=1
    //Postcondition: A navigator with a set number of iterations N is constructed
    public Navigator(int N){
        setNumberOfIterations(N);
    }

    //precondition: N>=1
    //postcondition: sets the number of iterations for a binary tree to be N.
    public void setNumberOfIterations(int N) {
        if(N<=0) throw new IllegalArgumentException("Number of iterations need to be at least 1");
        this.numberOfIterations = N;
    }
    public int getNumberOfIterations(){return numberOfIterations;}

    //Precondition: rootNode isn't null
    //Postcondition: iterates through the binary tree printing the price for each time period, the time period,
    //a random number from 1 to 100, and the movement through the binary tree being traversed.
    //The iteration terminates if the number of iterations for the navigator object
    //has been achieved or the entire tree was navigated.
    public void Navigate(Node rootNode) {

        if(rootNode == null) {
            throw new NullPointerException("The Navigator can't iterate through an empty tree");
        }

        int randomValueGenerated;
        boolean moveUp;
        SecureRandom randomNumberGenerator = new SecureRandom();

        Node currentNode = rootNode;
        int timePeriod = 0;

        do{
            randomValueGenerated = 1 + randomNumberGenerator.nextInt(100);
            moveUp = randomValueGenerated <= currentNode.getUpProbability();

            System.out.printf("Time: %d\nPrice: %.2f\nRandom Value Generated: %d\n",
                    timePeriod, currentNode.getPrice(), randomValueGenerated);

            if(currentNode.isLeaf()){
                System.out.println();
                System.out.println(timePeriod+1<getNumberOfIterations()?
                        "Number of iterations exceeded what the navigator allowed, terminating navigation\n":
                        "Navigation terminated\n");
            }
            else if(timePeriod+1==getNumberOfIterations()){
                System.out.println("\nNavigation terminated\n");
            }
            else{
                System.out.printf("Next Move: %s\n\n", moveUp? "UP": "Down");
            }

            if(moveUp){
                currentNode = currentNode.getUpNode();
            }
            else{
                currentNode = currentNode.getDownNode();
            }

            timePeriod++;
        }while (currentNode!=null&&timePeriod<getNumberOfIterations());
    }
}
