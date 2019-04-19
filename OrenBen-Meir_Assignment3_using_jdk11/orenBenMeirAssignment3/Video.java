//Name: Oren Ben-Meir
//EPLID:14144874
//Class: CSC221

package orenBenMeirAssignment3;

import java.util.Date;

public class Video extends MultiMediaItem {
    private final String director;

    public Video(String id, String title, Date addedOn, int playingTime, String director){
        super(id, title, addedOn, playingTime);
        this.director = director;
    }

    public final String getDirector(){
        return director;
    }

    @Override
    public int compareTo(Item comparedItem) {//prioritizes superclass comparisons, then comparisons on director
        int superCompare = super.compareTo(comparedItem);
        if(comparedItem instanceof Video && superCompare == 0){
            return getDirector().compareTo( ((Video) comparedItem).getDirector());
        }
        return superCompare;
    }

    @Override
    public String toString() {
        return String.format("%s    Director: %s",super.toString(), getDirector());
    }
}
