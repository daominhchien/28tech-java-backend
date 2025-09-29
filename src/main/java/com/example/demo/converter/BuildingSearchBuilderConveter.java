package com.example.demo.converter;
import java.util.List;
import java.util.Map;
import com.example.demo.builder.BuildingSearchBuilder;
import com.example.demo.until.MapUtil;
import org.springframework.stereotype.Component;



//  nhan biet laf 1 spring bean
@Component
public class BuildingSearchBuilderConveter {
    // Tra ve 1 cais BuildingSearchBuilder
    public BuildingSearchBuilder toBuildingSearchBuilder ( Map <String,Object> params, List<String> typecode){
        BuildingSearchBuilder buildingSearchBuilder = new BuildingSearchBuilder.Builder()
                                                                               .setName(MapUtil.getObject(params, "name",String.class ))
                                                                               .setNumberofbasement(MapUtil.getObject(params, "numberofbasement", Integer.class))
                                                                               .setWard(MapUtil.getObject(params, "ward", String.class))
                                                                               .setStreet(MapUtil.getObject(params, "street", String.class))
                                                                               .setGia_thue_max(MapUtil.getObject(params, "gia_thue_max", Integer.class))
                                                                               .setGia_thue_min(MapUtil.getObject(params, "gia_thue_min", Integer.class))
                                                                               .setManagername(MapUtil.getObject(params, "managername", String.class))
                                                                               .setManagerphone(MapUtil.getObject(params, "managerphone", String.class))
                                                                               .setMin_floorarea(MapUtil.getObject(params, "min_floorarea", Integer.class))
                                                                               .setMax_floorarea(MapUtil.getObject(params, "max_floorarea", Integer.class))
                                                                               .setStaffId(MapUtil.getObject(params, "staffId", Integer.class))
                                                                               .setTypecode(typecode)
                                                                                .build();


        return buildingSearchBuilder;


    }

}

// => caamf ddem xuong tang reponsitory
