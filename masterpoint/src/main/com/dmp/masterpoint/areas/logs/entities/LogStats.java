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
    private int numNewProjects;

    @Column(name = "num_new_users")
    private int numNewUsers;

    @Column(name = "num_new_hired_workman")
    private int numNewHiredWorkmen;

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

    public int getNumNewProjects() {
        return numNewProjects;
    }

    public void setNumNewProjects(int numNewProjects) {
        this.numNewProjects = numNewProjects;
    }

    public int getNumNewUsers() {
        return numNewUsers;
    }

    public void setNumNewUsers(int numNewUsers) {
        this.numNewUsers = numNewUsers;
    }

    public int getNumNewHiredWorkmen() {
        return numNewHiredWorkmen;
    }

    public void setNumNewHiredWorkmen(int numNewHiredWorkmen) {
        this.numNewHiredWorkmen = numNewHiredWorkmen;
    }
}
