package com.dmp.masterpoint.areas.logs.models.view;

import java.util.Date;

public class LogStatsViewModel {

    private Date timetamp;

    private Integer numNewProjects;

    private Integer numNewUsers;

    private Integer numNewHiredWorkmen;

    public LogStatsViewModel() {
    }

    public Date getTimetamp() {
        return timetamp;
    }

    public void setTimetamp(Date timetamp) {
        this.timetamp = timetamp;
    }

    public Integer getNumNewProjects() {
        return numNewProjects;
    }

    public void setNumNewProjects(Integer numNewProjects) {
        this.numNewProjects = numNewProjects;
    }

    public Integer getNumNewUsers() {
        return numNewUsers;
    }

    public void setNumNewUsers(Integer numNewUsers) {
        this.numNewUsers = numNewUsers;
    }

    public Integer getNumNewHiredWorkmen() {
        return numNewHiredWorkmen;
    }

    public void setNumNewHiredWorkmen(Integer numNewHiredWorkmen) {
        this.numNewHiredWorkmen = numNewHiredWorkmen;
    }
}
