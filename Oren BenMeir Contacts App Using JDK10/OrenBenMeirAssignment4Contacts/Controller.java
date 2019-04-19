package OrenBenMeirAssignment4Contacts;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML
    RadioButton lastNameListSelect;//Selects ListView to show only the last names of the people in contacts
    @FXML
    RadioButton fullListSelect;//Selects ListView to show full name and picture of selected person
    @FXML
    ListView contactsListView;//List view of your contacts
    @FXML
    Text statusText;//Tells you if you are editing or creating new contact;
    @FXML
    ImageView contactImageView;//image view of a contact photo
    @FXML
    TextField firstNameTextFld;//text field for first name
    @FXML
    TextField lastNameTextFld;//text field for last name
    @FXML
    TextField phoneNumberTextFld;//text field for phone #
    @FXML
    TextField emailTextFld;//text field for email
    @FXML
    Text messageText;//Text that tells you information if exceptions occurs or any other actions
    @FXML
    HBox rootNode;

    private Image blankProfileImage;//Image of a blank profile pic used as a default if a contact has none
    private ObservableList<Contact> contactsList;//observable list of contacts
    private ObservableList<contactsFullDisplay> contactsFullDisplaysList;//observable list of contactsFullDisplays
    private ToggleGroup listSelectGroup;//Makes sure the radio buttons toggle with each other
    private generalStatus currentStatus;//checks if you are editing or creating a contact or neither


    private enum generalStatus{EDITING, CREATING}//enum for determining what is your current action


    //private member Hbox subclass that displays full name of contact and profile pic
    private class contactsFullDisplay extends HBox{
        contactsFullDisplay(Contact contact) {
            //setting HBox dimensions
            super(2);
            super.setFillHeight(true);
            super.setMaxHeight(40);

            Text fullNameLabel = new Text();
            fullNameLabel.setText(contact.fullName());
            fullNameLabel.setFont(new Font(14));
            fullNameLabel.setStyle("BOLD");

            ImageView contactPicView = new ImageView();
            contactPicView.setImage(contact.getContactPhoto());
            contactPicView.setFitHeight(40);
            contactPicView.setFitWidth(40);

            super.getChildren().addAll(contactPicView, fullNameLabel);
        }
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //sets the radio buttons to be toggled
        listSelectGroup = new ToggleGroup();
        lastNameListSelect.setToggleGroup(listSelectGroup);
        fullListSelect.setToggleGroup(listSelectGroup);

        //creates observable list of contacts
        contactsList = FXCollections.observableArrayList();

        //creates observable lists of contact full displays
        contactsFullDisplaysList = FXCollections.observableArrayList();

        //sets last name to be the default mode of list view
        lastNameListSelect.setSelected(true);
        contactsListView.setItems(contactsList);

        //makes sure only 1 contact is selected
        contactsListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        blankProfileImage = contactImageView.getImage();//stores the default image from contactImageView
        settingToAddContact();//sets the default mode to add a new contact
    }


    //Saves contact info to a text file of yur choosing and exits program
    public void saveAndExit(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose Text File to Overwrite");
        FileChooser.ExtensionFilter textFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
        fileChooser.getExtensionFilters().add(textFilter);
        File file = fileChooser.showOpenDialog(rootNode.getScene().getWindow());
        if(file!=null){
            try(PrintWriter writer = new PrintWriter(file)){
                writer.println("CONTACTS LIST");
                writer.println("");
                writer.println("---------------------------------------------------------------------------------");
                for(Contact contact: contactsList){
                    writer.println("  First Name: "+contact.getFirstName());
                    writer.println("   Last Name: "+contact.getLastName());
                    writer.println("Phone Number: "+contact.getPhoneNumber());
                    writer.println("       Email: "+contact.getEmail());
                    writer.println("---------------------------------------------------------------------------------");
                }
            } catch (IOException e) {
                System.out.println(e);
                e.printStackTrace();
            }
            System.exit(0);
        }
    }


    //Removes image in contact image view and resets it to default
    public void removeImage(ActionEvent event) {
        contactImageView.setImage(blankProfileImage);
    }


    //Toggles between using last name only in list view or full name and contact pic
    public void toggleListView(javafx.event.ActionEvent event) {
        boolean isSelected = true;
        int index = 0;
        try {
            Object selectedObject = contactsListView.getSelectionModel().getSelectedItems().get(0);
            index = contactsListView.getItems().indexOf(selectedObject);
        }catch (IndexOutOfBoundsException e){
            isSelected = false;
        }catch (Exception e){
            System.out.println(e);
            e.printStackTrace();
        }
        if(event.getSource() == lastNameListSelect){
            contactsListView.setItems(contactsList);
        }
        else{
            contactsListView.setItems(contactsFullDisplaysList);
        }
        if(isSelected) contactsListView.getSelectionModel().select(index);
    }


    //deletes selected contact if it is selected
    public void delete(ActionEvent event) {
        if(currentStatus==generalStatus.CREATING){
            messageText.setText("You can't delete a contact that isn't already uploaded");
            return;
        }
        int index;
        try {
            Object selectedObject = contactsListView.getSelectionModel().getSelectedItems().get(0);
            index = contactsListView.getItems().indexOf(selectedObject);
        }catch (Exception e){
            System.out.println(e);
            messageText.setText("unexpected error: "+e.toString());
            e.printStackTrace();
            return;
        }
        contactsListView.getSelectionModel().clearSelection();
        try {
            contactsList.remove(index);
            contactsFullDisplaysList.remove(index);
            messageText.setText("Selected contact deleted");
        }catch (Exception e){
            messageText.setText(e.toString());
            System.out.println(e);
            e.printStackTrace();
            messageText.setText(e.toString());
            return;
        }
        if(contactsList.size()==0){
            settingToAddContact();
            messageText.setText("Empty contacts list, you can add a new contact");
        }
        if(index==contactsList.size()){
            index--;
        }
        contactsListView.getSelectionModel().select(index);
    }


    //Uploads image into contactImageView
    public void uploadImage(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Upload Image");
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg"));
        File imageFile = fileChooser.showOpenDialog(rootNode.getScene().getWindow());

        if(imageFile == null) return;

        try {
            BufferedImage bufferedImage = ImageIO.read(imageFile);
            Image image = SwingFXUtils.toFXImage(bufferedImage, null);
            contactImageView.setImage(image);

            messageText.setText("Image Uploaded!");
        }catch (NullPointerException e){
            System.out.println(e);
            e.printStackTrace();
            messageText.setText("Invalid file upload");
        }
        catch (Exception e){
            System.out.println(e);
            e.printStackTrace();
        }
    }

    //Event handling for setting up the program to add a new contact
    public void addContactPrepare(ActionEvent event) {
        settingToAddContact();
    }


    //Event handling for when one clicks the list view causing you to edit the selected item on the list if you
    // selected one.
    public void selectToEdit(Event mouseEvent) {
        if(contactsList.size() == 0){
            messageText.setText("There is nothing in the contacts list to select");
        }
        else {
            try {
                settingToEdit();
            }catch (IndexOutOfBoundsException e){
                //Nothing happens
            }
            catch (Exception e){
                System.out.println(e);
                e.printStackTrace();
            }
        }
    }


    //Event handling to reset your progress when editing or creating a new contact.
    //When editing, your info returns back to the original information in the selected contact since the
    //last time it was updated. When creating a new contact, all information is forced to be blank.
    public void reset(ActionEvent event) {
        switch (currentStatus){
            case EDITING:
                settingToEdit();
                break;
            case CREATING:
                settingToAddContact();
                break;
        }
    }


    //Uploads changes into the contacts list. If editing, the contact gets updated, when creating a new contact,
    //it gets added to the contacts list. The phone number needs to be valid though.
    public void saveChanges(ActionEvent event) {
        try{
            switch (currentStatus){
                case CREATING:
                    Contact newContact = new Contact(firstNameTextFld.getText(),lastNameTextFld.getText(),
                            emailTextFld.getText(),Long.parseLong(phoneNumberTextFld.getText()),
                            contactImageView.getImage());

                    contactsList.add(newContact);
                    contactsList.sort(Contact::compareTo);
                    contactsFullDisplaysList.clear();
                    for(Contact contact: contactsList) contactsFullDisplaysList.add(new contactsFullDisplay(contact));

                    settingToAddContact();
                    messageText.setText("New contact uploaded!!");
                    break;
                case EDITING:
                    Object selectedObject = contactsListView.getSelectionModel().getSelectedItems().get(0);
                    int index = contactsListView.getItems().indexOf(selectedObject);
                    Contact editedContact = contactsList.get(index);

                    editedContact.setFirstName(firstNameTextFld.getText());
                    editedContact.setLastName(lastNameTextFld.getText());
                    editedContact.setEmail(emailTextFld.getText());
                    editedContact.setPhoneNumber(Long.parseLong(phoneNumberTextFld.getText()));
                    editedContact.setContactPhoto(contactImageView.getImage());

                    contactsList.sort(Contact::compareTo);
                    contactsFullDisplaysList.clear();
                    contactsFullDisplay editedFullDisplay = null;
                    for(Contact contact: contactsList){
                        contactsFullDisplay newContactFullDisplay = new contactsFullDisplay(contact);
                        contactsFullDisplaysList.add(newContactFullDisplay);
                        if(contact==editedContact) editedFullDisplay = newContactFullDisplay;
                    }
                    if(lastNameListSelect.isSelected()){
                        contactsListView.getSelectionModel().select(editedContact);
                    }
                    else {
                        contactsListView.getSelectionModel().select(editedFullDisplay);
                    }
                    messageText.setText("New contact updated!!");
                    break;
            }
        }catch (NumberFormatException e){
            messageText.setText("Input a valid phone number\n"+e.toString());
            System.out.println(e);
            e.printStackTrace();
        }catch (Exception e){
            System.out.println(e);
            messageText.setText(e.toString());
            e.printStackTrace();
        }
    }


    //Sets the program to edit mode for a selected contact
    private void settingToEdit(){
        Contact editableContact;
        if(lastNameListSelect.isSelected()){
            editableContact = (Contact)contactsListView.getSelectionModel().getSelectedItems().get(0);
        }
        else {
            contactsFullDisplay selectedObject = (contactsFullDisplay)contactsListView.getSelectionModel().getSelectedItems().get(0);
            int index = contactsListView.getItems().indexOf(selectedObject);
            editableContact = contactsList.get(index);
        }
        currentStatus = generalStatus.EDITING;
        firstNameTextFld.setText(editableContact.getFirstName());
        lastNameTextFld.setText(editableContact.getLastName());
        emailTextFld.setText(editableContact.getEmail());
        phoneNumberTextFld.setText(Long.toString(editableContact.getPhoneNumber()));
        statusText.setText("Selected Contact From List:\n with the option to edit");
        messageText.setText("");
        contactImageView.setImage(editableContact.getContactPhoto());
    }


    //Sets the program to create new contact mode
    private void settingToAddContact(){
        currentStatus = generalStatus.CREATING;
        firstNameTextFld.setText("");
        lastNameTextFld.setText("");
        emailTextFld.setText("");
        phoneNumberTextFld.setText("");
        statusText.setText("Creating new contact");
        messageText.setText("");
        contactsListView.getSelectionModel().clearSelection();
        contactImageView.setImage(blankProfileImage);
    }

}
