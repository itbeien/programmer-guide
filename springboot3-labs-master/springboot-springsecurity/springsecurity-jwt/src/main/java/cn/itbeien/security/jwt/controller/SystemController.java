package cn.itbeien.security.jwt.controller;

import cn.itbeien.security.jwt.dto.CommonResult;
import cn.itbeien.security.jwt.dto.SystemLoginParam;
import cn.itbeien.security.jwt.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
/**
 * @author itbeien
 * 项目网站：https://www.itbeien.cn
 * 公众号：贝恩聊架构
 * 全网同名，欢迎小伙伴们关注
 * Copyright© 2025 itbeien
 */
@RestController
@RequestMapping("/api/v1")
public class SystemController {
    @Autowired
    private SystemService systemService;
    @Value("${jwt.tokenHead}")
    private String tokenHead;
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult login(@Validated @RequestBody SystemLoginParam systemLoginParam) {
        String token = systemService.login(systemLoginParam.getUsername(), systemLoginParam.getPassword());
        if (token == null) {
            return CommonResult.validateFailed("用户名或密码错误");
        }
        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put("token", token);
        tokenMap.put("tokenHead", tokenHead);
        return CommonResult.success(tokenMap);
    }
}
