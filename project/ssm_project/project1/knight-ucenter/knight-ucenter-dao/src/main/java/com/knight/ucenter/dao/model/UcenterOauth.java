package com.knight.ucenter.dao.model;

import java.io.Serializable;
import lombok.Data;

@Data
public class UcenterOauth implements Serializable {
    /**
     * 编号
     *
     * @mbg.generated
     */
    private Integer oauthId;

    /**
     * 认证方式名称
     *
     * @mbg.generated
     */
    private String name;

    private static final long serialVersionUID = 1L;
}