package com.example.demo.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.buildingDTO;
import com.example.demo.dto.buildingRequestDTO;
import com.example.demo.repository.entity.BuildingEntity;
import com.example.demo.repository.entity.DistrictEntity;
import com.example.demo.service.BuildingService;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@RestController
public class BuildingApi {

    // @GetMapping(value = "/api/building/")
    // public buildingDTO getBuilding(@RequestParam (value ="name", required =
    // false) String name,
    // @RequestParam (value ="number", required = false) int number,
    // @RequestParam (value ="ward", required = false) String ward) {

    // // Xử lý logic ở đây, ví dụ lưu vào database hoặc xử lý dữ liệu
    // buildingDTO building_1 = new buildingDTO();
    // building_1.setName(name);
    // building_1.setNumber(number);
    // building_1.setWard(ward);
    // System.out.println("Building API accessed: " + name + ", Number: " + number +
    // ", Ward: " + ward);
    // return building_1;
    // }

    @PersistenceContext
    private EntityManager entityManager;

    // goi qua interface service
    @Autowired
    private BuildingService buildingService;

    @GetMapping(value = "/api/building/")
    public List<buildingDTO> GetListBuilding(@RequestParam Map<String, String> params,
            @RequestParam(value = "typecode", required = false) List<String> typecode) {
        List<buildingDTO> results = buildingService.findAll(params, typecode);
        return results;
    }

    // @PostMapping(value = "/api/building/")
    // public Object getListBuilding(@RequestBody buildingDTO building) {

    // ValiDate(building);
    // return null;

    // }

    // public void ValiDate ( buildingDTO building) {
    // if (building.getName() == null || building.getName().isEmpty() ) {
    // throw new FieldRequiredException("Name is required");
    // }

    // }

    // ---------- params Map --------------
    // @RequestMapping(value = "/api/building/", method = RequestMethod.POST)
    // public void getBuilding2(@RequestParam Map<String,String> params) {

    // System.out.println("Building API accessed: ok");
    // }

    // ---------- body Map --------------
    // @RequestMapping(value = "/api/building/", method = RequestMethod.POST)
    // public void getBuilding3(@RequestBody Map<String,String> params) {

    // System.out.println("Building API accessed: ok");
    // }

    // ---------- body DTO --------------
    // @PostMapping(value = "/api/building/")
    // public buildingDTO getBuilding3(@RequestBody buildingDTO building) {
    // // sau xư lý dưới db

    // System.out.println("Building API accessed: ok");
    // return building;
    // }

    // -------TEST Post JPA ----------
    @PostMapping(value = "/api/building/")
    @Transactional
    public void createBuilding(@RequestBody buildingRequestDTO building) {
        // Xử lý logic để lưu building vào database
        BuildingEntity buildingEntity = new BuildingEntity();
        buildingEntity.setName(building.getName());
        buildingEntity.setStreet(building.getStreet());
        buildingEntity.setWard(building.getWard());
        buildingEntity.setRentprice(building.getRentprice());

        // Lấy DistrictEntity từ DB theo id gửi lên từ DTO
        DistrictEntity districtEntity = entityManager.find(DistrictEntity.class, building.getDistrict());
        if (districtEntity == null) {
            throw new RuntimeException("District không tồn tại với id = ");
        }
        buildingEntity.setDistrictEntity(districtEntity);
        entityManager.persist(buildingEntity);
        System.out.println("Đã tạo mới");
    }

    @DeleteMapping(value = "/api/building/{id}/")
    public void deleteBuilding(@PathVariable(required = false) int id,
            @RequestParam(value = "ward", required = false) String ward) {
        System.out.println("Đã xóa: " + id + ", Ward: " + ward);
    }
}
