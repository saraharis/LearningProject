package MyLearningProject.restservice.models;

import javax.persistence.*;

@Entity
@Table(name = "pins")
public class Area {

    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY )
    //Long id;

    String pincode;
    String district;
    String state;
    Long lat;
    Long lon;

    protected Area() {};

    public Area(String pincode, String district, String state, Long lat, Long lon){
        this.pincode = pincode;
        this.district = district;
        this.state = state;
        this.lat = lat;
        this.lon = lon;

    }

    public String getPincode() {
        return pincode;
    }

    public String getDistrict() {
        return district;
    }

    public String getState() {
        return state;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public void setState(String state) {
        this.state = state;
    }


    public Long getLat() {
        return lat;
    }

    public void setLat(Long lat) {
        this.lat = lat;
    }

    public Long getLon() {
        return lon;
    }

    public void setLon(Long lon) {
        this.lon = lon;
    }
}
