package cn.itbeien.quartz.config;

import cn.itbeien.quartz.job.ReportJob;
import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author itbeien
 * 项目网站：https://www.itbeien.cn
 * 公众号：贝恩聊架构
 * 全网同名，欢迎小伙伴们关注
 * Copyright© 2025 itbeien
 * 任务调度配置类
 */
@Configuration
public class QuartzConfig {
    @Bean
    public JobDetail reportJobDetail() {
        return JobBuilder.newJob(ReportJob.class)
                .withIdentity("reportJob")
                .storeDurably()
                .build();
    }

    @Bean
    public Trigger reportJobTrigger() {
        SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule()
                .withIntervalInSeconds(15)  // 每 15 秒执行一次
                .repeatForever();

        return TriggerBuilder.newTrigger()
                .forJob(reportJobDetail())
                .withIdentity("reportJobTrigger")
                .withSchedule(scheduleBuilder)
                .build();
    }

    /**
     * 基于 Cron 表达式的任务调度
     */
    @Bean
    public Trigger cronJobTrigger() {
        return TriggerBuilder.newTrigger()
                .forJob(reportJobDetail())
                .withIdentity("cronJobTrigger")
                .withSchedule(CronScheduleBuilder.cronSchedule("0 0/1 * 1/1 * ? *"))  // 每分钟执行一次
                .build();
    }
}
