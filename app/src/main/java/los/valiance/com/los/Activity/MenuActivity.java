package los.valiance.com.los.Activity;

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

import los.valiance.com.los.Fragments.CreateLeadFragment;
import los.valiance.com.los.Fragments.emo;
import los.valiance.com.los.R;

public class MenuActivity extends AppCompatActivity {

    Button createLead,searchLead,searchLoan,aboutUs,reports;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);
        createLead= (Button) findViewById(R.id.btn_createlead);
        searchLead= (Button) findViewById(R.id.btn_searchlead);
        searchLoan= (Button) findViewById(R.id.btn_searchloan);
        aboutUs= (Button) findViewById(R.id.btn_aboutus);
        reports= (Button) findViewById(R.id.btn_reports);

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
                Toast.makeText(MenuActivity.this,"Under Progress",Toast.LENGTH_SHORT).show();
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


       // displayView(10);
    }

    public void openfragment(int pos)
    {
        Log.i("reachedhere","FDFDFD");
        Intent intent = new Intent(MenuActivity.this, MainActivity.class);
        intent.putExtra("fragmentnumber",String.valueOf(pos));
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }

    public void displayView(int position) {
        Fragment fragment = null;
        String title = getString(R.string.app_name);


        switch (position) {

           /* case 1:
                fragment = new CreateLeadFragment();
                title = "Create Lead Form";
                break;*/
            default:
                fragment = new CreateLeadFragment();
                title = "Create Lead Form";
                break;


        }
        if (fragment != null) {
            Log.i("DFDFDF","reached");
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.popBackStackImmediate(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container_body, fragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
            getSupportActionBar().setTitle(title);

        }


    }
}
