package com.dmp.masterpoint.areas.users.services;

import com.dmp.masterpoint.areas.users.entities.User;
import com.dmp.masterpoint.areas.users.models.binding.RegisterBindingModel;
import com.dmp.masterpoint.areas.users.models.view.UserViewModel;
import org.springframework.security.core.userdetails.UserDetailsService;


public interface UserService extends UserDetailsService {

    User register(RegisterBindingModel bindingModel);

    UserViewModel findFirstByUsername(String username);

    UserViewModel findFirstByEmail(String email);

//    List<UserDTO> getAll();
//    UserDTO getById(String id);
//
//    void deleteById(String id);
//
//    void updateUser(String id, UserDTO bindingModel);
}
