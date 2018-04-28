package com.dmp.masterpoint.areas.users.services;

import com.dmp.masterpoint.areas.users.entities.Client;
import com.dmp.masterpoint.areas.users.entities.Role;
import com.dmp.masterpoint.areas.users.entities.User;
import com.dmp.masterpoint.areas.users.entities.Workman;
import com.dmp.masterpoint.areas.users.models.binding.RegisterBindingModel;
import com.dmp.masterpoint.areas.users.models.view.UserListViewModel;
import com.dmp.masterpoint.areas.users.models.view.UserViewModel;
import com.dmp.masterpoint.areas.users.repositories.RoleRepository;
import com.dmp.masterpoint.areas.users.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.lang.reflect.Type;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;


@Service
@Transactional
public class UserServiceImpl implements UserService {
    private final BCryptPasswordEncoder encoder;

    private final ModelMapper mapper;

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    @Autowired
    public UserServiceImpl(BCryptPasswordEncoder encoder, ModelMapper mapper, UserRepository userRepository, RoleRepository roleRepository) {
        this.encoder = encoder;
        this.mapper = mapper;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> result = this.userRepository.findFirstByUsername(username);

        if(!result.isPresent()) throw new UsernameNotFoundException("Username not found.");

        return result.get();
    }

    @Override
    public User register(RegisterBindingModel bindingModel) {

        User user = null;
        Role role = null;

        if (bindingModel.getType().equals("Client")) {
            user = this.mapper.map(bindingModel, Client.class);
            role = this.roleRepository.findFirstByAuthority("ROLE_CLIENT");
        } else {
            user = this.mapper.map(bindingModel, Workman.class);
            role = this.roleRepository.findFirstByAuthority("ROLE_WORKMAN");
        }

        user.setPassword(this.encoder.encode(bindingModel.getPassword()));
        user.addRole(role);
        user.setCredentialsNonExpired(true);
        user.setAccountNonExpired(true);
        user.setAccountNonLocked(true);
        user.setEnabled(true);

        return this.userRepository.save(user);
    }

    @Override
    public UserViewModel findFirstByUsername(String username) {

        Optional<User> user = userRepository.findFirstByUsername(username);

        if (user.isPresent()) {
            return this.mapper.map(user.get(), UserViewModel.class);
        }

        return null;
    }

    @Override
    public UserViewModel findFirstByEmail(String email) {
        Optional<User> user = userRepository.findFirstByEmail(email);

        if (user.isPresent()) {
            return this.mapper.map(user.get(), UserViewModel.class);
        }

        return null;
    }

    @Override
    public List<UserListViewModel> getAll() {
        List<User> users = this.userRepository.findAll();
        Type usersListType = new TypeToken<List<UserListViewModel>>() {}.getType();
        return this.mapper.map(users, usersListType);
    }

    @Override
    public UserListViewModel getById(String id) {
        Optional<User> user = this.userRepository.findById(id);
        if (!user.isPresent()) {
            return null;
        }

        UserListViewModel userViewModel = this.mapper.map(user.get(), UserListViewModel.class);
        for (GrantedAuthority grantedAuthority : user.get().getAuthorities()) {
            String authority = grantedAuthority.getAuthority();
            if (authority.equalsIgnoreCase("ROLE_ADMIN")){
                userViewModel.setIsAdmin(true);
            }
            if (authority.equalsIgnoreCase("ROLE_MODERATOR")){
                userViewModel.setIsModerator(true);
            }
            if (authority.equalsIgnoreCase("ROLE_CLIENT")){
                userViewModel.setIsClient(true);
            }
            if (authority.equalsIgnoreCase("ROLE_WORKMAN")){
                userViewModel.setIsWorkman(true);
            }
        }

        return userViewModel;
    }

    @Override
    public void deleteById(String id) {
        Optional<User> user = this.userRepository.findById(id);

        if (!user.isPresent()) {
            return;
        }

        this.userRepository.deleteById(id);

    }

    @Override
    public void updateUser(String id, UserListViewModel bindingModel) {
        User updatedUser =  this.userRepository.findById(id).orElse(null);
        if (updatedUser == null) return;

        updatedUser.setAuthorities(new HashSet<>());

        if (bindingModel.getIsAdmin()){
            return;
        }
        if (bindingModel.getIsModerator()){
            Role role = this.roleRepository.findFirstByAuthority("ROLE_MODERATOR");
            updatedUser.addRole(role);
        }
        if (bindingModel.getIsClient()){
            Role role = this.roleRepository.findFirstByAuthority("ROLE_CLIENT");
            updatedUser.addRole(role);
        }
        if (bindingModel.getIsWorkman()){
            Role role = this.roleRepository.findFirstByAuthority("ROLE_WORKMAN");
            updatedUser.addRole(role);
        }
        if (updatedUser.getAuthorities().size()==0){
            Role role = this.roleRepository.findFirstByAuthority("ROLE_USER");
            updatedUser.addRole(role);
        }
        this.userRepository.save(updatedUser);
    }


}
