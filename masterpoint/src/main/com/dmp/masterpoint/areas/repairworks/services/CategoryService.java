package com.dmp.masterpoint.areas.repairworks.services;

import com.dmp.masterpoint.areas.repairworks.entities.Category;
import com.dmp.masterpoint.areas.repairworks.models.binding.CategoryAddBindingModel;
import com.dmp.masterpoint.areas.repairworks.models.view.CategoryViewModel;

import java.util.List;

public interface CategoryService {

    Category add(CategoryAddBindingModel categoryAddBindingModel);

    CategoryViewModel findByName(String name);

    List<CategoryViewModel> getAll();

    void updateCategory(String name, CategoryAddBindingModel bindingModel);

    void deleteByName(String name);

    List<CategoryViewModel> findCategoriesAndRepairWorksByWorkman (String name);

}
