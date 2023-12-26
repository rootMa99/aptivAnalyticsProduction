package com.aptiv.dataAnalytics.repository;

import com.aptiv.dataAnalytics.domain.FileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FilesRepository extends JpaRepository<FileEntity, Long> {

    FileEntity findByFileName(String fileId);

    FileEntity findByFileId(String fileId);

}
