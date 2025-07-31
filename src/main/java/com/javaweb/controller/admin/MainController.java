package com.javaweb.controller.admin;

import com.javaweb.constant.SystemConstant;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class MainController {
    @GetMapping("/admin/main-list")
    public ModelAndView getMain() {
        ModelAndView modelAndView = new ModelAndView("admin/main/list");
        return modelAndView;
    }


}
