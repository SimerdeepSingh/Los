package los.valiance.com.los.Fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
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
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;


import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import los.valiance.com.los.Activity.LoginActivity;
import los.valiance.com.los.Activity.MenuActivity;
import los.valiance.com.los.Helper.CurrentDate;
import los.valiance.com.los.Helper.SessionManagement;
import los.valiance.com.los.Model.CoApplicantModel;
import los.valiance.com.los.Model.UserModel;
import los.valiance.com.los.R;

import static los.valiance.com.los.Helper.Constants.rooturl;


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


    RadioButton otherloandetailsyes,otherloandetailsno,coapplicantdetailsyes,coapplicantdetailsno;
    private Button Save;

    ArrayAdapter<String> defaultadapter;
    ArrayAdapter<String> titleAdapter;
    ArrayAdapter<String> applicantTypeAdapter;
    String[]defaultData={};
    RelativeLayout hiddenlayout;
    int counter=0;
       LinearLayout mainLayout;
       ArrayList<CoApplicantModel>coApplicantDetails=new ArrayList<>();

    TextInputLayout firstName,coapplicantIncome,lasName,coapplicantExpense;
    private String statusUrl=rooturl+"/GetAllLeadStatus";//";
    private String titleUrl=rooturl+"/GetTitleTypes";//";
    private String stateUrl=rooturl+"/GetAllState";//";
    private String sourceUrl=rooturl+"/GetAllLeadSource";//";
    private String salesOfficerUrl=rooturl+"/GetAllSalesOfficers";//";
    private String teamManagerUrl=rooturl+"/GetAllTeamManagers";
    private String loanTypeUrl=rooturl+"/GetAllLoanType";//";
    private String loanPurposeUrl=rooturl+"/GetAllSalesOfficers";//";
    private String typeOFEmployeeUrl=rooturl+"/GetAllEmployeeType";//";
    private String relationshipUrl=rooturl+"/GetAllRelationshipType";//";
    private String cityUrl=rooturl+"/GetAllDistrict";//";

    private String saveUrl=rooturl+"/AddUpdateLoanLeadEnquiry";

    private UserModel userModel;
    private SessionManagement session;
    public CreateLeadFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        session=new SessionManagement(getContext());

    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        Log.i("DFDFDF","reachet43d");
        View rootView = inflater.inflate(R.layout.fragment_create_lead, container, false);

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

        retrieveDataForDropdown(statusUrl,statusSpinner);
        retrieveDataForDropdown(titleUrl,titleSpinner);
        retrieveDataForDropdown(stateUrl,stateSpinner);
        retrieveDataForDropdown(sourceUrl,sourceSpinner);
        retrieveDataForDropdown(salesOfficerUrl,salesOfficerSpinner);
        retrieveDataForDropdown(teamManagerUrl,teamManagerSpinner);
        retrieveDataForDropdown(loanTypeUrl,loanTypeSpinner);
        retrieveDataForDropdown(loanPurposeUrl,loanPurposeSpinner);
        retrieveDataForDropdown(typeOFEmployeeUrl,typeOfEmployeeSpinner);

        Save= (Button) rootView.findViewById(R.id.Save);
        /*titleAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_dropdown_item_1line, TITLE_DATA);
        cityAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_dropdown_item_1line, TITLE_DATA);
        stateAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_dropdown_item_1line, TITLE_DATA);
*/

        userModel=session.getUserDetails();
       defaultadapter  = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_dropdown_item_1line, defaultData);
        titleAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_dropdown_item_1line, defaultData);
        applicantTypeAdapter =new ArrayAdapter<String>(getActivity(), android.R.layout.simple_dropdown_item_1line, defaultData);

        statusSpinner.setAdapter(defaultadapter);
        hiddenlayout=(RelativeLayout) rootView.findViewById(R.id.otherloanlayout);
        mainLayout=(LinearLayout)rootView.findViewById(R.id.mainlayout);



//        mainLayout.addView(coapplicantdetails);
        stateSpinner.setAdapter(defaultadapter);
        citySpinner.setAdapter(defaultadapter);
        titleSpinner.setAdapter(defaultadapter);
        sourceSpinner.setAdapter(defaultadapter);
        salesOfficerSpinner.setAdapter(defaultadapter);
        salesOfficerSpinner.setAdapter(defaultadapter);
        teamManagerSpinner.setAdapter(defaultadapter);
        loanTypeSpinner.setAdapter(defaultadapter);
        loanPurposeSpinner.setAdapter(defaultadapter);
        typeOfEmployeeSpinner.setAdapter(defaultadapter);




        otherloandetailsyes=(RadioButton)rootView.findViewById(R.id.radiobuttonyes);
        otherloandetailsno=(RadioButton)rootView.findViewById(R.id.radiobuttonno);
        coapplicantdetailsyes=(RadioButton)rootView.findViewById(R.id.radioapplicantbuttonyes);
                coapplicantdetailsno=(RadioButton)rootView.findViewById(R.id.radioapplicantbuttonno);
        otherloandetailsno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Toast.makeText(getContext(),"Clicked no",Toast.LENGTH_SHORT).show();
                hiddenlayout.setVisibility(View.GONE);
                otherloandetailsno.setChecked(false);
            }
        });
       otherloandetailsyes.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               hiddenlayout.setVisibility(View.VISIBLE);
               otherloandetailsyes.setChecked(false);
           }
       });

             coapplicantdetailsyes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Toast.makeText(getContext(),"Clicked no",Toast.LENGTH_SHORT).show();
                addCoApplicant(inflater,container);

        }});
        coapplicantdetailsno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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
                citySpinner.setAdapter(defaultadapter);
                Log.i("newtext","dfdfdfd");
                String newCityUrl=cityUrl+"/"+position;
                Log.i("newtext",cityUrl);
                retrieveDataForDropdown(newCityUrl,citySpinner);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Log.i("rererer","dfdfdf333333333");
            }
        });

        Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveDataToDb();
               /* for(CoApplicantModel el:coApplicantDetails)
                {


                }*/
            }
        });
        //Log.i("DFDFDF","reachet43dr5");
        return rootView;
    }

    private void saveDataToDb() {
        JSONArray coApplicantRecord=new JSONArray();
        JSONObject test=new JSONObject();
        coApplicantRecord.put(test);
        String currentDate=new CurrentDate().getCurrentdate();
        Map<String, Object> postParam= new HashMap<String, Object>();
        //postParam.put("LeadID",0);
        postParam.put("FirstName", "Fff");
        postParam.put("LastName", "Fff");
        postParam.put("ContactEmail","s@f.com");
        postParam.put("ContactPhone", "1234567890");
        postParam.put("AddressLine1", "Fff");
       // postParam.put("AddressLine2","Fff");
        postParam.put("District", 565);
        postParam.put("State", 2);
        postParam.put("Pin", "110018");
        //postParam.put("AddressTrueForPost",true);
        postParam.put("Landmark", "Fff");

        //postParam.put("LeadCreatedBy","11");
        postParam.put("LeadSource", 2);
        postParam.put("LeadStatus", 1);

       // postParam.put("Description", "Fff");
        postParam.put("LoanType",1);
        postParam.put("LoanPurposeType",1);
      //  postParam.put("LoanTypeName", "");

    //    postParam.put("strTimeFrameDate", "");
        postParam.put("IsAnyOtherLoansExist","0");
      //  postParam.put("OtherLoansBankName", "Fff");
       // postParam.put("OtherLoanAmount", 1);

       // postParam.put("Income", 1);
      //  postParam.put("Expense",1);
       // postParam.put("Notes", "Fff");
        postParam.put("RequestedLoanAmount", 1);

        postParam.put("RequestedLoanTenureInYears", 1);
      //  postParam.put("strLeadCreatedDate","");
      //  postParam.put("strLeadModifyDate", "");
      //  postParam.put("OutStandingAmount", "12");

        //postParam.put("LoanDate", "");
       // postParam.put("RunningEMI",1);
       // postParam.put("IsProcessingfee", false);
      //  postParam.put("TypeOfEmployement", 1);

       // postParam.put("ChequeNumber", "");
      //  postParam.put("NameOnCheque","");
        postParam.put("IsApplyingWithCoApplicant", "0");
        postParam.put("SalesOfficer", 1);

        postParam.put("TeamManager", 1);
        postParam.put("TitleType",1);
        postParam.put("LeadCoapplicantDetails", coApplicantRecord);
       // postParam.put("ProcessingFeesDetails", "");

Log.i("jsonsent", String.valueOf(new JSONObject(postParam)));

        JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.POST,saveUrl,new JSONObject(postParam),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        Log.i("responseofjson1",String.valueOf(response));
                        Log.i("responseofjson1",String.valueOf(saveUrl));
                        try {

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
        firstName=(TextInputLayout) coapplicantdetails.findViewById(R.id.FirstNameApplicant);
        lasName=(TextInputLayout) coapplicantdetails.findViewById(R.id.LastNameApplicant);

        ApplicantTitleSpinner=(Spinner)coapplicantdetails.findViewById(R.id.ApplicantTitle);
        ApplicantTitleSpinner.setAdapter(titleAdapter);

        ApplicantTypeSpinner=(Spinner)coapplicantdetails.findViewById(R.id.TypeOfApplicant);
        ApplicantTypeSpinner.setAdapter(applicantTypeAdapter);

        ApplicantRelationshipSpinner=(Spinner)coapplicantdetails.findViewById(R.id.RelationshipApplicant);
        ApplicantRelationshipSpinner.setAdapter(defaultadapter);
        retrieveDataForDropdown(relationshipUrl, ApplicantRelationshipSpinner);

        coapplicantIncome=(TextInputLayout) coapplicantdetails.findViewById(R.id.coApplicantincome);
        coapplicantExpense=(TextInputLayout) coapplicantdetails.findViewById(R.id.CoApplicantExpense);

        // lasName=(TextInputLayout) coapplicantdetails.findViewById(R.id.LNameApplicant);


        if(titleAdapter.getCount()==0)
            retrieveDataForDropdown(titleUrl, ApplicantTitleSpinner);
        if(applicantTypeAdapter.getCount()==0)
            retrieveDataForDropdown(typeOFEmployeeUrl, ApplicantTypeSpinner);


        // firstName.setId(counter);


        mainLayout.addView(coapplicantdetails);

        CoApplicantModel newModel=new CoApplicantModel();
        newModel.setApplicantType(ApplicantTypeSpinner);
        newModel.setCoapplicantIncome(coapplicantIncome);
        newModel.setGetCoapplicantExpense(coapplicantExpense);
        newModel.setTitleSpinner(ApplicantTitleSpinner);
        newModel.setFirstName(firstName);
        newModel.setLastName(lasName);
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

    public void retrieveDataForDropdown(String url, final Spinner spinner)
    {
        JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.GET,url,null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        Log.i("responseofjson123",String.valueOf(response));
                        try {

                            JSONArray listOfValues = response.getJSONArray("DataList");
                            defaultData= new String[listOfValues.length()+1];
                           defaultData[0]="Please Select";
                            for(int value=0;value<listOfValues.length();value++)
                            {
                                 JSONObject jsonValue=listOfValues.getJSONObject(value);
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

    private void showJSON(String json){
        JSONObject jsonRow;
        Log.i("responseofjson",json);
        //  jsonRow=new JSONObject(json);
    }
}
