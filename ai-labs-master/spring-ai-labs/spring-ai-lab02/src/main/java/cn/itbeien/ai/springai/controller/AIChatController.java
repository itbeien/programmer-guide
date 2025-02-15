package cn.itbeien.ai.springai.controller;

import jakarta.annotation.Resource;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.model.StreamingChatModel;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

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
public class AIChatController {

    private final OpenAiChatModel chatModel;
    private final StreamingChatModel streamingChatModel;

    @Autowired
    public AIChatController(OpenAiChatModel chatModel,StreamingChatModel streamingChatModel) {
        this.chatModel = chatModel;
        this.streamingChatModel = streamingChatModel;
    }

    @GetMapping("/hello")
    String generation(@RequestParam("prompt") String userInput) {
        return this.chatModel.call(userInput);
    }

    /**
     * 流式调用 将produces声明为文本事件流
     * @param message
     * @return
     */
    @GetMapping(value = "/stream",produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> stream(@RequestParam("prompt")String message){
        // 将流中的内容按顺序返回
        /*Prompt prompt = new Prompt(new UserMessage(message));
        return this.chatModel.stream(prompt);*/
        return this.streamingChatModel.stream(new UserMessage(message));
    }

}
