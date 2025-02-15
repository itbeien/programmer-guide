package cn.itbeien.ai.springai.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.io.IOException;

/**
 * @author itbeien
 * 项目网站：https://www.itbeien.cn
 * 公众号：贝恩聊架构
 * 全网同名，欢迎小伙伴们关注
 * Java/AI学习社群
 * Copyright© 2025 itbeien
 */
@Service
@Slf4j
public class DeepSeekService {

    @Value("${spring.ai.deepseek.api-url}")
    private String API_URL;

    @Value("${spring.ai.deepseek.api-key}")
    private String API_KEY;

    public String askDeepSeek(String question) throws IOException {
        CloseableHttpClient client = HttpClients.createDefault();

        // 创建 HTTP POST 请求
        HttpPost request = new HttpPost(API_URL);
        request.setHeader("Authorization", "Bearer " + API_KEY);
        request.setHeader("Content-Type", "application/json; charset=UTF-8");

        // 动态构建请求体
        String requestBody = String.format(
                "{\"model\": \"deepseek-chat\", \"messages\": [{\"role\": \"user\", \"content\": \"%s\"}], \"stream\": false}",
                question
        );
        log.info(requestBody);
        request.setEntity(new StringEntity(requestBody,"UTF-8"));

        // 发送请求并获取响应
        try (CloseableHttpResponse response = client.execute(request)) {
            // 返回响应内容
            return EntityUtils.toString(response.getEntity());
        }
    }
}
