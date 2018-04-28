package com.dmp.masterpoint.areas.repairworks.models.binding;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;


public class CategoryAddBindingModel {
    private static final String NAME_SIZE_ERROR_MESSAGE = "Името трябва да е с дължина от 3 до 50 символа";
    private static final String NAME_EMPTY_ERROR = "Моля попълнете!";

    private String id;

    @NotEmpty(message = NAME_EMPTY_ERROR)
    @Size(min = 3, max = 50, message = NAME_SIZE_ERROR_MESSAGE)
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
