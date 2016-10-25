package los.valiance.com.los.Fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.BoolRes;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
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
import java.util.List;

import los.valiance.com.los.Activity.MainActivity;
import los.valiance.com.los.Activity.MenuActivity;
import los.valiance.com.los.Adapters.LeadDetailsAdapter;
import los.valiance.com.los.Model.LeadDetails;
import los.valiance.com.los.R;

import static android.R.id.list;
import static los.valiance.com.los.Helper.Constants.rooturl;


public class ViewFragment extends Fragment {

    ListView list;
    private String viewLeadsUrl=rooturl+"/GetAllLoanLeadEnquiry";//";
    private ArrayList<LeadDetails> leadDetails;
    LeadDetailsAdapter adapter;
    public ViewFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_view, container, false);
        list = (ListView) rootView.findViewById(R.id.list);
        leadDetails=new ArrayList<>();
        retrieveDataForDropdown(viewLeadsUrl);
        return rootView;
    }


    @Override
    public void onDetach() {
        super.onDetach();

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
                                LeadDetails newLead=new LeadDetails();
                                newLead.setLeadId(Integer.parseInt(getAllLeads.getJSONObject(totalLeads).getString("LeadID")));
                                newLead.setFirstName(getAllLeads.getJSONObject(totalLeads).getString("FirstName"));
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

                                leadDetails.add(newLead);
                                Log.i("leadvalues123", String.valueOf(newLead.getLeadId()));
                            }
                            Log.i("leadvalues123", String.valueOf(leadDetails.size()));
                            setAdapter(leadDetails);

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

        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(stringRequest);
    }

    private void setAdapter(List<LeadDetails> ss) {

         adapter = new LeadDetailsAdapter(getContext(), ss);
        if (!adapter.isEmpty()) {
            list.setAdapter(adapter);
        } else {
            Toast.makeText(getContext(),"fdfdfd",Toast.LENGTH_SHORT).show();
        }
        list.setItemsCanFocus(true);
        //registerForContextMenu(list);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // TODO Add your menu entries here
        super.onCreateOptionsMenu(menu, inflater);
        menu.clear();
        inflater.inflate(R.menu.menu_main, menu);

        MenuItem searchItem = menu.findItem(R.id.action_search);
        MenuItem backItem = menu.findItem(R.id.action_back);
        backItem.setVisible(true);
        searchItem.setVisible(true);


      android.support.v7.widget.SearchView sv = new android.support.v7.widget.SearchView(((MainActivity) getActivity()).getSupportActionBar().getThemedContext());
        // Expand the search view and request focus
        MenuItemCompat.setShowAsAction(searchItem, MenuItemCompat.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW | MenuItemCompat.SHOW_AS_ACTION_IF_ROOM);
        MenuItemCompat.setActionView(searchItem, sv);
        sv.setOnQueryTextListener(new android.support.v7.widget.SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                // Toast.makeText(getActivity(), query, Toast.LENGTH_SHORT).show();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //  Toast.makeText(getActivity(), newText, Toast.LENGTH_SHORT).show();
                try {

                    setAdapter(leadDetails);
                   if (leadDetails.size() != 0) {
                       setAdapter(leadDetails);
                       adapter.getFilter().filter(newText);
                   }

                } catch (Exception e) {
                    Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();

                }
                return false;
            }
        });

        sv.setOnClickListener(new View.OnClickListener() {
                                  @Override
                                  public void onClick(View v) {

                                      //Toast.makeText(getActivity(), "clicked", Toast.LENGTH_SHORT).show();

                                  }
                              }
        );


    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_search) {
            return true;
        }

        /*
        if (id == R.id.action_refresh) {
            if (cd.isConnectingToInternet()) {
                new loadContacts().execute();

            } else
                Toast.makeText(getContext(), "Internet Connection Not Available", Toast.LENGTH_SHORT).show();
            return true;
        }*/

        if (id == R.id.action_back) {
            Intent intent = new Intent(getActivity(), MenuActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

}
