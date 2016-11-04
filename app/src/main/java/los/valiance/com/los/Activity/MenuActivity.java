package los.valiance.com.los.Activity;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import los.valiance.com.los.Adapters.VerificationAdapter;
import los.valiance.com.los.Fragments.CreateLeadFragment;
import los.valiance.com.los.Fragments.ViewFragment;
import los.valiance.com.los.Fragments.emo;
import los.valiance.com.los.R;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class MenuActivity extends AppCompatActivity {

    Button createLead,searchLead,searchLoan,aboutUs,reports,Verification;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("font/OpenSans-Regular.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );
        setContentView(R.layout.menu);
        createLead= (Button) findViewById(R.id.btn_createlead);
        searchLead= (Button) findViewById(R.id.btn_searchlead);
        searchLoan= (Button) findViewById(R.id.btn_searchloan);
        aboutUs= (Button) findViewById(R.id.btn_aboutus);
        reports= (Button) findViewById(R.id.btn_reports);
        Verification= (Button) findViewById(R.id.btn_verification);
        createLead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openfragment(1);
               // displayView(1);
            }
        });

        searchLead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openfragment(2);
             //   Toast.makeText(MenuActivity.this,"Under Progress",Toast.LENGTH_SHORT).show();
            }
        });
        searchLoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MenuActivity.this,"Under Progress",Toast.LENGTH_SHORT).show();
            }
        });

        aboutUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MenuActivity.this,"Under Progress",Toast.LENGTH_SHORT).show();
            }
        });
        reports.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MenuActivity.this,"Under Progress",Toast.LENGTH_SHORT).show();
            }
        });
        Verification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openfragment(3);
            }
        });


       // displayView(10);
    }


    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
    public void openfragment(int pos)
    {
        Log.i("reachedhere","FDFDFD");
        Intent intent = new Intent(MenuActivity.this, MainActivity.class);
        intent.putExtra("fragmentnumber",String.valueOf(pos));
        intent.putExtra("isDataAvailable","0");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }





}
