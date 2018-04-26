package com.dmp.masterpoint.areas.users.services;

import com.dmp.masterpoint.areas.users.models.view.WorkmanViewModel;

import java.util.List;

public interface WorkmanService {
    List<WorkmanViewModel> findAll();

    WorkmanViewModel findByName(String userName);
}
