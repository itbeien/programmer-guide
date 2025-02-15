package cn.itbeien.security.jwt.service;

import cn.itbeien.security.jwt.entity.SysUser;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * @author itbeien
 * 项目网站：https://www.itbeien.cn
 * 公众号：贝恩聊架构
 * 全网同名，欢迎小伙伴们关注
 * Copyright© 2024 itbeien
 */
public interface SystemService {
    /**
     * 使用用户名及密码登录
     * @param username
     * @param password
     * @return
     */
    String login(String username,String password);
    /**
     * 获取用户信息
     */
    UserDetails loadUserByUsername(String username);

    public SysUser getSysUserByUsername(String username);
}
