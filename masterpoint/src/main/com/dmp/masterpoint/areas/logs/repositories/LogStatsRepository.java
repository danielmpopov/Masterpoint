package com.dmp.masterpoint.areas.logs.repositories;

import com.dmp.masterpoint.areas.logs.entities.LogStats;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;

@Repository
public interface LogStatsRepository extends JpaRepository<LogStats, Long>{
    @Procedure(name = "udp_log_stats")
    void updateLogStats();
}
