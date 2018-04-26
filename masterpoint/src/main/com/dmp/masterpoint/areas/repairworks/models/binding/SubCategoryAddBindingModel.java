package com.dmp.masterpoint.areas.repairworks.models.binding;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class SubCategoryAddBindingModel {

    private String id;

    @NotEmpty(message = "Моля попълнете име на категорията")
    @Size(min = 3, max = 50, message = "Името трябва да е с дължина от 3 до 50 символа")
    private String name;

    private String category;

    @NotEmpty(message = "Моля попълнете валута/мерна единица")
    @Size(min = 3, max = 50, message = "Валута/мерна единица трябва да е с дължина от 3 до 50 символа")
    private String currencyPerUnit;

    public SubCategoryAddBindingModel() {
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCurrencyPerUnit() {
        return currencyPerUnit;
    }

    public void setCurrencyPerUnit(String currencyPerUnit) {
        this.currencyPerUnit = currencyPerUnit;
    }
}
