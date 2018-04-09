package com.knight.ucenter.dao.model;

import java.util.Date;
import lombok.Data;

@Data
public class UcenterUser {
    /**
     * 编号
     *
     * @mbg.generated
     */
    private Integer userId;

    /**
     * 用户账号
     *
     * @mbg.generated
     */
    private String userName;

    /**
     * 密码(MD5(密码+盐))
     *
     * @mbg.generated
     */
    private String password;

    /**
     * 盐
     *
     * @mbg.generated
     */
    private String salt;

    /**
     * 昵称
     *
     * @mbg.generated
     */
    private String nickname;

    /**
     * 性别(0:未知,1:男,2:女)
     *
     * @mbg.generated
     */
    private Byte sex;

    /**
     * 头像
     *
     * @mbg.generated
     */
    private String avatar;

    /**
     * 注册时间
     *
     * @mbg.generated
     */
    private Date createTime;

    /**
     * 注册IP地址
     *
     * @mbg.generated
     */
    private String createIp;

    /**
     * 最后登录时间
     *
     * @mbg.generated
     */
    private Date lastLoginTime;

    /**
     * 最后登录IP地址
     *
     * @mbg.generated
     */
    private String lastLoginIp;
}