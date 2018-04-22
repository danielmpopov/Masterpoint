package com.dmp.masterpoint.users.services;

import com.dmp.masterpoint.repairworks.models.view.CategoryViewModel;
import com.dmp.masterpoint.users.models.view.WorkmanViewModel;
import com.dmp.masterpoint.users.repositories.WorkmanRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class WorkmanServiceImpl implements WorkmanService {
    private final WorkmanRepository workmanRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public WorkmanServiceImpl(WorkmanRepository workmanRepository, ModelMapper modelMapper) {
        this.workmanRepository = workmanRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public List<WorkmanViewModel> findAll() {
        return this.workmanRepository.findAll()
                .stream()
                .map(c -> this.modelMapper.map(c, WorkmanViewModel.class ))
                .collect(Collectors.toList());
    }

    @Override
    public WorkmanViewModel findByName(String userName) {

        return this.modelMapper.map(this.workmanRepository.findFirstByUsername(userName), WorkmanViewModel.class);
    }

}
