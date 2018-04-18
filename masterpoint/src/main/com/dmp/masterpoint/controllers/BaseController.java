package com.dmp.masterpoint.controllers;

import org.springframework.web.servlet.ModelAndView;

public class BaseController {

    protected ModelAndView view(String viewName, Object viewModel) {
        ModelAndView modelAndView = new ModelAndView(viewName);
        modelAndView.getModel().put("model", viewModel);

        return modelAndView;
    }

    protected ModelAndView view(Object viewModel) {

        StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
        StackTraceElement caller = stackTraceElements[2];
        String[] names = caller.getClassName().split("\\.");
        String folder = names[names.length - 1].replace("Controller", "").toLowerCase();
        String file = caller.getMethodName();

        return this.view(folder + "/" + file, viewModel);
    }

    protected ModelAndView redirect(String url) {
        return new ModelAndView("redirect:" + url);
    }
}
