package com.example.demo.repository;

import com.example.demo.repository.entity.DistrictEntity;

public interface DistrictRepository {
    DistrictEntity findNameById ( String id );
} 