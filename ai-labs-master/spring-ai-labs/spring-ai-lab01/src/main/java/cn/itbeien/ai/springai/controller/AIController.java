package cn.itbeien.ai.springai.controller;

import cn.itbeien.ai.springai.service.DeepSeekService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.zhipuai.ZhiPuAiImageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author itbeien
 * 项目网站：https://www.itbeien.cn
 * 公众号：贝恩聊架构
 * 全网同名，欢迎小伙伴们关注
 * Java/AI学习社群
 * Copyright© 2025 itbeien
 */
@RestController
@RequestMapping("/ai")
@Slf4j
public class AIController {

    private final ChatClient chatClient;

    @Autowired
    private DeepSeekService deepSeekService;

    public AIController(ChatClient.Builder chatClientBuilder) {
        this.chatClient = chatClientBuilder.build();
    }

    @GetMapping("/hello")
    String generation(@RequestParam("userInput") String userInput) {
        return this.chatClient.prompt()
                .user(userInput)
                .call()
                .content();
    }

    @GetMapping("/deepseek")
    String deepseek(@RequestParam("userInput") String userInput) {
        try {
            return deepSeekService.askDeepSeek(userInput);
        }catch (Exception e){
            log.error("调用DeepSeek失败", e);
            return "error";
        }
    }

}
