package com.example.demo.repository;
import java.util.List;
import com.example.demo.repository.entity.RentareaEntity;

public interface RentareaReponsitory {

    List<RentareaEntity> findvalueByBuildingId ( Long Id );
    
} 