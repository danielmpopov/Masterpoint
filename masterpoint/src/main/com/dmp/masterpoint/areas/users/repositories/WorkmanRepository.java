package com.dmp.masterpoint.areas.users.repositories;

import com.dmp.masterpoint.areas.users.entities.Workman;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkmanRepository extends JpaRepository<Workman, String>{

    Workman findFirstByUsername(String userName);
}
