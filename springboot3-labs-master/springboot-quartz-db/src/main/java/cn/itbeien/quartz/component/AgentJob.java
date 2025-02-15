package cn.itbeien.quartz.component;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author itbeien
 * 项目网站：https://www.itbeien.cn
 * 公众号：贝恩聊架构
 * 全网同名，欢迎小伙伴们关注
 * Copyright© 2025 itbeien
 */
@Component
@Slf4j
public class AgentJob implements JobService{
    @Override
    public void run(String params) {
        log.info(" ========  agent-job-params:{}  ========",params);
    }
}
