package com.dmp.masterpoint.areas.repairworks.models.binding;

import com.dmp.masterpoint.areas.repairworks.models.view.SubCategoryViewModel;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Set;

public class CategoryAddBindingModel {

    private String id;

    @NotEmpty(message = "Моля попълнете име на категорията")
    @Size(min = 3, max = 50, message = "Името трябва да е с дължина от 3 до 50 символа")
    private String name;

    public CategoryAddBindingModel() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
