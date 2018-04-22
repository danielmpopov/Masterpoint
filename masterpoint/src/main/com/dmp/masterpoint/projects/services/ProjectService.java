package com.dmp.masterpoint.projects.services;

import com.dmp.masterpoint.projects.models.binding.ProjectAddBindingModel;
import com.dmp.masterpoint.projects.models.view.ProjectFullViewModel;
import com.dmp.masterpoint.projects.models.view.ProjectSummaryViewModel;

import java.util.List;

public interface ProjectService {
     List<ProjectFullViewModel> findAllOffers(String projectId);

     void add(ProjectAddBindingModel project, String username);

     List<ProjectSummaryViewModel> findMyProjects(String userName);

     ProjectFullViewModel findById(String id, String candidateWorkmanUsername);

     void deleteById(String id);

     void hire(String projectId, String username);
}
