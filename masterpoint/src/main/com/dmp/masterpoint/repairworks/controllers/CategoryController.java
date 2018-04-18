package com.dmp.masterpoint.repairworks.controllers;

import com.dmp.masterpoint.errors.CategoryNotFoundException;
import com.dmp.masterpoint.repairworks.models.binding.CategoryAddBindingModel;
import com.dmp.masterpoint.repairworks.models.binding.RepairWorkBindingModel;
import com.dmp.masterpoint.repairworks.models.view.CategoryViewModel;
import com.dmp.masterpoint.repairworks.services.CategoryService;
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
public class CategoryController {

    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }


    @GetMapping("/categories/add")
    @PreAuthorize("hasAnyRole('ADMIN','MODERATOR')")
    public ModelAndView add(ModelAndView modelAndView, @ModelAttribute("category") CategoryAddBindingModel bindingModel) {

        modelAndView.setViewName("category/add");

        return modelAndView;
    }

    @PostMapping("/categories/add")
    @PreAuthorize("hasAnyRole('ADMIN','MODERATOR')")
    public ModelAndView add(@Valid @ModelAttribute("category") CategoryAddBindingModel bindingModel, BindingResult result, ModelAndView modelAndView) {


        if (this.categoryService.findByName(bindingModel.getName()) != null) {
            result.rejectValue("name", null, "Вече има регистрирана категория с това име!");
        }

        if (result.hasErrors()) {
            modelAndView.setViewName("category/add");
        } else {
            this.categoryService.add(bindingModel);
            modelAndView.setViewName("redirect:/");
        }

        return modelAndView;
    }

    @GetMapping("/categories/show")
    @PreAuthorize("hasAnyRole('ADMIN','MODERATOR')")
    public ModelAndView show(ModelAndView modelAndView) {
        modelAndView.addObject("categories", this.categoryService.getAll());
        modelAndView.setViewName("category/show");
        return modelAndView;
    }

    @GetMapping("/categories/edit/{name}")
    @PreAuthorize("hasAnyRole('ADMIN','MODERATOR')")
    public ModelAndView edit(@PathVariable String name, ModelAndView modelAndView) {

        if (!modelAndView.getModel().containsKey("category")) {
            CategoryViewModel categoryViewModel = this.categoryService.findByName(name);
            if (categoryViewModel == null) {
                throw new CategoryNotFoundException();
            }
            modelAndView.getModel().put("category", categoryViewModel);
        }

        modelAndView.setViewName("category/edit");

        return modelAndView;
    }

    @PostMapping("/categories/edit/{name}")
    @PreAuthorize("hasAnyRole('ADMIN','MODERATOR')")
    public ModelAndView edit(@PathVariable String name, @Valid @ModelAttribute("category") CategoryAddBindingModel bindingModel,
                             BindingResult result, ModelAndView modelAndView) {

        if (this.categoryService.findByName(bindingModel.getName()) != null) {
            result.rejectValue("name", null, "Вече има регистрирана категория с това име!");
        }

        if (result.hasErrors()) {
            modelAndView.setViewName("/category/edit");
        } else {
            this.categoryService.updateCategory(name, bindingModel);
            modelAndView.setViewName("redirect:/categories/show");
        }

        return modelAndView;
    }

    @GetMapping("/categories/delete/{name}")
    @PreAuthorize("hasAnyRole('ADMIN','MODERATOR')")
    public ModelAndView delete(@PathVariable String name, ModelAndView modelAndView) {
        CategoryViewModel categoryViewModel = this.categoryService.findByName(name);

        if (categoryViewModel == null) {
            throw new CategoryNotFoundException();
        }

        modelAndView.getModel().put("category", categoryViewModel);

        modelAndView.setViewName("category/delete");

        return modelAndView;
    }

    @PostMapping("/categories/delete/{name}")
    @PreAuthorize("hasAnyRole('ADMIN','MODERATOR')")
    public ModelAndView deleteConfirm(@PathVariable String name, ModelAndView modelAndView) {

            this.categoryService.deleteByName(name);
            modelAndView.setViewName("redirect:/categories/show");


        return modelAndView;
    }

    @GetMapping("/categories/my")
    @PreAuthorize("hasAnyRole('WORKMAN')")
    public ModelAndView showMine(@Valid @ModelAttribute("repairwork") RepairWorkBindingModel bindingModel, ModelAndView modelAndView, Principal principal) {
        modelAndView.addObject("categories", this.categoryService.findCategoriesAndRepairWorksByWorkman(principal.getName()));
        modelAndView.setViewName("category/show-mine");
        return modelAndView;
    }


}
