package com.dmp.masterpoint.areas.logs.services;

import com.dmp.masterpoint.areas.logs.entities.LogMessage;
import com.dmp.masterpoint.areas.logs.models.binding.LogMessageDTO;
import com.dmp.masterpoint.areas.logs.repositories.LogRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class LogServiceImpl implements LogService {

    private final LogRepository logRepository;
    private final ModelMapper mapper;

    @Autowired
    public LogServiceImpl(LogRepository logRepository, ModelMapper mapper) {
        this.logRepository = logRepository;
        this.mapper = mapper;
    }

    @Override
    public void create(LogMessageDTO logMessageDTO) {

        LogMessage logMessage = this.mapper.map(logMessageDTO, LogMessage.class);

        this.logRepository.saveAndFlush(logMessage);
    }
}
