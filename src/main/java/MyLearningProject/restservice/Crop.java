package MyLearningProject.restservice;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "cropprops" )

public class Crop {
    @Id
    int id;
    String cropName;
    String n;
    String p;
    String k;
    String type;
    String sunlightMin;
    String sunlightMax;
    String sunlight;
    String season;

    protected Crop(){};

    public Crop(int id, String cropName, String n, String p, String k, String type,
                String sunlightMin, String sunlightMax, String sunlight, String season){
        this.id = id;
        this.cropName = cropName;
        this.n= n;
        this.p = p;
        this.k = k;
        this.type = type;
        this.sunlight = sunlight;
        this.sunlightMax = sunlightMax;
        this.sunlightMin = sunlightMin;
        this.season = season;

    }


    public String getCropName() {
        return cropName;
    }

    public void setCropName(String cropName) {
        this.cropName = cropName;
    }

    public String getN() {
        return n;
    }

    public void setN(String n) {
        this.n = n;
    }

    public String getP() {
        return p;
    }

    public void setP(String p) {
        this.p = p;
    }

    public String getK() {
        return k;
    }

    public void setK(String k) {
        this.k = k;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSunlightMin() {
        return sunlightMin;
    }

    public void setSunlightMin(String sunlightMin) {
        this.sunlightMin = sunlightMin;
    }

    public String getSunlightMax() {
        return sunlightMax;
    }

    public void setSunlightMax(String sunlightMax) {
        this.sunlightMax = sunlightMax;
    }

    public String getSunlight() {
        return sunlight;
    }

    public void setSunlight(String sunlight) {
        this.sunlight = sunlight;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSeason() {
        return season;
    }

    public void setSeason(String season) {
        this.season = season;
    }
}
