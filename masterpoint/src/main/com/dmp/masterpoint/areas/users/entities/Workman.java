package com.dmp.masterpoint.areas.users.entities;

import com.dmp.masterpoint.areas.projects.entities.Project;
import com.dmp.masterpoint.areas.repairworks.entities.RepairWork;

import javax.persistence.*;
import java.util.Set;

@Entity
@DiscriminatorValue(value = "Workman")
public class Workman extends User {

    @Column
    private String company;

    @OneToMany(mappedBy = "workman")
    private Set<RepairWork> repairWorks;

    @OneToMany(mappedBy = "workman", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Project> projects;

    public Workman() {
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public Set<RepairWork> getRepairWorks() {
        return repairWorks;
    }

    public void setRepairWorks(Set<RepairWork> repairWorks) {
        this.repairWorks = repairWorks;
    }

    public Set<Project> getProjects() {
        return projects;
    }

    public void setProjects(Set<Project> projects) {
        this.projects = projects;
    }
}
