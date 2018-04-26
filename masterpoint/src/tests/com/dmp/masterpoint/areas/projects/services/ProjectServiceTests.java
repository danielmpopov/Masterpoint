package com.dmp.masterpoint.areas.projects.services;

import com.dmp.masterpoint.areas.cities.entities.City;
import com.dmp.masterpoint.areas.projects.entities.Project;
import com.dmp.masterpoint.areas.projects.entities.Task;
import com.dmp.masterpoint.areas.projects.models.view.ProjectFullViewModel;
import com.dmp.masterpoint.areas.projects.repositories.ProjectRepository;
import com.dmp.masterpoint.areas.repairworks.entities.Category;
import com.dmp.masterpoint.areas.repairworks.entities.SubCategory;
import com.dmp.masterpoint.areas.repairworks.models.view.RepairWorkViewModel;
import com.dmp.masterpoint.areas.users.entities.Client;
import com.dmp.masterpoint.areas.users.entities.Workman;
import com.dmp.masterpoint.areas.users.models.view.WorkmanViewModel;
import com.dmp.masterpoint.areas.users.repositories.WorkmanRepository;
import com.dmp.masterpoint.areas.users.services.UserService;
import com.dmp.masterpoint.areas.users.services.WorkmanService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class ProjectServiceTests {
    private static final String NON_EXISTING_PROJECT_ID = "12323-323-3232";
    private static final String EXISTING_PROJECT_ID = "4da11e1c-48b3-11e8-842f-0ed5f89f718b";
    private static final String CLIENT_USERNAME = "Ivan";
    private static final String WORKMAN_USERNAME = "Gosho";
    private static final String PROJECT_CITY_NAME = "Karnobat";
    private static final String CATEGORY_ONE_NAME = "Category One";
    private static final String CATEGORY_TWO_NAME = "Category Two";
    private static final String SUB_CATEGORY_ONE_NAME = "Sub Category One";
    private static final String SUB_CATEGORY_TWO_NAME = "Sub Category Two";
    private static final String SUB_CATEGORY_THREE_NAME = "Sub Category Three";
    private static final BigDecimal FIRST_REPAIRWORK_PRICE_PER_UNIT = BigDecimal.valueOf(3);
    private static final BigDecimal SECOND_REPAIRWORK_PRICE_PER_UNIT = BigDecimal.valueOf(4);
    private static final BigDecimal TASK_ONE_QUANTITY = BigDecimal.valueOf(2);
    private static final BigDecimal TASK_TWO_QUANTITY = BigDecimal.valueOf(4);
    private static final BigDecimal TASK_THREE_QUANTITY = BigDecimal.valueOf(1);
    private static final BigDecimal TOTAL_PRICE = BigDecimal.valueOf(22);
    private static final BigDecimal PRICE_PER_TASK_TWO = BigDecimal.valueOf(16);
    private static final int NUM_AVAILABLE_TASKS = 2;
    private static final int NUM_TOTAL_TASKS = 3;

    @Mock
    private ProjectRepository projectRepository;
    @Mock
    private WorkmanService workmanService;
    @Mock
    private UserService userService;
    @Mock
    private WorkmanRepository workmanRepository;

    private ProjectServiceImpl projectService;


    @Before
    public void setUp(){

        this.projectService = new ProjectServiceImpl(this.projectRepository,this.workmanService,this.userService,this.workmanRepository,new ModelMapper());

        Client client = new Client();
        client.setUsername(CLIENT_USERNAME);

        City city = new City();
        city.setName(PROJECT_CITY_NAME);

        Workman workman = new Workman();
        workman.setUsername(WORKMAN_USERNAME);

        Category categoryOne = new Category();
        categoryOne.setName(CATEGORY_ONE_NAME);
        Category categoryTwo = new Category();
        categoryTwo.setName(CATEGORY_TWO_NAME);

        SubCategory subCategoryOne = new SubCategory();
        subCategoryOne.setName(SUB_CATEGORY_ONE_NAME);
        subCategoryOne.setCategory(categoryOne);
        subCategoryOne.setCurrencyPerUnit("лв/м2");
        SubCategory subCategoryTwo = new SubCategory();
        subCategoryTwo.setName(SUB_CATEGORY_TWO_NAME);
        subCategoryTwo.setCategory(categoryTwo);
        subCategoryTwo.setCurrencyPerUnit("лв/м2");
        SubCategory subCategoryThree = new SubCategory();
        subCategoryThree.setName(SUB_CATEGORY_THREE_NAME);
        subCategoryThree.setCategory(categoryOne);
        subCategoryThree.setCurrencyPerUnit("лв/м");

        Task taskOne = new Task();
        taskOne.setQuantity(TASK_ONE_QUANTITY);
        taskOne.setSubCategory(subCategoryOne);
        Task taskTwo = new Task();
        taskTwo.setQuantity(TASK_TWO_QUANTITY);
        taskTwo.setSubCategory(subCategoryTwo);
        Task taskThree = new Task();
        taskThree.setQuantity(TASK_THREE_QUANTITY);
        taskThree.setSubCategory(subCategoryThree);
        Set<Task> tasks = new HashSet<>();
        tasks.add(taskOne);
        tasks.add(taskTwo);
        tasks.add(taskThree);

        Project project = new Project();
        project.setClient(client);
        project.setId(EXISTING_PROJECT_ID);
        project.setTitle("Project Title");
        project.setTasks(tasks);
        project.setStartDate(LocalDate.now());
        project.setDescription("Description of the project");
        project.setCity(city);
        project.setWorkman(workman);
        project.setIsAssigned(true);

        RepairWorkViewModel repairWorkViewModelOne = new RepairWorkViewModel();
        repairWorkViewModelOne.setSubCategoryName("Sub Category One");
        repairWorkViewModelOne.setPricePerUnit(FIRST_REPAIRWORK_PRICE_PER_UNIT);

        RepairWorkViewModel repairWorkViewModelTwo= new RepairWorkViewModel();
        repairWorkViewModelTwo.setSubCategoryName("Sub Category Two");
        repairWorkViewModelTwo.setPricePerUnit(SECOND_REPAIRWORK_PRICE_PER_UNIT);

        Set<RepairWorkViewModel> repairWorks = new HashSet<>();
        repairWorks.add(repairWorkViewModelOne);
        repairWorks.add(repairWorkViewModelTwo);

        WorkmanViewModel workmanViewModel = new WorkmanViewModel();
        workmanViewModel.setUsername(WORKMAN_USERNAME);
        workmanViewModel.setRepairWorks(repairWorks);

        when(this.projectRepository.findById(EXISTING_PROJECT_ID)).thenReturn(Optional.of(project));
        when(this.workmanService.findByName(WORKMAN_USERNAME)).thenReturn(workmanViewModel);

    }

    @Test
    public void testFindById_withNoExistingProjectId_shouldReturnNull() {
        when(this.projectRepository.findById(NON_EXISTING_PROJECT_ID)).thenReturn(Optional.ofNullable(null));

        ProjectFullViewModel notFoundProjectViewModel = this.projectService.findById(NON_EXISTING_PROJECT_ID, null);

        assertEquals("Not returning null when project is not found", null, notFoundProjectViewModel);
    }

    @Test
    public void testFindById_withExistingProjectId_shouldReturnCorrectTotalPrice() {

        ProjectFullViewModel projectFullViewModel = this.projectService.findById(EXISTING_PROJECT_ID, null);

        assertEquals("Not correct total price", TOTAL_PRICE, projectFullViewModel.getTotalPrice());
    }

    @Test
    public void testFindById_withExistingProjectId_shouldReturnCorrectAvailableTasks() {

        ProjectFullViewModel projectFullViewModel = this.projectService.findById(EXISTING_PROJECT_ID, null);

        assertEquals("Not correct number of availble tasks", NUM_AVAILABLE_TASKS, (int) projectFullViewModel.getNumAvailableTasks());
    }

    @Test
    public void testFindById_withExistingProjectId_shouldReturnCorrectTotalTasks() {

        ProjectFullViewModel projectFullViewModel = this.projectService.findById(EXISTING_PROJECT_ID, null);

        assertEquals("Not correct number of availble tasks", NUM_TOTAL_TASKS, (int) projectFullViewModel.getTotalTasks());
    }

    @Test
    public void testFindById_withExistingProjectId_shouldReturnCorrectTotalPricePerTask() {

        ProjectFullViewModel projectFullViewModel = this.projectService.findById(EXISTING_PROJECT_ID, null);

        assertEquals("Not correct Price per task", PRICE_PER_TASK_TWO, projectFullViewModel.getTaskDetails().get(CATEGORY_TWO_NAME).get(0).getPricePerTask());
    }
}
