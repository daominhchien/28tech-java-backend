package com.example.demo.repository;
import java.util.List;
import com.example.demo.repository.entity.BuildingEntity;
import java.util.ArrayList;
import java.util.Map;
import com.example.demo.builder.BuildingSearchBuilder;

public interface BuildingRepository {
    List<BuildingEntity> findAll(BuildingSearchBuilder buildingSearchBuilder);

}
