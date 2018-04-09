package com.knight.upms.dao.model;

import lombok.Data;

@Data
public class UpmsUserOrganization {
    /**
     * 编号
     *
     * @mbg.generated
     */
    private Integer userOrganizationId;

    /**
     * 用户编号
     *
     * @mbg.generated
     */
    private Integer userId;

    /**
     * 组织编号
     *
     * @mbg.generated
     */
    private Integer organizationId;
}