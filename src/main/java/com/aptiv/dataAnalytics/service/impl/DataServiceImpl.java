package com.aptiv.dataAnalytics.service.impl;


import com.aptiv.dataAnalytics.domain.*;
import com.aptiv.dataAnalytics.model.DataExcel;
import com.aptiv.dataAnalytics.repository.*;
import com.aptiv.dataAnalytics.service.DataService;
import com.aptiv.dataAnalytics.service.UploadAndExtractData;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class DataServiceImpl implements DataService {

    ActualDataRepo actualDataRepo;
    CoordinatorRepo coordinatorRepo;
    CrewRepo crewRepo;
    DataRepo dataRepo;
    DataTaretRepo dataTaretRepo;
    FamilyRepo familyRepo;
    MonthRepo monthRepo;
    ProjectRepo projectRepo;
    ShiftLeaderRepo shiftLeaderRepo;
    TeamLeaderRepo teamLeaderRepo;
    WeekRepo weekRepo;
    @Override
    public void saveDataToDataBase(MultipartFile file) throws IllegalAccessException {
        ModelMapper mp=new ModelMapper();
        mp.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        if (UploadAndExtractData.isValidFormat(file)){
            try {
                List<DataExcel> dataExcelList= UploadAndExtractData.getDataFromEXcelFile(file.getInputStream());
                List<Data> dataList= new ArrayList<>();
                for (DataExcel dataExcel: dataExcelList){
                    Data data=new Data();
                    data.setActualData(mp.map(dataExcel.getActualDataExcel(), ActualData.class));
                    data.setDataTarget(mp.map(dataExcel.getDataTargetExcel(), DataTarget.class));
                    Coordinator coordinator= coordinatorRepo.findByName(dataExcel.getCoordinator());
                    if (coordinator==null){
                        Coordinator c=new Coordinator();
                        c.setName(dataExcel.getCoordinator());
                        coordinator=coordinatorRepo.save(c);
                    }
                    data.setCoordinator(coordinator);
                    ShiftLeader shiftLeader;

                }


            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
