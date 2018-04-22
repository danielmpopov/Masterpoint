package com.dmp.masterpoint.projects.models.view;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ProjectFullViewModel {

    private String id;

    private String Title;

    private String description;

    private String cityName;

    private LocalDate startDate;

    private String workmanUsername;

    private Set<TaskViewModel> tasks;

    private Map<String, List<TaskCompleteViewModel>> taskDetails;

    private BigDecimal totalPrice;

    private Integer numAvailableTasks;

    private Boolean isAssigned;

    public ProjectFullViewModel() {
        this.taskDetails = new HashMap<>();
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public Map<String, List<TaskCompleteViewModel>> getTaskDetails() {
        return taskDetails;
    }

    public void setTaskDetails(Map<String, List<TaskCompleteViewModel>> taskDetails) {
        this.taskDetails = taskDetails;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Integer getNumAvailableTasks() {
        return numAvailableTasks;
    }

    public void setNumAvailableTasks(Integer numAvailableTasks) {
        this.numAvailableTasks = numAvailableTasks;
    }

    public Integer getTotalTasks() {
        return this.getTasks().size();
    }

    public Boolean getIsAssigned() {
        return isAssigned;
    }

    public void setIsAssigned(Boolean assigned) {
        isAssigned = assigned;
    }
}
