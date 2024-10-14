package org.example.service;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;

@Entity
public class Doctor {

    @Id
    private Integer id;
    private String lastName;
    private String firstName;
    
    //@ManyToOne
    //@JoinColumn(name = "centre_id", referencedColumnName = "id")
    private Integer centreId;

    public Doctor(){}

    public Doctor(Integer id, String lastName ,String firstName, Integer centreId){
        this.id = id;
        this.lastName=lastName;
        this.firstName = firstName;
        this.centreId = centreId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Integer getCentre() {
        return centreId;
    }

    public void setCentre(Integer centre) {
        this.centreId = centreId;
    }

}
