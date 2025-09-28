package com.example.demo.repository.impl;

import com.example.demo.repository.DistrictRepository;
import com.example.demo.repository.entity.DistrictEntity;
import com.example.demo.until.ConnectionUtils;

import java.lang.StringBuilder;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import com.example.demo.until.*;

@Repository
public class DistrictRepositoryImpl implements DistrictRepository {

    // @Override
    // public DistrictEntity findNameById(String id) {

    // StringBuilder sql = new StringBuilder("SELECT d.name FROM district as d WHERE
    // d.id = " + id + "");
    // DistrictEntity district = new DistrictEntity();
    // try (Connection conn = ConnectionUtils.getConnection()) {
    // Statement pstmt = conn.createStatement();
    // ResultSet rs = pstmt.executeQuery(sql.toString());

    // while (rs.next()) {
    // district.setName(rs.getString("name"));
    // }
    // } catch (SQLException e) {
    // e.printStackTrace();
    // throw new RuntimeException("Error fetching buildings from database", e);
    // }

    // return district;
    // }

}
