package los.valiance.com.los.Helper;


import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

/**
 * This class detect the internet connection on the device
 */
public class InternetConnectionDetector {

    private Context _context;

    public InternetConnectionDetector(Context context) {
        this._context = context;
    }

    public boolean isConnectingToInternet() {
        ConnectivityManager connectivity = (ConnectivityManager) _context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity != null) {
            NetworkInfo[] info = connectivity.getAllNetworkInfo();
            if (info != null)
                for (int i = 0; i < info.length; i++)
                    if (info[i].getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }

        }


        return false;
    }

    public void showErrorMessage() {
        Toast.makeText(_context, "No Internet Connection...", Toast.LENGTH_SHORT).show();
    }
}