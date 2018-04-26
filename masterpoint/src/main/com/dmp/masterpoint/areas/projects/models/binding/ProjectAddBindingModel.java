package com.dmp.masterpoint.areas.projects.models.binding;

import com.dmp.masterpoint.areas.projects.validations.DateAfterToday;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;

public class ProjectAddBindingModel {

    private static final String  TITLE_VALIDATION_ERROR_MESSAGE = "Моля въведете име на проекта с дължина от 3 до 50 симжола";
    private static final String  DESCRIPTION_VALIDATION_ERROR_MESSAGE = "Моля въведете описание на проекта с дължина от 3 до 255 симжола";;
    private static final String  CITY_VALIDATION_ERROR_MESSAGE = "Моля изберете град";


    private String id;

    @NotEmpty(message = TITLE_VALIDATION_ERROR_MESSAGE)
    @Size(min = 3, max = 50, message = TITLE_VALIDATION_ERROR_MESSAGE)
    private String title;

    @NotEmpty(message = DESCRIPTION_VALIDATION_ERROR_MESSAGE)
    @Size(min = 3, max = 255, message = DESCRIPTION_VALIDATION_ERROR_MESSAGE)
    private  String description;

    @NotEmpty(message = CITY_VALIDATION_ERROR_MESSAGE)
    private String cityId;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @DateAfterToday
    private LocalDate startDate;

    private String clientId;

    @Valid
    private List<TaskAddBindingModel> tasks;

    public ProjectAddBindingModel() {
    }

    public List<TaskAddBindingModel> getTasks() {
        return tasks;
    }

    public void setTasks(List<TaskAddBindingModel> tasks) {
        this.tasks = tasks;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
