package los.valiance.com.los.Activity;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import los.valiance.com.los.Adapters.VerificationAdapter;
import los.valiance.com.los.Adapters.expandadapter;
import los.valiance.com.los.Model.VerificationModel;
import los.valiance.com.los.R;

import static los.valiance.com.los.Helper.Constants.rooturl;

public class VerificationActivity extends AppCompatActivity {

    ListView list;
    private String viewLeadsUrl=rooturl+"/GetAllLoanLeadEnquiry";//";
    private ArrayList<VerificationModel> verificationDetails;
    VerificationAdapter adapter;
    private static final int CAMERA_REQUEST = 1888;
    public static final int MY_PERMISSIONS_REQUEST_CAMERA = 2;
    final private int REQUEST_CODE_ASK_MULTIPLE_PERMISSIONS = 124;
    private Bitmap photo1;
    expandadapter exadapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        list = (ListView) findViewById(R.id.list);
        verificationDetails=new ArrayList<>();
        VerificationModel newLead=new VerificationModel();
        newLead.setLeadId(123);
        VerificationModel newLead1=new VerificationModel();
        newLead1.setLeadId(1232);
        verificationDetails.add(newLead);
        verificationDetails.add(newLead1);
        setAdapter(verificationDetails);
        //retrieveDataForDropdown(viewLeadsUrl);
        getpermission();

       /* FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
    }

    public void retrieveDataForDropdown(String url)
    {
        JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.GET,url,null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {


                        try {

                            JSONArray getAllLeads = response.getJSONObject("Data").getJSONArray("LoanLeadEnquiry");
                            Log.i("responseofjson1234567",String.valueOf(getAllLeads));
                            for(int totalLeads=0;totalLeads<getAllLeads.length();totalLeads++)
                            {
                                VerificationModel newLead=new VerificationModel();
                                newLead.setLeadId(Integer.parseInt(getAllLeads.getJSONObject(totalLeads).getString("LeadID")));
                              /*  newLead.setFirstName(getAllLeads.getJSONObject(totalLeads).getString("FirstName"));
                                newLead.setLastName(getAllLeads.getJSONObject(totalLeads).getString("LastName"));
                                newLead.setLoanPurposeType(Integer.parseInt(getAllLeads.getJSONObject(totalLeads).getString("LoanPurposeType")));
                                newLead.setLoanType(Integer.parseInt(getAllLeads.getJSONObject(totalLeads).getString("LoanType")));

                                newLead.setLeadStatus(Integer.parseInt(getAllLeads.getJSONObject(totalLeads).getString("LeadStatus")));
                                newLead.setTitleType(Integer.parseInt(getAllLeads.getJSONObject(totalLeads).getString("TitleType")));
                                newLead.setEmailId(getAllLeads.getJSONObject(totalLeads).getString("ContactEmail"));
                                newLead.setMobileNumber((getAllLeads.getJSONObject(totalLeads).getString("ContactPhone")));

                                newLead.setAddressLine1(getAllLeads.getJSONObject(totalLeads).getString("AddressLine1"));
                                newLead.setState(Integer.parseInt(getAllLeads.getJSONObject(totalLeads).getString("State")));
                                newLead.setDistrict(Integer.parseInt(getAllLeads.getJSONObject(totalLeads).getString("District")));
                                newLead.setPincode((getAllLeads.getJSONObject(totalLeads).getString("Pin")));

                                newLead.setAddressTrueForPost(Boolean.parseBoolean(getAllLeads.getJSONObject(totalLeads).getString("AddressTrueForPost"))?1:0);
                                newLead.setLandmark(getAllLeads.getJSONObject(totalLeads).getString("Landmark"));
                                newLead.setLeadSource(Integer.parseInt((getAllLeads.getJSONObject(totalLeads).getString("LeadSource"))));
                                newLead.setSalesOfficer(Integer.parseInt(getAllLeads.getJSONObject(totalLeads).getString("SalesOfficer")));
                                newLead.setTeamManager(Integer.parseInt((getAllLeads.getJSONObject(totalLeads).getString("TeamManager"))));

                                newLead.setDescription(getAllLeads.getJSONObject(totalLeads).getString("Description"));

                                newLead.setIsAnyOtherLoanExist(String.valueOf(Boolean.parseBoolean(getAllLeads.getJSONObject(totalLeads).getString("IsAnyOtherLoansExist"))?1:0));

                                newLead.setOtherLoanAmount(getAllLeads.getJSONObject(totalLeads).getString("OtherLoanAmount"));
                                newLead.setOutstandingAmount(getAllLeads.getJSONObject(totalLeads).getString("OutStandingAmount"));
                                newLead.setRunningEmi((int) Double.parseDouble(getAllLeads.getJSONObject(totalLeads).getString("RunningEMI")));

                                newLead.setTypeOfEmployment(Integer.parseInt(getAllLeads.getJSONObject(totalLeads).getString("LoanType")));
                                newLead.setIncome((int) Double.parseDouble(getAllLeads.getJSONObject(totalLeads).getString("Income")));
                                newLead.setExpense((int) Double.parseDouble(getAllLeads.getJSONObject(totalLeads).getString("Expense")));
                                newLead.setNotes(getAllLeads.getJSONObject(totalLeads).getString("Notes"));

                                newLead.setRequestedLoanAmount((int) Double.parseDouble(getAllLeads.getJSONObject(totalLeads).getString("RequestedLoanAmount")));
                                newLead.setRequestedLoanTenureInYears((int) Double.parseDouble(getAllLeads.getJSONObject(totalLeads).getString("RequestedLoanTenureInYears")));
                                newLead.setIsApplyingWithCoApplicant(getAllLeads.getJSONObject(totalLeads).getString("IsApplyingWithCoApplicant"));
                                newLead.setLeadCoapplicantDetails(getAllLeads.getJSONObject(totalLeads).getString("LeadCoapplicantDetails"));
*/
                                verificationDetails.add(newLead);
                                Log.i("leadvalues123", String.valueOf(newLead.getLeadId()));
                            }
                            Log.i("leadvalues123", String.valueOf(verificationDetails.size()));
                            setAdapter(verificationDetails);

                            /*defaultadapter  = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_dropdown_item_1line, defaultData);
                            //defaultadapter.add("Please Select");
                            Log.i("responseofjson123", String.valueOf(defaultData));
                            spinner.setAdapter(defaultadapter);
                            if(spinner.getId()==R.id.titlespinner)
                                titleAdapter=defaultadapter;
                            if(spinner.getId()==R.id.typeofemployee)
                                applicantTypeAdapter=defaultadapter;*/
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

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void setAdapter(List<VerificationModel> ss) {

        adapter = new VerificationAdapter(this, ss);
        if (!adapter.isEmpty()) {
            list.setAdapter(adapter);
        } else {
            Toast.makeText(this,"fdfdfd",Toast.LENGTH_SHORT).show();
        }
        list.setItemsCanFocus(true);
        //registerForContextMenu(list);
    }
    private void getpermission() {
        List<String> permissionsNeeded = new ArrayList<String>();

        final List<String> permissionsList = new ArrayList<String>();
        if (!addPermission(permissionsList, Manifest.permission.WRITE_EXTERNAL_STORAGE))
            permissionsNeeded.add("Read Gallery");
        if (!addPermission(permissionsList, Manifest.permission.CAMERA))
            permissionsNeeded.add("Access Camera");
        if (!addPermission(permissionsList, Manifest.permission.ACCESS_FINE_LOCATION))
            permissionsNeeded.add("GPS");

        if (permissionsList.size() > 0) {
            if (permissionsNeeded.size() > 0) {
                // Need Rationale
                String message = "You need to grant access to " + permissionsNeeded.get(0);
                for (int i = 1; i < permissionsNeeded.size(); i++)
                    message = message + ", " + permissionsNeeded.get(i);
                showMessageOKCancel(message,
                        new DialogInterface.OnClickListener() {
                            @TargetApi(Build.VERSION_CODES.M)
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                ActivityCompat.requestPermissions(VerificationActivity.this, permissionsList.toArray(new String[permissionsList.size()]), REQUEST_CODE_ASK_MULTIPLE_PERMISSIONS);
                            }
                        });
                return;
            }
            ActivityCompat.requestPermissions(VerificationActivity.this, permissionsList.toArray(new String[permissionsList.size()]), REQUEST_CODE_ASK_MULTIPLE_PERMISSIONS);
            return;
        }

    }


    private boolean addPermission(List<String> permissionsList, String permission) {

        if (ContextCompat.checkSelfPermission(VerificationActivity.this, permission) != PackageManager.PERMISSION_GRANTED) {
            permissionsList.add(permission);
            // Check for Rationale Option

            if (!ActivityCompat.shouldShowRequestPermissionRationale( VerificationActivity.this, permission))
                return false;

        }
        return true;
    }
    private void showMessageOKCancel(String message, DialogInterface.OnClickListener okListener) {
        new android.support.v7.app.AlertDialog.Builder(VerificationActivity.this)
                .setMessage(message)
                .setPositiveButton("OK", okListener)
                .setNegativeButton("Cancel", null)
                .create()
                .show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CODE_ASK_MULTIPLE_PERMISSIONS: {
                Map<String, Integer> perms = new HashMap<String, Integer>();
                // Initial
                perms.put(Manifest.permission.CAMERA, PackageManager.PERMISSION_GRANTED);
                perms.put(Manifest.permission.WRITE_EXTERNAL_STORAGE, PackageManager.PERMISSION_GRANTED);
                perms.put(Manifest.permission.ACCESS_FINE_LOCATION, PackageManager.PERMISSION_GRANTED);


                // Fill with results
                for (int i = 0; i < permissions.length; i++)
                    perms.put(permissions[i], grantResults[i]);
                // Check for ACCESS_FINE_LOCATION
                if (
                        perms.get(Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED
                                && perms.get(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED && perms.get(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                    // All Permissions Granted

                } else {
                    // Permission Denied
                    Toast.makeText(this, "Some Permission is Denied. Required those permission ", Toast.LENGTH_SHORT).show();

                }
            }
            break;

        }
    }

    public void setImage(VerificationAdapter verificationAdapter)
    {
        adapter=verificationAdapter;
        Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(cameraIntent, CAMERA_REQUEST);
    }

    public void setImageexpand(expandadapter verificationAdapter,int group)
    {
        exadapter=verificationAdapter;
        Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(cameraIntent, CAMERA_REQUEST);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CAMERA_REQUEST && resultCode == RESULT_OK) {
            photo1 = (Bitmap) data.getExtras().get("data");
            setImageList();
          //  verificationdetails.get(position).setImage1(String.valueOf(mphoto));
          //  VerificationAdapter verificationAdapter=new VerificationAdapter(getBaseContext(),verificationdetails);

            Log.i("imagereceived", String.valueOf(photo1));
        }
    }

    public void setImageList() {

        exadapter.getVerificationDetails().get(0).setImage1(photo1);
       // exadapter.refreshLayout();
    }
}
