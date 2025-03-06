DROP TABLE IF EXISTS sys_user_jsqlparser;

CREATE TABLE sys_user_jsqlparser
(
    id BIGINT NOT NULL COMMENT '主键ID',
    name VARCHAR(30) NULL DEFAULT NULL COMMENT '姓名',
    age INT NULL DEFAULT NULL COMMENT '年龄',
    email VARCHAR(50) NULL DEFAULT NULL COMMENT '邮箱',
    create_user VARCHAR(30) NULL DEFAULT NULL COMMENT '创建人',
    PRIMARY KEY (id)
);