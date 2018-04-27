package com.knight.upms.dao.model;

import java.io.Serializable;
import lombok.Data;

@Data
public class UpmsPermission implements Serializable {
    /**
     * 编号
     *
     * @mbg.generated
     */
    private Integer permissionId;

    /**
     * 所属系统
     *
     * @mbg.generated
     */
    private Integer systemId;

    /**
     * 所属上级
     *
     * @mbg.generated
     */
    private Integer pid;

    /**
     * 名称
     *
     * @mbg.generated
     */
    private String name;

    /**
     * 类型(1:目录,2:菜单,3:按钮)
     *
     * @mbg.generated
     */
    private Byte type;

    /**
     * 权限值
     *
     * @mbg.generated
     */
    private String permissionValue;

    /**
     * 路径
     *
     * @mbg.generated
     */
    private String uri;

    /**
     * 图标
     *
     * @mbg.generated
     */
    private String icon;

    /**
     * 状态(0:禁止,1:正常)
     *
     * @mbg.generated
     */
    private Byte status;

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

    private static final long serialVersionUID = 1L;
}