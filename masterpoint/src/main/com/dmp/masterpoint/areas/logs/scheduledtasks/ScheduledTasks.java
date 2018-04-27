package com.dmp.masterpoint.areas.logs.scheduledtasks;

import com.dmp.masterpoint.areas.logs.services.LogStatsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTasks {

    private final LogStatsService logStatsService;

    @Autowired
    public ScheduledTasks(LogStatsService logStatsService) {
        this.logStatsService = logStatsService;
    }


    @Scheduled(fixedRate = 900000)
    public void reportCurrentTime() {
      this.logStatsService.updateLogStats();
    }
}
