package com.aptiv.dataAnalytics.service.impl;


import com.aptiv.dataAnalytics.domain.*;
import com.aptiv.dataAnalytics.model.ActualDataExcel;
import com.aptiv.dataAnalytics.model.DataExcel;
import com.aptiv.dataAnalytics.model.DataTargetExcel;
import com.aptiv.dataAnalytics.model.ProjectRest;
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
    public ProjectRest getAllByProject(String name) {
        ModelMapper mp = new ModelMapper();
        mp.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        Project data=projectRepo.findByName(name);
        List<DataExcel>dataExcelList=new ArrayList<>();
        ProjectRest pr= mp.map(data, ProjectRest.class);
        for (Data d:data.getData()){
            DataExcel dataExcel=new DataExcel();
            dataExcel.setDataTargetExcel(mp.map(d.getDataTarget(), DataTargetExcel.class));
            dataExcel.setActualDataExcel(mp.map(d.getActualData(), ActualDataExcel.class));
            dataExcel.setDate(d.getDatecr());
            dataExcel.setWeek(d.getWeek().getWeekName());
            dataExcel.setMonth(d.getMonth().getMonthName());
            dataExcel.setCoordinator(d.getCoordinator().getName());
            dataExcel.setShiftLeader(d.getShiftLeader().getName());
            dataExcel.setTeamLeader(d.getTeamLeader().getName());
            dataExcel.setCrew(d.getCrew().getName());
            dataExcel.setFamily(d.getFamily().getName());
            dataExcel.setProject(d.getProject().getName());
            dataExcelList.add(dataExcel);
        }
        pr.setData(dataExcelList);
        return pr;
    }

    @Override
    public List<DataExcel> getAllData() {
        ModelMapper mp = new ModelMapper();
        mp.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        List<Data> data=dataRepo.findAll();
        List<DataExcel>dataExcelList=new ArrayList<>();

        for (Data d:data){
            DataExcel dataExcel=new DataExcel();
            dataExcel.setDataTargetExcel(mp.map(d.getDataTarget(), DataTargetExcel.class));
            dataExcel.setActualDataExcel(mp.map(d.getActualData(), ActualDataExcel.class));
            dataExcel.setDate(d.getDatecr());
            dataExcel.setWeek(d.getWeek().getWeekName());
            dataExcel.setMonth(d.getMonth().getMonthName());
            dataExcel.setCoordinator(d.getCoordinator().getName());
            dataExcel.setShiftLeader(d.getShiftLeader().getName());
            dataExcel.setTeamLeader(d.getTeamLeader().getName());
            dataExcel.setCrew(d.getCrew().getName());
            dataExcel.setFamily(d.getFamily().getName());
            dataExcel.setProject(d.getProject().getName());
            dataExcelList.add(dataExcel);
        }
        return dataExcelList;
    }



    @Override
    public void saveDataToDataBase(MultipartFile file) throws IllegalAccessException {
        ModelMapper mp=new ModelMapper();
        mp.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        if (UploadAndExtractData.isValidFormat(file)){
            try {
                List<DataExcel> dataExcelList= UploadAndExtractData.getDataFromEXcelFile(file.getInputStream());
                List<Data> dataList= new ArrayList<>();
                for (DataExcel dataExcel: dataExcelList){
                    System.out.println("record saved");
                    Data data=new Data();
                    Data upd=dataRepo.findByDatecrAndCrewName(dataExcel.getDate(), dataExcel.getCrew());
                    if (upd!=null){
                        System.out.println("data found");
                        data.setId(upd.getId());
                    }
                    data.setDatecr(dataExcel.getDate());
                    data.setActualData(mp.map(dataExcel.getActualDataExcel(), ActualData.class));
                    data.setDataTarget(mp.map(dataExcel.getDataTargetExcel(), DataTarget.class));
                    Coordinator coordinator= coordinatorRepo.findByName(dataExcel.getCoordinator());
                    if (coordinator==null){
                        Coordinator c=new Coordinator();
                        c.setName(dataExcel.getCoordinator());
                        coordinator=coordinatorRepo.save(c);
                    }
                    data.setCoordinator(coordinator);
                    ShiftLeader shiftLeader= shiftLeaderRepo.findByName(dataExcel.getShiftLeader());
                    if (shiftLeader==null){
                        ShiftLeader sl=new ShiftLeader();
                        sl.setName(dataExcel.getShiftLeader());
                        sl.setCoordinator(coordinator);
                        shiftLeader= shiftLeaderRepo.save(sl);
                    }
                    data.setShiftLeader(shiftLeader);
                    TeamLeader teamLeader=teamLeaderRepo.findByName(dataExcel.getTeamLeader());
                    if (teamLeader==null){
                        TeamLeader tl= new TeamLeader();
                        tl.setName(dataExcel.getTeamLeader());
                        tl.setShiftLeader(shiftLeader);
                        teamLeader= teamLeaderRepo.save(tl);
                    }
                    data.setTeamLeader(teamLeader);
                    Month month= monthRepo.findByMonthName(dataExcel.getMonth());
                    if (month==null){
                        Month month1= new Month();
                        month1.setMonthName(dataExcel.getMonth());
                        month= monthRepo.save(month1);
                    }
                    data.setMonth(month);
                    Week week= weekRepo.findByWeekName(dataExcel.getWeek());
                    if(week==null){
                        Week wk=new Week();
                        wk.setWeekName(dataExcel.getWeek());
                        wk.setMonth(month);
                        week= weekRepo.save(wk);
                    }
                    data.setWeek(week);
                    Project project=projectRepo.findByName(dataExcel.getProject());
                    if(project==null){
                        Project prj=new Project();
                        prj.setName(dataExcel.getProject());
                        project=projectRepo.save(prj);
                    }
                    data.setProject(project);
                    Family family=familyRepo.findByName(dataExcel.getFamily());
                    if (family==null){
                        Family f=new Family();
                        f.setName(dataExcel.getFamily());
                        f.setProject(project);
                        family=familyRepo.save(f);
                    }
                    data.setFamily(family);
                    Crew crew=crewRepo.findByName(dataExcel.getCrew());
                    if (crew==null){
                        Crew cr=new Crew();
                        cr.setName(dataExcel.getCrew());
                        cr.setFamily(family);
                        cr.setTeamLeader(teamLeader);
                        crew= crewRepo.save(cr);
                    }
                    data.setCrew(crew);
                    dataList.add(data);
                }
                dataRepo.saveAll(dataList);

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
