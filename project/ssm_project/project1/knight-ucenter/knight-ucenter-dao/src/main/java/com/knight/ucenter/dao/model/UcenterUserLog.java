package com.knight.ucenter.dao.model;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

@Data
public class UcenterUserLog implements Serializable {
    /**
     * 编号
     *
     * @mbg.generated
     */
    private Integer userLogId;

    /**
     * 用户编号
     *
     * @mbg.generated
     */
    private Integer userId;

    /**
     * 操作IP地址
     *
     * @mbg.generated
     */
    private String ip;

    /**
     * 操作时间
     *
     * @mbg.generated
     */
    private Date createTime;

    /**
     * 内容
     *
     * @mbg.generated
     */
    private byte[] content;

    /**
     * 操作环境
     *
     * @mbg.generated
     */
    private byte[] agent;

    private static final long serialVersionUID = 1L;
}