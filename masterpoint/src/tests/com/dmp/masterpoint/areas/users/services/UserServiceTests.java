package com.dmp.masterpoint.areas.users.services;

import com.dmp.masterpoint.areas.users.entities.Client;
import com.dmp.masterpoint.areas.users.entities.Role;
import com.dmp.masterpoint.areas.users.entities.User;
import com.dmp.masterpoint.areas.users.models.binding.RegisterBindingModel;
import com.dmp.masterpoint.areas.users.models.view.UserViewModel;
import com.dmp.masterpoint.areas.users.repositories.RoleRepository;
import com.dmp.masterpoint.areas.users.repositories.UserRepository;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class UserServiceTests {
    private static final String EXISTING_USERNAME = "Client1";

    @Mock
    private UserRepository userRepository;
    @Mock
    private RoleRepository roleRepository;

    private UserServiceImpl userService;

    private RegisterBindingModel registerBindingModel;

    private User user;


    @Before
    public void setUp() {
        userService = new UserServiceImpl(new BCryptPasswordEncoder(), new ModelMapper(),this.userRepository, this.roleRepository);
        this.registerBindingModel = new RegisterBindingModel();
        this.registerBindingModel.setUsername("username");
        this.registerBindingModel.setPassword("pass1");
        this.registerBindingModel.setConfirmPassword("pass1");
        this.registerBindingModel.setEmail("email@mail.com");

        Role role = mock(Role.class);

        when(this.userRepository.save(any()))
                .thenAnswer(i -> i.getArgument(0));

        when(this.roleRepository.findFirstByAuthority("ROLE_CLIENT"))
                .thenReturn(role);
        when(this.roleRepository.findFirstByAuthority("ROLE_WORKMAN"))
                .thenReturn(role);
    }

    @Test
    public void testRegisterAsClient_withBindingModel_ReturnUser() {

        this.registerBindingModel.setType("Client");

        User savedUser = this.userService.register(this.registerBindingModel);

        assertEquals("Usernames are different", this.registerBindingModel.getUsername(), savedUser.getUsername());
    }

    @Test
    public void testRegisterAsWorkman_withBindingModel_ReturnUser() {

        this.registerBindingModel.setType("Workman");

        User savedUser = this.userService.register(this.registerBindingModel);

        assertEquals("Usernames are different", this.registerBindingModel.getUsername(), savedUser.getUsername());
    }


    @Test
    public void testRegisterAsClient_withBindingModel_authoritiesShouldNotBeEmpty() {
        this.registerBindingModel.setType("Client");

        User savedUser = this.userService.register(this.registerBindingModel);

        assertNotEquals("User has no roles", 0, savedUser.getAuthorities().size());
    }

    @Test
    public void testRegisterAsWorkman_withBindingModel_authoritiesShouldNotBeEmpty() {
        this.registerBindingModel.setType("Workman");

        User savedUser = this.userService.register(this.registerBindingModel);

        assertNotEquals("User has no roles", 0, savedUser.getAuthorities().size());
    }

    @Test
    public void testFindFirstByUsername_withUserName_shouldReturnCorrectlyMappedViewModel() {
        //arrange
        user = new Client();
        user.setUsername(EXISTING_USERNAME);
        user.setEmail("client@client.com");
        user.setPassword("somePassword123");

        when(this.userRepository.findFirstByUsername(EXISTING_USERNAME)).thenReturn(Optional.of(user));

        //act
        UserViewModel userViewModel = this.userService.findFirstByUsername(EXISTING_USERNAME);

        //assert
        TestCase.assertEquals("Not correctly mapped name", EXISTING_USERNAME, userViewModel.getUsername());

    }


}
