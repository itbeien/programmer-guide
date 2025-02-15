package cn.itbeien.security.jwt.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

/**
 * @author itbeien
 * 项目网站：https://www.itbeien.cn
 * 公众号：贝恩聊架构
 * 全网同名，欢迎小伙伴们关注
 * Copyright© 2025 itbeien
 */
@Data
public class SystemLoginParam {
    @NotEmpty
    @Schema(title =  "用户名",requiredMode = Schema.RequiredMode.REQUIRED)
    private String username;
    @NotEmpty
    @Schema(title =  "密码",requiredMode = Schema.RequiredMode.REQUIRED)
    private String password;
}
