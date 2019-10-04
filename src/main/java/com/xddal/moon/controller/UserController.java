package com.xddal.moon.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.xddal.moon.auto.entity.SysUser;
import com.xddal.moon.common.page.PageInfo;
import com.xddal.moon.common.response.CommonResult;
import com.xddal.moon.common.response.JsonResult;
import com.xddal.moon.common.response.Restful;
import com.xddal.moon.service.SysUserService;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 用户信息管理
 *
 * @author xuedong
 */
@RestController
@RequestMapping("/api")
public class UserController {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private SysUserService sysUserService;

    /**
     * 查询单个用户
     *
     * @param jsonObject JSONObject
     * @return Restful
     */
    @PostMapping("/userId")
    @ApiOperation(value = "查询单个用户",notes = "单个查询用户")
    public Restful getById(@RequestBody JSONObject jsonObject) {
        try {
            return Restful.isOk().data(sysUserService.selectByPrimaryKey(jsonObject.getLong("id")));
        } catch (Exception e) {
            LOGGER.debug(e.getMessage());
            return Restful.isFail(e);
        }
    }

    /**
     * 查询所有用户信息
     *
     * @return Restful
     */
    @GetMapping("/user")
    @ApiOperation(value = "查询用户列表",notes = "查询用户列表")
    public Restful list() {
        try {
            return Restful.isOk().data(sysUserService.selectAll());
        } catch (Exception e) {
            LOGGER.debug(e.getMessage());
            return Restful.isFail(e);
        }

    }

    @GetMapping("/user1")
    @ApiOperation(value = "查询用户列表1",notes = "查询用户列表1")
    public JsonResult listResult() {
        JsonResult jsonResult = new JsonResult();
        List<SysUser> sysUsers = sysUserService.selectAll();
        jsonResult.setData(sysUsers);
        jsonResult.setState(JsonResult.SUCCESS);
        return jsonResult;

    }

    /**
     * 分页查询
     *
     * @return
     */
    @PostMapping("/page")
    @ApiOperation(value = "分页查询",notes = "分页查询")
    public Restful page(@RequestBody JSONObject jsonObject) {
        try {

//            Page<SysUser> persons = sysUserService.findByPage(jsonObject.getInteger("page"), jsonObject.getInteger("size"));
            // 需要把Page包装成PageInfo对象才能序列化。该插件也默认实现了一个PageInfo
            Page<SysUser> persons = sysUserService.findByPage(jsonObject.getInteger("page"), jsonObject.getInteger("size"));
            PageInfo<SysUser> pageInfo = new PageInfo<>(persons);
            LOGGER.debug(pageInfo.toString());
            LOGGER.debug(JSON.toJSONString(pageInfo));
            return Restful.isOk().data(pageInfo);
        } catch (Exception e) {
            LOGGER.debug(e.getMessage());
            return Restful.isFail(e);
        }

    }

    /**
     *
     * @param jsonObject JSONObjecth
     * @return JsonResult
     */
    @PostMapping("/page1")
    @ApiOperation(value = "分页查询1",notes = "分页查询1")
    public JsonResult list(@RequestBody JSONObject jsonObject) {
        Page<SysUser> persons = sysUserService.findByPage(jsonObject.getInteger("page"), jsonObject.getInteger("size"));
        PageInfo<SysUser> pageInfo = new PageInfo<>(persons);
        JsonResult jsonResult = new JsonResult();
        jsonResult.setData(pageInfo);
        jsonResult.setMessage("成功");
        return jsonResult;
    }



    /**
     * 分页查询
     * @param jsonObject JSONObject
     * @return CommonResult
     * @RequestMapping(value = "/list", method = RequestMethod.POST)
     */
    @PostMapping("/list")
    @ApiOperation(value = "分页查询2",notes = "分页查询2")
    public CommonResult<PageInfo> getPageList(@RequestBody JSONObject jsonObject) {
        int pageNo = jsonObject.getInteger("page");
        int pageSize = jsonObject.getInteger("size");
        Page<SysUser> persons = sysUserService.findByPage(pageNo, pageSize);
        PageInfo<SysUser> pageInfo = new PageInfo<>(persons);
        return CommonResult.success(pageInfo);
    }


    @PostMapping("/getList")
    public CommonResult getList(@RequestBody JSONObject jsonObject){

        PageInfo<SysUser> pageInfo = new PageInfo<>();

        return CommonResult.success(pageInfo);
    }

}
