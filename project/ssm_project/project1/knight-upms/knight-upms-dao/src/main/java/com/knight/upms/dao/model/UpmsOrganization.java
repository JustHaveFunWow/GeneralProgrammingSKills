package com.knight.upms.dao.model;

import lombok.Data;

@Data
public class UpmsOrganization {
    /**
     * 编号
     *
     * @mbg.generated
     */
    private Integer organizationId;

    /**
     * 所属上级
     *
     * @mbg.generated
     */
    private Integer pid;

    /**
     * 组织名称
     *
     * @mbg.generated
     */
    private String name;

    /**
     * 组织描述
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
}