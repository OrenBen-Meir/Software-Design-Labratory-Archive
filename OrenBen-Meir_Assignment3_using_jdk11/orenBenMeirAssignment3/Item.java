//Name: Oren Ben-Meir
//EPLID:14144874
//Class: CSC221

package orenBenMeirAssignment3;

import java.util.Date;

public abstract class Item implements Comparable<Item>{
    private enum CompareBy{ID, TITLE, ADDED_ON}

    private CompareBy comparedThrough; //Determines how the item will be compared (by item, or title, or added on)
    private final String id;
    private final String title;
    private final Date addedOn;

    public Item(String id, String title, Date addedOn) {
        this.id = id;
        this.title = title;
        this.addedOn = (Date) addedOn.clone();
        comparedThrough = CompareBy.ID;//Item is compared by id as a priority by default
    }

    public final String getId() {
        return id;
    }

    public final String getTitle() {
        return title;
    }

    public final Date getAddedOn() {
        return (Date) addedOn.clone();
    }

    //Sets item to be compared by id
    public void compareByID(){
        comparedThrough = CompareBy.ID;
    }

    //Sets item to be compared by title
    public void compareByTitle(){
        comparedThrough = CompareBy.TITLE;
    }

    //Sets item to be compared by added on
    public void compareByAddedOn(){
        comparedThrough = CompareBy.ADDED_ON;
    }

    @Override//Compares by what ever field you set the program to be (id is default)
    public int compareTo(Item comparedItem) {
        int idCompare = getId().compareTo(comparedItem.getId());
        int titleCompare = getTitle().compareTo(comparedItem.getTitle());
        int addedOnCompare = getAddedOn().compareTo(comparedItem.getAddedOn());
        switch (comparedThrough){
            case ID:
                if(idCompare != 0)
                    return idCompare;
                else if(titleCompare != 0)
                    return titleCompare;
                else
                    return addedOnCompare;
            case TITLE:
                if(titleCompare != 0)
                    return titleCompare;
                else if(idCompare != 0)
                    return idCompare;
                else
                    return addedOnCompare;
            default:
                if(addedOnCompare != 0)
                    return addedOnCompare;
                else if(idCompare != 0)
                    return idCompare;
                else
                    return titleCompare;
        }
    }

    @Override
    public String toString(){
        return String.format("ID: %s    Title: %s   Added On: %s",getId(), getTitle(), getAddedOn().toString());
    }
}