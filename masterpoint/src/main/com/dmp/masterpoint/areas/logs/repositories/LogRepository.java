package com.dmp.masterpoint.areas.logs.repositories;

import com.dmp.masterpoint.areas.logs.entities.LogMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogRepository extends JpaRepository<LogMessage, Long> {

}
