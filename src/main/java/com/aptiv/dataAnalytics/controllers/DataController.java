package com.aptiv.dataAnalytics.controllers;

import com.aptiv.dataAnalytics.domain.Data;
import com.aptiv.dataAnalytics.model.DataExcel;
import com.aptiv.dataAnalytics.service.DataService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/data")
@AllArgsConstructor
public class DataController {

    private DataService dataService;

    @GetMapping(path="/uploadData")
    public List<DataExcel> getAllData(){
        return dataService.getAllData();
    }

    @PostMapping(path="/uploadData")
    public void saveDataToDataBase(MultipartFile file) throws IllegalAccessException {
        dataService.saveDataToDataBase(file);
    }
}
