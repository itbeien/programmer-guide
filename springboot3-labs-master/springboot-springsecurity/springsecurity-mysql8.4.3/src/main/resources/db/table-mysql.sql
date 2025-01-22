DROP TABLE IF EXISTS sys_user;
-- 创建用户表
CREATE TABLE  `sys_user`
(
    `id`        INT(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `user_name` VARCHAR(50)  NOT NULL,
    `password`  VARCHAR(500) NOT NULL
);

-- 创建唯一索引
create unique index `user_name_index` on sys_user (`user_name`);