package los.valiance.com.los.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import los.valiance.com.los.Model.LeadDetails;

import static los.valiance.com.los.Helper.Constants.cityTable;
import static los.valiance.com.los.Helper.Constants.createLeadTable;
import static los.valiance.com.los.Helper.Constants.leadTypeTable;
import static los.valiance.com.los.Helper.Constants.loanPurposeTable;
import static los.valiance.com.los.Helper.Constants.loanTypeTable;
import static los.valiance.com.los.Helper.Constants.relationshipTable;
import static los.valiance.com.los.Helper.Constants.salesOfficerTable;
import static los.valiance.com.los.Helper.Constants.sourceTable;
import static los.valiance.com.los.Helper.Constants.stateTable;
import static los.valiance.com.los.Helper.Constants.statusTable;
import static los.valiance.com.los.Helper.Constants.teamManagerTable;
import static los.valiance.com.los.Helper.Constants.titleTable;

/**
 * Created by admin2 on 21-10-2016.
 */

public class LocalDatabase extends SQLiteOpenHelper {
    LinkedHashMap<Integer, String> dropdownDataList;
    ArrayList<LeadDetails> leadDetails;

    public LocalDatabase(Context context) {
        super(context, "Los", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String leadStatusList = "create table " + statusTable + "(Id Int ,Name String)";
        String leadTitleList = "create table " + titleTable + "(Id Int ,Name String)";
        String StateList = "create table " + stateTable + "(Id Int ,Name String)";
        String CityList = "create table CityList" + cityTable + "(Id Int ,Name String)";
        String SourceList = "create table " + sourceTable + "(Id Int ,Name String)";
        String SalesOfficerList = "create table " + salesOfficerTable + "(Id Int ,Name String)";
        String TeamManagerList = "create table " + teamManagerTable + "(Id Int ,Name String)";
        String LoanTypeList = "create table " + loanTypeTable + "(Id Int ,Name String)";
        String LoanPurposeList = "create table " + loanPurposeTable + "(Id Int ,Name String)";
        String TypeList = "create table " + leadTypeTable + "(Id Int ,Name String)";
        String RelationshipList = "create table " + relationshipTable + "(Id Int ,Name String)";
        String LeadCreation = "create table " + createLeadTable + "(LeadStatus Int,FirstName String,LastName String,ContactEmail String,ContactPhone String " +
                ",AddressLine1 String,District Int,State Int,Pin String,AddressTrueForPost Int ,Landmark String,LeadCreatedBy Int, LeadSource Int" +
                ",SalesOfficer Int,TeamManager Int,Description String,LoanType Int,LoanPurposeType Int,IsAnyOtherLoansExist String,OtherLoanAmount String" +
                ",OutStandingAmount String,RunningEMI Int,Income Int,Expense Int,Notes String,RequestedLoanAmount Int,RequestedLoanTenureInYears Int" +
                ",strLeadCreatedDate String,strLeadModifyDate String,LoanDate String,strTimeFrameDate String,TypeOfEmployement Int,IsApplyingWithCoApplicant String" +
                ",TitleType Int,LeadCoapplicantDetails String)";
        // String LeadDetails=""

        db.execSQL(leadStatusList);
        db.execSQL(leadTitleList);
        db.execSQL(StateList);
        db.execSQL(CityList);
        db.execSQL(SourceList);
        db.execSQL(SalesOfficerList);
        db.execSQL(TeamManagerList);
        db.execSQL(LoanTypeList);
        db.execSQL(LoanPurposeList);
        db.execSQL(TypeList);
        db.execSQL(RelationshipList);
        db.execSQL(LeadCreation);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void addLead(LeadDetails leadDetails) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("LeadStatus", leadDetails.getLeadStatus());
        values.put("FirstName", leadDetails.getFirstName());
        values.put("LastName", leadDetails.getLastName());
        values.put("ContactEmail", leadDetails.getEmailId());
        values.put("ContactPhone", leadDetails.getMobileNumber());
        values.put("AddressLine1", leadDetails.getAddressLine1());
        values.put("District", leadDetails.getDistrict());
        values.put("State", leadDetails.getState());
        values.put("Pin", leadDetails.getPincode());
        values.put("AddressTrueForPost", leadDetails.isAddressTrueForPost());
        values.put("Landmark", leadDetails.getLandmark());
        values.put("LeadCreatedBy", leadDetails.getLeadCreatedBy());
        values.put("LeadSource", leadDetails.getLeadSource());
        values.put("SalesOfficer", leadDetails.getSalesOfficer());
        values.put("TeamManager", leadDetails.getTeamManager());
        values.put("Description", leadDetails.getDescription());
        values.put("LoanType", leadDetails.getLoanType());
        values.put("LoanPurposeType", leadDetails.getLoanPurposeType());
        values.put("IsAnyOtherLoansExist", leadDetails.isAnyOtherLoanExist());
        values.put("OtherLoanAmount", leadDetails.getOtherLoanAmount());
        values.put("OutStandingAmount", leadDetails.getOutstandingAmount());
        values.put("RunningEMI", leadDetails.getRunningEmi());
        values.put("Income", leadDetails.getIncome());
        values.put("Expense", leadDetails.getExpense());
        values.put("Notes", leadDetails.getNotes());
        values.put("RequestedLoanAmount", leadDetails.getRequestedLoanAmount());
        values.put("RequestedLoanTenureInYears", leadDetails.getRequestedLoanTenureInYears());
        values.put("strLeadCreatedDate", leadDetails.getStrLeadCreatedDate());
        values.put("strLeadModifyDate", leadDetails.getStrLeadModifyDate());
        values.put("LoanDate", leadDetails.getLoanDate());
        values.put("strTimeFrameDate", leadDetails.getStrTimeFrameDate());
        values.put("TypeOfEmployement", leadDetails.getTypeOfEmployment());
        values.put("IsApplyingWithCoApplicant", leadDetails.isApplyingWithCoApplicant());
        values.put("TitleType", leadDetails.getTitleType());
        values.put("LeadCoapplicantDetails", leadDetails.getLeadCoapplicantDetails());

        sqLiteDatabase.insert(createLeadTable, null, values);

    }

    public ArrayList<LeadDetails> getAllUnsyncedLeads() {
        leadDetails = new ArrayList<>();
        String fetchdata = "select * from " + createLeadTable;
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(fetchdata, null);

        if (cursor.moveToFirst()) {
            do {
                LeadDetails newLead = new LeadDetails();
                newLead.setLeadStatus(cursor.getInt(0));
                newLead.setFirstName(cursor.getString(1));
                newLead.setLastName(cursor.getString(2));
                newLead.setEmailId(cursor.getString(3));
                newLead.setMobileNumber(cursor.getString(4));
                newLead.setAddressLine1(cursor.getString(5));
                newLead.setDistrict(cursor.getInt(6));
                newLead.setState(cursor.getInt(7));
                newLead.setPincode(cursor.getString(8));
                newLead.setAddressTrueForPost(cursor.getInt(9));
                newLead.setLandmark(cursor.getString(10));
                newLead.setLeadCreatedBy(cursor.getString(11));
                newLead.setLeadSource(cursor.getInt(12));
                newLead.setSalesOfficer(cursor.getInt(13));
                newLead.setTeamManager(cursor.getInt(14));
                newLead.setDescription(cursor.getString(15));
                newLead.setLoanType(cursor.getInt(16));
                newLead.setLoanPurposeType(cursor.getInt(17));
                newLead.setIsAnyOtherLoanExist(cursor.getString(18));
                newLead.setOtherLoanAmount(cursor.getString(19));
                newLead.setOutstandingAmount(cursor.getString(20));
                newLead.setRunningEmi(cursor.getInt(21));
                newLead.setIncome(cursor.getInt(22));
                newLead.setExpense(cursor.getInt(23));
                newLead.setNotes(cursor.getString(24));
                newLead.setRequestedLoanAmount(cursor.getInt(25));
                newLead.setRequestedLoanTenureInYears(cursor.getInt(26));
                newLead.setStrLeadCreatedDate(cursor.getString(27));
                newLead.setStrLeadModifyDate(cursor.getString(28));
                newLead.setLoanDate(cursor.getString(29));
                newLead.setStrTimeFrameDate(cursor.getString(30));
                newLead.setTypeOfEmployment(cursor.getInt(31));
                newLead.setIsApplyingWithCoApplicant(cursor.getString(32));
                newLead.setTitleType(cursor.getInt(33));
                newLead.setLeadCoapplicantDetails(cursor.getString(34));
                leadDetails.add(newLead);
            } while (cursor.moveToNext());
        }
        sqLiteDatabase.close();
        return leadDetails;
    }

    public void addDropDownData(String tableName, LinkedHashMap<Integer, String> dropdownData) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        for (Map.Entry<Integer, String> data : dropdownData.entrySet()) {
            ContentValues values = new ContentValues();
            values.put("Id", data.getKey());
            values.put("Name", data.getValue());
            sqLiteDatabase.insert(tableName, null, values);
        }
    }

    public LinkedHashMap<Integer, String> getDropDownData(String tableName) {
        dropdownDataList = new LinkedHashMap<>();
        String fetchdata = "select * from " + tableName;
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(fetchdata, null);

        if (cursor.moveToFirst()) {
            do {
                dropdownDataList.put(cursor.getInt(0), cursor.getString(1));
            } while (cursor.moveToNext());
        }
        sqLiteDatabase.close();
        return dropdownDataList;
    }


}
