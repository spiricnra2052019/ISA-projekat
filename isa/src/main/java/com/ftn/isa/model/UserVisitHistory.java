package com.ftn.isa.model;

import java.time.LocalDate;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity(name = "UserVisitHistory")
public class UserVisitHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(name = "bloodType", nullable = false)
    private String bloodType;

    @Column(name = "quantity", nullable = false)
    private int quantity;

    @Column(name = "numberOfEquipmentUsed", nullable = false)
    private int numberOfEquipmentUsed;

    @Column(name = "description", nullable = false)
    private String description;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private RegisteredUser user;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name = "date", nullable = true)
    private LocalDate date;

    public UserVisitHistory(Long id, String bloodType, int quantity, int numberOfEquipmentUsed, String description,
            RegisteredUser user, LocalDate date) {
        this.id = id;
        this.bloodType = bloodType;
        this.quantity = quantity;
        this.numberOfEquipmentUsed = numberOfEquipmentUsed;
        this.description = description;
        this.user = user;
        this.date = date;
    }

    public UserVisitHistory(Long id, String bloodType, int quantity, int numberOfEquipmentUsed, String description,
            RegisteredUser user) {
        this.id = id;
        this.bloodType = bloodType;
        this.quantity = quantity;
        this.numberOfEquipmentUsed = numberOfEquipmentUsed;
        this.description = description;
        this.user = user;
    }

    public UserVisitHistory() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBloodType() {
        return bloodType;
    }

    public void setBloodType(String bloodType) {
        this.bloodType = bloodType;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getNumberOfEquipmentUsed() {
        return numberOfEquipmentUsed;
    }

    public void setNumberOfEquipmentUsed(int numberOfEquipmentUsed) {
        this.numberOfEquipmentUsed = numberOfEquipmentUsed;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public RegisteredUser getUser() {
        return user;
    }

    public void setUser(RegisteredUser user) {
        this.user = user;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
