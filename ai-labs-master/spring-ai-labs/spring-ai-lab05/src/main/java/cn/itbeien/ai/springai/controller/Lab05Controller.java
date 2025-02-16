package cn.itbeien.ai.springai.controller;

import org.springframework.ai.chat.messages.AssistantMessage;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.messages.SystemMessage;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
public class Lab05Controller {

    private final OpenAiChatModel chatModel;

    // 自定义人设，来与用户进行角色扮演 提示词
    private final static String systemPrompt = "请你扮演程序员编程助手，且只能回答与计算机科学、编程、算法或其他技术相关的问题。对于非编程类的问题，你无法提供回答";

    // 历史消息列表
    static List<Message> historyMessage = new ArrayList<>(List.of(new SystemMessage(systemPrompt)));
    // 历史消息列表的最大长度
    static int maxLen = 15;

    public Lab05Controller(OpenAiChatModel chatModel) {
        this.chatModel = chatModel;
    }

    /**
     * 自定义人设，来与用户进行角色扮演
     * @param prompt
     * @return
     */
    @GetMapping("/content")
    public String content(@RequestParam("prompt") String prompt) {
        // 用户输入的文本是UserMessage
        historyMessage.add(new UserMessage(prompt));
        // 发给AI前对历史消息对列的长度进行检查
        if(historyMessage.size() > maxLen){
            historyMessage = historyMessage.subList(historyMessage.size()-maxLen-1,historyMessage.size());
            // 确保第一个是SystemMessage
            historyMessage.add(0,new SystemMessage(systemPrompt));
        }
        // 获取AssistantMessage
        ChatResponse chatResponse = chatModel.call(new Prompt(historyMessage));
        AssistantMessage assistantMessage = chatResponse.getResult().getOutput();
        // 将AI回复的消息放到历史消息列表中
        historyMessage.add(assistantMessage);
        return assistantMessage.getText();
    }

    /**
     * Prompts模板语法
     * @param language
     * @return
     */
    @GetMapping("/template")
    public String template(@RequestParam("prompt") String language) {
        // 提示词
        final String template = "请问{language}是谁发明的？什么时候发布的？优缺点是什么？";
        PromptTemplate promptTemplate = new PromptTemplate(template);
        // 动态地将language填充进去
        Prompt prompt = promptTemplate.create(Map.of("language", language));

        ChatResponse chatResponse = chatModel.call(prompt);

        AssistantMessage assistantMessage = chatResponse.getResult().getOutput();
        return assistantMessage.getText();
    }
}
