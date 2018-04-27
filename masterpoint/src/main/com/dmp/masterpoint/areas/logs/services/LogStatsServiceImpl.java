package com.dmp.masterpoint.areas.logs.services;

import com.dmp.masterpoint.areas.logs.repositories.LogStatsRepository;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.provider.HibernateUtils;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import javax.transaction.Transactional;

@Service
@Transactional
public class LogStatsServiceImpl implements LogStatsService{

    private final LogStatsRepository logStatsRepository;

    @Autowired
    public LogStatsServiceImpl(LogStatsRepository logStatsRepository) {
        this.logStatsRepository = logStatsRepository;
    }

    @Override
    public void updateLogStats() {
        this.logStatsRepository.updateLogStats();
    }
}
