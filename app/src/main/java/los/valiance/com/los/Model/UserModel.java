package los.valiance.com.los.Model;

/**
 * Created by admin2 on 12-10-2016.
 */
public class UserModel {
    private String UserName;

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    private String Password;
}
