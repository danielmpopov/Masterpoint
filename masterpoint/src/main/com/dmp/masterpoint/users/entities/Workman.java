package com.dmp.masterpoint.users.entities;

import com.dmp.masterpoint.repairworks.entities.RepairWork;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.Set;

@Entity
@DiscriminatorValue(value = "Workman")
public class Workman extends User {

    @Column
    private String company;

    @OneToMany(mappedBy = "workman")
    private Set<RepairWork> repairWorks;

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
}
