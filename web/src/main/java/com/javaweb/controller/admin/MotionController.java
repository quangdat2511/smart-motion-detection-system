package com.javaweb.controller.admin;

import com.javaweb.model.request.MotionRequestDTO;
import com.javaweb.model.response.MotionSearchResponse;
import com.javaweb.service.MotionService;
import com.javaweb.utils.DisplayTagUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Controller
public class MotionController {
    @Autowired
    private MotionService motionService;
    @GetMapping("/admin/motion-list")
    public ModelAndView getAllMotions(@RequestParam String deviceId, HttpServletRequest request) throws ExecutionException, InterruptedException {
        ModelAndView modelAndView = new ModelAndView("admin/motion/list");
        MotionRequestDTO motionDTO = new MotionRequestDTO();
        motionDTO.setDeviceId(deviceId);
        DisplayTagUtils.of(request, motionDTO);
        List<MotionSearchResponse> motionSearchResponses = motionService.findAll(motionDTO);
        motionDTO.setListResult(motionSearchResponses);
        motionDTO.setTotalItems(motionService.countTotalItems(deviceId));
        // Thêm trạng thái chuyển động mới nhất
        modelAndView.addObject("motionDTO", motionDTO);
        return modelAndView;
    }
}
