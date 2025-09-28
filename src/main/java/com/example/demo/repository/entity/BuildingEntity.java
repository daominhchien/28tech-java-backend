package com.example.demo.repository.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "building")
public class BuildingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;//

    @Column(name = "street")
    private String street;//

    @Column(name = "ward")
    private String ward;//

    @Column(name = "structure")
    private String structure;

    @Column(name = "numberofbasement")
    private Integer numberofbasement;

    @Column(name = "floorarea")
    private Integer floorarea;

    @Column(name = "direction")
    private String direction;

    @Column(name = "level")
    private String level;

    @Column(name = "rentprice")
    private Integer rentprice;

    @Column(name = "servicefee")
    private String servicefee;

    @Column(name = "brokeragefee")
    private String brokeragefee;

    @Column(name = "rentpricedescription")
    private String rentpricedescription;

    @Column(name = "managername")
    private String managername;

    @Column(name = "managerphonenumber")
    private String managerphonenumber;

    // 1> nhiều building thuộc về 1 quận
    @ManyToOne
    @JoinColumn(name = "districtid") // tạo ra cột districtid trong bảng building
    private DistrictEntity districtEntity;

    // 2> 1 building có nhiều rentarea
    @OneToMany(mappedBy = "buildingEntity", fetch = FetchType.LAZY)
    private List<RentareaEntity> rentAreas = new ArrayList<>();

    public List<RentareaEntity> getRentAreas() {
        return rentAreas;
    }

    public void setRentAreas(List<RentareaEntity> rentAreas) {
        this.rentAreas = rentAreas;
    }

    public DistrictEntity getDistrictEntity() {
        return districtEntity;
    }

    public void setDistrictEntity(DistrictEntity districtEntity) {
        this.districtEntity = districtEntity;
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

    public String getManagerphonenumber() {
        return managerphonenumber;
    }

    public void setManagerphonenumber(String managerphonenumber) {
        this.managerphonenumber = managerphonenumber;
    }

}
