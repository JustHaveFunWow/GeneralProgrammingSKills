package com.knight.upms.dao.model;

import java.io.Serializable;
import lombok.Data;

@Data
public class UpmsRolePermission implements Serializable {
    /**
     * 编号
     *
     * @mbg.generated
     */
    private Integer rolePermissionId;

    /**
     * 角色编号
     *
     * @mbg.generated
     */
    private Integer roleId;

    /**
     * 权限编号
     *
     * @mbg.generated
     */
    private Integer permissionId;

    private static final long serialVersionUID = 1L;
}