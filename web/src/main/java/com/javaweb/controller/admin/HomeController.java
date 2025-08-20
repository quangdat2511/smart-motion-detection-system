package com.javaweb.controller.admin;

import com.javaweb.model.dto.UserDTO;
import com.javaweb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller(value = "homeControllerOfAdmin")
public class HomeController {

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/admin/home", method = RequestMethod.GET)
	public ModelAndView homePage() {
		ModelAndView mav = new ModelAndView("admin/home");
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();

		// Lấy deviceId từ DB
		UserDTO user = userService.findOneByUserNameAndStatus(username, 1);
		String deviceId = user.getDeviceId();

		// Gắn vào model để JSP dùng được
		mav.addObject("deviceId", deviceId);
		return mav;
	}
}
