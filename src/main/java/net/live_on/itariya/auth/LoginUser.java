package net.live_on.itariya.auth;

import java.io.Serializable;

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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getFamilyName() {
		return familyName;
	}

	public void setFamilyName(String familyName) {
		this.familyName = familyName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

}
