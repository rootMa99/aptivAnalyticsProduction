package com.aptiv.dataAnalytics.controllers;

import com.aptiv.dataAnalytics.domain.Data;
import com.aptiv.dataAnalytics.domain.FileEntity;
import com.aptiv.dataAnalytics.model.DataExcel;
import com.aptiv.dataAnalytics.model.FileRest;
import com.aptiv.dataAnalytics.model.ProjectRest;
import com.aptiv.dataAnalytics.service.DataService;
import com.aptiv.dataAnalytics.service.FileService;
import com.aptiv.dataAnalytics.service.ProjectService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000/")
@RestController
@RequestMapping("/data")
@AllArgsConstructor
public class DataController {

    private DataService dataService;
    private ProjectService projectService;
    private FileService fileService;
    @GetMapping(path="/uploadData")
    public List<DataExcel> getAllData(){
        return dataService.getAllData();
    }
    @GetMapping(path="/getData")
    public ProjectRest getAllData(@RequestParam String projectName){
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
    @GetMapping("/downloadFile/{fileId:.+}")
    public ResponseEntity<ByteArrayResource> downloadFile(@PathVariable String fileId, HttpServletRequest request) throws FileNotFoundException {
        FileEntity fileEntity = fileService.getFileByFileId(fileId);
        return ResponseEntity.ok().contentType(MediaType.parseMediaType(fileEntity.getFileType())).header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" +fileEntity.getFileName()+"\"").body(new ByteArrayResource(fileEntity.getData()));
    }

    @PostMapping("/shiftleader/{shiftleaderName}")
    public FileRest addFileToShiftLeader(@PathVariable String shiftleaderName, @RequestParam(value="file") MultipartFile file){
        FileEntity fileEntity=fileService.addFileToSL(shiftleaderName, file);
        FileRest fileRest=new FileRest();
        BeanUtils.copyProperties(fileEntity, fileRest);
        return fileRest;
    }
    @PostMapping("/coordinator/{coordinatorName}")
    public FileRest addFileToCoordinator(@PathVariable String coordinatorName, @RequestParam(value="file") MultipartFile file){
        FileEntity fileEntity=fileService.addFileToCoordinator(coordinatorName, file);
        FileRest fileRest=new FileRest();
        BeanUtils.copyProperties(fileEntity, fileRest);
        return fileRest;
    }
    @PostMapping("/project/{projectName}")
    public FileRest addFileToProject(@PathVariable String projectName, @RequestParam(value="file") MultipartFile file){
        FileEntity fileEntity=fileService.addFileToProject(projectName, file);
        FileRest fileRest=new FileRest();
        BeanUtils.copyProperties(fileEntity, fileRest);
        return fileRest;
    }
}
