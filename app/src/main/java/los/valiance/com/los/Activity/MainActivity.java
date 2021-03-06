package los.valiance.com.los.Activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import java.util.List;

import los.valiance.com.los.Adapters.VerificationAdapter;
import los.valiance.com.los.Fragments.CreateLeadFragment;
import los.valiance.com.los.Fragments.VerificationFragment;
import los.valiance.com.los.Fragments.ViewFragment;
import los.valiance.com.los.Fragments.emo;
import los.valiance.com.los.Model.VerificationModel;
import los.valiance.com.los.R;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class MainActivity extends AppCompatActivity {

    private Toolbar mToolbar;

    private List<VerificationModel>verificationdetails;
    int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*mToolbar = (Toolbar) findViewById(R.id.toolbar);
        // mToolbar.setNavigationIcon(R.drawable.mobile_menu);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);*/
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("font/OpenSans-Regular.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );
        setContentView(R.layout.activity_main2);
       int fragmentToLoad=Integer.parseInt(getIntent().getExtras().getString("fragmentnumber"));
       int isDataAvailable= Integer.parseInt(getIntent().getExtras().getString("isDataAvailable"));
        //Log.i("valuesi",getIntent().getExtras().getString("fragmentnumber"));

        displayView(fragmentToLoad,isDataAvailable);
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

   /* @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // TODO Add your menu entries here
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu_main, menu);
        MenuItem backItem = menu.findItem(R.id.action_back);
        backItem.setVisible(true);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        searchItem.setVisible(false);
        return true;

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();


        if (id == R.id.action_back) {

            Intent intent = new Intent(MainActivity.this, MenuActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }*/

    @Override
    public void onBackPressed() {
    }
    public void displayView(int fragmentToLoad, int isDataAvailable) {
        Fragment fragment = null;
        String title = getString(R.string.app_name);


        switch (fragmentToLoad) {

           case 1:
               Bundle bundle=new Bundle();
               bundle.putString("isDataAvailable", String.valueOf(isDataAvailable));
                fragment = new CreateLeadFragment();
               fragment.setArguments(bundle);
                title = "Create Lead Form";
                break;
            case 2:
                fragment=new ViewFragment();
                title="View Lead";
                break;
            case 3:
                Intent intent = new Intent(MainActivity.this, VerificationActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();


               /* fragment=new VerificationFragment();
                title="Verification";*/
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
