package los.valiance.com.los.Fragments;

import android.content.Context;
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

import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

import java.util.ArrayList;

import los.valiance.com.los.R;


public class CreateLeadFragment extends Fragment {
    MaterialBetterSpinner materialBetterSpinner ;
    MaterialBetterSpinner stateSpinner;
    MaterialBetterSpinner citySpinner;

    RadioButton otherloandetailsyes,otherloandetailsno,coapplicantdetailsyes,coapplicantdetailsno;
    String[] SPINNER_DATA = {"COLD LEAD","CONTACTED","NOT CONTACTED","JUNK LEAD","LOST LEAD","QUALIFIED"};
    RelativeLayout hiddenlayout;
int counter=0;
       LinearLayout mainLayout;
ArrayList<TextInputLayout>coapplicantval=new ArrayList<>();

    TextInputLayout firstName,coapplicant;
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

        View rootView = inflater.inflate(R.layout.fragment_create_lead, container, false);
        // Inflate the layout for this fragment
        materialBetterSpinner = (MaterialBetterSpinner)rootView.findViewById(R.id.android_material_design_spinner);
        stateSpinner=(MaterialBetterSpinner)rootView.findViewById(R.id.state);
        citySpinner=(MaterialBetterSpinner)rootView.findViewById(R.id.city);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_dropdown_item_1line, SPINNER_DATA);
        materialBetterSpinner.setAdapter(adapter);
        hiddenlayout=(RelativeLayout) rootView.findViewById(R.id.otherloanlayout);
        mainLayout=(LinearLayout)rootView.findViewById(R.id.mainlayout);



//        mainLayout.addView(coapplicantdetails);
        stateSpinner.setAdapter(adapter);
        citySpinner.setAdapter(adapter);
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

        return rootView;
    }



    @Override
    public void onDetach() {
        super.onDetach();
    }


}
