package los.valiance.com.los.Activity;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import los.valiance.com.los.Fragments.CreateLeadFragment;
import los.valiance.com.los.Fragments.emo;
import los.valiance.com.los.R;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
       int fragmentToLoad=Integer.parseInt(getIntent().getExtras().getString("fragmentnumber"));
        //Log.i("valuesi",getIntent().getExtras().getString("fragmentnumber"));
        displayView(fragmentToLoad);
    }

    public void displayView(int position) {
        Fragment fragment = null;
        String title = getString(R.string.app_name);


        switch (position) {

           case 1:
                fragment = new CreateLeadFragment();
                title = "Create Lead Form";
                break;
            default:
                fragment = new emo();
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
