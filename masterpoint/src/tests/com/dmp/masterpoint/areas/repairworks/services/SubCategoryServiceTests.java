package com.dmp.masterpoint.areas.repairworks.services;

import com.dmp.masterpoint.areas.repairworks.entities.Category;
import com.dmp.masterpoint.areas.repairworks.entities.SubCategory;
import com.dmp.masterpoint.areas.repairworks.models.binding.SubCategoryAddBindingModel;
import com.dmp.masterpoint.areas.repairworks.models.view.SubCategoryViewModel;
import com.dmp.masterpoint.areas.repairworks.repositories.CategoryRepository;
import com.dmp.masterpoint.areas.repairworks.repositories.SubCategoryRepository;
import com.dmp.masterpoint.areas.repairworks.services.SubCategoryServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class SubCategoryServiceTests {

    private static final String SUB_CATEGORY_NAME = "Subcategory One";
    private static final String CATEGORY_NAME = "Category One";
    private static final String CURRENCY_PER_UNIT = "лв/м3";
    private static final String CATEGORY_ID = "4da11822-48b3-11e8-842f-0ed5f89f718b";
    private static final String NON_EXISTING_SUB_CATEGORY_NAME = "Category X";
    private static final String EXISTING_SUB_CATEGORY_NAME = "Category One";

    @Mock
    private SubCategoryRepository subCategoryRepository;

    @Mock
    private CategoryRepository categoryRepository;

    private SubCategoryAddBindingModel subCategoryAddBindingModel;

    private SubCategoryServiceImpl subCategoryService;

    @Before
    public void setUp() {

        this.subCategoryService = new SubCategoryServiceImpl(this.subCategoryRepository,this.categoryRepository, new ModelMapper());

        this.subCategoryAddBindingModel = new SubCategoryAddBindingModel();
        this.subCategoryAddBindingModel.setName(SUB_CATEGORY_NAME);
        this.subCategoryAddBindingModel.setCurrencyPerUnit(CURRENCY_PER_UNIT);
        this.subCategoryAddBindingModel.setCategory(CATEGORY_ID);

        Category category = new Category();
        category.setId(CATEGORY_ID);
        category.setName(CATEGORY_NAME);

        when(this.categoryRepository.findById(CATEGORY_ID)).thenReturn(Optional.of(category));
        when(this.subCategoryRepository.save(any())).thenAnswer(i->i.getArgument(0));
    }

    @Test
    public void testAdd_withSubCategoryBindingModel_shouldReturnCorrectlyMappedAndSavedSubcategoryName() {
        SubCategory addedSubCategory = this.subCategoryService.add(this.subCategoryAddBindingModel);

        assertEquals("Not correctely mapped subcategory name",SUB_CATEGORY_NAME,addedSubCategory.getName());
    }

    @Test
    public void testAdd_withSubCategoryBindingModel_shouldReturnCorrectlyMappedAndSavedSubCatCurrencyPerUnit() {
        SubCategory addedSubCategory = this.subCategoryService.add(this.subCategoryAddBindingModel);

        assertEquals("Not correctely mapped currency per unit",CURRENCY_PER_UNIT,addedSubCategory.getCurrencyPerUnit());
    }

    @Test
    public void testAdd_withSubCategoryBindingModel_shouldReturnCorrectlyMappedAndSavedSubCatCategoryName() {
        SubCategory addedSubCategory = this.subCategoryService.add(this.subCategoryAddBindingModel);

        assertEquals("Not correctely mapped currency per unit",CATEGORY_NAME,addedSubCategory.getCategory().getName());
    }

    @Test
    public void testFindByName_withNoExistingSubCategoryName_shouldReturnNull() {
        //arrange
        when(this.subCategoryRepository.findFirstByName(NON_EXISTING_SUB_CATEGORY_NAME)).thenReturn(null);

        //act
        SubCategoryViewModel subCategoryViewModel = this.subCategoryService.findByName(NON_EXISTING_SUB_CATEGORY_NAME);

        //assert
        assertEquals("Not returning null when subcategory is not found", null, subCategoryViewModel);
    }

    @Test
    public void testFindByName_withName_shouldReturnCorrectlyMappedViewModel() {
        //arrange
        SubCategory subCategory = new SubCategory();
        subCategory.setName(EXISTING_SUB_CATEGORY_NAME);
        subCategory.setCurrencyPerUnit(CURRENCY_PER_UNIT);

        when(this.subCategoryRepository.findFirstByName(EXISTING_SUB_CATEGORY_NAME)).thenReturn(subCategory);

        //act
        SubCategoryViewModel foundSubCategoryViewModel = this.subCategoryService.findByName(EXISTING_SUB_CATEGORY_NAME);

        //assert
        assertEquals("Not correctly mapped name", EXISTING_SUB_CATEGORY_NAME, foundSubCategoryViewModel.getName());
        assertEquals("Not correctly mapped currency per unit", CURRENCY_PER_UNIT, foundSubCategoryViewModel.getCurrencyPerUnit());
    }

}
