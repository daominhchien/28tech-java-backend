package com.example.demo.repository.impl;

import org.springframework.stereotype.Repository;

import com.example.demo.repository.RentareaReponsitory;

@Repository
public class RentareaReponsitoryImpl implements RentareaReponsitory {

    // @Override
    // public List<RentareaEntity> findvalueByBuildingId(Long Id) {

    // StringBuilder sql = new StringBuilder("SELECT r.value, r.buildingid FROM
    // rentarea as r WHERE r.buildingid = " + Id + "");
    // List<RentareaEntity> rentarea = new ArrayList<>();
    // try (Connection conn = ConnectionUtils.getConnection()) {
    // // dùng để tạo câu lệnh truy vấn
    // Statement pstmt = conn.createStatement();
    // // thực thi câu lệnh truy vấn và trả về kết quả
    // ResultSet rs = pstmt.executeQuery(sql.toString());
    // while (rs.next()) {
    // RentareaEntity entity = new RentareaEntity();
    // entity.setBuildingid(rs.getString("buildingid"));
    // entity.setValue(rs.getInt("value"));
    // rentarea.add(entity);
    // }
    // } catch (SQLException e) {
    // e.printStackTrace();
    // throw new RuntimeException("Error fetching buildings from database", e);
    // }

    // return rentarea;}
}
