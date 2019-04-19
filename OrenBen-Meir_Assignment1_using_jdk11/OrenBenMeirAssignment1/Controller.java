package OrenBenMeirAssignment1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.time.DateTimeException;
import java.util.InputMismatchException;

public class Controller {

    private HeartRates yourHeartInfo;

    @FXML
    private TextField firstNameField;

    @FXML
    private TextField lastNameField;

    @FXML
    private TextField monthField;

    @FXML
    private TextField dayField;

    @FXML
    private TextField yearField;

    @FXML
    private TextArea SubmissionReport;

    @FXML
    public void printHeartRateReport(ActionEvent event){
        yourHeartInfo = new HeartRates();
        SubmissionReport.clear();

        try{
            if(isTextFieldBlank(firstNameField)||isTextFieldBlank(lastNameField)) {
                throw new InputMismatchException("Your first and last name can't be blank");
            }

            yourHeartInfo.setFirstName(firstNameField.getText());
            yourHeartInfo.setLastName(lastNameField.getText());

            //The statement below can throw DateTimeException, InputMismatchException, and IllegalArgumentException.
            yourHeartInfo.setDateOfBirth(Integer.parseInt(monthField.getText()),
                    Integer.parseInt(dayField.getText()),Integer.parseInt(yearField.getText()));

            SubmissionReport.appendText(yourHeartInfo.toString());
        }
        catch (NumberFormatException e){
            SubmissionReport.appendText("Submission failed!!!\nMonth, day, and year need to be submitted as positive integers");
        }
        catch (DateTimeException | InputMismatchException | IllegalArgumentException e){
            SubmissionReport.appendText("Submission failed!!!\n"+ e.getLocalizedMessage());
        }
        catch (Exception e){
            SubmissionReport.appendText("Submission failed!!!\n\n"+ e.getStackTrace());
        }
    }

    private boolean isTextFieldBlank(TextField someTextField){
        return someTextField.getText().isBlank();
    }

}
