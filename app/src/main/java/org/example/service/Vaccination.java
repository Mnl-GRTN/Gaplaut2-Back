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
    private Centre Centre;

    private String mail;
    private String phoneNumber;
    private String last_name;
    private String first_name;

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
    public boolean isEqualTo(Vaccination v) {
        return this.id == v.id && this.Centre == v.Centre && this.mail.equals(v.mail) && this.phoneNumber.equals(v.phoneNumber) && this.last_name.equals(v.last_name) && this.first_name.equals(v.first_name) && this.date.equals(v.date) && this.isVaccined == v.isVaccined;
    }
    
}
