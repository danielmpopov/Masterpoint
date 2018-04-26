package com.dmp.masterpoint.areas.repairworks.models.view;

public class CategoryTasksViewModel {

    private String categoryName;

    private Integer numTasks;

    public CategoryTasksViewModel() {
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Integer getNumTasks() {
        return numTasks;
    }

    public void setNumTasks(Integer numTasks) {
        this.numTasks = numTasks;
    }
}
