package com.xddal.moon.auto.entity;

import lombok.*;

import java.io.Serializable;

@Data
public class Attachment implements Serializable {
    private Long id;

    private String attachName;

    private Byte attachType;

    private String originalName;

    private String gmtCreate;

    private String gmtModified;

    private Byte attachSort;

    private Long specificationId;

    private static final long serialVersionUID = 1L;

}