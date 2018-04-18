package com.dmp.masterpoint.repairworks.controllers;

import com.dmp.masterpoint.repairworks.models.binding.OfferAddBindingModel;
import com.dmp.masterpoint.repairworks.models.binding.RepairWorkBindingModel;
import com.dmp.masterpoint.repairworks.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class OfferController {

    private final CategoryService categoryService;

    @Autowired
    public OfferController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/offer/add")
    @PreAuthorize("hasAnyRole('CLIENT')")
    public ModelAndView show(ModelAndView modelAndView, @ModelAttribute("offer") OfferAddBindingModel bindingModel) {
        modelAndView.addObject("categories", this.categoryService.getAll());

        modelAndView.setViewName("offer/add");
        return modelAndView;
    }


    @PostMapping("/offer/add")
    @PreAuthorize("hasAnyRole('CLIENT')")
    public ModelAndView add(@Valid @ModelAttribute("offer") OfferAddBindingModel bindingModel, BindingResult result, ModelAndView modelAndView) {
        modelAndView.addObject("categories", this.categoryService.getAll());

        if (result.hasErrors()) {
            modelAndView.setViewName("offer/add");
        } else {
           // this.categoryService.add(bindingModel);
            modelAndView.setViewName("redirect:/");
        }

        return modelAndView;
    }
}
