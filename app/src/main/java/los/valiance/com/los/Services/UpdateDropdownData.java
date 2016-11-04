package los.valiance.com.los.Services;

import android.app.Service;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Timer;
import java.util.TimerTask;

import los.valiance.com.los.Database.LocalDatabase;
import los.valiance.com.los.Model.CityModel;
import los.valiance.com.los.R;

import static los.valiance.com.los.Helper.Constants.updateTime;
import static los.valiance.com.los.Helper.Constants.cityTable;
import static los.valiance.com.los.Helper.Constants.cityUrl;
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
/**
 * Created by admin2 on 03-11-2016.
 */

public class UpdateDropdownData extends Service {
    private static Timer timer = new Timer();
    LinkedHashMap<Integer,String> dropdownData;
    LocalDatabase localDatabase;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onStart(Intent intent, int startId) {
        // TODO Auto-generated method stub
        super.onStart(intent, startId);
        //   Log.d(TAG, "FirstService started");
        Log.i("servicerunning","true");
        timer.scheduleAtFixedRate(new timerTask(), 0, updateTime);
        localDatabase=LocalDatabase.getHelper(this);

        //   this.stopSelf();
    }

    private class timerTask extends TimerTask {
        public void run() {
            //toastHandler.sendEmptyMessage(0);
            new syncLocalDbData().execute();
          // syncLocalDbData();
        }

        
    }

    protected class syncLocalDbData extends AsyncTask<Void, Void, Void>

    {
        @Override
        protected Void doInBackground(Void... params) {
            retrieveDataForDropdown(statusUrl, statusTable, getResources().getStringArray(R.array.array_status)[0]);
            retrieveDataForDropdown(titleUrl, titleTable, getResources().getStringArray(R.array.array_title)[0]);
            retrieveDataForDropdown(stateUrl, stateTable, getResources().getStringArray(R.array.array_state)[0]);
            retrieveDataForDropdown(sourceUrl, sourceTable, getResources().getStringArray(R.array.array_source)[0]);
            retrieveDataForDropdown(salesOfficerUrl, salesOfficerTable, getResources().getStringArray(R.array.array_salesofficer)[0]);
            /*retrieveDataForDropdown(teamManagerUrl, teamManagerTable, getResources().getStringArray(R.array.array_teammanager)[0]);
            retrieveDataForDropdown(loanTypeUrl, loanTypeTable, getResources().getStringArray(R.array.array_loantype)[0]);
            retrieveDataForDropdown(loanPurposeUrl, loanPurposeTable, getResources().getStringArray(R.array.array_loanpurpose)[0]);
            retrieveDataForDropdown(typeOFEmployeeUrl, leadTypeTable, getResources().getStringArray(R.array.array_typeofemployee)[0]);
            retrieveDataForDropdown(relationshipUrl, relationshipTable, getResources().getStringArray(R.array.array_relationship)[0]);
            retrieveDataForCityDropdown(cityUrl, cityTable, getResources().getStringArray(R.array.array_city)[0]);*/
            return null;
        }
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

                        } catch (Exception e) {
                            Log.i("errorservice",e.getMessage());
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


    public void retrieveDataForCityDropdown(String url, final String tableName, final String defaultDataForSpinner)
    {
        Log.i("value",defaultDataForSpinner);
        JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.GET,url,null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        Log.i("responseofjson123",String.valueOf(response));
                        try {

                            JSONArray listOfValues = response.getJSONArray("DataList");

                            ArrayList<CityModel>cityData=new ArrayList<>();
                            CityModel defaultModel=new CityModel();
                            defaultModel.setCityId(0);
                            defaultModel.setName(defaultDataForSpinner);
                            defaultModel.setStateId(0);

                            cityData.add(defaultModel);
                            for(int value=0;value<listOfValues.length();value++)
                            {
                                JSONObject jsonValue=listOfValues.getJSONObject(value);
                                CityModel newModel=new CityModel();
                                newModel.setCityId( Integer.parseInt(jsonValue.get("ID").toString()));
                                newModel.setName(jsonValue.get("Name").toString());
                                newModel.setStateId(Integer.parseInt(jsonValue.get("StateID").toString()));
                                cityData.add(newModel);
                                // Log.i("responseofjson123", def);
                            }

                            Log.i("responseofjson123", String.valueOf(dropdownData));
                            localDatabase.addDistrictData(tableName,cityData);

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
