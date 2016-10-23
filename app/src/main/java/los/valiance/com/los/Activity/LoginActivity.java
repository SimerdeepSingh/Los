package los.valiance.com.los.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Switch;
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
import java.util.LinkedHashMap;
import java.util.Map;

import los.valiance.com.los.Database.LocalDatabase;
import los.valiance.com.los.Helper.InternetConnectionDetector;
import los.valiance.com.los.Helper.SessionManagement;
import los.valiance.com.los.Model.UserModel;
import los.valiance.com.los.R;

import static los.valiance.com.los.Helper.Constants.leadTypeTable;
import static los.valiance.com.los.Helper.Constants.loanPurposeTable;
import static los.valiance.com.los.Helper.Constants.loanPurposeUrl;
import static los.valiance.com.los.Helper.Constants.loanTypeTable;
import static los.valiance.com.los.Helper.Constants.loanTypeUrl;
import static los.valiance.com.los.Helper.Constants.relationshipTable;
import static los.valiance.com.los.Helper.Constants.relationshipUrl;
import static los.valiance.com.los.Helper.Constants.rooturl;
import static los.valiance.com.los.Helper.Constants.salesOfficerTable;
import static los.valiance.com.los.Helper.Constants.salesOfficerUrl;
import static los.valiance.com.los.Helper.Constants.sourceTable;
import static los.valiance.com.los.Helper.Constants.sourceUrl;
import static los.valiance.com.los.Helper.Constants.stateTable;
import static los.valiance.com.los.Helper.Constants.stateUrl;
import static los.valiance.com.los.Helper.Constants.statusTable;
import static los.valiance.com.los.Helper.Constants.statusUrl;
import static los.valiance.com.los.Helper.Constants.teamManagerTable;
import static los.valiance.com.los.Helper.Constants.teamManagerUrl;
import static los.valiance.com.los.Helper.Constants.titleTable;
import static los.valiance.com.los.Helper.Constants.titleUrl;
import static los.valiance.com.los.Helper.Constants.typeOFEmployeeUrl;


public class LoginActivity extends AppCompatActivity {

    private String loginurl=rooturl+"/ValidateLogin";//";
    InternetConnectionDetector icd;
    private TextInputEditText userName;
    private TextInputEditText password;
    private Button signIn;
    ProgressDialog progressDialog;
    SessionManagement session;
    UserModel user;
    LocalDatabase localDatabase;

    LinkedHashMap<Integer,String> dropdownData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        progressDialog = new ProgressDialog(this);
        icd=new InternetConnectionDetector(this);
        session=new SessionManagement(this);
        user=session.getUserDetails();
        localDatabase=new LocalDatabase(getBaseContext());

        retrieveDataForDropdown(statusUrl,statusTable, getResources().getStringArray(R.array.array_status)[0]);
        retrieveDataForDropdown(titleUrl,titleTable,getResources().getStringArray(R.array.array_title)[0]);
        retrieveDataForDropdown(stateUrl,stateTable,getResources().getStringArray(R.array.array_state)[0]);
        retrieveDataForDropdown(sourceUrl,sourceTable,getResources().getStringArray(R.array.array_source)[0]);
        retrieveDataForDropdown(salesOfficerUrl,salesOfficerTable,getResources().getStringArray(R.array.array_salesofficer)[0]);
        retrieveDataForDropdown(teamManagerUrl,teamManagerTable,getResources().getStringArray(R.array.array_teammanager)[0]);
        retrieveDataForDropdown(loanTypeUrl,loanTypeTable,getResources().getStringArray(R.array.array_loantype)[0]);
        retrieveDataForDropdown(loanPurposeUrl,loanPurposeTable,getResources().getStringArray(R.array.array_loanpurpose)[0]);
        retrieveDataForDropdown(typeOFEmployeeUrl,leadTypeTable,getResources().getStringArray(R.array.array_typeofemployee)[0]);
        retrieveDataForDropdown(relationshipUrl,relationshipTable,getResources().getStringArray(R.array.array_relationship)[0]);
        if(user==null) {

            // Comment for testing purpose
            userName = (TextInputEditText) findViewById(R.id.username);
            password = (TextInputEditText) findViewById(R.id.password);
            signIn = (Button) findViewById(R.id.signinbutton);
            signIn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (verifyData()) {
                        if (icd.isConnectingToInternet())
                            checkLogin(userName.getText().toString(), password.getText().toString());
                        else
                            Toast.makeText(LoginActivity.this, "Internet Connection Not Available", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

        else
        {
            Intent intent = new Intent(LoginActivity.this, MenuActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
        }
    }

    private boolean verifyData() {
        boolean flag=true;
        if(userName.getText().toString().isEmpty()) {
            userName.setError("Please Enter Username");
            flag=false;
        }
            if(password.getText().toString().isEmpty())
            {
                password.setError("Please Enter Password");
                flag=false;
            }
        return flag;
    }

    public void checkLogin(final String userName, final String password)
    {
        Map<String, String> postParam= new HashMap<String, String>();
        postParam.put("UserName", userName);
        postParam.put("Password", password);
        progressDialog.setTitle("Verifying...");
        progressDialog.setMessage("Please wait...");
        progressDialog.setCancelable(false);
        progressDialog.setIndeterminate(true);
        progressDialog.show();
        JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.POST,loginurl,new JSONObject(postParam),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.i("responseofjson1",String.valueOf(response));
                        try {
                            String status = response.getString("Status");
                            String userId=  response.getString("UserId");
                            if (status.equals("1")) {
                                user=new UserModel();
                                user.setUserName(userName);
                                user.setPassword(password);
                                user.setUserId(userId);

                                session.saveUserDetails(user);
                                Intent intent = new Intent(LoginActivity.this, MenuActivity.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(intent);
                                finish();

                            } else
                                Toast.makeText(LoginActivity.this,"Invalid Credentials",Toast.LENGTH_SHORT).show();
                        } catch (Exception e) {

                            Log.i("error",e.getMessage());
                            e.printStackTrace();
                        }

                        progressDialog.cancel();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.cancel();
                        Toast.makeText(LoginActivity.this,"Internal Error",Toast.LENGTH_SHORT).show();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(LoginActivity.this);
        requestQueue.add(stringRequest);
    }

    public void retrieveDataForDropdown(String url, final String tableName, final String defaultDataForSpinner)
    {
        Log.i("value",defaultDataForSpinner);
        JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.GET,url,null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        Log.i("responseofjson123",String.valueOf(response));
                        try {

                            JSONArray listOfValues = response.getJSONArray("DataList");

                            dropdownData=new LinkedHashMap<>();
                            dropdownData.put(0,defaultDataForSpinner);
                            for(int value=0;value<listOfValues.length();value++)
                            {
                                JSONObject jsonValue=listOfValues.getJSONObject(value);
                                int dataId= Integer.parseInt(jsonValue.get("ID").toString());
                                String dataValue=jsonValue.get("Name").toString();
                                dropdownData.put(dataId,dataValue);
                                // Log.i("responseofjson123", def);
                            }

                            Log.i("responseofjson123", String.valueOf(dropdownData));
                            localDatabase.addDropDownData(tableName,dropdownData);
                           /* if(spinner.getId()==R.id.titlespinner)
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



}
