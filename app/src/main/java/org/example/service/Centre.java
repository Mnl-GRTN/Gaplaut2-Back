package org.example.service;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Centre {

    @Id
    private Integer id;
    private String centreName;
    private String city;
    
    public Centre(){}

    public Centre(Integer id, String centreName,String city){
        this.id = id;
        this.centreName = centreName;
        this.city = city;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCentre_name() {
        return centreName;
    }

    public void setCentre_name(String centreName) {
        this.centreName = centreName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
