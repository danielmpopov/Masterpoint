package com.dmp.masterpoint.areas.logs.models.binding;

import java.time.LocalDate;
import java.util.Date;

public class LogMessageDTO {

    private Long id;

    private Date date;

    private String action;

    public LogMessageDTO() {
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
