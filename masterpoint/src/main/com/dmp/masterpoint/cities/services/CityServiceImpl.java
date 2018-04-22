package com.dmp.masterpoint.cities.services;

import com.dmp.masterpoint.cities.models.view.CityViewModel;
import com.dmp.masterpoint.cities.repositories.CityRepository;
import com.dmp.masterpoint.repairworks.models.view.CategoryViewModel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CityServiceImpl implements CityService{

    private final CityRepository cityRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public CityServiceImpl(CityRepository cityRepository, ModelMapper modelMapper) {
        this.cityRepository = cityRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<CityViewModel> findAll() {
        return this.cityRepository.findAll()
                .stream()
                .map(c -> this.modelMapper.map(c, CityViewModel.class ))
                .collect(Collectors.toList());
    }
}
