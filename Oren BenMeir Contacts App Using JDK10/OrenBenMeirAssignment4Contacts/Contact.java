package OrenBenMeirAssignment4Contacts;

import javafx.scene.image.Image;

public class Contact implements Comparable<Contact>{
    private String firstName;
    private String lastName;
    private String email;
    private long phoneNumber;
    private Image contactPhoto;
    public Contact(String firstName, String lastName, String email, long phoneNumber, Image contactPhoto){
        createFirstName(firstName);
        createLastName(lastName);
        createEmail(email);
        createPhoneNumber(phoneNumber);
        createContactPhoto(contactPhoto);
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public long getPhoneNumber() {
        return phoneNumber;
    }

    public Image getContactPhoto() {//Will try to return a copy instead later
        return contactPhoto;
    }

    public void setFirstName(String firstName) {
        createFirstName(firstName);
    }

    public void setLastName(String lastName) {
        createLastName(lastName);
    }

    public void setEmail(String email) {
        createEmail(email);
    }

    public void setPhoneNumber(long phoneNumber) {
        createPhoneNumber(phoneNumber);
    }

    public void setContactPhoto(Image contactPhoto) {
        createContactPhoto(contactPhoto);
    }

    private void createFirstName(String firstName){
        this.firstName = firstName;
    }
    private void createLastName(String lastName){
        this.lastName = lastName;
    }
    private void createEmail(String email){
        this.email = email;
    }
    private void createPhoneNumber(long phoneNumber){
        this.phoneNumber = phoneNumber;
    }
    private void createContactPhoto(Image contactPhoto){
        this.contactPhoto = contactPhoto;
    }

    @Override
    public String toString() {
        return getLastName();
    }

    public String fullName(){
        return getFirstName()+' '+getLastName();
    }

    @Override
    public int compareTo(Contact o) {
        return this.toString().compareToIgnoreCase(o.toString());
    }
}
