package com.aptiv.dataAnalytics.controllers;

import com.aptiv.dataAnalytics.domain.Admin;
import com.aptiv.dataAnalytics.domain.FileEntity;
import com.aptiv.dataAnalytics.model.Changepwd;
import com.aptiv.dataAnalytics.model.FileRest;
import com.aptiv.dataAnalytics.model.ProjectRest;
import com.aptiv.dataAnalytics.service.AuthenticationService;
import com.aptiv.dataAnalytics.service.DataService;
import com.aptiv.dataAnalytics.service.FileService;
import com.aptiv.dataAnalytics.service.ProjectService;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/admin")
@AllArgsConstructor
public class AdminController {
    private DataService dataService;
    private ProjectService projectService;
    private FileService fileService;
    private AuthenticationService authenticationService;
    @GetMapping(path="/getData")
    public ProjectRest getAllData(@RequestParam String projectName){
        return dataService.getAllByProject(projectName);
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
    @PostMapping(path="/uploadData")
    public void saveDataToDataBase(MultipartFile file) throws IllegalAccessException {
        dataService.saveDataToDataBase(file);
    }

    @PostMapping(path = "/adminpic")
    public FileRest uploadAdminPic(@RequestParam(value="file") MultipartFile file){
        FileEntity fileEntity=fileService.addAdminPic(file);
        FileRest fileRest=new FileRest();
        BeanUtils.copyProperties(fileEntity, fileRest);
        return fileRest;
    }

    @PostMapping(path = "/changePwd")
    public ResponseEntity<Admin> changePwd(@RequestBody Changepwd changepwd){
        return ResponseEntity.ok(authenticationService.changePassword(changepwd));
    }
}
