package cn.itbeien.security.jwt.util;

import cn.itbeien.security.jwt.exception.BusinessException;

/**
 * @author itbeien
 * 项目网站：https://www.itbeien.cn
 * 公众号：贝恩聊架构
 * 全网同名，欢迎小伙伴们关注
 * Copyright© 2025 itbeien
 */
public class Asserts {
    public static void fail(String message) {
        throw new BusinessException(message);
    }
}
