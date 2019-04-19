//Name: Oren Ben-Meir
//EPLID:14144874
//Class: CSC221

package orenBenMeirAssignment3;

import java.util.Calendar;
import java.util.Date;

public class Main {

    public static void main(String[] args) {

        Database library = new Database();

        Calendar cal = Calendar.getInstance();

        // adding database entries

        cal.set(1890, Calendar.AUGUST, 10);

        Date date = cal.getTime();

        library.addItem(new Textbook("TB15", "TextX", date, "John Doe"));

        cal.set(1954, Calendar.JANUARY, 18);

        date = cal.getTime();

        library.addItem(new Video("V09", "VideoB", date, 70000, "J. Smith"));

        cal.set(2000, Calendar.FEBRUARY, 29);

        date = cal.getTime();

        library.addItem(new Textbook("TB01", "TextY", date, "John Doe"));

        cal.set(2000, Calendar.FEBRUARY, 29);

        date = cal.getTime();

        library.addItem(new CD("CD07", "CD1", date, 1000, "B.D."));

        cal.set(1990, Calendar.APRIL, 30);

        date = cal.getTime();

        library.addItem(new CD("CD10", "CD1", date, 800, "X.Y."));

        cal.set(2000, Calendar.FEBRUARY, 29);

        date = cal.getTime();

        library.addItem(new CD("CD05", "CD1", date, 1000, "B.C."));

        cal.set(1890, Calendar.JULY, 2);

        date = cal.getTime();

        library.addItem(new Video("V12", "VideoA", date, 7000, "Joe Smith"));

        // print unsorted database

        System.out.println("----- DATABASE BEFORE SORTING: -----\n");

        library.list();

        // sort and print sorted database (by id as priority)

        library.sortByID();

        System.out.println("----- DATABASE AFTER SORTING BY ID -----\n");

        library.list();
        // sort and print sorted database (by title as priority)

        library.sortByTitle();

        System.out.println("----- DATABASE AFTER SORTING BY Title -----\n");

        library.list();
        // sort and print sorted database (by addedOn as priority)

        library.sortByAddedOn();

        System.out.println("----- DATABASE AFTER SORTING BY Date Added On -----\n");

        library.list();

    }
}
