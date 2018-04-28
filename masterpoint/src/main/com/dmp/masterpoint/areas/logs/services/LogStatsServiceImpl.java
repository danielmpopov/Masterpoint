package com.dmp.masterpoint.areas.logs.services;

import com.dmp.masterpoint.areas.logs.entities.LogStats;
import com.dmp.masterpoint.areas.logs.models.view.LogStatsViewModel;
import com.dmp.masterpoint.areas.logs.repositories.LogStatsRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class LogStatsServiceImpl implements LogStatsService{

    private final LogStatsRepository logStatsRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public LogStatsServiceImpl(LogStatsRepository logStatsRepository, ModelMapper modelMapper) {
        this.logStatsRepository = logStatsRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void updateLogStats() {
        this.logStatsRepository.updateLogStats();
    }

    @Override
    public LogStatsViewModel findLastStats() {
        return this.modelMapper.map(this.logStatsRepository.findLastStats(), LogStatsViewModel.class);
    }
}
