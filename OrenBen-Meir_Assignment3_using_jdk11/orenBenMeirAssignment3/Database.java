//Name: Oren Ben-Meir
//EPLID:14144874
//Class: CSC221

package orenBenMeirAssignment3;

import java.util.ArrayList;

public class Database {
    private ArrayList<Item> items;

    //Creates empty database of item objects
    public Database(){
        items = new ArrayList<>();
    }

    //Adds an Item object into the array list
    public void addItem(Item newItem){
        items.add(newItem);
    }

    //Prints contents of data base
    public void list(){
        for(Item currentItem: items){
            System.out.println(currentItem);
        }
    }

    private void sort(){
        items.sort(Item::compareTo);
    }

    //Sorts Item objects by id
    public void sortByID(){
        for(Item currentItem: items){
            currentItem.compareByID();
        }
        sort();
    }

    //Sorts Item objects by title
    public void sortByTitle(){
        for(Item currentItem: items){
            currentItem.compareByTitle();
        }
        sort();
    }

    //Sorts Item objects by the time the objects were added on
    public void sortByAddedOn(){
        for(Item currentItem: items){
            currentItem.compareByAddedOn();
        }
        sort();
    }

}
