package com.dmp.masterpoint.areas.projects.services;

import com.dmp.masterpoint.areas.projects.entities.Project;
import com.dmp.masterpoint.areas.projects.models.binding.ProjectAddBindingModel;
import com.dmp.masterpoint.areas.projects.models.view.ProjectFullViewModel;
import com.dmp.masterpoint.areas.projects.models.view.ProjectSummaryViewModel;

import java.util.List;

public interface ProjectService {
     List<ProjectFullViewModel> findAllOffers(String projectId);

     Project add(ProjectAddBindingModel project, String username);

     List<ProjectSummaryViewModel> findProjectsByClient(String userName);

     List<ProjectSummaryViewModel> findProjectsByWorkman(String userName);

     ProjectFullViewModel findById(String id, String candidateWorkmanUsername);

     void deleteById(String id);

     void hire(String projectId, String username);
}
