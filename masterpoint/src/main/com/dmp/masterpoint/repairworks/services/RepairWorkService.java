package com.dmp.masterpoint.repairworks.services;

import com.dmp.masterpoint.repairworks.models.binding.RepairWorkBindingModel;
import com.dmp.masterpoint.repairworks.models.view.RepairWorkViewModel;

import java.util.List;

public interface RepairWorkService {
    List<RepairWorkViewModel> findALLRepWorksByWorkman(String name);

    void add(RepairWorkBindingModel repairWorkBindingModel, String workmanName, String subCategoryName);

    RepairWorkViewModel findById(String id);

    void updateRepairWork(String id, RepairWorkBindingModel bindingModel);

    void deleteById(String id);
}
