package com.xddal.moon.external;

import com.github.pagehelper.Page;
import com.xddal.moon.auto.dao.SysUserMapper;
import com.xddal.moon.auto.entity.SysUser;


public interface SysUserExternalMapper extends SysUserMapper {

    /**
     * 分页查询
     *
     * @return
     */
    Page<SysUser> findByPage();
}