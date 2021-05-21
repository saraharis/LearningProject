package MyLearningProject.restservice.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Ncropprops" )

public class Crop {
    @Id
    int id;
    String cropName;
    Integer n;
    Integer p;
    Integer k;
    String type;
    Integer sunlightMin;
    Integer sunlightMax;
    String sunlight;
    String season;
    Long t_o_min;
    Long t_o_max;
    Long t_min;
    Long t_max;
    Long ph_min;
    Long ph_max;

    protected Crop(){};

    public Crop(int id, String cropName, Integer n, Integer p,
                Integer k, String type, Integer sunlightMin, Integer sunlightMax,
                String sunlight, String season, Long t_o_min, Long t_o_max,
                Long t_min, Long t_max, Long ph_min, Long ph_max) {
        this.id = id;
        this.cropName = cropName;
        this.n = n;
        this.p = p;
        this.k = k;
        this.type = type;
        this.sunlightMin = sunlightMin;
        this.sunlightMax = sunlightMax;
        this.sunlight = sunlight;
        this.season = season;
        this.t_o_min = t_o_min;
        this.t_o_max = t_o_max;
        this.t_min = t_min;
        this.t_max = t_max;
        this.ph_min = ph_min;
        this.ph_max = ph_max;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCropName() {
        return cropName;
    }

    public void setCropName(String cropName) {
        this.cropName = cropName;
    }

    public Integer getN() {
        return n;
    }

    public void setN(Integer n) {
        this.n = n;
    }

    public Integer getP() {
        return p;
    }

    public void setP(Integer p) {
        this.p = p;
    }

    public Integer getK() {
        return k;
    }

    public void setK(Integer k) {
        this.k = k;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getSunlightMin() {
        return sunlightMin;
    }

    public void setSunlightMin(Integer sunlightMin) {
        this.sunlightMin = sunlightMin;
    }

    public Integer getSunlightMax() {
        return sunlightMax;
    }

    public void setSunlightMax(Integer sunlightMax) {
        this.sunlightMax = sunlightMax;
    }

    public String getSunlight() {
        return sunlight;
    }

    public void setSunlight(String sunlight) {
        this.sunlight = sunlight;
    }

    public String getSeason() {
        return season;
    }

    public void setSeason(String season) {
        this.season = season;
    }

    public Long getT_o_min() {
        return t_o_min;
    }

    public void setT_o_min(Long t_o_min) {
        this.t_o_min = t_o_min;
    }

    public Long getT_o_max() {
        return t_o_max;
    }

    public void setT_o_max(Long t_o_max) {
        this.t_o_max = t_o_max;
    }

    public Long getT_min() {
        return t_min;
    }

    public void setT_min(Long t_min) {
        this.t_min = t_min;
    }

    public Long getT_max() {
        return t_max;
    }

    public void setT_max(Long t_max) {
        this.t_max = t_max;
    }


    public Long getPh_min() {
        return ph_min;
    }

    public void setPh_min(Long ph_min) {
        this.ph_min = ph_min;
    }

    public Long getPh_max() {
        return ph_max;
    }

    public void setPh_max(Long ph_max) {
        this.ph_max = ph_max;
    }
}
