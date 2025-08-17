package com.javaweb.api;
import com.javaweb.service.PushSaferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pushsafer")
public class PushSaferAPI {

    @Autowired
    private PushSaferService pushSaferService;
    @PutMapping("/{message}")
    public ResponseEntity<String> updatePushSaferStatus(@PathVariable String message) {
        if (!message.equalsIgnoreCase("on") && !message.equalsIgnoreCase("off")) {
            return ResponseEntity.badRequest().body("❌ Giá trị 'message' phải là 'on' hoặc 'off'");
        }
        boolean receive = "on".equalsIgnoreCase(message);
        pushSaferService.setReceiveMessage(receive);
        return ResponseEntity.ok(
                receive ? "Bật thông báo pushsafer" : "Tắt thông báo pushsafer"
        );
    }
}
