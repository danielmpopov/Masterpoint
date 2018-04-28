package com.dmp.masterpoint.areas.logs.entities;

import javax.persistence.*;
import java.util.Date;


@NamedStoredProcedureQuery(
        name="udp_log_stats",
        procedureName="udp_log_stats"
)
@Entity
@Table(name = "log_stats")
public class LogStats {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "timestamp")
    private Date timetamp;

    @Column(name = "num_new_projects")
    private Integer numNewProjects;

    @Column(name = "num_new_users")
    private Integer numNewUsers;

    @Column(name = "num_new_hired_workman")
    private Integer numNewHiredWorkmen;

    public LogStats() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
