package org.example.service;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Vaccination {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "centre_id", referencedColumnName = "id")
    private Centre centre;

    private String mail;
    private String phoneNumber;
    private String lastName;
    private String firstName;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate date; // Changer en LocalDateTime quand on aura des heures de vaccinations
    private Boolean isVaccined;

    public Vaccination() {}
    
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public Centre getCentre() {
        return centre;
    }
    public void setCentre(Centre centre) {
        this.centre = centre;
    }
    public String getMail() {
        return mail;
    }
    public void setMail(String mail) {
        this.mail = mail;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLast_name(String lastName) {
        this.lastName = lastName;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirst_name(String firstName) {
        this.firstName = firstName;
    }
    public LocalDate getDate() {
        return date;
    }
    public void setDate(LocalDate date) {
        this.date = date;
    }
    public Boolean getIsVaccined() {
        return isVaccined;
    }
    public void setIsVaccined(Boolean isVaccined) {
        this.isVaccined = isVaccined;
    }

    public Vaccination(Centre centre, String mail, String phoneNumber, String lastName, String firstName, LocalDate date) {
        this.centre = centre;
        this.mail = mail;
        this.phoneNumber = phoneNumber;
        this.lastName = lastName;
        this.firstName = firstName;
        this.date = date;
        this.isVaccined = false;
    }
    public boolean isEqualTo(Vaccination v) {
        return this.id == v.id && this.centre.isEqualTo(v.centre) && this.mail == v.mail && this.phoneNumber == v.phoneNumber && this.lastName == v.lastName && this.firstName == v.firstName && this.date == v.date && this.isVaccined == v.isVaccined;
    }
    
}
