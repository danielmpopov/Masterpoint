package com.dmp.masterpoint.repairworks.repositories;

import com.dmp.masterpoint.repairworks.entities.RepairWork;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepairWorkRepository  extends JpaRepository<RepairWork, String>{

    @Query(value = "SELECT DISTINCT  r FROM RepairWork AS r " +
            "LEFT JOIN r.workman AS w " +
            "WHERE w.username  = :name ")
    List<RepairWork> findALLRepWorksByWorkman(@Param("name") String name);

}
