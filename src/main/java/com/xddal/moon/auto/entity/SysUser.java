package com.xddal.moon.auto.entity;

import lombok.*;

import java.io.Serializable;
import java.util.Date;

@Data
public class SysUser implements Serializable {
    private Long id;

    private String user_name;

    private String password;

    private String mobile_phone;

    private String real_name;

    private String sex;

    private String email;

    private String qq;

    private Byte age;

    private Integer user_status;

    private String telephone;

    private String this_login_ip;

    private Date last_login_time;

    private Date login_time;

    private Date gmt_create;

    private Date gmt_modified;

    private static final long serialVersionUID = 1L;

}