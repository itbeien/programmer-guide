package cn.itbeien.quartz.service;

import cn.itbeien.quartz.entity.QuartzJob;
import cn.itbeien.quartz.entity.QuartzLog;
import cn.itbeien.quartz.enums.LogStatus;
import org.quartz.JobExecutionContext;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.lang.reflect.Method;
import java.util.Date;

/**
 * @author itbeien
 * 项目网站：https://www.itbeien.cn
 * 公众号：贝恩聊架构
 * 全网同名，欢迎小伙伴们关注
 * Copyright© 2025 itbeien
 */
public class QuartzRecord extends QuartzJobBean {

    @Override
    protected void executeInternal(JobExecutionContext context) {
        QuartzJob quartzJob = (QuartzJob)context.getMergedJobDataMap().get(QuartzJob.JOB_PARAM_KEY) ;
        QuartzLogService quartzLogService = (QuartzLogService)SpringContextUtil.getBean("quartzLogService") ;
        // 定时器日志记录
        QuartzLog quartzLog = new QuartzLog () ;
        quartzLog.setJobId(quartzJob.getId());
        quartzLog.setBeanName(quartzJob.getBeanName());
        quartzLog.setParams(quartzJob.getParams());
        quartzLog.setCreateTime(new Date());
        long beginTime = System.currentTimeMillis() ;
        try {
            // 加载并执行
            Object target = SpringContextUtil.getBean(quartzJob.getBeanName());
            Method method = target.getClass().getDeclaredMethod("run", String.class);
            method.invoke(target, quartzJob.getParams());
            long executeTime = System.currentTimeMillis() - beginTime;
            quartzLog.setTimes((int)executeTime);
            quartzLog.setState(LogStatus.LOG_SUS.getStatus());
        } catch (Exception e){
            // 异常信息
            long executeTime = System.currentTimeMillis() - beginTime;
            quartzLog.setTimes((int)executeTime);
            quartzLog.setState(LogStatus.LOG_FAIL.getStatus());
            quartzLog.setError(e.getMessage());
        } finally {
            // 保存执行日志
            quartzLogService.insert(quartzLog) ;
        }
    }
}