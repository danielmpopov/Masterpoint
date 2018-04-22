package com.dmp.masterpoint.projects.controllers;

import com.dmp.masterpoint.cities.services.CityService;
import com.dmp.masterpoint.errors.ProjectNotFoundException;
import com.dmp.masterpoint.projects.models.binding.ProjectAddBindingModel;
import com.dmp.masterpoint.projects.models.view.ProjectFullViewModel;
import com.dmp.masterpoint.repairworks.services.CategoryService;
import com.dmp.masterpoint.projects.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@Controller
public class ProjectController {

    private final CategoryService categoryService;
    private final ProjectService projectService;
    private final CityService cityService;

    @Autowired
    public ProjectController(CategoryService categoryService,  ProjectService projectService, CityService cityService) {
        this.categoryService = categoryService;
        this.projectService = projectService;
        this.cityService = cityService;
    }

    @GetMapping("/projects/add")
    @PreAuthorize("hasAnyRole('CLIENT')")
    public ModelAndView add(ModelAndView modelAndView, @ModelAttribute("project") ProjectAddBindingModel bindingModel) {
        modelAndView.addObject("categories", this.categoryService.getAll());
        modelAndView.addObject("cities", this.cityService.findAll());

        modelAndView.setViewName("project/add");
        return modelAndView;
    }


    @PostMapping("/projects/add")
    @PreAuthorize("hasAnyRole('CLIENT')")
    public ModelAndView add(@Valid @ModelAttribute("project") ProjectAddBindingModel bindingModel, BindingResult result,
                            ModelAndView modelAndView, Principal principal) {

        if (result.hasErrors()) {
            modelAndView.addObject("categories", this.categoryService.getAll());
            modelAndView.addObject("cities", this.cityService.findAll());
            modelAndView.setViewName("project/add");
        } else {
              this.projectService.add(bindingModel, principal.getName());
            modelAndView.setViewName("redirect:/");
        }

        return modelAndView;
    }


    @GetMapping("/projects/show")
    @PreAuthorize("hasAnyRole('CLIENT')")
    public ModelAndView show(ModelAndView modelAndView, Principal principal) {
        modelAndView.setViewName("project/list");
        modelAndView.addObject("projects", this.projectService.findMyProjects(principal.getName()));
        return modelAndView;
    }

    @GetMapping("/projects/detail/{projectId}")
    @PreAuthorize("hasAnyRole('CLIENT')")
    public ModelAndView add(@PathVariable String projectId, ModelAndView modelAndView) {

        modelAndView.setViewName("project/details");
            ProjectFullViewModel projectViewModel = this.projectService.findById(projectId, null);
            if (projectViewModel == null) {
                throw new ProjectNotFoundException();
            }
            modelAndView.addObject("project", projectViewModel);

        return modelAndView;
    }

    @GetMapping("/projects/delete/{projectId}")
    @PreAuthorize("hasAnyRole('CLIENT')")
    public ModelAndView delete(@PathVariable String projectId, ModelAndView modelAndView) {

        ProjectFullViewModel projectViewModel = this.projectService.findById(projectId, null);
        if (projectViewModel == null) {
            throw new ProjectNotFoundException();
        }

        this.projectService.deleteById(projectId);
        modelAndView.setViewName("redirect:/projects/show");
        return modelAndView;
    }

    @GetMapping("/projects/offers/{projectId}")
    @PreAuthorize("hasAnyRole('CLIENT')")
    public ModelAndView showAllOffers(@PathVariable String projectId, ModelAndView modelAndView) {

        modelAndView.setViewName("project/offers-list");

        List<ProjectFullViewModel> allOffers = this.projectService.findAllOffers(projectId);

        modelAndView.addObject("offers", allOffers);

        return modelAndView;
    }

    @GetMapping("/projects/offers/details/{projectId}/{workmanUserName}")
    @PreAuthorize("hasAnyRole('CLIENT')")
    public ModelAndView showOfferDetails(@PathVariable String projectId, @PathVariable String workmanUserName, ModelAndView modelAndView) {
        modelAndView.setViewName("project/details");

        ProjectFullViewModel projectViewModel = this.projectService.findById(projectId, workmanUserName);
        if (projectViewModel == null) {
            throw new ProjectNotFoundException();
        }

        modelAndView.addObject("project", projectViewModel);
        return modelAndView;
    }

    @GetMapping("/projects/hire/{projectId}/{workmanUserName}")
    @PreAuthorize("hasAnyRole('CLIENT')")
    public ModelAndView hire (@PathVariable String projectId, @PathVariable String workmanUserName, ModelAndView modelAndView) {

        ProjectFullViewModel projectViewModel = this.projectService.findById(projectId, workmanUserName);
        if (projectViewModel == null) {
            throw new ProjectNotFoundException();
        }

        this.projectService.hire(projectId,workmanUserName);
        modelAndView.setViewName("redirect:/projects/show");

        return modelAndView;
    }

}
