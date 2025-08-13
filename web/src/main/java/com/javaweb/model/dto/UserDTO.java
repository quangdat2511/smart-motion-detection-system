package com.javaweb.model.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
public class UserDTO extends AbstractDTO {
    @NotBlank(message = "Tên đăng nhập không được để trống")
    private String userName;

    @NotBlank(message = "Họ tên không được để trống")
    private String fullName;

    @NotBlank(message = "Mật khẩu không được để trống")
    private String password;
    private Integer status;
    private String deviceId; // ID thiết bị ESP32
    private List<RoleDTO> roles = new ArrayList<>();
    private String roleCode;
    private Map<String,String> roleDTOs = new HashMap<>();

}
