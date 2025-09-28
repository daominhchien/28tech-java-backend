package com.example.demo.service;
import java.util.List;
import com.example.demo.dto.buildingDTO;
import java.util.Map;

// định nghia hàm
public interface BuildingService {
    List<buildingDTO> findAll(Map<String,String> params, List<String> typecode );

}
