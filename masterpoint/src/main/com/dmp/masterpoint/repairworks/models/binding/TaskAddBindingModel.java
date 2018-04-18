package com.dmp.masterpoint.repairworks.models.binding;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import java.math.BigDecimal;

public class TaskAddBindingModel {

    private String subCategoryName;

    @DecimalMin(value = "1", message = "Моля въведете положително число!")
    @DecimalMax(value = "100", message = "Моля въведете по-малко от 100!")
    private BigDecimal quantity;

    public TaskAddBindingModel() {
    }

    public String getSubCategoryName() {
        return subCategoryName;
    }

    public void setSubCategoryName(String subCategoryName) {
        this.subCategoryName = subCategoryName;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }
}
