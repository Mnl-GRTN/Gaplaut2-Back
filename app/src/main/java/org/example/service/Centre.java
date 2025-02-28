package org.example.service;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Centre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String centreName;
    private String city;
    private String address;
    private String postalCode;
    
    public Centre(){}

    public Centre(Integer id, String centreName,String city, String address, String postalCode) {
        this.id = id;
        this.centreName = centreName;
        this.city = city;
        this.address = address;
        this.postalCode = postalCode;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCentreName() {
        return centreName;
    }

    public void setCentreName(String centreName) {
        this.centreName = centreName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean isEqualTo(Centre c){
        return this.id == c.id && this.centreName == c.centreName && this.city == c.city && this.address == c.address && this.postalCode == c.postalCode;
    }
}
