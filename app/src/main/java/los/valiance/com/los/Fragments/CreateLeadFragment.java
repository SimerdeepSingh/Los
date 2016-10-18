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
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import los.valiance.com.los.Activity.MenuActivity;
import los.valiance.com.los.Model.UserModel;
import los.valiance.com.los.R;

import static los.valiance.com.los.Helper.Constants.rooturl;


public class CreateLeadFragment extends Fragment {
    MaterialBetterSpinner statusSpinner ;
    MaterialBetterSpinner stateSpinner;
    MaterialBetterSpinner citySpinner;
    MaterialBetterSpinner titleSpinner;
    MaterialBetterSpinner sourceSpinner;
    MaterialBetterSpinner salesOfficerSpinner;
    MaterialBetterSpinner teamManagerSpinner;
    MaterialBetterSpinner loanTypeSpinner;
    MaterialBetterSpinner loanPurposeSpinner;

    RadioButton otherloandetailsyes,otherloandetailsno,coapplicantdetailsyes,coapplicantdetailsno;


    ArrayAdapter<String> defaultadapter;
    String[]defaultData={};
    RelativeLayout hiddenlayout;
    int counter=0;
       LinearLayout mainLayout;
       ArrayList<TextInputLayout>coapplicantval=new ArrayList<>();

    TextInputLayout firstName,coapplicant;
    private String statusUrl=rooturl+"/GetAllLeadStatus";//";
    private String titleUrl=rooturl+"/GetTitleTypes";//";
    private String stateUrl=rooturl+"/GetAllState";//";
    private String sourceUrl=rooturl+"/GetAllLeadSource";//";
    private String salesOfficerUrl=rooturl+"/GetAllSalesOfficers";//";
    private String loanTypeUrl=rooturl+"/GetAllSalesOfficers";//";
    private String loanPurposeUrl=rooturl+"/GetAllSalesOfficers";//";

    public CreateLeadFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        Log.i("DFDFDF","reachet43d");
        View rootView = inflater.inflate(R.layout.fragment_create_lead, container, false);

        statusSpinner = (MaterialBetterSpinner)rootView.findViewById(R.id.statusspinner);
        stateSpinner=(MaterialBetterSpinner)rootView.findViewById(R.id.state);
        citySpinner=(MaterialBetterSpinner)rootView.findViewById(R.id.city);
        titleSpinner=(MaterialBetterSpinner)rootView.findViewById(R.id.titlespinner);
        sourceSpinner =(MaterialBetterSpinner)rootView.findViewById(R.id.source);
        salesOfficerSpinner=(MaterialBetterSpinner)rootView.findViewById(R.id.SalesOfficer);

        retrieveDataForDropdown(statusUrl,statusSpinner);
        retrieveDataForDropdown(titleUrl,titleSpinner);
        retrieveDataForDropdown(stateUrl,stateSpinner);
        retrieveDataForDropdown(sourceUrl,sourceSpinner);
        retrieveDataForDropdown(salesOfficerUrl,salesOfficerSpinner);


        /*titleAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_dropdown_item_1line, TITLE_DATA);
        cityAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_dropdown_item_1line, TITLE_DATA);
        stateAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_dropdown_item_1line, TITLE_DATA);


*/
       defaultadapter  = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_dropdown_item_1line, defaultData);
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

                View coapplicantdetails=inflater.inflate(R.layout.coapplicantform, container, false);
                firstName=(TextInputLayout) coapplicantdetails.findViewById(R.id.FNameApplicant);
                firstName.setId(counter);
                coapplicantval.add(firstName);

                mainLayout.addView(coapplicantdetails);
                coapplicantdetailsno.setChecked(false);
                for(TextInputLayout el:coapplicantval)
                {
                    Log.i("newtext",el.getEditText().getText().toString());
                }
                counter++;
            }
        });
        coapplicantdetailsno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                coapplicantdetailsyes.setChecked(false);

            }});
        //Log.i("DFDFDF","reachet43dr5");
        return rootView;
    }



    @Override
    public void onDetach() {
        super.onDetach();
    }

    public void retrieveDataForDropdown(String url, final MaterialBetterSpinner spinner)
    {
        JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.GET,url,null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        Log.i("responseofjson123",String.valueOf(response));
                        try {

                            JSONArray listOfValues = response.getJSONArray("DataList");
                            defaultData= new String[listOfValues.length()];
                            for(int value=0;value<listOfValues.length();value++)
                            {
                                 JSONObject jsonValue=listOfValues.getJSONObject(value);
                                 defaultData[value]= jsonValue.get("Name").toString();
                                // Log.i("responseofjson123", def);
                            }
                            defaultadapter  = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_dropdown_item_1line, defaultData);
                            Log.i("responseofjson123", String.valueOf(defaultData));
                            spinner.setAdapter(defaultadapter);
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
