package com.knight.upms.dao.model;

import lombok.Data;

@Data
public class UpmsUserPermission {
    /**
     * 编号
     *
     * @mbg.generated
     */
    private Integer userPermissionId;

    /**
     * 用户编号
     *
     * @mbg.generated
     */
    private Integer userId;

    /**
     * 权限编号
     *
     * @mbg.generated
     */
    private Integer permissionId;

    /**
     * 权限类型(-1:减权限,1:增权限)
     *
     * @mbg.generated
     */
    private Byte type;
}