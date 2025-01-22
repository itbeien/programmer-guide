package cn.itbeien.springsecurity.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

/**
 * @author itbeien
 * 项目网站：https://www.itbeien.cn
 * 公众号：贝恩聊架构
 * 全网同名，欢迎小伙伴们关注
 * Copyright© 2024 itbeien
 */
@Configuration
//@EnableWebSecurity //springboot项目中可以不用写该配置注解
public class FrameworkSecurityConfig {


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorize -> authorize
                        .anyRequest().authenticated()
                )
                .formLogin(Customizer.withDefaults())
                .httpBasic(Customizer.withDefaults());
        //方便使用swagger测试先关闭csrf攻击防御
        http.csrf(csrf->csrf.disable());
        return http.build();
    }

   /* @Bean
    public UserDetailsService userDetailsService() {
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(User.withDefaultPasswordEncoder().username("itbeien").password("itbeien").roles("USER").build());
        return manager;
    }*/

    /**
     * 创建基于MySQL数据库的用户信息
     * @return
     */
   /* @Bean
    public UserDetailsService userDetailsService() {
        MySQLUserDetailsManager manager = new MySQLUserDetailsManager();
        return manager;
    }*/
}
