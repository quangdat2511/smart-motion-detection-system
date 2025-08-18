package com.javaweb.controller.admin;

import com.javaweb.model.dto.MotionDTO;
import com.javaweb.service.MotionService;
import com.javaweb.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api/motion")
public class MotionApiController {

    @Autowired
    private MotionService motionService;
    @Autowired
    private EmailService emailService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getMotions(@RequestParam(name = "deviceId", required = true) String deviceId) {
        if (deviceId == null || deviceId.trim().isEmpty()) {
            Map<String, Object> err = new HashMap<>();
            err.put("status", "error");
            err.put("message", "deviceId is required");
            return ResponseEntity.badRequest().body(err);
        }

        try {
            List<MotionDTO> motions = motionService.getMotions(deviceId);
            Map<String, Object> resp = new HashMap<>();
            resp.put("status", "success");
            resp.put("data", motions);

            int countPerson = 0;
            int countMotion = 0;
            for (MotionDTO m : motions) {
                if (m == null) continue;
                countMotion++;
                String mt = m.getMotionType();
                if (mt == null) continue;
                String normalized = mt.trim().toLowerCase();
                // match exact "person" OR contains "person" (ví dụ "person_detected")
                if ("person".equals(normalized) || normalized.contains("person")) {
                    countPerson++;
                }
            }

            resp.put("countPerson", countPerson);
            resp.put("countMotion", countMotion);
            resp.put("email", emailService.sendMail(countMotion, countPerson));

            return ResponseEntity.ok(resp);
        } catch (InterruptedException ie) {
            Thread.currentThread().interrupt();
            Map<String, Object> err = new HashMap<>();
            err.put("status", "error");
            err.put("message", "Task interrupted");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(err);
        } catch (ExecutionException ee) {
            String msg = ee.getCause() != null ? ee.getCause().getMessage() : ee.getMessage();
            Map<String, Object> err = new HashMap<>();
            err.put("status", "error");
            err.put("message", msg);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(err);
        } catch (Exception ex) {
            Map<String, Object> err = new HashMap<>();
            err.put("status", "error");
            err.put("message", ex.getMessage() != null ? ex.getMessage() : ex.toString());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(err);
        }
    }
}