package nimdanoob.knight.web.domain.model;

import java.util.Date;
import lombok.Data;

@Data
public class UserDetails {
    /**
     * 编号
     *
     * @mbg.generated
     */
    private Integer userId;

    /**
     * 个性签名
     *
     * @mbg.generated
     */
    private String signature;

    /**
     * 真实姓名
     *
     * @mbg.generated
     */
    private String realName;

    /**
     * 出生日期
     *
     * @mbg.generated
     */
    private Date birthday;

    /**
     * 帐号安全问题
     *
     * @mbg.generated
     */
    private String question;

    /**
     * 帐号安全答案
     *
     * @mbg.generated
     */
    private String answer;
}