package com.dmp.masterpoint.areas.repairworks.services;

import com.dmp.masterpoint.areas.repairworks.entities.SubCategory;
import com.dmp.masterpoint.areas.repairworks.models.binding.SubCategoryAddBindingModel;
import com.dmp.masterpoint.areas.repairworks.models.view.SubCategoryViewModel;

public interface SubCategoryService {
    SubCategory add(SubCategoryAddBindingModel categoryAddBindingModel);

    SubCategoryViewModel findByName(String name);

    void updateSubCategory(String name, SubCategoryAddBindingModel bindingModel);

    void deleteByName(String name);
}
