package com.aptiv.dataAnalytics.service.impl;

import com.aptiv.dataAnalytics.domain.Data;
import com.aptiv.dataAnalytics.domain.Project;
import com.aptiv.dataAnalytics.model.ActualDataExcel;
import com.aptiv.dataAnalytics.model.DataExcel;
import com.aptiv.dataAnalytics.model.DataTargetExcel;
import com.aptiv.dataAnalytics.model.ProjectRest;
import com.aptiv.dataAnalytics.repository.ProjectRepo;
import com.aptiv.dataAnalytics.service.ProjectService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class ProjectServiceImpl implements ProjectService {
    ProjectRepo projectRepo;

    @Override
    public List<ProjectRest> getProjects() {
        ModelMapper mp=new ModelMapper();
        mp.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        List<Project>projects=projectRepo.findAll();
        List<ProjectRest>projectRests=new ArrayList<>();
        for (Project p:projects){
            ProjectRest pr=new ProjectRest();
            List<DataExcel>dataExcelList=new ArrayList<>();

            for (Data d:p.getData()){
                DataExcel dataExcel=new DataExcel();
                dataExcel.setProject(d.getProject().getName());
                dataExcel.setDataTargetExcel(mp.map(d.getDataTarget(), DataTargetExcel.class));
                dataExcel.setActualDataExcel(mp.map(d.getActualData(), ActualDataExcel.class));
                dataExcel.setDate(d.getDate());
                dataExcel.setWeek(d.getWeek().getWeekName());
                dataExcel.setMonth(d.getMonth().getMonthName());
                dataExcel.setCoordinator(d.getCoordinator().getName());
                dataExcel.setShiftLeader(d.getShiftLeader().getName());
                dataExcel.setTeamLeader(d.getTeamLeader().getName());
                dataExcel.setCrew(d.getCrew().getName());
                dataExcel.setFamily(d.getFamily().getName());
                dataExcelList.add(dataExcel);
            }
            pr.setData(dataExcelList);
            pr.setName(p.getName());
            projectRests.add(pr);
        }
        return projectRests;
    }
}
