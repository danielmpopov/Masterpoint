package com.dmp.masterpoint.areas.projects.models.view;

import com.dmp.masterpoint.areas.repairworks.models.view.CategoryTasksViewModel;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ProjectSummaryViewModel {

    private String id;

    private String Title;

    private String cityName;

    private LocalDate startDate;

    private String workmanUsername;

    private Set<TaskViewModel> tasks;

    private Map<String, Integer> categories;

    private Boolean isAssigned;

    public ProjectSummaryViewModel() {
        this.categories = new HashMap<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public String getWorkmanUsername() {
        return workmanUsername;
    }

    public void setWorkmanUsername(String workmanUsername) {
        this.workmanUsername = workmanUsername;
    }

    public Set<TaskViewModel> getTasks() {
        return tasks;
    }

    public void setTasks(Set<TaskViewModel> tasks) {
        this.tasks = tasks;
    }

    public Map<String, Integer> getCategories() {
        return categories;
    }

    public void setCategories(Map<String, Integer> categories) {
        this.categories = categories;
    }

    public Boolean getIsAssigned() {
        return isAssigned;
    }

    public void setIsAssigned(Boolean assigned) {
        isAssigned = assigned;
    }
}
