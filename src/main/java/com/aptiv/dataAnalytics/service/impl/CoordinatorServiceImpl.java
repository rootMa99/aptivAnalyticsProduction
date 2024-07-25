package com.aptiv.dataAnalytics.service.impl;

import com.aptiv.dataAnalytics.domain.ActualData;
import com.aptiv.dataAnalytics.domain.Coordinator;
import com.aptiv.dataAnalytics.domain.DataTarget;
import com.aptiv.dataAnalytics.domain.FileEntity;
import com.aptiv.dataAnalytics.model.ActualDataExcel;
import com.aptiv.dataAnalytics.model.CoordinatorRest;
import com.aptiv.dataAnalytics.model.DataExcel;
import com.aptiv.dataAnalytics.model.DataTargetExcel;
import com.aptiv.dataAnalytics.repository.CoordinatorRepo;
import com.aptiv.dataAnalytics.service.CoordinatorService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class CoordinatorServiceImpl implements CoordinatorService {

    CoordinatorRepo coordinatorRepo;

    @Override
    public CoordinatorRest getCoordinatorData(String name) {
        Coordinator c = coordinatorRepo.findByName(name);
        CoordinatorRest cr = new CoordinatorRest();
        cr.setCoordinatorName(c.getName());

        List<DataExcel> des = c.getData().stream().map(d -> {
            String fileDownloadUri = "";
            Coordinator coordinator = d.getCoordinator();
            if (coordinator != null) {
                FileEntity file = coordinator.getFile();
                if (file != null) {
                    fileDownloadUri = file.getFileDownloadUri();
                }
            }

            ActualData actualData = d.getActualData();
            DataTarget dataTarget = d.getDataTarget();
            return new DataExcel(
                    d.getProject().getName(),
                    "",
                    d.getFamily().getName(),
                    d.getCrew().getName(),
                    d.getTeamLeader().getName(),
                    d.getShiftLeader().getName(),
                    "",
                    "",
                    fileDownloadUri,
                    d.getMonth().getMonthName(),
                    d.getWeek().getWeekName(),
                    d.getDatecr(),
                    new ActualDataExcel(
                            actualData.getOutput(),
                            actualData.getProdH(),
                            actualData.getPaidH(),
                            actualData.getTotalhc(),
                            actualData.getHc(),
                            actualData.getOt(),
                            actualData.getAb(),
                            actualData.getTlo(),
                            actualData.getDt(),
                            actualData.getWsd()
                    ),
                    new DataTargetExcel(
                            dataTarget.getOutputTarget(),
                            dataTarget.getProdTarget(),
                            dataTarget.getPayedTarget(),
                            dataTarget.getHcTarget(),
                            dataTarget.getAbsTarget(),
                            dataTarget.getDtTarget(),
                            dataTarget.getScrap(),
                            dataTarget.getScrapTarget()
                    )
            );
        }).collect(Collectors.toList());

        cr.setDataExcelList(des);
        return cr;
    }


}
