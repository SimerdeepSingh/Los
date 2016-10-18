package los.valiance.com.los.ApiConnection;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by admin2 on 12-10-2016.
 */
public class GetDataFromDb {
    String url;
    Context context;
    public GetDataFromDb(Context context, String url)
    {
        this.url=url;
        this.context=context;
    }

    public void retrieveData()
    {
        Map<String, String> postParam= new HashMap<String, String>();
        postParam.put("UserName", "salesofficer");
        postParam.put("Password", "admin");
        Log.i("urlhit",url);
        JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.POST,url,new JSONObject(postParam),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        //showJSON(response);
                        Log.i("responseofjson",String.valueOf(response));
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(context,error.getMessage(),Toast.LENGTH_LONG).show();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
    }


    private void showJSON(String json){
        JSONObject jsonRow;
        Log.i("responseofjson",json);
        //  jsonRow=new JSONObject(json);
    }
}
