package cn.itbeien.quartz.service;


import cn.itbeien.quartz.entity.QuartzLog;
import cn.itbeien.quartz.mapper.QuartzLogMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * @author itbeien
 * 项目网站：https://www.itbeien.cn
 * 公众号：贝恩聊架构
 * 全网同名，欢迎小伙伴们关注
 * Copyright© 2025 itbeien
 */
@Service
public class QuartzLogService {

    @Resource
    private QuartzLogMapper quartzLogMapper ;

    public Integer insert(QuartzLog quartzLog) {
        return quartzLogMapper.insert(quartzLog);
    }
}
