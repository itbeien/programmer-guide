package cn.itbeien.security.jwt.service.impl;

import cn.itbeien.security.jwt.entity.SysResource;
import cn.itbeien.security.jwt.entity.SysUser;
import cn.itbeien.security.jwt.mapper.SysUserMapper;
import cn.itbeien.security.jwt.service.SystemService;
import cn.itbeien.security.jwt.util.Asserts;
import cn.itbeien.security.jwt.util.JwtTokenUtil;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author itbeien
 * 项目网站：https://www.itbeien.cn
 * 公众号：贝恩聊架构
 * 全网同名，欢迎小伙伴们关注
 * Copyright© 2024 itbeien
 */
@Slf4j
@Service
public class SystemServiceImpl implements SystemService {
    @Resource
    private SysUserMapper sysUserMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Override
    public String login(String username, String password) {
        String token = "";
        //密码需要客户端加密后传递
        try {
            UserDetails userDetails = loadUserByUsername(username);
            if(!passwordEncoder.matches(password,userDetails.getPassword())){
                Asserts.fail("密码不正确");
            }
            if(!userDetails.isEnabled()){
                Asserts.fail("帐号已被禁用");
            }
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
            token = jwtTokenUtil.generateToken(userDetails);
        } catch (AuthenticationException e) {
            log.error("登录异常:{}", e.getMessage());
        }
        return token;
    }


    @Override
    public UserDetails loadUserByUsername(String username){
        //获取用户信息
        SysUser sysUser = getSysUserByUsername(username);
        if (sysUser != null) {
            List<SysResource> resourceList = new ArrayList<>();
            return new SysUserDetails(sysUser,resourceList);
        }
        throw new UsernameNotFoundException("用户名或密码错误");
    }

    @Override
    public SysUser getSysUserByUsername(String username) {
        //先从缓存中获取数据
        //TODO
        //缓存中没有从数据库中获取
        QueryWrapper<SysUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_name", username);
        SysUser sysUser = sysUserMapper.selectOne(queryWrapper);
        if (sysUser != null) {
            //将数据库中的数据存入缓存中
            //TODO
            return sysUser;
        }
        return null;
    }

//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
}
