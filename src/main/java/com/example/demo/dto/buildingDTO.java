package com.example.demo.dto;
import java.util.List;

public class buildingDTO {
    private String name; 
    private int numberofbasement;
    private String address;
    private String managername;
    private String managerphone;
    private Integer floorarea;
    private Integer rentprice;
    private String servicefee;
    private String brokeragefee;
    private List<String> rentArea;

    public List<String> getRentArea() {
        return rentArea;
    }
    public void setRentArea(List<String> rentArea) {
        this.rentArea = rentArea;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getNumberofbasement() {
        return numberofbasement;
    }
    public void setNumberofbasement(int numberofbasement) {
        this.numberofbasement = numberofbasement;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getManagername() {
        return managername;
    }
    public void setManagername(String managername) {
        this.managername = managername;
    }
    public String getManagerphone() {
        return managerphone;
    }
    public void setManagerphone(String managerphone) {
        this.managerphone = managerphone;
    }
    public Integer getFloorarea() {
        return floorarea;
    }
    public void setFloorarea(Integer floorarea) {
        this.floorarea = floorarea;
    }
    public Integer getRentprice() {
        return rentprice;
    }
    public void setRentprice(Integer rentprice) {
        this.rentprice = rentprice;
    }
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




}
