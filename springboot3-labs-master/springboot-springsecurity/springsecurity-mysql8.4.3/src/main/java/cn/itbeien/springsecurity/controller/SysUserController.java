package cn.itbeien.springsecurity.controller;

import cn.itbeien.springsecurity.entity.SysUser;
import cn.itbeien.springsecurity.service.SysUserService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author itbeien
 * 项目网站：https://www.itbeien.cn
 * 公众号：贝恩聊架构
 * 全网同名，欢迎小伙伴们关注
 * Copyright© 2025 itbeien
 */
@RestController
@RequestMapping("/user")
public class SysUserController {

    @Resource
    private SysUserService sysUserService;

    @GetMapping("/list")
    public List<SysUser> getUsers() {
        return sysUserService.list();
    }

    @PostMapping("/add")
    public String addUser(@RequestBody SysUser sysUser) {
        sysUserService.saveSysUser(sysUser);
        return "success";
    }
}
