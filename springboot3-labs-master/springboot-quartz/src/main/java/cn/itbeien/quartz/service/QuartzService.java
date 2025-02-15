package cn.itbeien.quartz.service;

import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 贝恩聊架构
 * 动态管理quartz任务
 * 动态的添加、暂停、恢复和删除任务
 * 项目网站：https://www.itbeien.cn
 * 全网同名，欢迎关注
 */
@Service
@Slf4j
public class QuartzService {
    private final Scheduler scheduler;

    @Autowired
    public QuartzService(Scheduler scheduler) {
        this.scheduler = scheduler;
    }

    public void scheduleJob(JobDetail jobDetail, Trigger trigger) {
        try {
            scheduler.scheduleJob(jobDetail, trigger);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    public void pauseJob(String jobName, String jobGroup) {
        try {
            JobKey jobKey = new JobKey(jobName, jobGroup);
            scheduler.pauseJob(jobKey);
            System.out.println("job paused: " + jobKey);
        } catch (SchedulerException e) {
            log.error(e.getMessage());
        }
    }

    public void resumeJob(String jobName, String jobGroup) {
        try {
            JobKey jobKey = new JobKey(jobName, jobGroup);
            scheduler.resumeJob(jobKey);
            System.out.println("job resumed: " + jobKey);
        } catch (SchedulerException e) {
            log.error(e.getMessage());
        }
    }

    public void deleteJob(String jobName, String jobGroup) {
        try {
            JobKey jobKey = new JobKey(jobName, jobGroup);
            boolean deleted = scheduler.deleteJob(jobKey);
            if (deleted) {
                System.out.println("job deleted: " + jobKey);
            } else {
                System.out.println("job not found: " + jobKey);
            }
        } catch (SchedulerException e) {
            log.error(e.getMessage());
        }
    }
}
