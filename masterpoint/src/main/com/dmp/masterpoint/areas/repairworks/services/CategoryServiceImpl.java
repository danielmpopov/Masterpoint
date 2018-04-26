package com.dmp.masterpoint.areas.repairworks.services;

import com.dmp.masterpoint.areas.repairworks.entities.Category;
import com.dmp.masterpoint.areas.repairworks.models.binding.CategoryAddBindingModel;
import com.dmp.masterpoint.areas.repairworks.models.view.CategoryViewModel;
import com.dmp.masterpoint.areas.repairworks.models.view.RepairWorkViewModel;
import com.dmp.masterpoint.areas.repairworks.models.view.SubCategoryViewModel;
import com.dmp.masterpoint.areas.repairworks.repositories.CategoryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final RepairWorkService repairWorkService;
    private final ModelMapper mapper;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository, RepairWorkService repairWorkService, ModelMapper mapper) {
        this.categoryRepository = categoryRepository;
        this.repairWorkService = repairWorkService;
        this.mapper = mapper;
    }

    @Override
    public Category add(CategoryAddBindingModel categoryAddBindingModel) {
        Category category = this.mapper.map(categoryAddBindingModel, Category.class);

        return this.categoryRepository.save(category);
    }

    @Override
    public CategoryViewModel findByName(String name) {
        Category category = this.categoryRepository.findFirstByName(name);

        if (category != null) {
            return this.mapper.map(category, CategoryViewModel.class);
        }

        return null;
    }

    @Override
    public List<CategoryViewModel> getAll() {
        return this.categoryRepository.findAll()
                .stream()
                .map(c -> this.mapper.map(c, CategoryViewModel.class ))
                .collect(Collectors.toList());
    }

    @Override
    public void updateCategory(String name, CategoryAddBindingModel bindingModel) {
        Category category = this.categoryRepository.findFirstByName(name);
        if (category == null) return;

        Category updatedCategory = this.mapper.map(bindingModel, Category.class);
        updatedCategory.setId(category.getId());
        this.categoryRepository.save(updatedCategory);
    }

    @Override
    public void deleteByName(String name) {
        Category category = this.categoryRepository.findFirstByName(name);
        this.categoryRepository.delete(category);
    }

    @Override
    public List<CategoryViewModel> findCategoriesAndRepairWorksByWorkman(String name) {
        List<CategoryViewModel> allCategories = this.getAll();

        List<RepairWorkViewModel> allRepWorksByWorkman = this.repairWorkService.findALLRepWorksByWorkman(name);

        for (RepairWorkViewModel repairWork : allRepWorksByWorkman) {
            for (CategoryViewModel category : allCategories) {
                for (SubCategoryViewModel subCategoryViewModel : category.getSubCategories()) {
                    if (repairWork.getSubCategoryName().equals(subCategoryViewModel.getName())) {
                        subCategoryViewModel.setRepairWorkPrice(repairWork.getPricePerUnit());
                        subCategoryViewModel.setRepairWorkId(repairWork.getId());
                    }
                }
            }
        }

       return allCategories;
    }
}
