package com.aptiv.dataAnalytics.service.impl;

import com.aptiv.dataAnalytics.domain.FileEntity;
import com.aptiv.dataAnalytics.exception.FileStorageException;
import com.aptiv.dataAnalytics.model.Utils;
import com.aptiv.dataAnalytics.repository.FilesRepository;
import com.aptiv.dataAnalytics.service.FileService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.FileNotFoundException;
import java.io.IOException;


@Service
@AllArgsConstructor
public class FileServideImpl implements FileService {


    Utils utils;
    FilesRepository filesRepository;

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
                    ServletUriComponentsBuilder.fromCurrentContextPath().path("/files").path("/downloadFile/").path(fileId).toUriString();

            return new FileEntity(fileId, fileName, file.getContentType(), file.getBytes(),
                    fileDownloadUri);
        } catch (
                Exception e
        ) {
            throw new FileStorageException("Could not store file " + fileName + ". Please try again!");
        }
    }

    @Override
    public FileEntity addFileToSL(String name, MultipartFile file) {
        return null;
    }
}
