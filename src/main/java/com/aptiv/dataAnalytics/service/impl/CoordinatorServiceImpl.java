package com.aptiv.dataAnalytics.service.impl;

import com.aptiv.dataAnalytics.domain.Coordinator;
import com.aptiv.dataAnalytics.domain.Data;
import com.aptiv.dataAnalytics.model.ActualDataExcel;
import com.aptiv.dataAnalytics.model.CoordinatorRest;
import com.aptiv.dataAnalytics.model.DataExcel;
import com.aptiv.dataAnalytics.model.DataTargetExcel;
import com.aptiv.dataAnalytics.repository.CoordinatorRepo;
import com.aptiv.dataAnalytics.service.CoordinatorService;
import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class CoordinatorServiceImpl implements CoordinatorService {

    CoordinatorRepo coordinatorRepo;
@Override
    public CoordinatorRest getCoordinatorData(String name) {
        Coordinator c = coordinatorRepo.findByName(name);
        CoordinatorRest cr = new CoordinatorRest();
        cr.setCoordinatorName(c.getName());
        List<DataExcel> des = new ArrayList<>();
        for (Data d : c.getData()) {
            DataExcel de = new DataExcel(d.getProject().getName(), "", d.getFamily().getName(), d.getCrew().getName(),
                    d.getTeamLeader().getName(), d.getShiftLeader().getName(), "", "",
                    d.getCoordinator().getFile().getFileDownloadUri(), d.getMonth().getMonthName(),
                    d.getWeek().getWeekName(), d.getDatecr(), new ActualDataExcel(d.getActualData().getOutput(),
                    d.getActualData().getProdH(), d.getActualData().getPaidH(), d.getActualData().getTotalhc(),
                    d.getActualData().getHc(), d.getActualData().getOt(), d.getActualData().getAb(),
                    d.getActualData().getTlo(), d.getActualData().getDt(), d.getActualData().getWsd()),
                    new DataTargetExcel(d.getDataTarget().getOutputTarget(), d.getDataTarget().getProdTarget(),
                            d.getDataTarget().getPayedTarget(), d.getDataTarget().getHcTarget(),
                            d.getDataTarget().getAbsTarget(), d.getDataTarget().getDtTarget(),
                            d.getDataTarget().getScrap(), d.getDataTarget().getScrapTarget()));
            des.add(de);
        }
        cr.setDataExcelList(des);
        return cr;
    }

}
