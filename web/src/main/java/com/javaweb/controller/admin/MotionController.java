package com.javaweb.controller.admin;

import com.javaweb.model.request.MotionRequestDTO;
import com.javaweb.model.response.MotionSearchResponse;
import com.javaweb.security.utils.SecurityUtils;
import com.javaweb.service.MotionService;
import com.javaweb.utils.DisplayTagUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ExecutionException;

@Controller
public class MotionController {
    @Autowired
    private MotionService motionService;
    @GetMapping("/admin/motion-list")
    public ModelAndView getAllMotions(@RequestParam(required = false) String deviceId, HttpServletRequest request) throws ExecutionException, InterruptedException {
        if (deviceId == null) {
            // fallback: lấy từ principal/session
            deviceId = Objects.requireNonNull(SecurityUtils.getPrincipal()).getDeviceId();
        }
        ModelAndView modelAndView = new ModelAndView("admin/motion/list");
        MotionRequestDTO motionRequestDTO = new MotionRequestDTO();
        motionRequestDTO.setDeviceId(deviceId);
        DisplayTagUtils.of(request, motionRequestDTO);
        List<MotionSearchResponse> motionSearchResponses = motionService.findAll(motionRequestDTO);
        motionRequestDTO.setListResult(motionSearchResponses);
        motionRequestDTO.setTotalItems(motionService.countTotalItems(deviceId));
        // Thêm trạng thái chuyển động mới nhất
        modelAndView.addObject("motionRequestDTO", motionRequestDTO);
        return modelAndView;
    }
}
