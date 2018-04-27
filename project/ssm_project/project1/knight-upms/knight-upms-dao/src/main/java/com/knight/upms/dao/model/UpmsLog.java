package com.knight.upms.dao.model;

import java.io.Serializable;
import lombok.Data;

@Data
public class UpmsLog implements Serializable {
    /**
     * 编号
     *
     * @mbg.generated
     */
    private Integer logId;

    /**
     * 操作描述
     *
     * @mbg.generated
     */
    private String description;

    /**
     * 操作用户
     *
     * @mbg.generated
     */
    private String username;

    /**
     * 操作时间
     *
     * @mbg.generated
     */
    private Long startTime;

    /**
     * 消耗时间
     *
     * @mbg.generated
     */
    private Integer spendTime;

    /**
     * 根路径
     *
     * @mbg.generated
     */
    private String basePath;

    /**
     * URI
     *
     * @mbg.generated
     */
    private String uri;

    /**
     * URL
     *
     * @mbg.generated
     */
    private String url;

    /**
     * 请求类型
     *
     * @mbg.generated
     */
    private String method;

    /**
     * 用户标识
     *
     * @mbg.generated
     */
    private String userAgent;

    /**
     * IP地址
     *
     * @mbg.generated
     */
    private String ip;

    /**
     * 权限值
     *
     * @mbg.generated
     */
    private String permissions;

    private String parameter;

    private String result;

    private static final long serialVersionUID = 1L;
}