package MyLearningProject.restservice.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Ndistrict_profile")
public class DistrictSoil {

    @Id
    String district;
    Integer N;
    Integer P;
    Integer K;
    Long pH;

    protected DistrictSoil() {};

    public DistrictSoil(String district, Integer n, Integer p, Integer k, Long pH) {
        this.district = district;
        N = n;
        P = p;
        K = k;
        this.pH = pH;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public Integer getN() {
        return N;
    }

    public void setN(Integer n) {
        N = n;
    }

    public Integer getP() {
        return P;
    }

    public void setP(Integer p) {
        P = p;
    }

    public Integer getK() {
        return K;
    }

    public void setK(Integer k) {
        K = k;
    }

    public Long getpH() {
        return pH;
    }

    public void setpH(Long pH) {
        this.pH = pH;
    }


}
