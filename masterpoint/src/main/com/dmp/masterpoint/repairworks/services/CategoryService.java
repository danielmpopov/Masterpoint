package com.dmp.masterpoint.repairworks.services;

import com.dmp.masterpoint.repairworks.models.binding.CategoryAddBindingModel;
import com.dmp.masterpoint.repairworks.models.view.CategoryViewModel;

import java.util.List;

public interface CategoryService {

    void add(CategoryAddBindingModel categoryAddBindingModel);

    CategoryViewModel findByName(String name);

    List<CategoryViewModel> getAll();

    void updateCategory(String name, CategoryAddBindingModel bindingModel);

    void deleteByName(String name);

    List<CategoryViewModel> findCategoriesAndRepairWorksByWorkman (String name);

}
