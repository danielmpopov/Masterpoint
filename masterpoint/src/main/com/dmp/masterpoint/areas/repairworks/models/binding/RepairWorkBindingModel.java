package com.dmp.masterpoint.areas.repairworks.models.binding;

import javax.validation.constraints.DecimalMax;
import java.math.BigDecimal;

public class RepairWorkBindingModel {

    private String subCategoryId;

    private String workmanId;

    @DecimalMax(value = "100", message = "Ne poveche ot 100")
    private BigDecimal pricePerUnit;


    public RepairWorkBindingModel() {
    }

    public BigDecimal getPricePerUnit() {
        return pricePerUnit;
    }

    public void setPricePerUnit(BigDecimal pricePerUnit) {
        this.pricePerUnit = pricePerUnit;
    }

    public String getSubCategoryId() {
        return subCategoryId;
    }

    public void setSubCategoryId(String subCategoryId) {
        this.subCategoryId = subCategoryId;
    }

    public String getWorkmanId() {
        return workmanId;
    }

    public void setWorkmanId(String workmanId) {
        this.workmanId = workmanId;
    }
}
