package com.dmp.masterpoint.areas.repairworks.models.binding;

import javax.validation.constraints.DecimalMax;
import java.math.BigDecimal;

public class RepairWorkBindingModel {
    private static final String PRICE_PER_UNIT_ERROR = "Моля въведете цена от 0.001 до 1000";

    private String subCategoryId;

    private String workmanId;

    @DecimalMax(value = "1000", message = PRICE_PER_UNIT_ERROR)
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
