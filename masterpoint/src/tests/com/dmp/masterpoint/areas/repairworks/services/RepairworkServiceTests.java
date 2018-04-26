package com.dmp.masterpoint.areas.repairworks.services;

import com.dmp.masterpoint.areas.repairworks.entities.Category;
import com.dmp.masterpoint.areas.repairworks.entities.RepairWork;
import com.dmp.masterpoint.areas.repairworks.entities.SubCategory;
import com.dmp.masterpoint.areas.repairworks.models.binding.RepairWorkBindingModel;
import com.dmp.masterpoint.areas.repairworks.models.view.RepairWorkViewModel;
import com.dmp.masterpoint.areas.repairworks.models.view.SubCategoryViewModel;
import com.dmp.masterpoint.areas.repairworks.repositories.RepairWorkRepository;
import com.dmp.masterpoint.areas.repairworks.services.RepairWorkServiceImpl;
import com.dmp.masterpoint.areas.repairworks.services.SubCategoryService;
import com.dmp.masterpoint.areas.users.entities.Workman;
import com.dmp.masterpoint.areas.users.models.view.UserViewModel;
import com.dmp.masterpoint.areas.users.models.view.WorkmanViewModel;
import com.dmp.masterpoint.areas.users.services.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class RepairworkServiceTests {

    private static final BigDecimal PRICE_PER_UNIT = BigDecimal.valueOf(100);
    private static final String SUB_CATEGORY_NAME = "Subcategory One";
    private static final String WORKMAN_USERNAME = "Gosho";
    private static final int NUM_REPAIRWORKS_BY_WORKMNAN = 1;

    @Mock
    private RepairWorkRepository repairWorkRepository;

    @Mock
    private UserService userService;

    @Mock
    private SubCategoryService subCategoryService;

    private RepairWorkServiceImpl repairWorkService;

    @Before
    public void setUp() {

        this.repairWorkService = new RepairWorkServiceImpl(this.repairWorkRepository, new ModelMapper(), this.userService, this.subCategoryService);

        Category categoryOne = new Category();
        categoryOne.setId("4da11e1c-48b3-11e8-842f-0ed5f89f718b");
        categoryOne.setName("Category One");

        SubCategory subCategoryOne = new SubCategory();
        subCategoryOne.setName(SUB_CATEGORY_NAME);
        subCategoryOne.setCategory(categoryOne);
        subCategoryOne.setCurrencyPerUnit("лв/м2");
        subCategoryOne.setId("4da11d04-48b3-11e8-842f-0ed5f89f718b");

        Workman workmanOne = new Workman();
        workmanOne.setUsername(WORKMAN_USERNAME);

        RepairWork repairWorkOne = new RepairWork();
        repairWorkOne.setPricePerUnit(PRICE_PER_UNIT);
        repairWorkOne.setId("4da11bd8-48b3-11e8-842f-0ed5f89f718b");
        repairWorkOne.setSubCategory(subCategoryOne);
        repairWorkOne.setWorkman(workmanOne);

        List<RepairWork> allRepairworks = new ArrayList<>();
        allRepairworks.add(repairWorkOne);

        when(this.repairWorkRepository.findAllByWorkman_Username(WORKMAN_USERNAME)).thenReturn(allRepairworks);

    }

    @Test
    public void findALLRepWorksByWorkman_withWorkmanUsername_shouldReturnCorrectNumOfRepairworks() {
        List<RepairWorkViewModel> repairWorkViewModelList = this.repairWorkService.findALLRepWorksByWorkman(WORKMAN_USERNAME);

        assertEquals("Not correct number of repairworks", NUM_REPAIRWORKS_BY_WORKMNAN , repairWorkViewModelList.size());
    }

    @Test
    public void findALLRepWorksByWorkman_withWorkmanUsername_shouldReturnCorrectlyMappedViewModel() {
        List<RepairWorkViewModel> repairWorkViewModelList = this.repairWorkService.findALLRepWorksByWorkman(WORKMAN_USERNAME);

        assertEquals("Not correctly mapped price per unit", PRICE_PER_UNIT , repairWorkViewModelList.get(0).getPricePerUnit());

        assertEquals("Not correctly mapped subcategory name",SUB_CATEGORY_NAME, repairWorkViewModelList.get(0).getSubCategoryName());
    }

    @Test
    public void testAddRepairwork_withBindingModel_nameShouldBeCorrectlyMappedAndSaved () {
        //arrange
        RepairWorkBindingModel repairWorkBindingModel = new RepairWorkBindingModel();
        repairWorkBindingModel.setPricePerUnit(PRICE_PER_UNIT);

        UserViewModel workman = new UserViewModel();
        workman.setUsername(WORKMAN_USERNAME);
        workman.setId("4da11f3e-48b3-11e8-842f-0ed5f89f718b");

        SubCategoryViewModel subCategoryViewModel = new SubCategoryViewModel();
        subCategoryViewModel.setName(SUB_CATEGORY_NAME);
        subCategoryViewModel.setId("4da11d04-48b3-11e8-842f-0ed5f89f718b");

        when(this.repairWorkRepository.save(any())).thenAnswer(i->i.getArgument(0));
        when(this.userService.findFirstByUsername(WORKMAN_USERNAME)).thenReturn(workman);
        when(this.subCategoryService.findByName(SUB_CATEGORY_NAME)).thenReturn(subCategoryViewModel);

        //act
        RepairWork addedRepairwork = this.repairWorkService.add(repairWorkBindingModel, WORKMAN_USERNAME, SUB_CATEGORY_NAME);

        //assert
        assertEquals("Not correctely mapped and saved repairwork",PRICE_PER_UNIT, addedRepairwork.getPricePerUnit());
    }
}

