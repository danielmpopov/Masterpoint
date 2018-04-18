package com.dmp.masterpoint.repairworks.controllers;

import com.dmp.masterpoint.errors.RepairWorkNotFoundException;
import com.dmp.masterpoint.repairworks.models.binding.RepairWorkBindingModel;
import com.dmp.masterpoint.repairworks.models.view.RepairWorkViewModel;
import com.dmp.masterpoint.repairworks.services.RepairWorkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.security.Principal;

@Controller
public class RepairWorkController {
    private final RepairWorkService repairWorkService;

    @Autowired
    public RepairWorkController(RepairWorkService repairWorkService) {
        this.repairWorkService = repairWorkService;
    }


    @GetMapping("/repairworks/add/{subcategoryName}")
    @PreAuthorize("hasAnyRole('WORKMAN')")
    public ModelAndView add(@PathVariable String subcategoryName, ModelAndView modelAndView, @ModelAttribute("repairwork") RepairWorkBindingModel bindingModel) {

        modelAndView.setViewName("category/show-mine");

        return modelAndView;
    }

    @PostMapping("/repairworks/add/{subcategoryName}")
    @PreAuthorize("hasAnyRole('WORKMAN')")
    public ModelAndView add(@PathVariable String subcategoryName,@Valid @ModelAttribute("repairwork") RepairWorkBindingModel bindingModel,
                            BindingResult result,
                            ModelAndView modelAndView,
                            Principal principal) {

        if (result.hasErrors()) {
            modelAndView.setViewName("redirect:/categories/my");
        } else {
            this.repairWorkService.add(bindingModel, principal.getName(),subcategoryName);
            modelAndView.setViewName("redirect:/categories/my");
        }

        return modelAndView;
    }


    @GetMapping("/repairworks/edit/{id}")
    @PreAuthorize("hasAnyRole('WORKMAN')")
    public ModelAndView edit(@PathVariable String id, ModelAndView modelAndView) {

        if (!modelAndView.getModel().containsKey("repairwork")) {
            RepairWorkViewModel repairWorkViewModel = this.repairWorkService.findById(id);
            if (repairWorkViewModel == null) {
                throw new RepairWorkNotFoundException();
            }

            //check if Principal name = workmanName
            modelAndView.getModel().put("repairwork", repairWorkViewModel);
        }

        modelAndView.setViewName("category/show-mine");

        return modelAndView;
    }

    @PostMapping("/repairworks/edit/{id}")
    @PreAuthorize("hasAnyRole('WORKMAN')")
    public ModelAndView edit(@PathVariable String id, @Valid @ModelAttribute("repairwork") RepairWorkBindingModel bindingModel,
                             BindingResult result, ModelAndView modelAndView) {

        if (result.hasErrors()) {
            modelAndView.setViewName("redirect:/categories/my"); // ako se stigne do tuk da throwna greshka po-dobre
        } else {
            this.repairWorkService.updateRepairWork(id, bindingModel);
            modelAndView.setViewName("redirect:/categories/my");
        }

        return modelAndView;
    }

    @GetMapping("/repairworks/delete/{id}")
    @PreAuthorize("hasAnyRole('WORKMAN')")
    public ModelAndView delete(@PathVariable String id, ModelAndView modelAndView) {
        RepairWorkViewModel repairWorkViewModel = this.repairWorkService.findById(id);

        if (repairWorkViewModel == null) {
            throw new RepairWorkNotFoundException();
        }

        modelAndView.getModel().put("repairwork", repairWorkViewModel);

        modelAndView.setViewName("category/show-mine");

        return modelAndView;
    }

    @PostMapping("/repairworks/delete/{id}")
    @PreAuthorize("hasAnyRole('WORKMAN')")
    public ModelAndView deleteConfirm(@PathVariable String id, ModelAndView modelAndView) {

        this.repairWorkService.deleteById(id);
        modelAndView.setViewName("redirect:/categories/my");

        return modelAndView;
    }

}
