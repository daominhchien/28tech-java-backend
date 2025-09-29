package com.example.demo.repository.impl;
import com.example.demo.dto.buildingDTO;
import com.example.demo.repository.BuildingRepository;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.demo.repository.entity.BuildingEntity;
import com.example.demo.until.NumberUntil;
import com.example.demo.until.StringUntil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import com.example.demo.builder.BuildingSearchBuilder;
import java.lang.reflect.Field;
import java.util.HashMap;




@Repository 
public class BuildingRepositoryImpl implements BuildingRepository {
        static final String dbUrl = "jdbc:mysql://localhost:3306/estatebasic";
        static final String dbUser = "root";
        static final String dbPassword ="chien1207";

        // join voi assignmentbuildinglay staffID
        // join voi buildingrenttype join voi renttype lay type code
        // join voi district lay district
        // join voi rentarea lay rentarea
    public void joinTable(BuildingSearchBuilder buildingSearchBuilder, StringBuilder sql){

        //dungf get 

        Integer staffId = buildingSearchBuilder.getStaffId();
        if (staffId != null){
            sql.append(" inner join assignmentbuilding as ass on b.id = ass.buildingid ");
        }
        List<String> typecode = buildingSearchBuilder.getTypecode();

        if ( typecode !=null && typecode.size() !=0){
            sql.append( "  inner join buildingrenttype as buil on buil.buildingid = b.id ");
            sql.append(" inner join renttype as rent on rent.id = buil.renttypeid");

        }

        Integer rentAreaTo =buildingSearchBuilder.getMax_floorarea();
        Integer rentAreaFrom =buildingSearchBuilder.getMin_floorarea();

        // lay area thue tung building so sanh vs area
        if (rentAreaTo != null && rentAreaFrom != null){
            sql.append(" inner join rentarea as r on b.id = r.buildingid ");

        }
        // c2(clean) dùng exist bên queryspecial khỏi càn join, bỏ join bên này qua hàm quẻySpecial
 
    }

    public static void Normal(BuildingSearchBuilder buildingSearchBuilder, StringBuilder where){

        // for( Map.Entry<String,String> it : params.entrySet()){
        //     if(!it.getKey().equals("staffId") && !it.getKey().equals("typeCode") && !it.getKey().equals("min_floorarea") && !it.getKey().equals("max_floorarea") && !it.getKey().equals("gia_thue_min") && !it.getKey().equals("gia_thue_max")){
        //         String value = it.getValue().toString(); //chuyen object ve string
        //         if(StringUntil.checkString(value)){
        //             if(NumberUntil.checkNumber(value) == true){
        //                 where.append(" and b." +it.getKey() +" = "+ it.getValue());
        //             }
        //         }
        //         else{
        //             where.append(" and b." +it.getKey() +" LIKE '%"+ it.getValue() + "%' " );


        //         }
        //     }
        // }

        try{
            Field[] fields = BuildingSearchBuilder.class.getDeclaredFields();
            for ( Field item: fields ){
                // co truy cap vao rieng tu
                item.setAccessible(true);
                String fieldName = item.getName();
                // eps kieu ve string
                Object filedvalue = item.get(buildingSearchBuilder);
                if(!fieldName.equals("staffId") && !fieldName.equals("typecode") && !fieldName.equals("min_floorarea") && !fieldName.equals("max_floorarea") && !fieldName.equals("gia_thue_min") && !fieldName.equals("gia_thue_max")){
                    if(filedvalue != null){
                        // kiem tra type cua so 
                        if(item.getType().getName().equals("java.lang.Integer") || item.getType().getName().equals("java.lang.Long") ){
                            where.append(" and b." + fieldName + " = " + filedvalue);
                        }
                        // neu laf string
                        else{
                            where.append(" and b." + fieldName + " LIKE '%" + filedvalue + "%' ");
                        }
                    }
                }

            

            }

        }catch(Exception e){
            e.printStackTrace();
        }

        
    }

    public static void querySpecial(BuildingSearchBuilder buildingSearchBuilder, StringBuilder where){
        Integer staffId = buildingSearchBuilder.getStaffId();
        if(staffId != null){
            where.append(" AND ass.staffid = " + staffId );

        }
        Integer rentAreaTo = buildingSearchBuilder.getMax_floorarea();
        Integer rentAreaFrom = buildingSearchBuilder.getMin_floorarea();
        // lay area thue
        if (rentAreaTo != null && rentAreaFrom != null){
            if(rentAreaFrom != null){
                where.append(" AND r.value >= " + rentAreaFrom);
            }
            if(rentAreaTo != null){
                where.append(" AND r.value <= " + rentAreaTo);
            }
        }

        // so sanh floorarea
        Integer FloorTo = buildingSearchBuilder.getMax_floorarea();
        Integer FloorFrom = buildingSearchBuilder.getMin_floorarea();
        if (FloorTo != null && FloorFrom != null){
            // where.append ( ... and exists())
            if(FloorFrom != null){
                where.append(" AND b.floorarea >= " + FloorFrom);
            }
            if(FloorTo != null){
                where.append(" AND b.floorarea <= " + FloorTo);
            }
            // where.append(" )")
        }
        // so sang rice

        Integer RiceTo = buildingSearchBuilder.getGia_thue_max();
        Integer RiceFrom = buildingSearchBuilder.getGia_thue_min();
            if (RiceTo != null && RiceFrom != null){
            if(RiceFrom != null){
                where.append(" AND b.rentprice >= " + RiceFrom);
            }
            if(RiceTo != null){
                where.append(" AND b.rentprice <= " + RiceTo);
            }
        }
        // java 7
        //  if ( typecode !=null && typecode.size() !=0){
        //     List<String> code = new ArrayList<>();
        //     for (String item : typecode ){
        //         code.add("'" + item + "'");
        //     }
        //     where.append(" AND rent.typecode IN (" + String.join(",", code) + ")");
        //  }

        // java 8 stream
        List<String> typecode = buildingSearchBuilder.getTypecode();
            if ( typecode !=null && typecode.size() !=0){
                where.append(" AND (");
                String sql = typecode.stream().map(item -> "renttype.code LIKE" + "'%" + item + "%'").collect(Collectors.joining(" OR "));
                where.append(sql);
                where.append(" )");
            }


    }

    @Override
    public List<BuildingEntity> findAll(BuildingSearchBuilder buildingSearchBuilder) {

        
        List<BuildingEntity> buildingS = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(dbUrl, dbUser, dbPassword)) {
            StringBuilder sql = new StringBuilder(" select * from building as b ");
            joinTable(buildingSearchBuilder, sql);
            StringBuilder where = new StringBuilder(" WHERE 1=1 ");
            Normal(buildingSearchBuilder, where);
            querySpecial(buildingSearchBuilder, where);
            where.append(" GROUP BY b.id ");
            sql.append(where);

            System.out.println(sql);
            Statement pstmt = conn.createStatement();
            ResultSet rs = pstmt.executeQuery(sql.toString());

            while (rs.next()) {
                BuildingEntity bd = new BuildingEntity();
                bd.setId(rs.getLong("id"));
                bd.setName(rs.getString("name"));
                bd.setStreet(rs.getString("street"));
                bd.setWard(rs.getString("ward"));
                bd.setDistrict(rs.getString("districtid"));
                bd.setStructure(rs.getString("structure"));
                bd.setNumberofbasement(rs.getInt("numberofbasement"));
                bd.setFloorarea(rs.getInt("floorarea"));
                bd.setServicefee(rs.getString("servicefee"));
                bd.setBrokeragefee(rs.getString("brokeragefee"));
                bd.setDirection(rs.getString("direction"));
                bd.setLevel(rs.getString("level"));
                bd.setRentprice(rs.getInt("rentprice"));
                bd.setRentpricedescription(rs.getString("rentpricedescription"));
                bd.setManagername(rs.getString("managername"));
                bd.setManagerphone(rs.getString("managerphonenumber"));
       


                    // Map other fields as necessary

                buildingS.add(bd);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error fetching buildings from database", e);
        }

        return buildingS;
    }




}
