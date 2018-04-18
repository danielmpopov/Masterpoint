package com.dmp.masterpoint.repairworks.models.binding;

import javax.validation.Valid;
import java.util.List;

public class OfferAddBindingModel {

    @Valid
    private List<TaskAddBindingModel> tasks;

    public OfferAddBindingModel() {
    }

    public List<TaskAddBindingModel> getTasks() {
        return tasks;
    }

    public void setTasks(List<TaskAddBindingModel> tasks) {
        this.tasks = tasks;
    }
}
