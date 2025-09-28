package com.example.demo.service.impl;
import com.example.demo.service.BuildingService;
import java.util.List;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.buildingDTO;
import com.example.demo.repository.BuildingRepository;
import com.example.demo.repository.entity.BuildingEntity;
import java.util.Map;
import com.example.demo.repository.DistrictRepository;
import com.example.demo.repository.entity.DistrictEntity;
import com.example.demo.repository.entity.RentareaEntity;
import com.example.demo.repository.RentareaReponsitory;
import com.example.demo.until.StringUntil;
import com.example.demo.until.NumberUntil;
import com.example.demo.converter.BuildingDTOConverter;




@Service
public class BuildingServiceImpl  implements BuildingService {

    // goi qua interface
    @Autowired
    private BuildingRepository buildingRepository;

    @Autowired
    private BuildingDTOConverter buildingDTOConverter;

    @Override
    public List<buildingDTO> findAll(Map<String,String> params, List<String> typecode  ) {
        // nhận dữ liệu từ repository
        List<BuildingEntity> entities = buildingRepository.findAll(params,typecode);
        // chuyển đổi entity -> dto
        // Giai đoạn filter
        List<buildingDTO> results = new ArrayList<buildingDTO>();
        for (BuildingEntity entity : entities) {
            // buildingDTO dto = new buildingDTO();
            // bên kia tra ve
            buildingDTO dto = buildingDTOConverter.toBuildingDTO(entity);
            results.add(dto);
        }
        return results;
    }

}
