package com.dmp.masterpoint.areas.users.entities;

import com.dmp.masterpoint.areas.projects.entities.Project;

import javax.persistence.*;
import java.util.Set;

@Entity
@DiscriminatorValue(value = "Client")
public class Client extends User {

    @Column(name = "phone_number")
    private String phoneNumber;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Project> projects;

    public Client() {
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Set<Project> getProjects() {
        return projects;
    }

    public void setProjects(Set<Project> projects) {
        this.projects = projects;
    }
}
