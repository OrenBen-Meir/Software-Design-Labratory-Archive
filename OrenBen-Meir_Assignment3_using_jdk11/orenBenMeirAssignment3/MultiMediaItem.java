//Name: Oren Ben-Meir
//EPLID:14144874
//Class: CSC221

package orenBenMeirAssignment3;

import java.util.Date;

public abstract class MultiMediaItem extends Item {
    private final Integer playingTime;

    public MultiMediaItem(String id, String title, Date addedOn, int playingTime){
        super(id, title, addedOn);
        this.playingTime = playingTime;
    }

    public final Integer getPlayingTime(){
        return playingTime;
    }

    @Override//prioritizes superclass comparisons, then comparisons on playingTime
    public int compareTo(Item comparedItem) {
        int superCompare = super.compareTo(comparedItem);
        if(comparedItem instanceof MultiMediaItem && superCompare == 0){
            return getPlayingTime().compareTo( ((MultiMediaItem) comparedItem).getPlayingTime());
        }
        return superCompare;
    }

    @Override
    public String toString() {
        return String.format("%s    Playing Time: %d", super.toString(), getPlayingTime());
    }
}
