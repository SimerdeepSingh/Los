package los.valiance.com.los.Model;

/**
 * Created by admin2 on 26-10-2016.
 */

public class CityModel {
   private int cityId;
    private int stateId;
    private String Name;

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public int getStateId() {
        return stateId;
    }

    public void setStateId(int stateId) {
        this.stateId = stateId;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}
