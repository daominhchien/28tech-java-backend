package com.example.demo.repository.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.example.demo.repository.BuildingRepository;
import com.example.demo.repository.entity.BuildingEntity;
import com.example.demo.until.ConnectionUtils;
import com.example.demo.until.NumberUntil;
import com.example.demo.until.StringUntil;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

@Repository
public class JDBCBuildingRepositoryImpl implements BuildingRepository {

    @PersistenceContext
    private EntityManager entityManager;

    // join voi assignmentbuildinglay staffID
    // join voi buildingrenttype join voi renttype lay type code
    // join voi district lay district
    // join voi rentarea lay rentarea
    public void joinTable(Map<String, String> params, List<String> typecode, StringBuilder sql) {
        String staffId = (String) params.get("staffId");
        if (StringUntil.checkString(staffId)) {
            sql.append(" inner join assignmentbuilding as ass on b.id = ass.buildingid ");
        }

        if (typecode != null && typecode.size() != 0) {
            sql.append("  inner join buildingrenttype as buil on buil.buildingid = b.id ");
            sql.append(" inner join renttype as rent on rent.id = buil.renttypeid");

        }

        String rentAreaTo = (String) params.get("max_floorarea");
        String rentAreaFrom = (String) params.get("min_floorarea");
        // lay area thue tung building so sanh vs area
        if (StringUntil.checkString(rentAreaTo) && StringUntil.checkString(rentAreaFrom)) {
            sql.append(" inner join rentarea as r on b.id = r.buildingid ");

        }
        // c2(clean) dùng exist bên queryspecial khỏi càn join, bỏ join bên này qua hàm
        // quẻySpecial

    }

    public static void Normal(Map<String, String> params, StringBuilder where) {

        for (Map.Entry<String, String> it : params.entrySet()) {
            if (!it.getKey().equals("staffId") && !it.getKey().equals("typeCode")
                    && !it.getKey().equals("min_floorarea") && !it.getKey().equals("max_floorarea")
                    && !it.getKey().equals("gia_thue_min") && !it.getKey().equals("gia_thue_max")) {
                String value = it.getValue().toString(); // chuyen object ve string
                if (StringUntil.checkString(value)) {
                    if (NumberUntil.checkNumber(value) == true) {
                        where.append(" and b." + it.getKey() + " = " + it.getValue());
                    }
                } else {
                    where.append(" and b." + it.getKey() + " LIKE '%" + it.getValue() + "%' ");
                }
            }
        }

    }

    public static void querySpecial(Map<String, String> params, List<String> typecode, StringBuilder where) {
        String staffId = (String) params.get("staffId");
        if (StringUntil.checkString(staffId)) {
            where.append(" AND ass.staffid = " + staffId);

        }
        String rentAreaTo = (String) params.get("max_floorarea");
        String rentAreaFrom = (String) params.get("min_floorarea");
        // lay area thue
        if (StringUntil.checkString(rentAreaTo) && StringUntil.checkString(rentAreaFrom)) {
            if (StringUntil.checkString(rentAreaFrom)) {
                where.append(" AND r.value >= " + rentAreaFrom);
            }
            if (StringUntil.checkString(rentAreaTo)) {
                where.append(" AND r.value <= " + rentAreaTo);
            }
        }

        // so sanh floorarea
        String FloorTo = (String) params.get("max_floorarea");
        String FloorFrom = (String) params.get("min_floorarea");
        if (StringUntil.checkString(FloorTo) && StringUntil.checkString(FloorFrom)) {
            // where.append ( ... and exists())
            if (StringUntil.checkString(FloorFrom)) {
                where.append(" AND b.floorarea >= " + FloorFrom);
            }
            if (StringUntil.checkString(FloorTo)) {
                where.append(" AND b.floorarea <= " + FloorTo);
            }
            // where.append(" )")
        }
        // so sang rice

        String RiceTo = (String) params.get("gia_thue_max");
        String RiceFrom = (String) params.get("gia_thue_min");
        if (StringUntil.checkString(RiceTo) && StringUntil.checkString(RiceFrom)) {
            if (StringUntil.checkString(RiceFrom)) {
                where.append(" AND b.rentprice >= " + RiceFrom);
            }
            if (StringUntil.checkString(RiceTo)) {
                where.append(" AND b.rentprice <= " + RiceTo);
            }
        }
        // java 7
        // if ( typecode !=null && typecode.size() !=0){
        // List<String> code = new ArrayList<>();
        // for (String item : typecode ){
        // code.add("'" + item + "'");
        // }
        // where.append(" AND rent.typecode IN (" + String.join(",", code) + ")");
        // }

        // java 8 stream
        if (typecode != null && typecode.size() != 0) {
            where.append(" AND (");
            String sql = typecode.stream()
                    .map(item -> "renttype.code LIKE" + "'%" + item + "%'")
                    .collect(Collectors.joining(" OR "));
            where.append(sql);
            where.append(" )");
        }

    }

    @Override
    public List<BuildingEntity> findAll(Map<String, String> params, List<String> typecode) {

        List<BuildingEntity> buildingS = new ArrayList<>();
        try (Connection conn = ConnectionUtils.getConnection()) {
            // StringBuilder sql = new StringBuilder(
            // " select b.id, b.name, b.street, b.ward, b.districtid, b.structure,
            // b.numberofbasement, b.floorarea, b.servicefee, b.brokeragefee, b.direction,
            // b.level, b.rentprice, b.rentpricedescription, b.managername,
            // b.managerphonenumber from building as b ");
            StringBuilder sql = new StringBuilder(
                    " select b.* from building as b ");
            joinTable(params, typecode, sql);
            StringBuilder where = new StringBuilder(" WHERE 1=1 ");
            Normal(params, where);
            querySpecial(params, typecode, where);
            where.append(" GROUP BY b.id ");
            sql.append(where);

            Query query = entityManager.createNativeQuery(sql.toString(), BuildingEntity.class);
            buildingS = query.getResultList();

            // System.out.println(sql);
            // Statement pstmt = conn.createStatement();
            // ResultSet rs = pstmt.executeQuery(sql.toString());

            // while (rs.next()) {
            // BuildingEntity bd = new BuildingEntity();
            // bd.setId(rs.getLong("id"));
            // bd.setName(rs.getString("name"));
            // bd.setStreet(rs.getString("street"));
            // bd.setWard(rs.getString("ward"));
            // // bd.setDistrict(rs.getString("districtid"));
            // bd.setStructure(rs.getString("structure"));
            // bd.setNumberofbasement(rs.getInt("numberofbasement"));
            // bd.setFloorarea(rs.getInt("floorarea"));
            // bd.setServicefee(rs.getString("servicefee"));
            // bd.setBrokeragefee(rs.getString("brokeragefee"));
            // bd.setDirection(rs.getString("direction"));
            // bd.setLevel(rs.getString("level"));
            // bd.setRentprice(rs.getInt("rentprice"));
            // bd.setRentpricedescription(rs.getString("rentpricedescription"));
            // bd.setManagername(rs.getString("managername"));
            // bd.setManagerphone(rs.getString("managerphonenumber"));

            // // Map other fields as necessary

            // buildingS.add(bd);
            // }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error fetching buildings from database", e);
        }

        return buildingS;
    }

}
