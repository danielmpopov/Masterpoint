package com.dmp.masterpoint.areas.repairworks.services;

import com.dmp.masterpoint.areas.repairworks.entities.Category;
import com.dmp.masterpoint.areas.repairworks.models.binding.CategoryAddBindingModel;
import com.dmp.masterpoint.areas.repairworks.models.view.CategoryViewModel;
import com.dmp.masterpoint.areas.repairworks.models.view.RepairWorkViewModel;
import com.dmp.masterpoint.areas.repairworks.models.view.SubCategoryViewModel;
import com.dmp.masterpoint.areas.repairworks.repositories.CategoryRepository;
import com.dmp.masterpoint.areas.repairworks.services.CategoryServiceImpl;
import com.dmp.masterpoint.areas.repairworks.services.RepairWorkService;
import com.dmp.masterpoint.config.ApplicationBeanConfiguration;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

import java.math.BigDecimal;
import java.util.*;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(classes = {ApplicationBeanConfiguration.class})
@SpringBootTest
@ActiveProfiles("test")
public class CategoryServiceTests {

    private static final String CATEGORY_ONE_NAME = "Category One";
    private static final String CATEGORY_ONE_ID = "4da11354-48b3-11e8-842f-0ed5f89f718b";
    private static final String NON_EXISTING_CATEGORY_NAME = "Category Three";
    private static final int CATEGORIES_COUNT = 1;
    private static final int SUBCATEGORIES_COUNT = 3;
    private static final int REPAIRWORKS_BY_WORKMAN_COUNT = 2;

    private CategoryAddBindingModel categoryAddBindingModel;

    @Mock
    private CategoryRepository categoryRepository;
    @Mock
    private RepairWorkService repairWorkService;

    private CategoryServiceImpl categoryService;

    @Before
    public void setUp() {

        categoryAddBindingModel = new CategoryAddBindingModel();
        categoryAddBindingModel.setId("4da110a2-48b3-11e8-842f-0ed5f89f718b");
        categoryAddBindingModel.setName(CATEGORY_ONE_NAME);

        when(this.categoryRepository.save(any())).thenAnswer(i->i.getArgument(0));

        this.categoryService = new CategoryServiceImpl(this.categoryRepository,this.repairWorkService,new ModelMapper());


    }

    @Test
    public void testAddCategoryAndMapping_withBindingModel_nameShouldBeCorrectlySaved () {
        Category addedCategory = this.categoryService.add(categoryAddBindingModel);

        assertEquals("Not correctely saved category",CATEGORY_ONE_NAME, addedCategory.getName());
    }

    @Test
    public void testFindByName_withNoExistingCategoryName_shouldReturnNull() {
        when(this.categoryRepository.findFirstByName(NON_EXISTING_CATEGORY_NAME)).thenReturn(null);

        CategoryViewModel categoryViewModel = this.categoryService.findByName(NON_EXISTING_CATEGORY_NAME);

        assertEquals("Not returning null when category is not found", null, categoryViewModel);
    }

    @Test
    public void testFindByName_withName_shouldReturnCorrectlyMappedViewModel() {
        Category categoryOne = new Category();
        categoryOne.setName(CATEGORY_ONE_NAME);
        categoryOne.setId(CATEGORY_ONE_ID);

        when(this.categoryRepository.findFirstByName(CATEGORY_ONE_NAME)).thenReturn(categoryOne);

        CategoryViewModel categoryViewModel = this.categoryService.findByName(CATEGORY_ONE_NAME);

        assertEquals("Not correctly mapped name", CATEGORY_ONE_NAME, categoryViewModel.getName());
        assertEquals("Not correctly mapped Id", CATEGORY_ONE_ID, categoryViewModel.getId());
    }

    @Test
    public void findCategoriesAndRepairWorksByWorkman_withWorkmanName_shouldReturnCorrectCount() {
        //arrange
        RepairWorkViewModel repairWorkOne = new RepairWorkViewModel();
        repairWorkOne.setId("4da11822-48b3-11e8-842f-0ed5f89f718b");
        repairWorkOne.setPricePerUnit(BigDecimal.valueOf(5));
        repairWorkOne.setSubCategoryName("Subcategory One");
        repairWorkOne.setWorkmanName("Иван");

        RepairWorkViewModel repairWorkTwo = new RepairWorkViewModel();
        repairWorkTwo.setId("4da11980-48b3-11e8-842f-0ed5f89f718b");
        repairWorkTwo.setPricePerUnit(BigDecimal.valueOf(6));
        repairWorkTwo.setSubCategoryName("Subcategory Two");
        repairWorkTwo.setWorkmanName("Иван");

        List<RepairWorkViewModel> allRepWorksByWorkman = new ArrayList<>();
        allRepWorksByWorkman.add(repairWorkOne);
        allRepWorksByWorkman.add(repairWorkTwo);

        SubCategoryViewModel subCategoryOne = new SubCategoryViewModel();
        subCategoryOne.setName("Subcategory One");
        SubCategoryViewModel subCategoryTwo = new SubCategoryViewModel();
        subCategoryTwo.setName("Subcategory Two");
        SubCategoryViewModel subCategoryThree = new SubCategoryViewModel();
        subCategoryThree.setName("Subcategory Three");

        Set<SubCategoryViewModel> subCategories = new HashSet<>();
        subCategories.add(subCategoryOne);
        subCategories.add(subCategoryTwo);
        subCategories.add(subCategoryThree);

        List<CategoryViewModel> allCategories = new ArrayList<>();
        CategoryViewModel categoryViewModel = new CategoryViewModel();
        categoryViewModel.setName(CATEGORY_ONE_NAME);
        categoryViewModel.setId(CATEGORY_ONE_ID);
        categoryViewModel.setSubCategories(subCategories);
        allCategories.add(categoryViewModel);

        when(this.repairWorkService.findALLRepWorksByWorkman("Иван")).thenReturn(allRepWorksByWorkman);

        CategoryServiceImpl spyCategoryService = Mockito.spy(this.categoryService);
        Mockito.doReturn(allCategories).when(spyCategoryService).getAll();

        //act
        List<CategoryViewModel> categoriesByWorkman = spyCategoryService.findCategoriesAndRepairWorksByWorkman("Иван");

        //assert
        assertEquals("Categories count not correct",CATEGORIES_COUNT,categoriesByWorkman.size());
        assertEquals("Subcategories count not correct",SUBCATEGORIES_COUNT,categoriesByWorkman.get(0).getSubCategories().size());
        assertEquals("Repairworks by workman count not correct",REPAIRWORKS_BY_WORKMAN_COUNT,categoriesByWorkman.get(0).getSubCategories().stream().filter(s -> s.getRepairWorkId() != null).count());
    }

}
