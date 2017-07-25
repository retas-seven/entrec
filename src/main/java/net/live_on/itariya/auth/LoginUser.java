package net.live_on.itariya.auth;

import java.io.Serializable;

import lombok.Data;

@Data
public class LoginUser implements Serializable {
	private static final long serialVersionUID = 1;

	/** ID */
    private Integer id;

	/** ユーザID */
    private String userId;

    /** 姓 */
    private String familyName;

    /** 名 */
    private String name;

    /** メールアドレス */
    private String mail;
}
