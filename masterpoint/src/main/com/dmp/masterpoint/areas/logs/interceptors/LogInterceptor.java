package com.dmp.masterpoint.areas.logs.interceptors;

import com.dmp.masterpoint.areas.logs.annotations.Log;
import com.dmp.masterpoint.areas.logs.entities.LogMessage;
import com.dmp.masterpoint.areas.logs.models.binding.LogMessageDTO;
import com.dmp.masterpoint.areas.logs.services.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

@Component
public class LogInterceptor extends HandlerInterceptorAdapter {

    private final LogService logService;

    @Autowired
    public LogInterceptor(LogService logService) {
        this.logService = logService;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response,
                           Object handler, ModelAndView modelAndView) throws Exception {

        if (handler instanceof HandlerMethod) {
            HandlerMethod method = (HandlerMethod) handler;

            LogMessageDTO logMessageDTO = new LogMessageDTO();

            String action;

            boolean isHireAction = method.hasMethodAnnotation(Log.class) && method.getMethod().getName().equals("hire");
            boolean isRegisterAction = method.hasMethodAnnotation(Log.class) && modelAndView.getViewName().equals("redirect:/login");
            boolean isNewProjectAction = method.hasMethodAnnotation(Log.class) && modelAndView.getViewName().equals("redirect:/");

            if (isHireAction || isNewProjectAction || isRegisterAction) {
                action = method.getMethod().getName();
                logMessageDTO.setAction(action);
                logMessageDTO.setDate(new Date());
                this.logService.create(logMessageDTO);
            }
        }


    }
}
