package com.knight.upms.dao.model;

import lombok.Data;

@Data
public class UpmsRolePermission {
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
}