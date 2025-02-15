CREATE TABLE `quartz_job` (
                              `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '任务id',
                              `bean_name` varchar(200) DEFAULT NULL COMMENT 'SpringBean名称',
                              `params` varchar(2000) DEFAULT NULL COMMENT '执行参数',
                              `cron_expres` varchar(100) DEFAULT NULL COMMENT 'cron表达式',
                              `state` int(1) DEFAULT NULL COMMENT '任务状态：1正常，2暂停，3删除',
                              `remark` varchar(100) DEFAULT NULL COMMENT '备注',
                              `create_time` datetime DEFAULT NULL COMMENT '创建时间',
                              PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='任务列表';

CREATE TABLE `quartz_log` (
                              `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '任务日志id',
                              `job_id` int(11) NOT NULL COMMENT '任务id',
                              `bean_name` varchar(200) DEFAULT NULL COMMENT 'SpringBean名称',
                              `params` varchar(2000) DEFAULT NULL COMMENT '执行参数',
                              `state` tinyint(4) NOT NULL COMMENT '任务状态：1成功，2失败',
                              `error` varchar(2000) DEFAULT NULL COMMENT '失败信息',
                              `times` int(11) NOT NULL COMMENT '耗时(单位：毫秒)',
                              `create_time` datetime DEFAULT NULL COMMENT '创建时间',
                              PRIMARY KEY (`id`),
                              KEY `job_id` (`job_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='任务日志';

INSERT INTO `quartz_job` (`id`, `bean_name`, `params`, `cron_expres`, `state`, `remark`, `create_time`) VALUES (1, 'merchantJob', 'merchantJob-params', '0 /2 * * * ? *', 2, '商户报表任务', '2025-02-04 21:25:15');
INSERT INTO `quartz_job` (`id`, `bean_name`, `params`, `cron_expres`, `state`, `remark`, `create_time`) VALUES (2, 'agentJob', 'agentJob-params-update', '0 /3 * * * ? *', 2, '代理商结算任务', '2025-02-04 21:25:15');

