package com.dmp.masterpoint.areas.repairworks.models.view;

import java.math.BigDecimal;
import java.util.Set;

public class SubCategoryViewModel {

    private String id;

    private String name;

    private String categoryName;

    private String currencyPerUnit;

    private BigDecimal repairWorkPrice;

    private String repairWorkId;

    public SubCategoryViewModel() {
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

    public BigDecimal getRepairWorkPrice() {
        return repairWorkPrice;
    }

    public void setRepairWorkPrice(BigDecimal repairWorkPrice) {
        this.repairWorkPrice = repairWorkPrice;
    }

    public String getRepairWorkId() {
        return repairWorkId;
    }

    public void setRepairWorkId(String repairWorkId) {
        this.repairWorkId = repairWorkId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
