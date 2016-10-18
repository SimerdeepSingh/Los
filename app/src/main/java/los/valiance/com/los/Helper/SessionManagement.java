package los.valiance.com.los.Helper;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

import los.valiance.com.los.Model.UserModel;


/**
 * This class saves the user details in shared preferences
 */
public class SessionManagement {
    SharedPreferences pref;

    // Editor for Shared preferences
    SharedPreferences.Editor editor;

    // Context
    Context context;

    // Shared pref mode
    int PRIVATE_MODE = 0;

    // Sharedpref file name
    private static final String PREF_NAME = "Lospref";

    // All Shared Preferences Keys
    private static final String IS_LOGIN = "IsLoggedIn";

    // User name (make variable public to access from outside)
    public static final String KEY_NAME = "UserDetails";
    public static final String KEY_AUTH = "";

    // Email address (make variable public to access from outside)
    public static final String KEY_ROlE = "role";

    public static final String GCMtoken = "gcm";
    public static final String mobileno = "";
    public static final String init = "";


    public SessionManagement(Context context) {
        this.context = context;
        pref = context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
        //  editor.clear();
        //   editor.commit();

    }


    public void saveUserDetails(UserModel user) {

        Gson gson = new Gson();
        String json = gson.toJson(user);
        editor.putString("UserDetails", json);
        editor.commit();
    }



    public UserModel getUserDetails() {
        Gson gson = new Gson();
        String json = pref.getString("UserDetails", "");
        if (json.isEmpty())
            return null;
        UserModel userInfo = gson.fromJson(json, UserModel.class);
        return userInfo;

    }


}
