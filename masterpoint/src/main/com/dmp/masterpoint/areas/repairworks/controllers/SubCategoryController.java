package com.dmp.masterpoint.areas.repairworks.controllers;

import com.dmp.masterpoint.errors.SubCategoryNotFoundException;
import com.dmp.masterpoint.areas.repairworks.models.binding.SubCategoryAddBindingModel;
import com.dmp.masterpoint.areas.repairworks.models.view.SubCategoryViewModel;
import com.dmp.masterpoint.areas.repairworks.services.SubCategoryService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import javax.validation.Valid;


@Controller
public class SubCategoryController {

    private final SubCategoryService subCategoryService;

    public SubCategoryController(SubCategoryService subCategoryService) {
        this.subCategoryService = subCategoryService;
    }

    @GetMapping("/subcategories/add/{category}")
    @PreAuthorize("hasAnyRole('ADMIN','MODERATOR')")
    public ModelAndView add(@PathVariable String category, ModelAndView modelAndView,
                            @ModelAttribute("subCategory") SubCategoryAddBindingModel bindingModel) {

        modelAndView.setViewName("subcategory/add");

        return modelAndView;
    }


    @PostMapping("/subcategories/add/{category}")
    @PreAuthorize("hasAnyRole('ADMIN','MODERATOR')")
    public ModelAndView add(@Valid @ModelAttribute("subCategory") SubCategoryAddBindingModel bindingModel, BindingResult result, ModelAndView modelAndView) {


        if (this.subCategoryService.findByName(bindingModel.getName()) != null) {
            result.rejectValue("name", null, "Вече има регистрирана такава услуга!");
        }

        if (result.hasErrors()){
            modelAndView.setViewName("subcategory/add");
        } else {
            this.subCategoryService.add(bindingModel);
            modelAndView.setViewName("redirect:/categories/show");
        }

        return modelAndView;
    }

    @GetMapping("/subcategories/edit/{name}")
    @PreAuthorize("hasAnyRole('ADMIN','MODERATOR')")
    public ModelAndView edit(@PathVariable String name, ModelAndView modelAndView) {

        if (!modelAndView.getModel().containsKey("subCategory")) {
            SubCategoryViewModel subCategoryModel = this.subCategoryService.findByName(name);
            if (subCategoryModel == null) {
                throw new SubCategoryNotFoundException();
            }
            modelAndView.getModel().put("subCategory", subCategoryModel);
        }

        modelAndView.setViewName("subcategory/edit");

        return modelAndView;
    }

    @PostMapping("subcategories/edit/{name}")
    @PreAuthorize("hasAnyRole('ADMIN','MODERATOR')")
    public ModelAndView edit(@PathVariable String name, @Valid @ModelAttribute("subCategory") SubCategoryAddBindingModel bindingModel,
                             BindingResult result, ModelAndView modelAndView) {
        if (result.hasErrors()) {
            modelAndView.setViewName("/subcategory/edit");
        } else {
            this.subCategoryService.updateSubCategory(name, bindingModel);
            modelAndView.setViewName("redirect:/categories/show");
        }

        return modelAndView;
    }

    @GetMapping("/subcategories/delete/{name}")
    @PreAuthorize("hasAnyRole('ADMIN','MODERATOR')")
    public ModelAndView delete(@PathVariable String name, ModelAndView modelAndView) {
        SubCategoryViewModel subCategoryModel = this.subCategoryService.findByName(name);

        if (subCategoryModel == null) {
            throw new SubCategoryNotFoundException();
        }

        modelAndView.getModel().put("subCategory", subCategoryModel);

        modelAndView.setViewName("subcategory/delete");

        return modelAndView;
    }

    @PostMapping("/subcategories/delete/{name}")
    @PreAuthorize("hasAnyRole('ADMIN','MODERATOR')")
    public ModelAndView deleteConfirm(@PathVariable String name, ModelAndView modelAndView) {

        this.subCategoryService.deleteByName(name);
        modelAndView.setViewName("redirect:/categories/show");

        return modelAndView;
    }
}
