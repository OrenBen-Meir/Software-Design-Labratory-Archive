package Oren_BenMeir_Assignment2;

public final class Node {//Immutable node class for a binary tree that predicts stock prices

    private final int upProbability;//Probability for a stock price to go up
    private final double price; //price stored in the node
    private final Node downNode;
    private final Node upNode;

    public Node(double price, int upProbability){
        this(price, upProbability,null,null);
    }
    public Node(double price,int upProbability, Node downNode,Node upNode){
        this.price = price;
        this.upProbability = upProbability;
        this.downNode = downNode;
        this.upNode = upNode;
    }


    public int getUpProbability(){return upProbability;}//getter for upProbability

    public double getPrice(){return price;}//getter for price

    public Node getDownNode(){return downNode;}//getter for downNode

    public Node getUpNode(){return upNode;}//getter for upNode

    public boolean isLeaf(){return downNode==null&&downNode==null;}//Returns true if the node is a leaf

}
