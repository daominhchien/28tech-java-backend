package com.example.demo.converter;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.dto.buildingDTO;
import com.example.demo.repository.entity.BuildingEntity;
import com.example.demo.repository.entity.DistrictEntity;
import com.example.demo.repository.entity.RentareaEntity;

@Component
public class BuildingDTOConverter {

    // @Autowired
    // private DistrictRepository districtRepository;

    // @Autowired
    // private RentareaReponsitory rentareaReponsitory;

    @Autowired
    private ModelMapper modelMapper;

    public buildingDTO toBuildingDTO(BuildingEntity entity) {
        buildingDTO dto = modelMapper.map(entity, buildingDTO.class);

        // nhan du lieu tu reponse
        // DistrictEntity district =
        // districtRepository.findNameById(entity.getDistrictEntity());
        DistrictEntity district = entity.getDistrictEntity();
        dto.setAddress(entity.getStreet() + ", " + entity.getWard() + ", " + district.getName());
        // List<RentareaEntity> rentarea =
        // rentareaReponsitory.findvalueByBuildingId(entity.getId());
        List<RentareaEntity> rentarea = entity.getRentAreas();
        List<String> rentareas = new ArrayList<>();
        for (RentareaEntity item : rentarea) {
            // chuyen ins sang string
            rentareas.add(item.getValue().toString());
        }
        // c2: dùng .stream() + .map() + .collect()
        dto.setRentArea(rentareas);

        // dto.setName(entity.getName());
        // dto.setNumberofbasement(entity.getNumberofbasement());
        // dto.setManagername(entity.getManagername());
        // dto.setManagerphone(entity.getManagerphone());
        // dto.setFloorarea(entity.getFloorarea());
        // dto.setRentprice(entity.getRentprice());
        // dto.setServicefee(entity.getServicefee());
        // dto.setBrokeragefee(entity.getBrokeragefee());

        return dto;
    }

}
