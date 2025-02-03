package org.example.service;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String lastName;
    private String firstName;
    
    //name = "Classname_FKattributname", referencedColumName = "nameofcolumnInBDD"
    @ManyToOne
    @JoinColumn(name = "centre_id", referencedColumnName = "id")
    private Centre centre;

    public Doctor(){}

    public Doctor(Integer id, String lastName ,String firstName, Centre centre){
        this.id = id;
        this.lastName=lastName;
        this.firstName = firstName;
        this.centre = centre;
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

    public Centre getCentre() {
        return centre;
    }

    public void setCentre(Centre centre) {
        this.centre = centre;
    }

    public boolean isEqualTo(Doctor d){
        return this.id == d.id && this.centre.equals(d.centre) && this.firstName.equals(d.firstName) && this.lastName.equals(d.lastName);
    }
}
