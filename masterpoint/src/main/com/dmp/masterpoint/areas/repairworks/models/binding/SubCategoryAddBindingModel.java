package com.dmp.masterpoint.areas.repairworks.models.binding;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class SubCategoryAddBindingModel {
    private static final String NAME_EMPTY_ERROR_MESSAGE = "Моля попълнете име на категорията";
    private static final String NAME_SIZE_ERROR_MESSAGE = "Името трябва да е с дължина от 3 до 50 символа";
    private static final String CURRENCY_PER_UNIT_EMPTY_ERROR_MESSAGE = "Моля попълнете валута/мерна единица";
    private static final String CURRENCY_PER_PATTERN_ERROR_MESSAGE = "Моля попълнете във формат валута/мерна единица";

    private String id;

    @NotEmpty(message = NAME_EMPTY_ERROR_MESSAGE)
    @Size(min = 3, max = 50, message = NAME_SIZE_ERROR_MESSAGE)
    private String name;

    private String category;

    @NotEmpty(message = CURRENCY_PER_UNIT_EMPTY_ERROR_MESSAGE)
    @Pattern(regexp = ".+\\/.+", message = CURRENCY_PER_PATTERN_ERROR_MESSAGE)
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
