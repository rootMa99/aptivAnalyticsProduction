package com.aptiv.dataAnalytics.service;

import com.aptiv.dataAnalytics.domain.Data;
import com.aptiv.dataAnalytics.model.DataExcel;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface DataService {
    void saveDataToDataBase(MultipartFile file) throws IllegalAccessException;
    List<DataExcel> getAllData();
    List<DataExcel> getAllByProject(String name);
}
