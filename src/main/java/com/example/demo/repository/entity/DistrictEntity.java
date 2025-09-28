package com.example.demo.repository.entity;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;
import java.util.List;
import java.util.ArrayList;



@Entity
@Table(name="district")
public class DistrictEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="code")
    private String code;

    @Column(name="name")
    private String name;

    // 1 quận có nhiều building
    // mappedBy: ánh xj từ field bên BuildingEntity
    @OneToMany(mappedBy ="districtEntity", fetch= FetchType.LAZY)
    private List<BuildingEntity> buildings = new ArrayList<>();
    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public List<BuildingEntity> getBuildings() {
        return buildings;
    }
    public void setBuildings(List<BuildingEntity> buildings) {
        this.buildings = buildings;
    }
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

}
