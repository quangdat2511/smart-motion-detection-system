package com.javaweb.api;

import com.javaweb.constant.SystemConstant;
import com.javaweb.exception.MyException;
import com.javaweb.model.dto.PasswordDTO;
import com.javaweb.model.dto.UserDTO;
import com.javaweb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserAPI {

    @Autowired
    private UserService userService;
    @Autowired
    private UserDetailsService userDetailsService;
    @PostMapping
    public ResponseEntity<UserDTO> createUsers(@RequestBody UserDTO newUser) {
        return ResponseEntity.ok(userService.insert(newUser));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> updateUsers(@PathVariable("id") long id, @RequestBody UserDTO userDTO) {
        return ResponseEntity.ok(userService.update(id, userDTO));
    }

    @PutMapping("/change-password/{id}")
    public ResponseEntity<String> changePasswordUser(@PathVariable("id") long id, @RequestBody PasswordDTO passwordDTO) {
        try {
            userService.updatePassword(id, passwordDTO);
            return ResponseEntity.ok(SystemConstant.UPDATE_SUCCESS);
        } catch (MyException e) {
            //LOGGER.error(e.getMessage());
            return ResponseEntity.ok(e.getMessage());
        }
    }

    @PutMapping("/password/{id}/reset")
    public ResponseEntity<UserDTO> resetPassword(@PathVariable("id") long id) {
        return ResponseEntity.ok(userService.resetPassword(id));
    }


    @PutMapping("/profile/{username}")
    public ResponseEntity<UserDTO> updateProfileOfUser(
            @PathVariable("username") String username,
            @RequestBody UserDTO userDTO) {

        // 1. Cập nhật thông tin user trong database
        UserDTO updatedUserDTO = userService.updateProfileOfUser(username, userDTO);
        UserDetails updatedUserDetails = userDetailsService.loadUserByUsername(username);

        // 3. Tạo Authentication mới
        UsernamePasswordAuthenticationToken newAuth = new UsernamePasswordAuthenticationToken(
                updatedUserDetails,
                updatedUserDetails.getPassword(),
                updatedUserDetails.getAuthorities()
        );

        // 4. Cập nhật vào SecurityContext
        SecurityContextHolder.getContext().setAuthentication(newAuth);

        // 5. Trả về user đã cập nhật
        return ResponseEntity.ok(updatedUserDTO);
    }
    @DeleteMapping
    public ResponseEntity<Void> deleteUsers(@RequestBody long[] idList) {
        if (idList.length > 0) {
            userService.delete(idList);
        }
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/check-password/{id}")
    public ResponseEntity<String> checkPassword(@PathVariable("id") long id, @RequestParam("password") String password){

        // Kiểm tra mật khẩu
        if (userService.checkPassword(id, password)) {
            return ResponseEntity.ok("✅ Mật khẩu đúng");
        } else {
            return ResponseEntity.badRequest().body("❌ Mật khẩu không đúng");
        }
    }

}
