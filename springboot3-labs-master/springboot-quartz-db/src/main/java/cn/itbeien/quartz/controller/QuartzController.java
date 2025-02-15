package cn.itbeien.quartz.controller;

import cn.itbeien.quartz.entity.QuartzJob;
import cn.itbeien.quartz.service.QuartzJobService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

/**
 * @author itbeien
 * 项目网站：https://www.itbeien.cn
 * 公众号：贝恩聊架构
 * 全网同名，欢迎小伙伴们关注
 * Copyright© 2025 itbeien
 */
@RestController
@RequestMapping ("/quartz")
public class QuartzController {
    @PostMapping("/test")
    public String test(){
        return "test";
    }


    @Resource
    private QuartzJobService quartzJobService ;

    @Operation(summary = "查询定时任务")
    @GetMapping("/job/{id}")
    public QuartzJob getById(@PathVariable Integer id){
        return quartzJobService.getById(id) ;
    }

    @Operation(summary = "新增定时任务")
    @PostMapping("/job")
    public Integer insert(@RequestBody QuartzJob quartzJob){
        return quartzJobService.insert(quartzJob) ;
    }

    @Operation(summary = "更新定时任务")
    @PutMapping("/job")
    public Integer update(@RequestBody QuartzJob quartzJob){
        return quartzJobService.update(quartzJob) ;
    }

    @Operation(summary = "停止定时任务")
    @PutMapping("/job/pause/{id}")
    public void pause(@PathVariable("id") Integer id) {
        quartzJobService.pause(id);
    }

    @Operation(summary = "恢复定时任务")
    @PutMapping("/job/resume/{id}")
    public void resume(@PathVariable("id") Integer id) {
        quartzJobService.resume(id) ;
    }

    @Operation(summary = "执行定时任务一次")
    @GetMapping("/job/processOne/{id}")
    public void processOne(@PathVariable("id") Integer id) {
        quartzJobService.runOnce(id) ;
    }
}
