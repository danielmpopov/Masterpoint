package com.dmp.masterpoint.areas.repairworks.services;

import com.dmp.masterpoint.areas.repairworks.entities.RepairWork;
import com.dmp.masterpoint.areas.repairworks.models.binding.RepairWorkBindingModel;
import com.dmp.masterpoint.areas.repairworks.models.view.RepairWorkViewModel;
import com.dmp.masterpoint.areas.repairworks.repositories.RepairWorkRepository;
import com.dmp.masterpoint.areas.users.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class RepairWorkServiceImpl implements RepairWorkService{

    private final RepairWorkRepository repairWorkRepository;
    private final ModelMapper modelMapper;
    private final UserService userService;
    private final SubCategoryService subCategoryService;

    @Autowired
    public RepairWorkServiceImpl(RepairWorkRepository repairWorkRepository, ModelMapper modelMapper, UserService userService, SubCategoryService subCategoryService) {
        this.repairWorkRepository = repairWorkRepository;
        this.modelMapper = modelMapper;
        this.userService = userService;
        this.subCategoryService = subCategoryService;
    }


    @Override
    public List<RepairWorkViewModel> findALLRepWorksByWorkman(String name) {

        return this.repairWorkRepository.findAllByWorkman_Username(name)
                .stream()
                .map(r -> this.modelMapper.map(r, RepairWorkViewModel.class ))
                .collect(Collectors.toList());
    }

    @Override
    public RepairWork add(RepairWorkBindingModel repairWorkBindingModel, String workmanName, String subCategoryName) {

        String workmanId = this.userService.findFirstByUsername(workmanName).getId();
        String subCategoryId = this.subCategoryService.findByName(subCategoryName).getId();
        repairWorkBindingModel.setSubCategoryId(subCategoryId);
        repairWorkBindingModel.setWorkmanId(workmanId);
        RepairWork repairWork = this.modelMapper.map(repairWorkBindingModel, RepairWork.class);
        return this.repairWorkRepository.save(repairWork);
    }

    @Override
    public RepairWorkViewModel findById(String id) {
        Optional<RepairWork> repairWork = this.repairWorkRepository.findById(id);

        if (repairWork.isPresent()) {
            return this.modelMapper.map(repairWork.get(), RepairWorkViewModel.class);
        }

        return null;
    }

    @Override
    public void updateRepairWork(String id, RepairWorkBindingModel bindingModel) {
        Optional<RepairWork> repairWorkOpt = this.repairWorkRepository.findById(id);
        if (!repairWorkOpt.isPresent()) {
            return;
        }

        RepairWork repairWork = repairWorkOpt.get();

        repairWork.setPricePerUnit(bindingModel.getPricePerUnit());

        this.repairWorkRepository.saveAndFlush(repairWork);
    }

    @Override
    public void deleteById(String id) {
        Optional<RepairWork> repairWorkOpt = this.repairWorkRepository.findById(id);

        if (!repairWorkOpt.isPresent()) {
            return;
        }

        RepairWork repairWork = repairWorkOpt.get();

        this.repairWorkRepository.delete(repairWork);
    }
}
