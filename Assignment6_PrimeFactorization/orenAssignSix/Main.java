package orenAssignSix;

import java.util.*;
import java.util.stream.IntStream;


final class PrimeChecker{
    //final class for an object that stores an integer called num and tests
    // if it is prime and gets its prime factors

    private int num;//number being tested
    private ArrayList<Integer> primeFactors; //ArrayList of prime factors

    //default constructor, sets num to 1
    public PrimeChecker(){
        this(1);
    }

    //constructor that initializes num
    public PrimeChecker(int num){
        setNum(num);
    }

    //sets num
    public void setNum(int num){
        primeFactors = createPrimeFactors(num);
        this.num = num;
    }

    //getter for num
    public int getNum(){
        return this.num;
    }

    //returns a string containing the prime factors
    public String primeFactorsString(){
        StringBuilder primeFactorsStrBuilder = new StringBuilder();
        primeFactors.stream()
                .map(x -> String.format("%d ", x))
                .forEach(primeFactorsStrBuilder::append);
        return primeFactorsStrBuilder.toString();
    }

    //returns true if num is prime, else returns false
    public boolean isNumPrime(){
        return (num == 1 || num < 0)? false: primeFactors.size()==0;
    }

    //returns true if N is prime, else returns false
    private static boolean isPrime(int N){
        return (N<2)? false: IntStream.range(2,N).parallel().allMatch(x -> N%x!=0);
    }

    //returns an array list of prime factors (this function uses a Set data structure)
    private static ArrayList<Integer> createPrimeFactors(int num){
        if(num == 0) throw new IllegalArgumentException("integer cant be 0");

        Set<Integer> factorPrimeSet = new TreeSet<>();
        ArrayList<Integer> primeFactors = new ArrayList<>();
        final int number;

        if(Math.abs(num) == 1) return primeFactors;
        else if(num<0){
            number = -num;
            if(isPrime(number)){ primeFactors.add(number); return primeFactors; }
        }
        else number = num;

        IntStream.range(2,number)
                .filter(x -> number%x==0 && isPrime(x))
                .forEach(factorPrimeSet::add);
        factorPrimeSet
                .forEach(x -> { for(int L = number; L%x==0 && L!=0; L = L/x) primeFactors.add(x);});
        return primeFactors;
    }
}

public class Main {

    public static void main(String[] args) {
        PrimeChecker primeTester = new PrimeChecker();//will test for a prime number

        while(true){
            try {
                System.out.print("Input a number to test if prime: ");
                primeTester.setNum((new Scanner(System.in)).nextInt());//inputs integer into primeTester
                if(primeTester.isNumPrime()) {//if prime, tells if prime
                    System.out.printf("%d is prime\n", primeTester.getNum());
                }
                else{//if not prime, shows prime factorization
                    System.out.printf("Not prime, here are the prime factors\n%s\n",primeTester.primeFactorsString());
                }
            }
            catch (InputMismatchException | IllegalArgumentException ex){//illegal input for number is handled
                System.err.println("Error, enter a nonzero integer");
            }

            System.out.print("\nPres y to continue, press any other key to exit: ");
            //exit loop if inputted string starts with 'y' or 'Y'
            if(Character.toLowerCase((new Scanner(System.in)).nextLine().charAt(0))!='y') break;
            System.out.println("________________________________\n");
        }
    }
}