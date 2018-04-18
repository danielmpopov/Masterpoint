package com.dmp.masterpoint.users.services;

import com.dmp.masterpoint.users.entities.Client;
import com.dmp.masterpoint.users.entities.Role;
import com.dmp.masterpoint.users.entities.User;
import com.dmp.masterpoint.users.entities.Workman;
import com.dmp.masterpoint.users.models.binding.RegisterBindingModel;
import com.dmp.masterpoint.users.models.view.UserViewModel;
import com.dmp.masterpoint.users.repositories.RoleRepository;
import com.dmp.masterpoint.users.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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
    public void register(RegisterBindingModel bindingModel) {

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

        this.userRepository.save(user);
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


}
