package los.valiance.com.los.Helper;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * This class returns the current date
 */
public class CurrentDate {
    public String getCurrentdate() {
        DateFormat dateFormatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        dateFormatter.setLenient(false);
        Date today = new Date();
        return dateFormatter.format(today);
    }
}
