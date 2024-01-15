package com.aptiv.dataAnalytics.service;

import com.aptiv.dataAnalytics.model.ProjectRest;

import java.util.Date;
import java.util.List;

public interface ProjectService {
    List<ProjectRest> getProjects();
}
