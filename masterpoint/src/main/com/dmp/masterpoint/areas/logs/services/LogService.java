package com.dmp.masterpoint.areas.logs.services;

import com.dmp.masterpoint.areas.logs.models.binding.LogMessageDTO;

public interface LogService {
    void create(LogMessageDTO logMessageDTO);
}
