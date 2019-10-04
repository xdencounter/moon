package com.xddal.moon.auto.dao;

import com.github.pagehelper.Page;
import com.xddal.moon.auto.entity.SysUser;
import java.util.List;

public interface SysUserMapper {
    /**
     * 根据id删除
     *
     * @param id
     * @return
     */
    int deleteByPrimaryKey(Long id);

    /**
     * 插入
     *
     * @param record
     * @return
     */
    int insert(SysUser record);

    /**
     * 根据id查询
     *
     * @param id
     * @return
     */
    SysUser selectByPrimaryKey(Long id);

    /**
     * 查询所有
     *
     * @return
     */

    List<SysUser> selectAll();

    /**
     * 更新记录
     *
     * @param record SysUser
     * @return int
     */

    int updateByPrimaryKey(SysUser record);

//    /**
//     * 分页查询
//     *
//     * @return
//     */
//    Page<SysUser> findByPage();
}