package com.dmp.masterpoint.areas.users.controllers;

import com.dmp.masterpoint.controllers.BaseController;
import com.dmp.masterpoint.errors.UserAlreadyLoggedInException;
import com.dmp.masterpoint.areas.users.models.binding.RegisterBindingModel;
import com.dmp.masterpoint.areas.users.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

        if(error != null) modelAndView.addObject("error", "Username or Password Not Correct!");
        if(logout != null) modelAndView.addObject("logout", "You have successfuly logged out!");

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

    @PostMapping("/register")
    public ModelAndView registerConfirm(@Valid @ModelAttribute("user") RegisterBindingModel bindingModel,
                                        BindingResult result,
                                        ModelAndView modelAndView) {

        if (!bindingModel.getPassword().equals(bindingModel.getConfirmPassword())) {
            result.rejectValue("confirmPassword", null, "Confirm password should be the same as password");
        }

        if (userService.findFirstByUsername(bindingModel.getUsername()) != null) {
            result.rejectValue("username", null, "There is already a registered user with that username");
        }

        if (userService.findFirstByEmail(bindingModel.getEmail()) != null) {
            result.rejectValue("email", null, "There is already a registered user with that email");
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


}
