package com.aptiv.dataAnalytics.service.impl;

import com.aptiv.dataAnalytics.domain.Coordinator;
import com.aptiv.dataAnalytics.domain.FileEntity;
import com.aptiv.dataAnalytics.domain.Project;
import com.aptiv.dataAnalytics.domain.ShiftLeader;
import com.aptiv.dataAnalytics.exception.FileStorageException;
import com.aptiv.dataAnalytics.model.Utils;
import com.aptiv.dataAnalytics.repository.CoordinatorRepo;
import com.aptiv.dataAnalytics.repository.FilesRepository;
import com.aptiv.dataAnalytics.repository.ProjectRepo;
import com.aptiv.dataAnalytics.repository.ShiftLeaderRepo;
import com.aptiv.dataAnalytics.service.FileService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.FileNotFoundException;


@Service
@AllArgsConstructor
public class FileServideImpl implements FileService {


    Utils utils;
    FilesRepository filesRepository;
    ShiftLeaderRepo shiftLeaderRepo;
    CoordinatorRepo coordinatorRepo;
    ProjectRepo projectRepo;

    @Override
    public FileEntity storeFile(MultipartFile file) {
        return null;
    }
    @Override
    public FileEntity getFileByFileId(String fileId) throws FileNotFoundException {
        FileEntity fe=filesRepository.findByFileId(fileId);
        if (fe ==null){
            throw new FileNotFoundException("File not found with id " + fileId);
        }
        return fe;
    }

    @Override
    public FileEntity uploadFile(MultipartFile file) {

        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        try {

            if (fileName.contains("..")) {
                throw new FileStorageException("File Name Contain A Invalid Path Sequence");
            }

            String fileId = utils.generateProjectId(22);
            String fileDownloadUri =
                    ServletUriComponentsBuilder.fromCurrentContextPath().path("/data").path("/downloadFile/").path(fileId).toUriString();

            return new FileEntity(fileId, fileName, file.getContentType(), file.getBytes(),
                    fileDownloadUri);
        } catch (
                Exception e
        ) {
            throw new FileStorageException("Could not store file " + fileName + ". Please try again!");
        }
    }
    @Override
    public FileEntity addAdminPic(MultipartFile file){
        FileEntity fileEntity= uploadFile(file);
        String fileDownloadUri =
                ServletUriComponentsBuilder.fromCurrentContextPath().path("/data").path("/downloadFile/").path("admin").toUriString();
        fileEntity.setFileDownloadUri(fileDownloadUri);
        fileEntity.setFileName("adminpic");
        fileEntity.setFileId("admin");
        FileEntity fileN= filesRepository.findByFileId("admin");
        if (fileN==null){
            return filesRepository.save(fileEntity);
        }else {
            fileN.setData(fileEntity.getData());
            return filesRepository.save(fileN);
        }
    }
    @Override
    public FileEntity addFileToSL(String name, MultipartFile file) {
        FileEntity fileEntity= uploadFile(file);
        ShiftLeader shiftLeader= shiftLeaderRepo.findByName(name);
        if (shiftLeader==null)throw new FileStorageException("No Record Found with name: "+name);
        FileEntity fileE=filesRepository.findByShiftLeaderId(shiftLeader.getId());
        if (fileE==null){
            fileEntity.setShiftLeader(shiftLeader);
            fileEntity= filesRepository.save(fileEntity);
            return fileEntity;
        }else {
            fileE.setFileDownloadUri(fileEntity.getFileDownloadUri());
            fileE.setFileType(fileEntity.getFileType());
            fileE.setFileName(fileEntity.getFileName());
            fileE.setFileId(fileEntity.getFileId());
            fileE.setData(fileEntity.getData());
            fileE.setShiftLeader(shiftLeader);
            fileE=filesRepository.save(fileE);
            return fileE;
        }
    }

    @Override
    public FileEntity addFileToCoordinator(String name, MultipartFile file) {
        FileEntity fileEntity= uploadFile(file);
        Coordinator coordinator= coordinatorRepo.findByName(name);
        if(coordinator==null)throw new FileStorageException("No Record Found with name: "+name);
        FileEntity fileE=filesRepository.findByCoordinatorId(coordinator.getId());
        if (fileE==null){
            fileEntity.setCoordinator(coordinator);
            fileEntity= filesRepository.save(fileEntity);
            return fileEntity;
        }else {
            fileE.setFileDownloadUri(fileEntity.getFileDownloadUri());
            fileE.setFileType(fileEntity.getFileType());
            fileE.setFileName(fileEntity.getFileName());
            fileE.setFileId(fileEntity.getFileId());
            fileE.setData(fileEntity.getData());
            fileE.setCoordinator(coordinator);
            fileE=filesRepository.save(fileE);
            return fileE;
        }
    }

    @Override
    public FileEntity addFileToProject(String projectName, MultipartFile file) {
        FileEntity fileEntity= uploadFile(file);
        Project project= projectRepo.findByName(projectName);
        if (project==null)throw new FileStorageException("No Record Found with name: "+projectName);
        FileEntity fileE=filesRepository.findByProjectId(project.getId());
        if (fileE==null){
            fileEntity.setProject(project);
            fileEntity= filesRepository.save(fileEntity);
            return fileEntity;
        }else {
            fileE.setFileDownloadUri(fileEntity.getFileDownloadUri());
            fileE.setFileType(fileEntity.getFileType());
            fileE.setFileName(fileEntity.getFileName());
            fileE.setFileId(fileEntity.getFileId());
            fileE.setData(fileEntity.getData());
            fileE.setProject(project);
            fileE=filesRepository.save(fileE);
            return fileE;
        }

    }


}
