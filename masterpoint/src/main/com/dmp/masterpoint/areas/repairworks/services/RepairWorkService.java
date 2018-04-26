package com.dmp.masterpoint.areas.repairworks.services;

import com.dmp.masterpoint.areas.repairworks.entities.RepairWork;
import com.dmp.masterpoint.areas.repairworks.models.binding.RepairWorkBindingModel;
import com.dmp.masterpoint.areas.repairworks.models.view.RepairWorkViewModel;

import java.util.List;

public interface RepairWorkService {
    List<RepairWorkViewModel> findALLRepWorksByWorkman(String name);

    RepairWork add(RepairWorkBindingModel repairWorkBindingModel, String workmanName, String subCategoryName);

    RepairWorkViewModel findById(String id);

    void updateRepairWork(String id, RepairWorkBindingModel bindingModel);

    void deleteById(String id);
}
