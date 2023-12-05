package com.aptiv.dataAnalytics.controllers;

import com.aptiv.dataAnalytics.domain.Data;
import com.aptiv.dataAnalytics.model.DataExcel;
import com.aptiv.dataAnalytics.model.ProjectRest;
import com.aptiv.dataAnalytics.service.DataService;
import com.aptiv.dataAnalytics.service.ProjectService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/data")
@AllArgsConstructor
public class DataController {

    private DataService dataService;
    private ProjectService projectService;
    @GetMapping(path="/uploadData")
    public List<DataExcel> getAllData(){
        return dataService.getAllData();
    }
    @GetMapping(path="/getData")
    public List<DataExcel> getAllData(@RequestParam String projectName){
        return dataService.getAllByProject(projectName);
    }
    @GetMapping(path="/projects")
    public List<ProjectRest>getProject(){
        return projectService.getProjects();
    }
    @PostMapping(path="/uploadData")
    public void saveDataToDataBase(MultipartFile file) throws IllegalAccessException {
        dataService.saveDataToDataBase(file);
    }
}
