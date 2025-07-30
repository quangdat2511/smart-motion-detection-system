package com.javaweb.controller.admin;

import com.javaweb.constant.SystemConstant;
import com.javaweb.model.request.CustomerSearchRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ActionController {
    @GetMapping("/admin/action-list")
    public ModelAndView getAllCustomers(@ModelAttribute(SystemConstant.MODEL) CustomerSearchRequest customerSearchRequest, HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView("admin/action/list");
        return modelAndView;
    }


}
