package MyLearningProject.restservice;

import javax.persistence.*;

@Entity
@Table(name = "demo2")
public class Area {

    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY )
    //Long id;

    String pincode;
    String district;
    String state;

    protected Area() {};

    public Area(String pincode, String district, String state){
        this.pincode = pincode;
        this.district = district;
        this.state = state;

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
}
