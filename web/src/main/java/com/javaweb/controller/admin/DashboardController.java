package com.javaweb.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class DashboardController {
    @GetMapping("/admin/dashboard-list")
    public ModelAndView getDashboard() {
        ModelAndView modelAndView = new ModelAndView("admin/dashboard/list");
        return modelAndView;
    }


}
