package com.dmp.masterpoint.areas.logs.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "actions_logs")
public class LogMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Date date;

    @Column
    private String action;

    public LogMessage() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
}
