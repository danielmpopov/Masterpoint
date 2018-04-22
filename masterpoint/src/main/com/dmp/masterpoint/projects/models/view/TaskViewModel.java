package com.dmp.masterpoint.projects.models.view;

import com.dmp.masterpoint.repairworks.models.view.SubCatSumaryViewModel;

import java.math.BigDecimal;

public class TaskViewModel {

    private SubCatSumaryViewModel subCategory;

    private BigDecimal quantity;

    public TaskViewModel() {
    }


    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public SubCatSumaryViewModel getSubCategory() {
        return subCategory;
    }

    public void setSubCategory(SubCatSumaryViewModel subCategory) {
        this.subCategory = subCategory;
    }
}
