package com.dmp.masterpoint.areas.repairworks.repositories;

import com.dmp.masterpoint.areas.repairworks.entities.SubCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubCategoryRepository extends JpaRepository<SubCategory, String> {
    SubCategory findFirstByName(String name);
}
