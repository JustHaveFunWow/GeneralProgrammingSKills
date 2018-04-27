package com.knight.upms.dao.model;

import java.io.Serializable;
import lombok.Data;

@Data
public class UpmsUserOrganization implements Serializable {
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

    private static final long serialVersionUID = 1L;
}