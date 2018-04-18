package com.dmp.masterpoint.repairworks.repositories;

import com.dmp.masterpoint.repairworks.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, String>{
    Category findFirstByName(String name);


}
