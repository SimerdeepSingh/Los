package los.valiance.com.los.Model;

import org.json.JSONArray;

/**
 * Created by admin2 on 20-10-2016.
 */

public class LeadDetails {
    private String loanId;
    private String firstName;
    private String AddressLine1;
    private String AddressLine2;
    private String chequeNumber;
    private String emailId;
    private String mobileNumber;
    private String Description;
    private String Landmark;
    private String lastName;
    private String leadCreatedBy;
    private String LoanPurposeTypeName;
    private String  loanTypeName;
    private String  Notes;
    private String  outstandingAmount;
    private String  Pincode;

    public String getLoanId() {
        return loanId;
    }

    public void setLoanId(String loanId) {
        this.loanId = loanId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getAddressLine1() {
        return AddressLine1;
    }

    public void setAddressLine1(String addressLine1) {
        AddressLine1 = addressLine1;
    }

    public String getAddressLine2() {
        return AddressLine2;
    }

    public void setAddressLine2(String addressLine2) {
        AddressLine2 = addressLine2;
    }

    public String getChequeNumber() {
        return chequeNumber;
    }

    public void setChequeNumber(String chequeNumber) {
        this.chequeNumber = chequeNumber;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getLandmark() {
        return Landmark;
    }

    public void setLandmark(String landmark) {
        Landmark = landmark;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLeadCreatedBy() {
        return leadCreatedBy;
    }

    public void setLeadCreatedBy(String leadCreatedBy) {
        this.leadCreatedBy = leadCreatedBy;
    }

    public String getLoanPurposeTypeName() {
        return LoanPurposeTypeName;
    }

    public void setLoanPurposeTypeName(String loanPurposeTypeName) {
        LoanPurposeTypeName = loanPurposeTypeName;
    }

    public String getLoanTypeName() {
        return loanTypeName;
    }

    public void setLoanTypeName(String loanTypeName) {
        this.loanTypeName = loanTypeName;
    }

    public String getNotes() {
        return Notes;
    }

    public void setNotes(String notes) {
        Notes = notes;
    }

    public String getOutstandingAmount() {
        return outstandingAmount;
    }

    public void setOutstandingAmount(String outstandingAmount) {
        this.outstandingAmount = outstandingAmount;
    }

    public String getPincode() {
        return Pincode;
    }

    public void setPincode(String pincode) {
        Pincode = pincode;
    }

    public int getDistrict() {
        return District;
    }

    public void setDistrict(int district) {
        District = district;
    }

    public int getExpense() {
        return Expense;
    }

    public void setExpense(int expense) {
        Expense = expense;
    }

    public int getIncome() {
        return Income;
    }

    public void setIncome(int income) {
        Income = income;
    }

    public int getLeadId() {
        return leadId;
    }

    public void setLeadId(int leadId) {
        this.leadId = leadId;
    }

    public int getLeadSource() {
        return leadSource;
    }

    public void setLeadSource(int leadSource) {
        this.leadSource = leadSource;
    }

    public int getLeadStatus() {
        return leadStatus;
    }

    public void setLeadStatus(int leadStatus) {
        this.leadStatus = leadStatus;
    }

    public int getLoanPurposeType() {
        return loanPurposeType;
    }

    public void setLoanPurposeType(int loanPurposeType) {
        this.loanPurposeType = loanPurposeType;
    }

    public int getLoanType() {
        return loanType;
    }

    public void setLoanType(int loanType) {
        this.loanType = loanType;
    }

    public int getOtherLoanAmount() {
        return otherLoanAmount;
    }

    public void setOtherLoanAmount(int otherLoanAmount) {
        this.otherLoanAmount = otherLoanAmount;
    }

    public int getRequestedLoanAmount() {
        return requestedLoanAmount;
    }

    public void setRequestedLoanAmount(int requestedLoanAmount) {
        this.requestedLoanAmount = requestedLoanAmount;
    }

    public int getRequestedLoanTenureInYears() {
        return requestedLoanTenureInYears;
    }

    public void setRequestedLoanTenureInYears(int requestedLoanTenureInYears) {
        this.requestedLoanTenureInYears = requestedLoanTenureInYears;
    }

    public int getRunningEmi() {
        return runningEmi;
    }

    public void setRunningEmi(int runningEmi) {
        this.runningEmi = runningEmi;
    }

    public int getSalesOfficer() {
        return salesOfficer;
    }

    public void setSalesOfficer(int salesOfficer) {
        this.salesOfficer = salesOfficer;
    }

    public int getState() {
        return State;
    }

    public void setState(int state) {
        State = state;
    }

    public int getTeamManager() {
        return teamManager;
    }

    public void setTeamManager(int teamManager) {
        this.teamManager = teamManager;
    }

    public int getTitleType() {
        return titleType;
    }

    public void setTitleType(int titleType) {
        this.titleType = titleType;
    }

    public int getTypeOfEmployment() {
        return typeOfEmployment;
    }

    public void setTypeOfEmployment(int typeOfEmployment) {
        this.typeOfEmployment = typeOfEmployment;
    }

    public boolean isAddressTrueForPost() {
        return AddressTrueForPost;
    }

    public void setAddressTrueForPost(boolean addressTrueForPost) {
        AddressTrueForPost = addressTrueForPost;
    }

    public boolean isAnyOtherLoanExist() {
        return isAnyOtherLoanExist;
    }

    public void setAnyOtherLoanExist(boolean anyOtherLoanExist) {
        isAnyOtherLoanExist = anyOtherLoanExist;
    }

    public boolean isApplyingWithCoApplicant() {
        return isApplyingWithCoApplicant;
    }

    public void setApplyingWithCoApplicant(boolean applyingWithCoApplicant) {
        isApplyingWithCoApplicant = applyingWithCoApplicant;
    }

    public boolean isProcessingFee() {
        return isProcessingFee;
    }

    public void setProcessingFee(boolean processingFee) {
        isProcessingFee = processingFee;
    }

    private int  District;
    private int  Expense;
    private int  Income;
    private int  leadId;
    private int  leadSource;
    private int  leadStatus;
    private int  loanPurposeType;
    private int  loanType;
    private int  otherLoanAmount;
    private int  requestedLoanAmount;
    private int  requestedLoanTenureInYears;
    private int  runningEmi;
    private int  salesOfficer;
    private int  State;
    private int  teamManager;
    private int  titleType;
    private int  typeOfEmployment;

    private boolean AddressTrueForPost;
    private boolean isAnyOtherLoanExist;
    private boolean isApplyingWithCoApplicant;
    private boolean isProcessingFee;



}
