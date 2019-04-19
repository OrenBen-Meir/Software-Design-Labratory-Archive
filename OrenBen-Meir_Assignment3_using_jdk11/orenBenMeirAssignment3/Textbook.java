//Name: Oren Ben-Meir
//EPLID:14144874
//Class: CSC221

package orenBenMeirAssignment3;

import java.util.Date;

public class Textbook extends Item {
    private final String author;

    public Textbook(String id, String title, Date addedOn, String author){
        super(id, title, addedOn);
        this.author = author;
    }

    public final String getAuthor(){
        return author;
    }

    @Override
    public int compareTo(Item comparedItem) {//prioritizes superclass comparisons, then comparisons on author
        int superCompare = super.compareTo(comparedItem);
        if(comparedItem instanceof Textbook && superCompare == 0){
            return getAuthor().compareTo( ((Textbook) comparedItem).getAuthor());
        }
        return superCompare;
    }

    @Override
    public String toString() {
        return String.format("%s    Author: %s", super.toString(), getAuthor());
    }
}
