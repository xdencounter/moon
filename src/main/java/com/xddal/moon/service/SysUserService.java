package com.xddal.moon.service;

import com.github.pagehelper.Page;
import com.xddal.moon.auto.entity.SysUser;

import java.util.List;

public interface SysUserService {

    SysUser selectByPrimaryKey(Long id);

    List<SysUser> selectAll();


    /**
     * 分页查询
     *
     * @param pageNo   页号
     * @param pageSize 每页显示记录数
     * @return
     */
    Page<SysUser> findByPage(int pageNo, int pageSize);

}
