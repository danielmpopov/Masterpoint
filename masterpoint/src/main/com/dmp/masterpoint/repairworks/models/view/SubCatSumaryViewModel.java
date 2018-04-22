package com.dmp.masterpoint.repairworks.models.view;

import java.math.BigDecimal;

public class SubCatSumaryViewModel {

    private String name;

    private String categoryName;

    private String currencyPerUnit;

    public SubCatSumaryViewModel() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCurrencyPerUnit() {
        return currencyPerUnit;
    }

    public void setCurrencyPerUnit(String currencyPerUnit) {
        this.currencyPerUnit = currencyPerUnit;
    }
}
