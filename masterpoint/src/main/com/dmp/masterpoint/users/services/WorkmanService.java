package com.dmp.masterpoint.users.services;

import com.dmp.masterpoint.users.models.view.WorkmanViewModel;

import java.util.List;

public interface WorkmanService {
    List<WorkmanViewModel> findAll();

    WorkmanViewModel findByName(String userName);
}
