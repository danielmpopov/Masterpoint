package com.dmp.masterpoint.projects.models.view;

import com.dmp.masterpoint.repairworks.models.view.SubCatSumaryViewModel;

import java.math.BigDecimal;

public class TaskCompleteViewModel {

    private String subCategory;

    private BigDecimal quantity;

    private BigDecimal pricePerUnit;

    private String currencyPerUnit;

    private BigDecimal pricePerTask;

    public TaskCompleteViewModel() {
    }

    public String getSubCategory() {
        return subCategory;
    }

    public void setSubCategory(String subCategory) {
        this.subCategory = subCategory;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPricePerUnit() {
        return pricePerUnit;
    }

    public void setPricePerUnit(BigDecimal pricePerUnit) {
        this.pricePerUnit = pricePerUnit;
    }

    public String getCurrencyPerUnit() {
        return currencyPerUnit;
    }

    public void setCurrencyPerUnit(String currencyPerUnit) {
        this.currencyPerUnit = currencyPerUnit;
    }

    public BigDecimal getPricePerTask() {
        return pricePerTask;
    }

    public void setPricePerTask(BigDecimal pricePerTask) {
        this.pricePerTask = pricePerTask;
    }

}
