package com.javaweb.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {
    @GetMapping("/admin/main-list")
    public ModelAndView getMain() {
        ModelAndView modelAndView = new ModelAndView("admin/main/list");
        return modelAndView;
    }


}
