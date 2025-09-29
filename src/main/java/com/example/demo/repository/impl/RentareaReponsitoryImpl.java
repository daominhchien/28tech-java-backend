package com.example.demo.repository.impl;
import java.util.List;
import com.example.demo.repository.RentareaReponsitory;
import com.example.demo.repository.entity.RentareaEntity;
import org.springframework.stereotype.Repository;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


@Repository
public class RentareaReponsitoryImpl implements RentareaReponsitory{
            static final String dbUrl = "jdbc:mysql://localhost:3306/estatebasic";
        static final String dbUser = "root";
        static final String dbPassword ="chien1207";

    @Override
    public List<RentareaEntity> findvalueByBuildingId(Long Id) {


        StringBuilder  sql = new StringBuilder("SELECT r.value, r.buildingid FROM rentarea as r WHERE r.buildingid = " + Id  + "");
        List<RentareaEntity> rentarea = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(dbUrl, dbUser, dbPassword)) {
            Statement pstmt = conn.createStatement();
            ResultSet rs = pstmt.executeQuery(sql.toString());
            while (rs.next()) {
                RentareaEntity entity = new RentareaEntity();
                entity.setBuildingid(rs.getString("buildingid"));
                entity.setValue(rs.getInt("value"));
                rentarea.add(entity);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error fetching buildings from database", e);
        }

        return rentarea;}
    }
