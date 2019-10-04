package com.xddal.moon.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xddal.moon.external.SysUserExternalMapper;
import com.xddal.moon.auto.dao.SysUserMapper;
import com.xddal.moon.auto.entity.SysUser;
import com.xddal.moon.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author xuedong
 */
@Service
public class SysUserServiceImpl implements SysUserService {



    @Autowired(required = false)
    private SysUserMapper sysUserMapper;
    @Autowired(required = false)
    private SysUserExternalMapper sysUserExternalMapper;


    @Override
    public SysUser selectByPrimaryKey(Long id) {
        return sysUserMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<SysUser> selectAll() {
        return sysUserMapper.selectAll();
    }

    @Override
    public Page<SysUser> findByPage(int pageNo, int pageSize) {
        PageHelper.startPage(pageNo, pageSize);
        return sysUserExternalMapper.findByPage();
    }


}
