package cn.itbeien.quartz.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author itbeien
 * 项目网站：https://www.itbeien.cn
 * 公众号：贝恩聊架构
 * 全网同名，欢迎小伙伴们关注
 * Copyright© 2025 itbeien
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuartzLog {

    @TableId(value = "id",type = IdType.AUTO)
    private Long id;

    private Integer jobId;

    private String beanName;

    private String params;

    private Integer state;

    private String error;

    private Integer times;

    private Date createTime;

}