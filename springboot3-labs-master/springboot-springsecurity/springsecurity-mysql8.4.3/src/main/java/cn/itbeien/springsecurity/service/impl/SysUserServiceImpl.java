package cn.itbeien.springsecurity.service.impl;

import cn.itbeien.springsecurity.config.MySQLUserDetailsManager;
import cn.itbeien.springsecurity.entity.SysUser;
import cn.itbeien.springsecurity.mapper.SysUserMapper;
import cn.itbeien.springsecurity.service.SysUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

/**
 * @author itbeien
 * 项目网站：https://www.itbeien.cn
 * 公众号：贝恩聊架构
 * 全网同名，欢迎小伙伴们关注
 * Copyright© 2024 itbeien
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

    @Resource
    private MySQLUserDetailsManager mySQLUserDetailsManager;

    @Override
    public void saveSysUser(SysUser sysUser) {
        UserDetails userDetails = User.withDefaultPasswordEncoder().
                username(sysUser.getUsername()).password(sysUser.getPassword()).build();
        mySQLUserDetailsManager.createUser(userDetails);
    }

    public static void main(String[] args) {
        UserDetails userDetails = User.withDefaultPasswordEncoder().username("itbeien").password("itbeien").build();
        System.out.println( userDetails.getPassword());
    }
}
