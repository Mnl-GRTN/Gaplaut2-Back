package org.example.service;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;

@Entity
public class Vaccination {

    @Id
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "centre_id", referencedColumnName = "id")
    private Centre Centre;

    private String mail;
    private String phoneNumber;
    private String last_name;
    private String first_name;
    private LocalDateTime date;
    private Boolean isVaccined;
    
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public Centre getCentre() {
        return Centre;
    }
    public void setCentre(Centre centre) {
        Centre = centre;
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
    public String getLast_name() {
        return last_name;
    }
    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }
    public String getFirst_name() {
        return first_name;
    }
    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }
    public LocalDateTime getDate() {
        return date;
    }
    public void setDate(LocalDateTime date) {
        this.date = date;
    }
    public Boolean getIsVaccined() {
        return isVaccined;
    }
    public void setIsVaccined(Boolean isVaccined) {
        this.isVaccined = isVaccined;
    }

    
}
