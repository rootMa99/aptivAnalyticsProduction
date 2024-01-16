package com.aptiv.dataAnalytics.service;

import com.aptiv.dataAnalytics.domain.FileEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;

public interface FileService {
    FileEntity storeFile(MultipartFile file);
    FileEntity uploadFile(MultipartFile file);
    FileEntity getFileByFileId(String fileId) throws FileNotFoundException;

    FileEntity addAdminPic(MultipartFile file);

    FileEntity addFileToSL(String name, MultipartFile file);
    FileEntity addFileToCoordinator(String name, MultipartFile file);
    FileEntity addFileToProject(String projectName, MultipartFile file);
}
