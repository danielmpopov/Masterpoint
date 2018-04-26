package com.dmp.masterpoint.areas.users.repositories;


import com.dmp.masterpoint.areas.users.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, String> {

    Role findFirstByAuthority(String authority);

}
