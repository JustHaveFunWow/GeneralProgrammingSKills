package com.knight.upms.dao.model;

import lombok.Data;

@Data
public class UpmsSystem {
    /**
     * 编号
     *
     * @mbg.generated
     */
    private Integer systemId;

    /**
     * 图标
     *
     * @mbg.generated
     */
    private String icon;

    /**
     * 背景
     *
     * @mbg.generated
     */
    private String banner;

    /**
     * 主题
     *
     * @mbg.generated
     */
    private String theme;

    /**
     * 根目录
     *
     * @mbg.generated
     */
    private String basepath;

    /**
     * 状态(-1:黑名单,1:正常)
     *
     * @mbg.generated
     */
    private Byte status;

    /**
     * 系统名称
     *
     * @mbg.generated
     */
    private String name;

    /**
     * 系统标题
     *
     * @mbg.generated
     */
    private String title;

    /**
     * 系统描述
     *
     * @mbg.generated
     */
    private String description;

    /**
     * 创建时间
     *
     * @mbg.generated
     */
    private Long ctime;

    /**
     * 排序
     *
     * @mbg.generated
     */
    private Long orders;
}