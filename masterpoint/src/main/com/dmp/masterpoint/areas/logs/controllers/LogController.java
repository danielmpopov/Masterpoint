package com.dmp.masterpoint.areas.logs.controllers;

import com.dmp.masterpoint.areas.logs.services.LogStatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class LogController {

    private final LogStatsService logStatsService;

    @Autowired
    public LogController(LogStatsService logStatsService) {
        this.logStatsService = logStatsService;
    }

    @GetMapping("/logs/stats")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ModelAndView showStats (ModelAndView modelAndView) {
        modelAndView.setViewName("log/stats");
        modelAndView.addObject("logStats", this.logStatsService.findLastStats());
        return modelAndView;
    }
}
