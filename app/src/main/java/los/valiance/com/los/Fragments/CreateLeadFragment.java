package los.valiance.com.los.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import los.valiance.com.los.Activity.MainActivity;
import los.valiance.com.los.Activity.MenuActivity;
import los.valiance.com.los.Database.LocalDatabase;
import los.valiance.com.los.Helper.CurrentDate;
import los.valiance.com.los.Helper.InternetConnectionDetector;
import los.valiance.com.los.Helper.SessionManagement;
import los.valiance.com.los.Model.CoApplicantModel;
import los.valiance.com.los.Model.LeadDetails;
import los.valiance.com.los.Model.UserModel;
import los.valiance.com.los.R;

import static los.valiance.com.los.Helper.Constants.cityTable;
import static los.valiance.com.los.Helper.Constants.leadTypeTable;
import static los.valiance.com.los.Helper.Constants.loanPurposeTable;
import static los.valiance.com.los.Helper.Constants.loanTypeTable;
import static los.valiance.com.los.Helper.Constants.relationshipTable;
import static los.valiance.com.los.Helper.Constants.salesOfficerTable;
import static los.valiance.com.los.Helper.Constants.saveUrl;
import static los.valiance.com.los.Helper.Constants.sourceTable;
import static los.valiance.com.los.Helper.Constants.stateTable;
import static los.valiance.com.los.Helper.Constants.statusTable;
import static los.valiance.com.los.Helper.Constants.teamManagerTable;
import static los.valiance.com.los.Helper.Constants.titleTable;


public class CreateLeadFragment extends Fragment {
   Spinner statusSpinner ;
    Spinner stateSpinner;
    Spinner citySpinner;
    Spinner titleSpinner;
    Spinner sourceSpinner;
    Spinner salesOfficerSpinner;
    Spinner teamManagerSpinner;
    Spinner loanTypeSpinner;
    Spinner loanPurposeSpinner;
    Spinner typeOfEmployeeSpinner;
    Spinner ApplicantTitleSpinner;
    Spinner ApplicantRelationshipSpinner;
    Spinner ApplicantTypeSpinner;
LocalDatabase localDatabase;

    RadioButton otherloandetailsyes,otherloandetailsno,coapplicantdetailsyes,coapplicantdetailsno;
    private Button Save;

    ArrayAdapter<String> defaultadapter;
    ArrayAdapter<String> titleAdapter;
    ArrayAdapter<String> applicantTypeAdapter;
    String[]defaultData={};
    RelativeLayout hiddenlayout;

    ArrayList<String>cityId=new ArrayList<>();
    int counter=0;
       LinearLayout mainLayout;
       ArrayList<CoApplicantModel>coApplicantDetails=new ArrayList<>();

    TextInputLayout coapplicantfirstName,coapplicantIncome,coapplicantlasName,coapplicantExpense;

    TextInputLayout firstName,lastName,emailId,mobileNumber,Address,Pincode,Landmark,Description,loanAmount;
    TextInputLayout dueAmountOfLoan,runningEmi,Income,Expenses,Notes,requestedAmount,requestedLoanTenure;
    String isDataAvailable="0";
    String leadId="";






    private UserModel userModel;
    CheckBox postalAddress;
    private SessionManagement session;
    private String isLoanExist="0";
    private String isApplyingWithCoApplicant="0";

    LinkedHashMap <Integer,String>statusList,titleList,stateList,sourceList,salesOfficerList,teamManagerList,loanTypeList;
    LinkedHashMap <Integer,String> loanPurposeList,employeeTypeList,relationshipList;
    InternetConnectionDetector ICD;
    LeadDetails showLeadDetails;



    public CreateLeadFragment() {

        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        session=new SessionManagement(getContext());

    }



    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {

        Bundle bundle = this.getArguments();
        isDataAvailable = bundle.getString("isDataAvailable");
        localDatabase=LocalDatabase.getHelper(getContext());
        Log.i("objectid", String.valueOf(localDatabase));
        View rootView = inflater.inflate(R.layout.fragment_create_lead, container, false);

        firstName= (TextInputLayout) rootView.findViewById(R.id.FirstName);
       // firstName.setTypeface(Typeface.createFromAsset(getContext().getAssets(),"font/OpenSans-Bold.ttf"));
        lastName= (TextInputLayout) rootView.findViewById(R.id.LastName);
        emailId= (TextInputLayout) rootView.findViewById(R.id.emailId);
        mobileNumber= (TextInputLayout) rootView.findViewById(R.id.phonenumber);
        Address= (TextInputLayout) rootView.findViewById(R.id.Address);
        Pincode= (TextInputLayout) rootView.findViewById(R.id.pincode);
        Landmark= (TextInputLayout) rootView.findViewById(R.id.landmark);
        Description= (TextInputLayout) rootView.findViewById(R.id.description);
        loanAmount= (TextInputLayout) rootView.findViewById(R.id.loanamount);
        dueAmountOfLoan= (TextInputLayout) rootView.findViewById(R.id.dueamountofloan);
        runningEmi= (TextInputLayout) rootView.findViewById(R.id.runningemiofloan);
        Income= (TextInputLayout) rootView.findViewById(R.id.income);
        Expenses= (TextInputLayout) rootView.findViewById(R.id.expenses);
        Notes= (TextInputLayout) rootView.findViewById(R.id.Notes);
        requestedAmount=(TextInputLayout) rootView.findViewById(R.id.RequestedAmount);
        requestedLoanTenure=(TextInputLayout) rootView.findViewById(R.id.RequestedLoanTenure);

        postalAddress= (CheckBox) rootView.findViewById(R.id.postalAddress);

        Log.i("outputofcheck", String.valueOf(postalAddress.isChecked()));
        statusSpinner = (Spinner)rootView.findViewById(R.id.statusspinner);
        stateSpinner=(Spinner)rootView.findViewById(R.id.state);
        citySpinner=(Spinner)rootView.findViewById(R.id.city);
        titleSpinner=(Spinner)rootView.findViewById(R.id.titlespinner);
        sourceSpinner =(Spinner)rootView.findViewById(R.id.source);
        salesOfficerSpinner=(Spinner)rootView.findViewById(R.id.SalesOfficer);
        teamManagerSpinner=(Spinner)rootView.findViewById(R.id.teammanager);
        loanTypeSpinner=(Spinner)rootView.findViewById(R.id.loantype);
        loanPurposeSpinner=(Spinner)rootView.findViewById(R.id.loanpurpose);
        typeOfEmployeeSpinner=(Spinner)rootView.findViewById(R.id.typeofemployee);

        ICD=new InternetConnectionDetector(getContext());


        statusList=localDatabase.getDropDownData(statusTable);
        setDropdownValues(statusSpinner,statusList);
        titleList=localDatabase.getDropDownData(titleTable);
        setDropdownValues(titleSpinner,titleList);
        stateList=localDatabase.getDropDownData(stateTable);
        setDropdownValues(stateSpinner,stateList);
        sourceList=localDatabase.getDropDownData(sourceTable);
        setDropdownValues(sourceSpinner,sourceList);
        salesOfficerList=localDatabase.getDropDownData(salesOfficerTable);
        setDropdownValues(salesOfficerSpinner,salesOfficerList);
        teamManagerList=localDatabase.getDropDownData(teamManagerTable);
        setDropdownValues(teamManagerSpinner,teamManagerList);
        loanTypeList=localDatabase.getDropDownData(loanTypeTable);
        setDropdownValues(loanTypeSpinner,loanTypeList);
        loanPurposeList=localDatabase.getDropDownData(loanPurposeTable);
        setDropdownValues(loanPurposeSpinner,loanPurposeList);
        employeeTypeList=localDatabase.getDropDownData(leadTypeTable);
        setDropdownValues(typeOfEmployeeSpinner,employeeTypeList);


        Save= (Button) rootView.findViewById(R.id.Save);


        userModel=session.getUserDetails();
        defaultadapter  = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_dropdown_item_1line, defaultData);
        titleAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_dropdown_item_1line, defaultData);
        applicantTypeAdapter =new ArrayAdapter<String>(getActivity(), android.R.layout.simple_dropdown_item_1line, defaultData);


        hiddenlayout=(RelativeLayout) rootView.findViewById(R.id.otherloanlayout);
        mainLayout=(LinearLayout)rootView.findViewById(R.id.mainlayout);





        otherloandetailsyes=(RadioButton)rootView.findViewById(R.id.radiobuttonyes);
        otherloandetailsno=(RadioButton)rootView.findViewById(R.id.radiobuttonno);
        coapplicantdetailsyes=(RadioButton)rootView.findViewById(R.id.radioapplicantbuttonyes);
                coapplicantdetailsno=(RadioButton)rootView.findViewById(R.id.radioapplicantbuttonno);
        otherloandetailsno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Toast.makeText(getContext(),"Clicked no",Toast.LENGTH_SHORT).show();
                isLoanExist="0";
                hiddenlayout.setVisibility(View.GONE);
                otherloandetailsyes.setChecked(false);
            }
        });
       otherloandetailsyes.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               isLoanExist="1";
               hiddenlayout.setVisibility(View.VISIBLE);
               otherloandetailsno.setChecked(false);
           }
       });

             coapplicantdetailsyes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isApplyingWithCoApplicant="1";
                // Toast.makeText(getContext(),"Clicked no",Toast.LENGTH_SHORT).show();
                addCoApplicant(inflater,container, null);

        }});
        coapplicantdetailsno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isApplyingWithCoApplicant="0";
                for(CoApplicantModel details :coApplicantDetails)
                {
                    mainLayout.removeView( details.getCoApplicantView());
                }
                coApplicantDetails.clear();
                coapplicantdetailsyes.setChecked(false);

            }});


        stateSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
               // citySpinner.setAdapter(defaultadapter);
                Log.i("newtext","dfdfdfd");
                LinkedHashMap<Integer,String>district=localDatabase.getDistricts(cityTable,position);
                Log.i("hashmapvalue", String.valueOf(district.size()));
                String defaultData[]=new String[district.size()];
                int index=0;
                for (Map.Entry<Integer,String> districtEntry : district.entrySet()) {
                    defaultData[index]=districtEntry.getValue();
                    cityId.add(String.valueOf(districtEntry.getKey()));
                    index+=1;
                }

                defaultadapter  = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_dropdown_item_1line, defaultData);

                Log.i("responseofjson123", String.valueOf(defaultData));
                citySpinner.setAdapter(defaultadapter);
                Log.i("letscheck", String.valueOf(cityId));
                if(isDataAvailable.equals("1"))
                {
                    int indexToSet=session.getTemporaryViewDetails().getDistrict()-Integer.parseInt(cityId.get(0));
                    if(indexToSet<cityId.size())
                    {
                        citySpinner.setSelection(indexToSet);
                    }
                }
                /*String newCityUrl=cityUrl+"/"+position;
                Log.i("newtext",cityUrl);
                retrieveDataForDropdown(newCityUrl,citySpinner, citySpinner.getItemAtPosition(0).toString());*/
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Log.i("rererer","dfdfdf333333333");
            }
        });

        Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              // validate details
                if( checkValidation()) {
                    if(ICD.isConnectingToInternet())
                        saveDataToDb();
                     else
                        saveToLocalDb();

                }
               /* for(CoApplicantModel el:coApplicantDetails)
                {


                }*/
            }
        });

        if(isDataAvailable.equals("1"))
        {

            showLeadDetails=session.getTemporaryViewDetails();
        //    Log.i("this", String.valueOf(showLeadDetails.));
            leadId= String.valueOf(showLeadDetails.getLeadId());
            statusSpinner.setSelection(showLeadDetails.getLeadStatus());
            titleSpinner.setSelection(showLeadDetails.getTitleType());
            firstName.getEditText().setText(showLeadDetails.getFirstName());
            lastName.getEditText().setText(showLeadDetails.getLastName());
            emailId.getEditText().setText(showLeadDetails.getEmailId());
            mobileNumber.getEditText().setText(showLeadDetails.getMobileNumber());
            Address.getEditText().setText(showLeadDetails.getAddressLine1());
            stateSpinner.setSelection(showLeadDetails.getLeadStatus());
            //citySpinner.setSelection(showLeadDetails.getDistrict());
            Pincode.getEditText().setText(showLeadDetails.getPincode());
            postalAddress.setChecked(showLeadDetails.isAddressTrueForPost()==1);
            Landmark.getEditText().setText(showLeadDetails.getLandmark());

           // citySpinner.setSelection(1);
            sourceSpinner.setSelection(showLeadDetails.getLeadSource());
            Log.i("leadsource", String.valueOf(showLeadDetails.getLeadSource()));
            salesOfficerSpinner.setSelection(showLeadDetails.getSalesOfficer());
            teamManagerSpinner.setSelection(showLeadDetails.getTeamManager());
            Description.getEditText().setText(showLeadDetails.getDescription());
            loanTypeSpinner.setSelection(showLeadDetails.getLoanType());
            loanPurposeSpinner.setSelection(showLeadDetails.getLoanPurposeType());
           Log.i("valueofloan", showLeadDetails.isAnyOtherLoanExist());
            otherloandetailsyes.setChecked(showLeadDetails.isAnyOtherLoanExist().equals("1"));
            otherloandetailsno.setChecked(!showLeadDetails.isAnyOtherLoanExist().equals("1"));
            isLoanExist=showLeadDetails.isAnyOtherLoanExist();
           if(showLeadDetails.isAnyOtherLoanExist().equals("1"))
               hiddenlayout.setVisibility(View.VISIBLE);

            loanAmount.getEditText().setText(showLeadDetails.getOtherLoanAmount());
            dueAmountOfLoan.getEditText().setText(showLeadDetails.getOutstandingAmount());
            runningEmi.getEditText().setText(String.valueOf(showLeadDetails.getRunningEmi()));

            typeOfEmployeeSpinner.setSelection(showLeadDetails.getTypeOfEmployment());
            Income.getEditText().setText(String.valueOf(showLeadDetails.getIncome()));
            Expenses.getEditText().setText(String.valueOf(showLeadDetails.getExpense()));
            Notes.getEditText().setText(showLeadDetails.getNotes());
            requestedAmount.getEditText().setText(String.valueOf(showLeadDetails.getRequestedLoanAmount()));
            requestedLoanTenure.getEditText().setText(String.valueOf(showLeadDetails.getRequestedLoanTenureInYears()));
           // Log.i("valueofloan", showLeadDetails.isApplyingWithCoApplicant());
            coapplicantdetailsyes.setChecked(Boolean.parseBoolean(showLeadDetails.isApplyingWithCoApplicant()));
            coapplicantdetailsno.setChecked(!Boolean.parseBoolean(showLeadDetails.isApplyingWithCoApplicant()));
            isApplyingWithCoApplicant= String.valueOf(Boolean.parseBoolean(showLeadDetails.isApplyingWithCoApplicant())?1:0);

            Log.i("valueis", String.valueOf(isApplyingWithCoApplicant));
            if(isApplyingWithCoApplicant.equals("1"))
            {
                try {
                    Log.i("valueis", String.valueOf(showLeadDetails.getLeadCoapplicantDetails()));
                    JSONArray jsonArray=new JSONArray(showLeadDetails.getLeadCoapplicantDetails());
                    for(int coApplicant=0;coApplicant<jsonArray.length();coApplicant++)
                    {
                        JSONObject jsonObject=jsonArray.getJSONObject(coApplicant);
                        addCoApplicant(inflater,container,jsonObject);
                    }
                    Log.i("valueis", String.valueOf(jsonArray));
                } catch (JSONException e) {
                    Log.i("valueis", e.getLocalizedMessage());
                    e.printStackTrace();
                }
            }

        }
        //Log.i("DFDFDF","reachet43dr5");
        return rootView;
    }

    private void saveToLocalDb() {
        JSONArray coApplicantRecord=new JSONArray();
        if(coApplicantDetails.size()==0)
            coApplicantRecord.put(new JSONObject());
        for(CoApplicantModel details:coApplicantDetails)
        {
            JSONObject newModel=new JSONObject();
            try {
                newModel.put("Expence",Integer.parseInt(details.getGetCoapplicantExpense().getEditText().getText().toString()));
                newModel.put("FirstName",details.getFirstName().getEditText().getText().toString());
                newModel.put("Income",Integer.parseInt(details.getCoapplicantIncome().getEditText().getText().toString()));
                newModel.put("LastName",details.getLastName().getEditText().getText().toString());
                newModel.put("RelationShip",details.getRelationship().getSelectedItemPosition());
                newModel.put("TitleType",details.getTitleSpinner().getSelectedItemPosition());
                newModel.put("TypeofEmployee",details.getApplicantType().getSelectedItemPosition());
                //  newModel.put("LeadId",Integer.parseInt(details.getGetCoapplicantEx.getEditText().getText().toString()));


            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        String currentDate=new CurrentDate().getCurrentdate();
        LeadDetails newLead=new LeadDetails();
        newLead.setLeadStatus(statusSpinner.getSelectedItemPosition());
        newLead.setFirstName(firstName.getEditText().getText().toString());
        newLead.setLastName(lastName.getEditText().getText().toString());
        newLead.setEmailId(emailId.getEditText().getText().toString());
        newLead.setMobileNumber(mobileNumber.getEditText().getText().toString());
        newLead.setAddressLine1(Address.getEditText().getText().toString());
        newLead.setDistrict(citySpinner.getSelectedItemPosition());
        newLead.setState(stateSpinner.getSelectedItemPosition());
        newLead.setPincode(Pincode.getEditText().getText().toString());
        newLead.setAddressTrueForPost(postalAddress.isChecked()? 1 : 0);
        newLead.setLandmark(Landmark.getEditText().getText().toString());
        newLead.setLeadCreatedBy(userModel.getUserId());
        newLead.setLeadSource(sourceSpinner.getSelectedItemPosition());
        newLead.setSalesOfficer(salesOfficerSpinner.getSelectedItemPosition());
        newLead.setTeamManager(teamManagerSpinner.getSelectedItemPosition());
        newLead.setDescription(Description.getEditText().getText().toString());
        newLead.setLoanType(loanTypeSpinner.getSelectedItemPosition());
        newLead.setLoanPurposeType(loanPurposeSpinner.getSelectedItemPosition());
        newLead.setIsAnyOtherLoanExist(isLoanExist);
        newLead.setOtherLoanAmount(loanAmount.getEditText().getText().toString());
        newLead.setOutstandingAmount(dueAmountOfLoan.getEditText().getText().toString());

        if(!runningEmi.getEditText().getText().toString().isEmpty())
        newLead.setRunningEmi(Integer.parseInt(runningEmi.getEditText().getText().toString()));
        if(!Income.getEditText().getText().toString().isEmpty())
        newLead.setIncome(Integer.parseInt(Income.getEditText().getText().toString()));
        if(!Expenses.getEditText().getText().toString().isEmpty())
        newLead.setExpense(Integer.parseInt(Expenses.getEditText().getText().toString()));

        newLead.setNotes(Notes.getEditText().getText().toString());
        newLead.setRequestedLoanAmount(Integer.parseInt(requestedAmount.getEditText().getText().toString()));
        newLead.setRequestedLoanTenureInYears(Integer.parseInt(requestedLoanTenure.getEditText().getText().toString()));
        newLead.setStrLeadCreatedDate(currentDate);
        newLead.setStrLeadModifyDate(currentDate);
        newLead.setLoanDate(currentDate);
        newLead.setStrTimeFrameDate(currentDate);
        newLead.setTypeOfEmployment(typeOfEmployeeSpinner.getSelectedItemPosition());
        newLead.setIsApplyingWithCoApplicant(isApplyingWithCoApplicant);
        newLead.setTitleType(titleSpinner.getSelectedItemPosition());
        newLead.setLeadCoapplicantDetails(String.valueOf(coApplicantRecord));

        localDatabase.addLead(newLead);
        Log.i("unsynceddata", String.valueOf(localDatabase.getAllUnsyncedLeads()));


    }

    private void setDropdownValues(Spinner spinnerToSet, LinkedHashMap<Integer, String> dataToSet) {
        String[] defaultData=new String[dataToSet.size()];
        int dataIndex=0;
        for(Map.Entry<Integer, String> data : dataToSet.entrySet()) {
            defaultData[dataIndex] = data.getValue();
            dataIndex += 1;
        }
        ArrayAdapter<String> defaultadapter  = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_dropdown_item_1line, defaultData);
        spinnerToSet.setAdapter(defaultadapter);
    }

    private boolean checkValidation() {
        boolean flag=true;
        if(firstName.getEditText().getText().toString().isEmpty()) {
            firstName.getEditText().setError(getString(R.string.error_field_required));
            flag=false;
        }
        if(lastName.getEditText().getText().toString().isEmpty()) {
            lastName.getEditText().setError(getString(R.string.error_field_required));
            flag=false;
        }
        if(emailId.getEditText().getText().toString().isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(emailId.getEditText().getText()).matches())
        {
            if(emailId.getEditText().getText().toString().isEmpty())
                emailId.getEditText().setError(getString(R.string.error_field_required));
            else
                emailId.getEditText().setError(getString(R.string.error_invalid_email));
            flag=false;
        }

        if(mobileNumber.getEditText().getText().toString().length()<10 || mobileNumber.getEditText().getText().toString().isEmpty())
        {
            if(mobileNumber.getEditText().getText().toString().isEmpty())
               mobileNumber.getEditText().setError(getString(R.string.error_field_required));
            else
                mobileNumber.getEditText().setError(getString(R.string.error_invalid_mobile));
            flag=false;
        }

        if(Address.getEditText().getText().toString().isEmpty()) {
            Address.getEditText().setError(getString(R.string.error_field_required));
            flag=false;
        }

        if(stateSpinner.getSelectedItemPosition()==0) {
            ((TextView)stateSpinner.getSelectedView()).setError(getString(R.string.error_field_required));
            flag=false;
        }

       /* if(citySpinner.getSelectedItemPosition()==0) {
            ((TextView)citySpinner.getSelectedView()).setError(getString(R.string.error_field_required));
            flag=false;
        }*/

        if(Pincode.getEditText().getText().toString().isEmpty() || Pincode.getEditText().getText().toString().length()<6) {
            if(Pincode.getEditText().getText().toString().isEmpty())
            Pincode.getEditText().setError(getString(R.string.error_field_required));
            else
                Pincode.getEditText().setError(getString(R.string.error_invalid_pincode));
            flag=false;
        }

        if(sourceSpinner.getSelectedItemPosition()==0) {
            ((TextView)sourceSpinner.getSelectedView()).setError(getString(R.string.error_field_required));
            flag=false;
        }

        if(salesOfficerSpinner.getSelectedItemPosition()==0) {
            ((TextView)salesOfficerSpinner.getSelectedView()).setError(getString(R.string.error_field_required));
            flag=false;
        }

        if(teamManagerSpinner.getSelectedItemPosition()==0) {
            ((TextView)teamManagerSpinner.getSelectedView()).setError(getString(R.string.error_field_required));
            flag=false;
        }
if(statusSpinner.getSelectedItemPosition()==0)
{
    ((TextView)statusSpinner.getSelectedView()).setError(getString(R.string.error_field_required));
    flag=false;
}

        if(loanTypeSpinner.getSelectedItemPosition()==0) {
            ((TextView)loanTypeSpinner.getSelectedView()).setError(getString(R.string.error_field_required));
            flag=false;
        }

        if(loanPurposeSpinner.getSelectedItemPosition()==0) {
            ((TextView)loanPurposeSpinner.getSelectedView()).setError(getString(R.string.error_field_required));
            flag=false;
        }

        if(requestedAmount.getEditText().getText().toString().isEmpty()) {
            requestedAmount.getEditText().setError(getString(R.string.error_field_required));
            flag=false;
        }

        if(requestedLoanTenure.getEditText().getText().toString().isEmpty()) {
            requestedLoanTenure.getEditText().setError(getString(R.string.error_field_required));
            flag=false;
        }



            /*if(password.getText().toString().isEmpty())
        {
            password.setError("Please Enter Password");
            flag=false;
        }*/
        return flag;



    }

    private void saveDataToDb() {
        JSONArray coApplicantRecord=new JSONArray();
        if(coApplicantDetails.size()==0)
            coApplicantRecord.put(new JSONObject());
        for(CoApplicantModel details:coApplicantDetails)
        {
            JSONObject newModel=new JSONObject();
            try {
                newModel.put("Expence",Integer.parseInt(details.getGetCoapplicantExpense().getEditText().getText().toString()));
                newModel.put("FirstName",details.getFirstName().getEditText().getText().toString());
                newModel.put("Income",Integer.parseInt(details.getCoapplicantIncome().getEditText().getText().toString()));
                newModel.put("LastName",details.getLastName().getEditText().getText().toString());
              //  newModel.put("LeadId",Integer.parseInt(details.getGetCoapplicantEx.getEditText().getText().toString()));
                newModel.put("RelationShip",details.getRelationship().getSelectedItemPosition());
                newModel.put("TitleType",details.getTitleSpinner().getSelectedItemPosition());
                newModel.put("TypeofEmployee",details.getApplicantType().getSelectedItemPosition());


            } catch (JSONException e) {
                e.printStackTrace();
            }
             coApplicantRecord.put(newModel);
        }
        Log.i("valueofjsonarray", String.valueOf(coApplicantRecord));

       String currentDate=new CurrentDate().getCurrentdate();
        Map<String, Object> postParam= new HashMap<String, Object>();

        if(isDataAvailable.equals("1"))
        postParam.put("LeadID",leadId);
        postParam.put("LeadStatus",statusSpinner.getSelectedItemPosition());
        postParam.put("FirstName", firstName.getEditText().getText().toString());
        postParam.put("LastName", lastName.getEditText().getText().toString());
        postParam.put("ContactEmail",emailId.getEditText().getText().toString());
        postParam.put("ContactPhone", mobileNumber.getEditText().getText().toString());
        postParam.put("AddressLine1", Address.getEditText().getText().toString());
       // postParam.put("AddressLine2","Fff");
        postParam.put("District", cityId.get(citySpinner.getSelectedItemPosition()-1));
        postParam.put("State", stateSpinner.getSelectedItemPosition());
        postParam.put("Pin", Pincode.getEditText().getText().toString());

        postParam.put("AddressTrueForPost",postalAddress.isChecked());
        postParam.put("Landmark", Landmark.getEditText().getText().toString());
        postParam.put("LeadCreatedBy",userModel.getUserId()); //login userid

        postParam.put("LeadSource",sourceSpinner.getSelectedItemPosition());
        postParam.put("SalesOfficer", salesOfficerSpinner.getSelectedItemPosition());
        postParam.put("TeamManager", teamManagerSpinner.getSelectedItemPosition());

        postParam.put("Description", Description.getEditText().getText().toString());
        postParam.put("LoanType",loanTypeSpinner.getSelectedItemPosition());
        postParam.put("LoanPurposeType",loanPurposeSpinner.getSelectedItemPosition());

        postParam.put("IsAnyOtherLoansExist",isLoanExist);

        if(loanAmount.getEditText().getText().toString().isEmpty())
            postParam.put("OtherLoanAmount","0");
        else
         postParam.put("OtherLoanAmount",loanAmount.getEditText().getText().toString());
        if(dueAmountOfLoan.getEditText().getText().toString().isEmpty())
            postParam.put("OutStandingAmount", "0");
            else
         postParam.put("OutStandingAmount", dueAmountOfLoan.getEditText().getText().toString()); //dueamount
        if(!runningEmi.getEditText().getText().toString().isEmpty())
          postParam.put("RunningEMI",Integer.parseInt(runningEmi.getEditText().getText().toString()));
        else
            postParam.put("RunningEMI","0");

        if(!Income.getEditText().getText().toString().isEmpty())
        postParam.put("Income",Integer.parseInt(Income.getEditText().getText().toString()));


        if(!Expenses.getEditText().getText().toString().isEmpty())
            postParam.put("Expense",Integer.parseInt(Expenses.getEditText().getText().toString()));




        postParam.put("Notes", Notes.getEditText().getText().toString());
        //
        postParam.put("RequestedLoanAmount",Integer.parseInt(requestedAmount.getEditText().getText().toString()));

        postParam.put("RequestedLoanTenureInYears", Integer.parseInt(requestedAmount.getEditText().getText().toString()));
        postParam.put("strLeadCreatedDate",currentDate); //currentdate
        postParam.put("strLeadModifyDate", currentDate); //current date
        postParam.put("LoanDate", currentDate);  //currendate
        //postParam.put("LoanTypeName", "");\
        postParam.put("strTimeFrameDate", currentDate); //currentdate
        postParam.put("TypeOfEmployement", typeOfEmployeeSpinner.getSelectedItemPosition());
        postParam.put("IsApplyingWithCoApplicant", isApplyingWithCoApplicant);

        postParam.put("TitleType",titleSpinner.getSelectedItemPosition());
        postParam.put("LeadCoapplicantDetails", coApplicantRecord);

        Log.i("jsonsent", String.valueOf(new JSONObject(postParam)));

        JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.POST,saveUrl,new JSONObject(postParam),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        Log.i("responseofjson1",String.valueOf(response));
                        Log.i("responseofjsonurl",String.valueOf(saveUrl));
                        try {
                            Toast.makeText(getContext(),"Created succesfully ",Toast.LENGTH_SHORT).show();
                            }
                         catch (Exception e) {

                            Log.i("error",e.getMessage());
                            e.printStackTrace();
                        }


                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
Log.i("responseofjson1", String.valueOf(error.getMessage()));
                        Toast.makeText(getContext(),"Internal Error",Toast.LENGTH_SHORT).show();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(stringRequest);





    }





    public void addCoApplicant(LayoutInflater inflater, ViewGroup container, JSONObject jsonObject)
    {
        View coapplicantdetails=inflater.inflate(R.layout.coapplicantform, container, false);
        coapplicantfirstName=(TextInputLayout) coapplicantdetails.findViewById(R.id.FirstNameApplicant);
        coapplicantlasName=(TextInputLayout) coapplicantdetails.findViewById(R.id.LastNameApplicant);

        ApplicantTitleSpinner=(Spinner)coapplicantdetails.findViewById(R.id.ApplicantTitle);
        ApplicantTitleSpinner.setAdapter(titleAdapter);

        ApplicantTypeSpinner=(Spinner)coapplicantdetails.findViewById(R.id.TypeOfApplicant);
        ApplicantTypeSpinner.setAdapter(applicantTypeAdapter);

        ApplicantRelationshipSpinner=(Spinner)coapplicantdetails.findViewById(R.id.RelationshipApplicant);
       // ApplicantRelationshipSpinner.setAdapter(defaultadapter);

        relationshipList=localDatabase.getDropDownData(relationshipTable);
        setDropdownValues(ApplicantRelationshipSpinner,relationshipList);
        setDropdownValues(ApplicantTitleSpinner,titleList);
        setDropdownValues(ApplicantTypeSpinner,employeeTypeList);
        //retrieveDataForDropdown(relationshipUrl, ApplicantRelationshipSpinner, ApplicantRelationshipSpinner.getItemAtPosition(0).toString());

        coapplicantIncome=(TextInputLayout) coapplicantdetails.findViewById(R.id.coApplicantincome);
        coapplicantExpense=(TextInputLayout) coapplicantdetails.findViewById(R.id.CoApplicantExpense);

        // lasName=(TextInputLayout) coapplicantdetails.findViewById(R.id.LNameApplicant);





        // firstName.setId(counter);


        mainLayout.addView(coapplicantdetails);

        CoApplicantModel newModel=new CoApplicantModel();
        newModel.setApplicantType(ApplicantTypeSpinner);
        newModel.setCoapplicantIncome(coapplicantIncome);
        newModel.setGetCoapplicantExpense(coapplicantExpense);
        newModel.setTitleSpinner(ApplicantTitleSpinner);
        newModel.setFirstName(coapplicantfirstName);
        newModel.setLastName(coapplicantlasName);
        newModel.setRelationship(ApplicantRelationshipSpinner);
        newModel.setCoApplicantView(coapplicantdetails);

        coApplicantDetails.add(newModel);
        coapplicantdetailsno.setChecked(false);

        if(jsonObject!=null)
        {
            try {
                coapplicantfirstName.getEditText().setText(jsonObject.getString("FirstName").toString());
                coapplicantlasName.getEditText().setText(jsonObject.getString("LastName").toString());
                ApplicantTitleSpinner.setSelection(Integer.parseInt(jsonObject.getString("TitleType")));
                ApplicantTypeSpinner.setSelection(Integer.parseInt(jsonObject.getString("TypeofEmployee")));
                ApplicantRelationshipSpinner.setSelection(Integer.parseInt(jsonObject.getString("RelationShip")));
                coapplicantIncome.getEditText().setText(jsonObject.getString("Income").toString());
                coapplicantExpense.getEditText().setText(jsonObject.getString("Expence").toString());

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        for(CoApplicantModel el:coApplicantDetails)
        {
            Log.i("newtext",el.getRelationship().getSelectedItem().toString());
            Log.i("newtext",el.getTitleSpinner().getSelectedItem().toString());
            Log.i("newtext",el.getApplicantType().getSelectedItem().toString());
        }
    }


    @Override
    public void onDetach() {
        super.onDetach();
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // TODO Add your menu entries here
        super.onCreateOptionsMenu(menu, inflater);
        menu.clear();
        inflater.inflate(R.menu.menu_main, menu);
        MenuItem backItem = menu.findItem(R.id.action_back);
        backItem.setVisible(true);

        MenuItem refreshItem = menu.findItem(R.id.action_refresh);
        refreshItem.setVisible(true);

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_back) {
            if(isDataAvailable.equals("1"))
            {
                Intent intent = new Intent(getActivity(), MainActivity.class);
                intent.putExtra("fragmentnumber","2");
                intent.putExtra("isDataAvailable","0");
                startActivity(intent);
            }
            else
            {
            Intent intent = new Intent(getActivity(), MenuActivity.class);
            startActivity(intent);
        }
        }
        if(id == R.id.action_refresh)
        {
            Toast.makeText(getContext(),"Uner Progress",Toast.LENGTH_SHORT).show();
        }


        return super.onOptionsItemSelected(item);
    }
}
