package orenBenMeirMortgageCalculator;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;


public class Controller implements Initializable{
    @FXML
    TextField purchasePriceTextField;
    @FXML
    TextField downPaymentTextField;
    @FXML
    TextField interestRatePercentTextField;
    @FXML
    Slider yearsSlider;
    @FXML
    Label yearsLabel;
    @FXML
    Label errorLabel;
    @FXML
    TextField loanAmountTextField;
    @FXML
    TextField monthlyPayment10YearsTextField;
    @FXML
    TextField monthlyPayment20YearsTextField;
    @FXML
    TextField monthlyPayment30YearsTextField;
    @FXML
    TextField customMonthlyPaymentTextField;
    private int years;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        yearsSlider.valueProperty().addListener(
                (observable, oldValue, newValue) -> {
                    years = newValue.intValue();
                    yearsLabel.setText(String.format("%d years", years));
                 }
        );
        years = 1;
    }

    private double loanAmount(double purchasePrice, double downPayment){
        return purchasePrice - downPayment;
    }
    private double monthlyPayment(double purchasePrice, double downPayment, double interestRatePercent, double years){
        double interestRate = interestRatePercent/100;
        double futureValueMultiplier = Math.pow(1+interestRate/12, 12*years);
        if(futureValueMultiplier==1){
            return loanAmount(purchasePrice, downPayment)/(12*years);
        }
        else{
            double futureLoanValue = loanAmount(purchasePrice, downPayment)*futureValueMultiplier*interestRate/12;
            return futureLoanValue/(futureValueMultiplier - 1);
        }
    }

    public void calculateAction(ActionEvent event) {
        try {
            double purchasePrice = Double.parseDouble(purchasePriceTextField.getText());
            double downPayment = Double.parseDouble(downPaymentTextField.getText());
            double interestRatePercent = Double.parseDouble(interestRatePercentTextField.getText());

            double loanAmountValue = loanAmount(purchasePrice, downPayment);
            double monthlyPayment10Years = monthlyPayment(purchasePrice, downPayment, interestRatePercent, 10);
            double monthlyPayment20Years = monthlyPayment(purchasePrice, downPayment, interestRatePercent, 20);
            double monthlyPayment30Years = monthlyPayment(purchasePrice, downPayment, interestRatePercent, 30);
            double customMonthlyPayment = monthlyPayment(purchasePrice, downPayment, interestRatePercent, years);

            loanAmountTextField.setText(String.format("%.2f", loanAmountValue));
            monthlyPayment10YearsTextField.setText(String.format("%.2f", monthlyPayment10Years));
            monthlyPayment20YearsTextField.setText(String.format("%.2f", monthlyPayment20Years));
            monthlyPayment30YearsTextField.setText(String.format("%.2f", monthlyPayment30Years));
            customMonthlyPaymentTextField.setText(String.format("%.2f", customMonthlyPayment));
            errorLabel.setText("");
        }
        catch (NumberFormatException ex){
            errorLabel.setText("Invalid input");
            loanAmountTextField.setText("");
            monthlyPayment10YearsTextField.setText("");
            monthlyPayment20YearsTextField.setText("");
            monthlyPayment30YearsTextField.setText("");
            customMonthlyPaymentTextField.setText("");
        }
    }
}
