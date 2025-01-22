package cn.itbeien.springsecurity.service;

import cn.itbeien.springsecurity.entity.SysUser;
import com.baomidou.mybatisplus.extension.service.IService;

public interface SysUserService extends IService<SysUser> {
    void saveSysUser(SysUser sysUser);
}
