package com.dmp.masterpoint.areas.logs.services;

import com.dmp.masterpoint.areas.logs.models.view.LogStatsViewModel;

public interface LogStatsService {
    void updateLogStats();

    LogStatsViewModel findLastStats();
}
