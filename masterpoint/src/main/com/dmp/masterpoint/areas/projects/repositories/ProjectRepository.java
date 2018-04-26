package com.dmp.masterpoint.areas.projects.repositories;

import com.dmp.masterpoint.areas.projects.entities.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, String> {

//    @Query(value = "SELECT DISTINCT  p FROM Project AS p " +
//            "LEFT JOIN p.client AS c " +
//            "LEFT JOIN p.tasks AS t " +
//            "WHERE c.username  = :userName ")
//    List<Project> findAllByClientName(@Param("userName") String userName);

    List<Project> findAllByClient_Username(String userName);
}
