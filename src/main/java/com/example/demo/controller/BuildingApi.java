package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.Map;
import org.springframework.web.bind.annotation.RequestBody;
import com.example.demo.dto.buildingDTO;
import com.example.demo.dto.errorResponseDTO;

import CustomException.FieldRequiredException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;


import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.DeleteMapping;
import java.util.List;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.HashMap;
import com.example.demo.service.BuildingService;
import java.util.Map;





@RestController
public class BuildingApi {

    // goi qua interface service
    @Autowired
    private BuildingService buildingService;

    @GetMapping(value = "/api/building/")
    public List<buildingDTO> GetListBuilding( @RequestParam Map<String, Object> params, 
                                              @RequestParam (value ="typecode", required = false) List<String> typecode) {
        List<buildingDTO> results = buildingService.findAll(params, typecode);
        return results;
    }

    @DeleteMapping ( value = "/api/building/{id}/")
    public void deleteBuilding(@PathVariable(required = false) int id,
                               @RequestParam (value = "ward", required = false) String ward) {
        System.out.println("Đã xóa: " + id+ ", Ward: " + ward);
    }
}
