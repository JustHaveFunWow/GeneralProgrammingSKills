package com.knight.ucenter.dao.model;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

@Data
public class UcenterUserOauth implements Serializable {
    /**
     * 编号
     *
     * @mbg.generated
     */
    private Integer userOauthId;

    /**
     * 帐号编号
     *
     * @mbg.generated
     */
    private Integer userId;

    /**
     * 认证方式编号
     *
     * @mbg.generated
     */
    private Integer oauthId;

    /**
     * 绑定状态(0:解绑,1:绑定)
     *
     * @mbg.generated
     */
    private Byte status;

    /**
     * 创建时间
     *
     * @mbg.generated
     */
    private Date createTime;

    /**
     * 第三方ID
     *
     * @mbg.generated
     */
    private byte[] openId;

    private static final long serialVersionUID = 1L;
}