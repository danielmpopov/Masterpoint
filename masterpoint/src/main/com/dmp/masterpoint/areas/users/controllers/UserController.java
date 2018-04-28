package com.dmp.masterpoint.areas.users.controllers;

import com.dmp.masterpoint.areas.logs.annotations.Log;
import com.dmp.masterpoint.areas.users.models.view.UserListViewModel;
import com.dmp.masterpoint.areas.users.models.view.UserViewModel;
import com.dmp.masterpoint.controllers.BaseController;
import com.dmp.masterpoint.errors.UserAlreadyLoggedInException;
import com.dmp.masterpoint.areas.users.models.binding.RegisterBindingModel;
import com.dmp.masterpoint.areas.users.services.UserService;
import com.dmp.masterpoint.errors.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.security.Principal;

@Controller
public class UserController extends BaseController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public ModelAndView login(@RequestParam(required = false, name = "error") String error,
                              @RequestParam(required = false, name = "logout") String logout,
                              Principal principal,
                              ModelAndView modelAndView) {

        if (principal != null) {
            throw new UserAlreadyLoggedInException();
        }

        modelAndView.setViewName("user/login");

        if(error != null) modelAndView.addObject("error", "Потребителското име или паролата са грешни");
        if(logout != null) modelAndView.addObject("logout", "Вие се разлогнахте успешно!");

        return modelAndView;
    }

    @PostMapping("/logout")
    @PreAuthorize("hasAnyRole('USER','ADMIN','MODERATOR')")
    public ModelAndView logout(@RequestParam(required = false, name = "logout") String logout, ModelAndView modelAndView, RedirectAttributes redirectAttributes) {
        modelAndView.setViewName("redirect:/user/login");

        if(logout != null) redirectAttributes.addFlashAttribute("logout", logout);

        return modelAndView;
    }

    @GetMapping("/register")
    public ModelAndView register(ModelAndView modelAndView,
                                 Principal principal,
                                 @ModelAttribute("user") RegisterBindingModel bindingModel) {

        if (principal != null) {
            throw new UserAlreadyLoggedInException();
        }

        modelAndView.setViewName("user/register");

        return modelAndView;
    }

    @Log
    @PostMapping("/register")
    public ModelAndView registerConfirm(@Valid @ModelAttribute("user") RegisterBindingModel bindingModel,
                                        BindingResult result,
                                        ModelAndView modelAndView) {

        if (!bindingModel.getPassword().equals(bindingModel.getConfirmPassword())) {
            result.rejectValue("confirmPassword", null, "Моля потвърдете парлата!");
        }

        if (userService.findFirstByUsername(bindingModel.getUsername()) != null) {
            result.rejectValue("username", null, "Вече има регистриран потребител с това име!");
        }

        if (userService.findFirstByEmail(bindingModel.getEmail()) != null) {
            result.rejectValue("email", null, "Вече има регистриран потребител с този email!");
        }

        if (result.hasErrors()){
            modelAndView.setViewName("user/register");
        } else {
            this.userService.register(bindingModel);
            modelAndView.setViewName("redirect:/login");
        }


        return modelAndView;
    }

    @GetMapping("/unauthorized")
    public ModelAndView unauthorized(ModelAndView modelAndView) {
        modelAndView.setViewName("user/unauthorized");

        return modelAndView;
    }


    @GetMapping("/users/profile")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView viewProfile(ModelAndView modelAndView, Principal principal){

        String userName = principal.getName();
        modelAndView.setViewName("user/profile");
        modelAndView.addObject("userName", userName);

        return modelAndView;
    }

    @GetMapping("/users/show")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ModelAndView show(ModelAndView modelAndView) {

        modelAndView.addObject("users", this.userService.getAll());
        modelAndView.setViewName("user/listUsers");
        return modelAndView;
    }

    @GetMapping("/users/delete/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ModelAndView delete(@PathVariable String id, ModelAndView modelAndView) {
        UserListViewModel userViewModel = this.userService.getById(id);
        if (userViewModel == null) {
            throw new UserNotFoundException();
        }

        modelAndView.addObject("id", id);
        modelAndView.addObject("user", userViewModel);
        modelAndView.setViewName("user/delete");
        return modelAndView;
    }

    @PostMapping("/users/delete/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ModelAndView deleteConfirm(@PathVariable String id, ModelAndView modelAndView) {
        this.userService.deleteById(id);
        modelAndView.setViewName("redirect:/users/show");
        return modelAndView;
    }

    @GetMapping("/users/edit/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public String edit(@PathVariable String id, Model model) {
        if (!model.containsAttribute("user")) {
            UserListViewModel userViewModel = this.userService.getById(id);
            if (userViewModel == null) {
                throw new UserNotFoundException();
            }
            model.addAttribute("user", userViewModel);
        }
        model.addAttribute("id", id);

        return "user/edit";
    }

    @PostMapping("users/edit/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public String edit(@PathVariable String id,@ModelAttribute UserListViewModel bindingModel, BindingResult bindingResult, RedirectAttributes attr) {
        if (bindingResult.hasErrors()) {
            attr.addFlashAttribute("user", bindingModel);

            return "redirect:/users/edit/" + id;
        }

        this.userService.updateUser(id, bindingModel);

        return "redirect:/users/show";
    }


}
