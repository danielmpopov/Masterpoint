package com.dmp.masterpoint.areas.projects.services;

import com.dmp.masterpoint.areas.projects.entities.Project;
import com.dmp.masterpoint.areas.projects.models.binding.ProjectAddBindingModel;
import com.dmp.masterpoint.areas.projects.models.binding.TaskAddBindingModel;
import com.dmp.masterpoint.areas.projects.models.view.*;
import com.dmp.masterpoint.areas.projects.repositories.ProjectRepository;
import com.dmp.masterpoint.areas.repairworks.models.view.RepairWorkViewModel;
import com.dmp.masterpoint.areas.users.models.view.WorkmanViewModel;
import com.dmp.masterpoint.areas.users.repositories.WorkmanRepository;
import com.dmp.masterpoint.areas.users.services.UserService;
import com.dmp.masterpoint.areas.users.services.WorkmanService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class ProjectServiceImpl implements ProjectService {
    private final ProjectRepository repository;
    private final WorkmanService workmanService;
    private final UserService userService;
    private final WorkmanRepository workmanRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public ProjectServiceImpl(ProjectRepository repository, WorkmanService workmanService, UserService userService, WorkmanRepository workmanRepository, ModelMapper modelMapper) {
        this.repository = repository;
        this.workmanService = workmanService;
        this.userService = userService;
        this.workmanRepository = workmanRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public List<ProjectFullViewModel> findAllOffers(String projectId) {

        List<ProjectFullViewModel> allOffers = new ArrayList<>();

        List<WorkmanViewModel> allWorkkmen = this.workmanService.findAll();

        for (WorkmanViewModel workmanView : allWorkkmen) {
            String workmanUserName = workmanView.getUsername();
            ProjectFullViewModel workmanProjectOffer = this.findById(projectId, workmanUserName);
            allOffers.add(workmanProjectOffer);
        }

        return allOffers;


    }

    @Override
    public Project add(ProjectAddBindingModel projectBindingModel, String username) {

        String clientId = this.userService.findFirstByUsername(username).getId();

        projectBindingModel.setClientId(clientId);

        List<TaskAddBindingModel> tasks = projectBindingModel.getTasks().stream().filter(t -> (t.getQuantity() != null && t.getSubCategoryId() != null))
                .collect(Collectors.toList());
        projectBindingModel.setTasks(tasks);
        Project project = this.modelMapper.map(projectBindingModel, Project.class);

        return this.repository.save(project);

    }

    @Override
    public List<ProjectSummaryViewModel> findMyProjects(String userName) {
        List<Project> allProjects = this.repository.findAllByClient_Username(userName);
        List<ProjectSummaryViewModel> allByClientName = new ArrayList<>();

        for (Project project : allProjects) {

            ProjectSummaryViewModel projectView = this.modelMapper.map(project, ProjectSummaryViewModel.class);

            Map<String, Integer> categories = new HashMap<>();

            for (TaskViewModel taskViewModel : projectView.getTasks()) {
                String taskCategory = taskViewModel.getSubCategory().getCategoryName();
                if (!categories.containsKey(taskCategory)) {
                    categories.put(taskCategory, 1);
                } else {
                    categories.put(taskCategory, categories.get(taskCategory) + 1);
                }
            }
            projectView.setCategories(categories);
            allByClientName.add(projectView);
        }

        return allByClientName;
    }

    @Override
    public ProjectFullViewModel findById(String id, String candidateWorkmanUsername) {

        Optional<Project> project = this.repository.findById(id);

        if (!project.isPresent()) {
            return null;
        }

        ProjectFullViewModel projectView = this.modelMapper.map(project.get(), ProjectFullViewModel.class);
        projectView.setTotalPrice(BigDecimal.valueOf(0));
        projectView.setNumAvailableTasks(0);

        Map<String, List<TaskCompleteViewModel>> taskDetails = new HashMap<>();
        Set<RepairWorkViewModel> repairWorks = new HashSet<>();

        // ako methoda se polzva za listvane na potencialni kandidat maistori
        if (candidateWorkmanUsername != null) {
            projectView.setWorkmanUsername(candidateWorkmanUsername);
        }

        //ako e nov proekt i nqma izbran maistor:
        if (projectView.getWorkmanUsername() != null) {
            repairWorks = this.workmanService.findByName(projectView.getWorkmanUsername()).getRepairWorks();
        }


        for (TaskViewModel taskViewModel : projectView.getTasks()) {
            String taskCategory = taskViewModel.getSubCategory().getCategoryName();
            String taskSubcategory = taskViewModel.getSubCategory().getName();
            String currencyPerUnit = taskViewModel.getSubCategory().getCurrencyPerUnit();
            BigDecimal taskQuantity = taskViewModel.getQuantity();
            BigDecimal taskPricePerUnit = BigDecimal.valueOf(0);
            for (RepairWorkViewModel repairWork : repairWorks) {
                if (repairWork.getSubCategoryName().equals(taskSubcategory)) {
                    taskPricePerUnit = repairWork.getPricePerUnit();
                    projectView.setNumAvailableTasks(projectView.getNumAvailableTasks()+ 1);
                }
            }

            TaskCompleteViewModel taskCompleteView = new TaskCompleteViewModel();
            taskCompleteView.setSubCategory(taskSubcategory);
            taskCompleteView.setCurrencyPerUnit(currencyPerUnit);
            taskCompleteView.setQuantity(taskQuantity);
            taskCompleteView.setPricePerUnit(taskPricePerUnit);
            taskCompleteView.setPricePerTask(taskQuantity.multiply(taskPricePerUnit));
            projectView.setTotalPrice(projectView.getTotalPrice().add(taskCompleteView.getPricePerTask()));

            if (!taskDetails.containsKey(taskCategory)) {
                taskDetails.put(taskCategory, new ArrayList<>());
            }

            taskDetails.get(taskCategory).add(taskCompleteView);
        }

        projectView.setTaskDetails(taskDetails);
        return projectView;


    }

    @Override
    public void deleteById(String id) {
        Optional<Project> project= this.repository.findById(id);

        if (!project.isPresent()) {
            return;
        }
        this.repository.delete(project.get());
    }

    @Override
    public void hire(String projectId, String username) {


        Optional<Project> projectOptional= this.repository.findById(projectId);
        if (!projectOptional.isPresent()) {
            return;
        }
        Project project = projectOptional.get();
        project.setIsAssigned(true);
        project.setWorkman(this.workmanRepository.findFirstByUsername(username));

        this.repository.saveAndFlush(project);

    }
}
