package com.example.demo.service;
import java.util.List;
import com.example.demo.dto.buildingDTO;
import java.util.Map;
import com.example.demo.builder.BuildingSearchBuilder;

// định nghia hàm
public interface BuildingService {
    List<buildingDTO> findAll(Map <String,Object> params, List<String> typecode);

}
