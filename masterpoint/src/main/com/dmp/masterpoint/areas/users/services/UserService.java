package com.dmp.masterpoint.areas.users.services;

import com.dmp.masterpoint.areas.users.entities.User;
import com.dmp.masterpoint.areas.users.models.binding.RegisterBindingModel;
import com.dmp.masterpoint.areas.users.models.view.UserListViewModel;
import com.dmp.masterpoint.areas.users.models.view.UserViewModel;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;


public interface UserService extends UserDetailsService {

    User register(RegisterBindingModel bindingModel);

    UserViewModel findFirstByUsername(String username);

    UserViewModel findFirstByEmail(String email);

    List<UserListViewModel> getAll();

    UserListViewModel getById(String id);

    void deleteById(String id);

    void updateUser(String id, UserListViewModel bindingModel);
}
