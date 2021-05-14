package MyLearningProject.restservice.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "district_profile")
public class DistrictSoil {

    @Id
    String district;
    String N;
    String P;
    String K;
    String pH;

    protected DistrictSoil() {};

    public DistrictSoil(String district, String N, String P, String K, String pH){

        this.district = district;
        this.N = N;
        this.P = P;
        this.K = K;
        this.pH = pH;


    }


    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getN() {
        return N;
    }

    public void setN(String n) {
        N = n;
    }

    public String getP() {
        return P;
    }

    public void setP(String p) {
        P = p;
    }

    public String getK() {
        return K;
    }

    public void setK(String k) {
        K = k;
    }

    public String getpH() {
        return pH;
    }

    public void setpH(String pH) {
        this.pH = pH;
    }
}
