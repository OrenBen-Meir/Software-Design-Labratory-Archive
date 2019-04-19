//Name: Oren Ben-Meir
//EPLID:14144874
//Class: CSC221

package orenBenMeirAssignment3;

import java.util.Date;

public class CD extends MultiMediaItem {
    private final String artist;

    public CD(String id, String title, Date addedOn, int playingTime, String artist){
        super(id, title, addedOn, playingTime);
        this.artist = artist;
    }

    public final String getArtist() {
        return artist;
    }

    @Override
    public int compareTo(Item comparedItem) {//prioritizes superclass comparisons, then comparisons on artist
        int superCompare = super.compareTo(comparedItem);
        if(comparedItem instanceof CD && superCompare == 0){
            return getArtist().compareTo( ((CD) comparedItem).getArtist());
        }
        return superCompare;
    }

    @Override
    public String toString() {
        return String.format("%s    Artist: %s",super.toString(), getArtist());
    }
}
