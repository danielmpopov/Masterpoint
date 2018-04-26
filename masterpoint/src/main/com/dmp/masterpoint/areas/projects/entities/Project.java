package com.dmp.masterpoint.areas.projects.entities;

import com.dmp.masterpoint.areas.cities.entities.City;
import com.dmp.masterpoint.areas.users.entities.Client;
import com.dmp.masterpoint.areas.users.entities.Workman;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "projects")
public class Project {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "id", updatable = false, nullable = false)
    private String id;

    @Column(nullable = false)
    private String Title;

    @Column(nullable = false)
    private  String description;

    @ManyToOne(optional = false)
    @JoinColumn(name = "city_id",referencedColumnName = "id")
    private City city;

    @Column(nullable = false)
    private LocalDate startDate;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "client_id",referencedColumnName = "id")
    private Client client;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "workman_id",referencedColumnName = "id")
    private Workman workman;

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Task> tasks;

    @Column(name = "is_assigned")
    private boolean isAssigned;

    public Project() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }


    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Workman getWorkman() {
        return workman;
    }

    public void setWorkman(Workman workman) {
        this.workman = workman;
    }

    public Set<Task> getTasks() {
        return tasks;
    }

    public void setTasks(Set<Task> tasks) {
        this.tasks = tasks;
    }

    public boolean getIsAssigned() {
        return isAssigned;
    }

    public void setIsAssigned(boolean isAssigned) {
        this.isAssigned = isAssigned;
    }

    @PrePersist
    private void prePersist() {
        tasks.forEach( c -> c.setProject(this));
    }
}
