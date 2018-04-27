package com.knight.upms.dao.model;

import java.io.Serializable;
import lombok.Data;

@Data
public class UpmsUserRole implements Serializable {
    /**
     * 编号
     *
     * @mbg.generated
     */
    private Integer userRoleId;

    /**
     * 用户编号
     *
     * @mbg.generated
     */
    private Integer userId;

    /**
     * 角色编号
     *
     * @mbg.generated
     */
    private Integer roleId;

    private static final long serialVersionUID = 1L;
}