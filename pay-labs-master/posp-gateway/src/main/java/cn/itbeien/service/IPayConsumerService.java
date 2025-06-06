package cn.itbeien.service;

import com.alibaba.fastjson2.JSONObject;

/**
 * @author itbeien
 * 项目网站：https://www.itbeien.cn
 * 公众号：贝恩聊架构
 * 全网同名，欢迎小伙伴们关注
 * 从服务注册中心发现pay服务并进行消费调用
 * Copyright© 2024 itbeien
 */
public interface IPayConsumerService {
    public String callPayService(JSONObject jsonObject);
    public String callFeignPayService(JSONObject jsonObject);

    public String callFeignSignService(JSONObject jsonObject);
}
