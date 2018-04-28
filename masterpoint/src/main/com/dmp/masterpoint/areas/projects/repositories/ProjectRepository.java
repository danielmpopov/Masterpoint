package com.dmp.masterpoint.areas.projects.repositories;

import com.dmp.masterpoint.areas.projects.entities.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, String> {


    List<Project> findAllByClient_Username(String userName);

    List<Project> findAllByWorkman_Username(String userName);
}
