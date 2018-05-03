package com.knight.ids.dao.model;

import java.io.Serializable;
import lombok.Data;

@Data
public class KnightIds implements Serializable {
    /**
     * 编号
     *
     * @mbg.generated
     */
    private Integer idsSystemId;

    /**
     * 业务名称
     *
     * @mbg.generated
     */
    private String idsBusinessName;

    /**
     * 业务id
     *
     * @mbg.generated
     */
    private Integer idsBusinessId;

    private static final long serialVersionUID = 1L;
}