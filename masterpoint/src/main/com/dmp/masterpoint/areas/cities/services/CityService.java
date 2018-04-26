package com.dmp.masterpoint.areas.cities.services;

import com.dmp.masterpoint.areas.cities.models.view.CityViewModel;

import java.util.List;

public interface CityService {
    List<CityViewModel> findAll();
}
