package com.knight.upms.dao.model;

import java.io.Serializable;
import lombok.Data;

@Data
public class UpmsRole implements Serializable {
    /**
     * 编号
     *
     * @mbg.generated
     */
    private Integer roleId;

    /**
     * 角色名称
     *
     * @mbg.generated
     */
    private String name;

    /**
     * 角色标题
     *
     * @mbg.generated
     */
    private String title;

    /**
     * 角色描述
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

    private static final long serialVersionUID = 1L;
}