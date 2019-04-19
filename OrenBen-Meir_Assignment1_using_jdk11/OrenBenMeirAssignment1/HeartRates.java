package OrenBenMeirAssignment1;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.Period;

public class HeartRates {
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;

    public HeartRates(String firstName, String lastName, int month, int day, int year){
        setFirstName(firstName);
        setLastName(lastName);
        setDateOfBirth(month, day, year);
    }

    public HeartRates(){
        this("","",LocalDate.now().getMonthValue(), LocalDate.now().getDayOfMonth(), LocalDate.now().getYear());
    }

    //Returns years between the date inputted and now
    private int yearsFromNow(LocalDate someDate){
        return Period.between(someDate,LocalDate.now()).getYears();
    }

    //getter and setter of first name
    public String getFirstName(){
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    //getter and setter of last name
    public String getLastName(){
        return lastName;
    }
    public void setLastName(String lastName){
        this.lastName = lastName;
    }

    //getter and setter of month
    public int getMonth(){
       return dateOfBirth.getMonthValue();
    }
    public void setMonth(int month){
        setDateOfBirth(month, getDay(), getYear());
    }

    //getter and setter of day
    public int getDay(){
        return dateOfBirth.getDayOfMonth();
    }
    public void setDay(int day){
        setDateOfBirth(getMonth(), day, getYear());
    }

    //getter and setter of year
    public int getYear(){
        return dateOfBirth.getYear();
    }
    public void setYear(int year){
        setDateOfBirth(getMonth(), getDay(), year);
    }

    //setter of date of birth
    public void setDateOfBirth(int month, int day, int year){
        if(year>LocalDate.now().getYear()){
            throw new DateTimeException("The date of birth provided can't be in a future date");
        }
        else if(year==LocalDate.now().getYear() && month>LocalDate.now().getMonthValue()){
            throw new DateTimeException("The date of birth provided can't be in a future date");
        }
        else if(year==LocalDate.now().getYear() && month==LocalDate.now().getMonthValue() && day>LocalDate.now().getDayOfMonth()){
            throw new DateTimeException("The date of birth provided can't be in a future date");
        }

        LocalDate newDateOfBirth = LocalDate.of(year, month, day);

        if(yearsFromNow(newDateOfBirth)>=220)
            throw new IllegalArgumentException("The date of birth is not valid as the age can't be grater than 220");

        dateOfBirth = newDateOfBirth;
    }

    //returns a formatted string containing the contents of the date of birth class
    public String dateOfBirthString(){
        return String.format("%d/%d/%d",getMonth(),getDay(), getYear());
    }

    //returns the person's age in years
    public int ageInYears(){
        return yearsFromNow(dateOfBirth);
    }

    //Returns the integer value of maximum heart rate in beats per minute
    public int maximumHeartRateInBPM(){
        return 220 - ageInYears();
    }

    //Returns a string showing the value of the maximum heart rate and its units (which is beats per minute)
    public String maximumHeartRate(){
        return String.format("%d beats per minute", maximumHeartRateInBPM());
    }

    //Returns the maximum target heart rate in beats per minute
    public double minimumTargetHeartRateInBPM(){
        return .50*maximumHeartRateInBPM();
    }

    //Returns the minimum target heart rate in beats per minute
    public double maximumTargetHeartRateInBPM(){
        return .85*maximumHeartRateInBPM();
    }

    //prints out a string demonstrating the range of the target heart rate
    public String targetHeartRateRange(){
        return String.format("%.1f beats per minute - %.1f beats per minute",
                minimumTargetHeartRateInBPM(), maximumTargetHeartRateInBPM());
    }

    //Needed to print the object with all the information needed
    @Override
    public String toString(){
        return String.format(
                "First name: %s%nLast name: %s%nDate of birth: %s%nAge: %d years old%nMaximum heart rate: %s%nTarget heart rate range: %s%n",
                getFirstName(),getLastName(),
                dateOfBirthString(),ageInYears(),
                maximumHeartRate(),targetHeartRateRange());
    }

}