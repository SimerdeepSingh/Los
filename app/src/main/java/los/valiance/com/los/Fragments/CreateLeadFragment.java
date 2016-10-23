package los.valiance.com.los.Fragments;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
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
import com.google.gson.Gson;


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
import los.valiance.com.los.Helper.SessionManagement;
import los.valiance.com.los.Model.CoApplicantModel;
import los.valiance.com.los.Model.UserModel;
import los.valiance.com.los.R;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

import static los.valiance.com.los.Helper.Constants.cityUrl;
import static los.valiance.com.los.Helper.Constants.leadTypeTable;
import static los.valiance.com.los.Helper.Constants.loanPurposeTable;
import static los.valiance.com.los.Helper.Constants.loanTypeTable;
import static los.valiance.com.los.Helper.Constants.relationshipTable;
import static los.valiance.com.los.Helper.Constants.relationshipUrl;
import static los.valiance.com.los.Helper.Constants.rooturl;
import static los.valiance.com.los.Helper.Constants.salesOfficerTable;
import static los.valiance.com.los.Helper.Constants.saveUrl;
import static los.valiance.com.los.Helper.Constants.sourceTable;
import static los.valiance.com.los.Helper.Constants.stateTable;
import static los.valiance.com.los.Helper.Constants.statusTable;
import static los.valiance.com.los.Helper.Constants.teamManagerTable;
import static los.valiance.com.los.Helper.Constants.titleTable;
import static los.valiance.com.los.Helper.Constants.titleUrl;
import static los.valiance.com.los.Helper.Constants.typeOFEmployeeUrl;


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







    private UserModel userModel;
    CheckBox postalAddress;
    private SessionManagement session;
    private String isLoanExist="0";
    private String isApplyingWithCoApplicant="0";

    LinkedHashMap <Integer,String>statusList,titleList,stateList,sourceList,salesOfficerList,teamManagerList,loanTypeList;
    LinkedHashMap <Integer,String> loanPurposeList,employeeTypeList,relationshipList;
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
        Log.i("DFDFDF","reachet43d");
        View rootView = inflater.inflate(R.layout.fragment_create_lead, container, false);
        localDatabase=new LocalDatabase(getContext());
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

       // retrieveDataForDropdown(statusUrl,statusSpinner,statusSpinner.getItemAtPosition(0).toString());
       // retrieveDataForDropdown(titleUrl,titleSpinner, titleSpinner.getItemAtPosition(0).toString());
       // retrieveDataForDropdown(stateUrl,stateSpinner, stateSpinner.getItemAtPosition(0).toString());
       // retrieveDataForDropdown(sourceUrl,sourceSpinner, sourceSpinner.getItemAtPosition(0).toString());
        //retrieveDataForDropdown(salesOfficerUrl,salesOfficerSpinner, salesOfficerSpinner.getItemAtPosition(0).toString());
      //  retrieveDataForDropdown(teamManagerUrl,teamManagerSpinner, teamManagerSpinner.getItemAtPosition(0).toString());
      //  retrieveDataForDropdown(loanTypeUrl,loanTypeSpinner, loanTypeSpinner.getItemAtPosition(0).toString());
     //   retrieveDataForDropdown(loanPurposeUrl,loanPurposeSpinner, loanPurposeSpinner.getItemAtPosition(0).toString());
     //   retrieveDataForDropdown(typeOFEmployeeUrl,typeOfEmployeeSpinner, typeOfEmployeeSpinner.getItemAtPosition(0).toString());

        Save= (Button) rootView.findViewById(R.id.Save);
        /*titleAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_dropdown_item_1line, TITLE_DATA);
        cityAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_dropdown_item_1line, TITLE_DATA);
        stateAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_dropdown_item_1line, TITLE_DATA);
*/

        userModel=session.getUserDetails();
       defaultadapter  = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_dropdown_item_1line, defaultData);
        titleAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_dropdown_item_1line, defaultData);
        applicantTypeAdapter =new ArrayAdapter<String>(getActivity(), android.R.layout.simple_dropdown_item_1line, defaultData);


        hiddenlayout=(RelativeLayout) rootView.findViewById(R.id.otherloanlayout);
        mainLayout=(LinearLayout)rootView.findViewById(R.id.mainlayout);



//        mainLayout.addView(coapplicantdetails);
      /*  statusSpinner.setAdapter(defaultadapter);
        stateSpinner.setAdapter(defaultadapter);
        citySpinner.setAdapter(defaultadapter);
        titleSpinner.setAdapter(defaultadapter);
        sourceSpinner.setAdapter(defaultadapter);
        salesOfficerSpinner.setAdapter(defaultadapter);
        salesOfficerSpinner.setAdapter(defaultadapter);
        teamManagerSpinner.setAdapter(defaultadapter);
        loanTypeSpinner.setAdapter(defaultadapter);
        loanPurposeSpinner.setAdapter(defaultadapter);
        typeOfEmployeeSpinner.setAdapter(defaultadapter);*/




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
                addCoApplicant(inflater,container);

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
                String newCityUrl=cityUrl+"/"+position;
                Log.i("newtext",cityUrl);
                retrieveDataForDropdown(newCityUrl,citySpinner, citySpinner.getItemAtPosition(0).toString());
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
                if( checkValidation())
                saveDataToDb();
               /* for(CoApplicantModel el:coApplicantDetails)
                {


                }*/
            }
        });
        //Log.i("DFDFDF","reachet43dr5");
        return rootView;
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

        if(citySpinner.getSelectedItemPosition()==0) {
            ((TextView)citySpinner.getSelectedView()).setError(getString(R.string.error_field_required));
            flag=false;
        }

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
            //newModel
           // String =new Gson().toJson(details);
           // Log.i("valueofjsonarray", json);
        }
       // JSONArray coApplicantRecord = new JSONArray(coApplicantDetails);
        Log.i("valueofjsonarray", String.valueOf(coApplicantRecord));

      //  coApplicantRecord.put(test);
       String currentDate=new CurrentDate().getCurrentdate();
        Map<String, Object> postParam= new HashMap<String, Object>();

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
         postParam.put("OtherLoanAmount",loanAmount.getEditText().getText().toString());
         postParam.put("OutStandingAmount", dueAmountOfLoan.getEditText().getText().toString()); //dueamount
        if(!runningEmi.getEditText().getText().toString().isEmpty())
          postParam.put("RunningEMI",Integer.parseInt(runningEmi.getEditText().getText().toString()));
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
                        Log.i("responseofjson1",String.valueOf(saveUrl));
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

                        Toast.makeText(getContext(),"Internal Error",Toast.LENGTH_SHORT).show();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(stringRequest);





    }





    public void addCoApplicant(LayoutInflater inflater, ViewGroup container)
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

   public void retrieveDataForDropdown(String url, final Spinner spinner, final String defaultDataForSpinner)
    {
        JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.GET,url,null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        Log.i("responseofjson123",String.valueOf(response));
                        try {

                            JSONArray listOfValues = response.getJSONArray("DataList");
                            defaultData= new String[listOfValues.length()+1];
                            defaultData[0]=defaultDataForSpinner;
                            cityId.clear();
                            for(int value=0;value<listOfValues.length();value++)
                            {
                                 JSONObject jsonValue=listOfValues.getJSONObject(value);
                                 cityId.add(jsonValue.get("ID").toString());
                                 defaultData[value+1]= jsonValue.get("Name").toString();
                                // Log.i("responseofjson123", def);
                            }
                            defaultadapter  = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_dropdown_item_1line, defaultData);
                            //defaultadapter.add("Please Select");
                            Log.i("responseofjson123", String.valueOf(defaultData));
                            spinner.setAdapter(defaultadapter);
                            if(spinner.getId()==R.id.titlespinner)
                                titleAdapter=defaultadapter;
                            if(spinner.getId()==R.id.typeofemployee)
                                applicantTypeAdapter=defaultadapter;
                        } catch (Exception e) {
                           Log.i("error",e.getMessage());
                            e.printStackTrace();
                        }


                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                       // Toast.makeText(LoginActivity.this,error.getMessage(),Toast.LENGTH_SHORT).show();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(stringRequest);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // TODO Add your menu entries here
        super.onCreateOptionsMenu(menu, inflater);
        menu.clear();
        inflater.inflate(R.menu.menu_main, menu);
        MenuItem backItem = menu.findItem(R.id.action_back);
        backItem.setVisible(true);

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_back) {
            Intent intent = new Intent(getActivity(), MenuActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}
