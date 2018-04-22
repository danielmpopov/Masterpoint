package com.dmp.masterpoint.cities.services;

import com.dmp.masterpoint.cities.models.view.CityViewModel;

import java.util.List;

public interface CityService {
    List<CityViewModel> findAll();
}
