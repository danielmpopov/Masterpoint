package com.dmp.masterpoint.repairworks.services;

import com.dmp.masterpoint.repairworks.models.binding.SubCategoryAddBindingModel;
import com.dmp.masterpoint.repairworks.models.view.SubCategoryViewModel;

public interface SubCategoryService {
    void add(SubCategoryAddBindingModel categoryAddBindingModel);

    SubCategoryViewModel findByName(String name);

    void updateSubCategory(String name, SubCategoryAddBindingModel bindingModel);

    void deleteByName(String name);
}
