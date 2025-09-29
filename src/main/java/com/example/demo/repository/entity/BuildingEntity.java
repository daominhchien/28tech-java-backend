package com.example.demo.repository.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Column;

public class BuildingEntity {
    
    private Long id;
    private String name;//
    private String street;//
    private String ward;//
    private String district;
    private String structure;
    private Integer numberofbasement;//
    private Integer floorarea;//
    private String direction;//
    private String level;//
    private Integer rentprice;//
    private String servicefee;
    private String brokeragefee;
    private String rentpricedescription;

    private String managername;//
    private String managerphonenumber;//

    
    public String getServicefee() {
        return servicefee;
    }
    public void setServicefee(String servicefee) {
        this.servicefee = servicefee;
    }
    public String getBrokeragefee() {
        return brokeragefee;
    }
    public void setBrokeragefee(String brokeragefee) {
        this.brokeragefee = brokeragefee;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getStreet() {
        return street;
    }
    public void setStreet(String street) {
        this.street = street;
    }
    public String getWard() {
        return ward;
    }
    public void setWard(String ward) {
        this.ward = ward;
    }
    public String getDistrict() {
        return district;
    }
    public void setDistrict(String district) {
        this.district = district;
    }
    public String getStructure() {
        return structure;
    }
    public void setStructure(String structure) {
        this.structure = structure;
    }
    public Integer getNumberofbasement() {
        return numberofbasement;
    }
    public void setNumberofbasement(Integer numberofbasement) {
        this.numberofbasement = numberofbasement;
    }
    public Integer getFloorarea() {
        return floorarea;
    }
    public void setFloorarea(Integer floorarea) {
        this.floorarea = floorarea;
    }
    public String getDirection() {
        return direction;
    }
    public void setDirection(String direction) {
        this.direction = direction;
    }
    public String getLevel() {
        return level;
    }
    public void setLevel(String level) {
        this.level = level;
    }
    public Integer getRentprice() {
        return rentprice;
    }
    public void setRentprice(Integer rentprice) {
        this.rentprice = rentprice;
    }
    public String getRentpricedescription() {
        return rentpricedescription;
    }
    public void setRentpricedescription(String rentpricedescription) {
        this.rentpricedescription = rentpricedescription;
    }
    public String getManagername() {
        return managername;
    }
    public void setManagername(String managername) {
        this.managername = managername;
    }
    public String getManagerphone() {
        return managerphonenumber;
    }
    public void setManagerphone(String managerphonenumber) {
        this.managerphonenumber = managerphonenumber;
    }
    
}
