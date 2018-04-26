package com.dmp.masterpoint.areas.repairworks.services;

import com.dmp.masterpoint.areas.repairworks.entities.Category;
import com.dmp.masterpoint.areas.repairworks.entities.SubCategory;
import com.dmp.masterpoint.areas.repairworks.models.binding.SubCategoryAddBindingModel;
import com.dmp.masterpoint.areas.repairworks.models.view.SubCategoryViewModel;
import com.dmp.masterpoint.areas.repairworks.repositories.CategoryRepository;
import com.dmp.masterpoint.areas.repairworks.repositories.SubCategoryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class SubCategoryServiceImpl implements SubCategoryService{

    private final SubCategoryRepository subCategoryRepository;
    private final CategoryRepository categoryRepository;
    private final ModelMapper mapper;

    @Autowired
    public SubCategoryServiceImpl(SubCategoryRepository subCategoryRepository, CategoryRepository categoryRepository, ModelMapper mapper) {
        this.subCategoryRepository = subCategoryRepository;
        this.categoryRepository = categoryRepository;
        this.mapper = mapper;
    }

    @Override
    public SubCategory add(SubCategoryAddBindingModel subCategoryAddBindingModel) {
        SubCategory subCategory = this.mapper.map(subCategoryAddBindingModel, SubCategory.class);

        String categoryId = subCategoryAddBindingModel.getCategory();
        Optional<Category> category = this.categoryRepository.findById(categoryId);
        if (category.isPresent()) {
            subCategory.setCategory(category.get());
        }

        return this.subCategoryRepository.save(subCategory);
    }

    @Override
    public SubCategoryViewModel findByName(String name) {
        SubCategory subCategory = this.subCategoryRepository.findFirstByName(name);

        if (subCategory != null) {
            return this.mapper.map(subCategory, SubCategoryViewModel.class);
        }

        return null;
    }

    @Override
    public void updateSubCategory(String name, SubCategoryAddBindingModel bindingModel) {
        SubCategory subCategory = this.subCategoryRepository.findFirstByName(name);
        if (subCategory == null) return;

        subCategory.setCurrencyPerUnit(bindingModel.getCurrencyPerUnit());
        subCategory.setName(bindingModel.getName());

        this.subCategoryRepository.saveAndFlush(subCategory);
    }

    @Override
    public void deleteByName(String name) {
        SubCategory subCategory = this.subCategoryRepository.findFirstByName(name);
        this.subCategoryRepository.deleteById(subCategory.getId());
    }
}
