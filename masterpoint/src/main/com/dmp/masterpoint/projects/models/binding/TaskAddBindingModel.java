package com.dmp.masterpoint.projects.models.binding;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import java.math.BigDecimal;

public class TaskAddBindingModel {
    private static final String  MIN_QUANTITY_ERROR_MESSAGE = "Моля въведете число по-голямо или равно на 0!";
    private static final String  MAX_QUANTITY_ERROR_MESSAGE = "Моля въведете число по-малко или равно на 10000!";

    private String id;

    private String subCategoryId;

    @DecimalMin(value = "1", message = MIN_QUANTITY_ERROR_MESSAGE)
    @DecimalMax(value = "10000", message = MAX_QUANTITY_ERROR_MESSAGE)
    private BigDecimal quantity;

    private String ProjectId;

    public TaskAddBindingModel() {
    }

    public String getSubCategoryId() {
        return subCategoryId;
    }

    public void setSubCategoryId(String subCategoryId) {
        this.subCategoryId = subCategoryId;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProjectId() {
        return ProjectId;
    }

    public void setProjectId(String projectId) {
        ProjectId = projectId;
    }
}
