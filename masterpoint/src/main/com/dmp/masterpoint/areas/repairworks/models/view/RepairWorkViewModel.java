package com.dmp.masterpoint.areas.repairworks.models.view;

import java.math.BigDecimal;

public class RepairWorkViewModel {

    private String id;

    private String subCategoryName;

    private BigDecimal pricePerUnit;

    private String workmanName;

    public RepairWorkViewModel() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSubCategoryName() {
        return subCategoryName;
    }

    public void setSubCategoryName(String subCategoryName) {
        this.subCategoryName = subCategoryName;
    }

    public BigDecimal getPricePerUnit() {
        return pricePerUnit;
    }

    public void setPricePerUnit(BigDecimal pricePerUnit) {
        this.pricePerUnit = pricePerUnit;
    }

    public String getWorkmanName() {
        return workmanName;
    }

    public void setWorkmanName(String workmanName) {
        this.workmanName = workmanName;
    }
}
