package com.dmp.masterpoint.users.models.view;

import com.dmp.masterpoint.repairworks.entities.RepairWork;
import com.dmp.masterpoint.repairworks.models.view.RepairWorkViewModel;

import java.util.Set;

public class WorkmanViewModel {

    private String username;

    private Set<RepairWorkViewModel> repairWorks;

    public WorkmanViewModel() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Set<RepairWorkViewModel> getRepairWorks() {
        return repairWorks;
    }

    public void setRepairWorks(Set<RepairWorkViewModel> repairWorks) {
        this.repairWorks = repairWorks;
    }
}
