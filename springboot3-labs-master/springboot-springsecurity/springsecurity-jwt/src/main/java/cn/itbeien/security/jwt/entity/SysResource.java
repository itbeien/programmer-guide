package cn.itbeien.security.jwt.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class SysResource implements Serializable {
    private Long id;

    private Date createTime;

    private String name;

    private String url;

    private String description;
}
