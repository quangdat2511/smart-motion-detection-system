package com.javaweb.controller.admin;

import com.javaweb.model.dto.MotionDTO;
import com.javaweb.model.response.MotionSearchResponse;
import com.javaweb.service.MotionService;
import com.javaweb.utils.DisplayTagUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class MotionController {
    @Autowired
    private MotionService motionService;
    @GetMapping("/admin/motion-list")
    public ModelAndView getAllCustomers(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView("admin/motion/list");
        MotionDTO motionDTO = new MotionDTO();
        DisplayTagUtils.of(request, motionDTO);
        List<MotionSearchResponse> motionSearchResponses = motionService.findAll(motionDTO);
        motionDTO.setListResult(motionSearchResponses);
        motionDTO.setTotalItems(motionService.countTotalItems());
        // Thêm trạng thái chuyển động mới nhất
        String latestStatus = motionService.getLatestMotionStatus(); // cần thêm method này trong service
        motionDTO.setLatestMotionStatus(latestStatus);
        modelAndView.addObject("motionDTO", motionDTO);
        return modelAndView;
    }
}
