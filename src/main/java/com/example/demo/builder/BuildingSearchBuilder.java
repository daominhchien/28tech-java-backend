package com.example.demo.builder;
import java.util.List;


public class BuildingSearchBuilder {
    private String name; //
    private Integer numberofbasement;
    private String ward;//
    private String street;//
    private String managername;//
    private String managerphone;//
    private Integer min_floorarea;//
    private Integer max_floorarea;//
    private Integer gia_thue_min;//
    private Integer gia_thue_max;//
    private Integer staffId;
    private List<String> typecode;



    // Hàm khởi tạo=> tham số là con 
    public BuildingSearchBuilder(Builder builder) {
        this.name = builder.name;
        this.numberofbasement = builder.numberofbasement;
        this.ward = builder.ward;
        this.street = builder.street;
        this.managername = builder.managername;
        this.managerphone = builder.managerphone;
        this.min_floorarea = builder.min_floorarea;
        this.max_floorarea = builder.max_floorarea;
        this.gia_thue_min = builder.gia_thue_min;
        this.gia_thue_max = builder.gia_thue_max;
        this.staffId = builder.staffId;
        this.typecode = builder.typecode;
    }
           public List<String> getTypecode() {
        return typecode;
    }

    public String getName() {
        return name;
    }
    public Integer getNumberofbasement() {
        return numberofbasement;
    }
    public String getWard() {
        return ward;
    }
    public String getStreet() {
        return street;
    }
    public String getManagername() {
        return managername;
    }
    public String getManagerphone() {
        return managerphone;
    }
    public Integer getMin_floorarea() {
        return min_floorarea;
    }
    public Integer getMax_floorarea() {
        return max_floorarea;
    }
    public Integer getGia_thue_min() {
        return gia_thue_min;
    }
    public Integer getGia_thue_max() {
        return gia_thue_max;
    }
    public Integer getStaffId() {
        return staffId;
    }

    // class con 
    public static class Builder{
        private String name; //
        private Integer numberofbasement;
        private String ward;//
        private String street;//
        private String managername;//
        private String managerphone;//
        private Integer min_floorarea;//
        private Integer max_floorarea;//
        private Integer gia_thue_min;//
        private Integer gia_thue_max;//
        private Integer staffId;
        private List<String> typecode;
        


        public Builder setName(String name) {
            this.name = name;
            return this;
        }



        public Builder setNumberofbasement(Integer numberofbasement) {
            this.numberofbasement = numberofbasement;
            return this;
        }



        public Builder setWard(String ward) {
            this.ward = ward;
            return this;
        }



        public Builder setStreet(String street) {
            this.street = street;
            return this;
        }



        public Builder setManagername(String managername) {
            this.managername = managername;
            return this;
        }



        public Builder setManagerphone(String managerphone) {
            this.managerphone = managerphone;
            return this;
        }



        public Builder setMin_floorarea(Integer min_floorarea) {
            this.min_floorarea = min_floorarea;
            return this;
        }



        public Builder setMax_floorarea(Integer max_floorarea) {
            this.max_floorarea = max_floorarea;
            return this;
        }



        public Builder setGia_thue_min(Integer gia_thue_min) {
            this.gia_thue_min = gia_thue_min;
            return this;
        }



        public Builder setGia_thue_max(Integer gia_thue_max) {
            this.gia_thue_max = gia_thue_max;
            return this;
        }



        public Builder setStaffId(Integer staffId) {
            this.staffId = staffId;
            return this;
        }



        public Builder setTypecode(List<String> typecode) {
            this.typecode = typecode;
            return this;
        }



        public BuildingSearchBuilder build() {
            // gọi chính thằng con để trả về
            return new BuildingSearchBuilder(this);
        }




    }

}
