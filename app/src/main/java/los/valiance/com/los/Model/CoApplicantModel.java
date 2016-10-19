package los.valiance.com.los.Model;

import android.support.design.widget.TextInputLayout;
import android.view.View;
import android.widget.Spinner;


/**
 * Created by admin2 on 18-10-2016.
 */
public class CoApplicantModel {

 Spinner titleSpinner,relationship,applicantType;
    TextInputLayout firstName;
    TextInputLayout lastName;
    TextInputLayout coapplicantIncome;
    View coApplicantView;

    public View getCoApplicantView() {
        return coApplicantView;
    }

    public void setCoApplicantView(View coApplicantView) {
        this.coApplicantView = coApplicantView;
    }

    public Spinner getTitleSpinner() {
        return titleSpinner;
    }

    public void setTitleSpinner(Spinner titleSpinner) {
        this.titleSpinner = titleSpinner;
    }

    public Spinner getRelationship() {
        return relationship;
    }

    public void setRelationship(Spinner relationship) {
        this.relationship = relationship;
    }

    public Spinner getApplicantType() {
        return applicantType;
    }

    public void setApplicantType(Spinner applicantType) {
        this.applicantType = applicantType;
    }

    public TextInputLayout getFirstName() {
        return firstName;
    }

    public void setFirstName(TextInputLayout firstName) {
        this.firstName = firstName;
    }

    public TextInputLayout getLastName() {
        return lastName;
    }

    public void setLastName(TextInputLayout lastName) {
        this.lastName = lastName;
    }

    public TextInputLayout getCoapplicantIncome() {
        return coapplicantIncome;
    }

    public void setCoapplicantIncome(TextInputLayout coapplicantIncome) {
        this.coapplicantIncome = coapplicantIncome;
    }

    public TextInputLayout getGetCoapplicantExpense() {
        return getCoapplicantExpense;
    }

    public void setGetCoapplicantExpense(TextInputLayout getCoapplicantExpense) {
        this.getCoapplicantExpense = getCoapplicantExpense;
    }

    TextInputLayout getCoapplicantExpense;

}
