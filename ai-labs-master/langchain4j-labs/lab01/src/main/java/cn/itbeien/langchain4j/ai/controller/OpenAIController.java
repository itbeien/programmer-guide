package cn.itbeien.langchain4j.ai.controller;

import dev.langchain4j.model.chat.ChatLanguageModel;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
@RequestMapping("/openai")
public class OpenAIController {
    @Resource
    private ChatLanguageModel chatLanguageModel;

    /*@Value("${langchain4j.open-ai.chat-model.api-key}")
    private String apiKey;

    @Value("${langchain4j.open-ai.chat-model.model-name}")
    private String modelName;*/

   /* @Bean
    public ChatLanguageModel chatLanguageModel() {
        return OpenAiChatModel.builder()
                .apiKey(this.apiKey)
                .modelName(this.modelName)
                .build();
    }*/

    @GetMapping("/hello")
    public String helloAI() {
        return chatLanguageModel.generate("你是哪家公司开发的");
    }
}
