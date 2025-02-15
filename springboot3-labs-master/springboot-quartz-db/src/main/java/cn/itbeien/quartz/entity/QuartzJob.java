package cn.itbeien.quartz.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
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
@Schema(description = "QuartzJob实体类")
public class QuartzJob implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 任务调度参数key
     */
    public static final String JOB_PARAM_KEY = "ITBEIEN_JOB_PARAM_KEY";

    @Schema(description = "任务id")
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    @Schema(description = "Bean名称")
    private String beanName;

    @Schema(description = "执行参数")
    private String params;

    @Schema(description = "Cron表达式")
    private String cronExpres;

    @Schema(description = "任务状态：1正常，2暂停，3删除")
    private Integer state;

    @Schema(description = "备注")
    private String remark;

    @Schema(description = "创建时间")
    private Date createTime;
}