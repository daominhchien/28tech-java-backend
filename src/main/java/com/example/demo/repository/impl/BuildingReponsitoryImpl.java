package com.example.demo.repository.impl;

import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.example.demo.repository.BuildingRepository;
import com.example.demo.repository.entity.BuildingEntity;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

@Repository
@Primary // chỉ định dùng
public class BuildingReponsitoryImpl implements BuildingRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<BuildingEntity> findAll(Map<String, String> params, List<String> typecode) {
        // TODO Auto-generated method stub

        // JPQL
        // String jpql = "SELECT b FROM BuildingEntity b WHERE 1=1";
        // Query query = entityManager.createQuery(jpql, BuildingEntity.class);
        // return query.getResultList();

        // C2: sql native ( dungf nhieeuf )
        String sql = "SELECT * FROM building WHERE 1=1";
        Query query = entityManager.createNativeQuery(sql, BuildingEntity.class);
        return query.getResultList();

    }

}
