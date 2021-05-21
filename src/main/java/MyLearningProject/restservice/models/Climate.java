package MyLearningProject.restservice.models;

import javax.persistence.*;

@Entity
@Table(name = "Nclimate")
public class Climate {

@Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY )

Long id;
Long lat;
Long lon;
String parameter;
int month;
Long value;

protected Climate(){};


    public Climate(Long id, Long lat, Long lon, String parameter, int month, String parameter1, Long value) {
        this.id = id;
        this.lat = lat;
        this.lon = lon;
        this.parameter = parameter;
        this.month = month;
        this.value = value;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getParameter() {
        return parameter;
    }

    public void setParameter(String parameter) {
        this.parameter = parameter;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public Long getValue() {
        return value;
    }

    public void setValue(Long value) {
        this.value = value;
    }
}
