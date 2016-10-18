package los.valiance.com.los.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import los.valiance.com.los.Helper.InternetConnectionDetector;
import los.valiance.com.los.Helper.SessionManagement;
import los.valiance.com.los.Model.UserModel;
import los.valiance.com.los.R;

import static los.valiance.com.los.Helper.Constants.rooturl;
public class LoginActivity extends AppCompatActivity {

    private String loginurl=rooturl+"/ValidateLogin";//";
    InternetConnectionDetector icd;
    private TextInputEditText userName;
    private TextInputEditText password;
    private Button signIn;
    ProgressDialog progressDialog;
    SessionManagement session;
    UserModel user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        progressDialog = new ProgressDialog(this);
        icd=new InternetConnectionDetector(this);
        session=new SessionManagement(this);
        user=session.getUserDetails();
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
                            if (status.equals("1")) {
                                user=new UserModel();
                                user.setUserName(userName);
                                user.setPassword(password);
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
                        Toast.makeText(LoginActivity.this,error.getMessage(),Toast.LENGTH_SHORT).show();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(LoginActivity.this);
        requestQueue.add(stringRequest);
    }


}
