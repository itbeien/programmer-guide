package cn.itbeien.langchain4j.ai.test;

import dev.langchain4j.model.chat.ChatLanguageModel;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author itbeien
 * 项目网站：https://www.itbeien.cn
 * 公众号：贝恩聊架构
 * 全网同名，欢迎小伙伴们关注
 * Java/AI学习社群
 * Copyright© 2025 itbeien
 */
@SpringBootTest
@Slf4j
public class LangChain4jLab01Test {
    @Resource
    private ChatLanguageModel chatLanguageModel;
    @Test
    public void test() {
        String answer = chatLanguageModel.generate("你好,你是哪家公司开发的");
        log.info("answer: {}", answer);
    }
}
