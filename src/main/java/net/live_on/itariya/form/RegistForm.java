package net.live_on.itariya.form;

import java.io.Serializable;

import javax.faces.view.ViewScoped;

import lombok.Data;
import net.live_on.itariya.validator.NameNotEmpty;


@ViewScoped
@Data
public class RegistForm implements Serializable {
	private static final long serialVersionUID = 1;

    /** メールアドレス */
    @NameNotEmpty(name = "メールアドレス")
    private String mail;

    /** パスワード */
    @NameNotEmpty(name = "パスワード")
    private String password;

    /** 確認用パスワード（一致チェック用） */
    @NameNotEmpty(name = "確認用パスワード")
    private String cofirmPassword;


//	public String getMail() {
//		return mail;
//	}
//
//	public void setMail(String mail) {
//		this.mail = mail;
//	}
//
//	public String getPassword() {
//		return password;
//	}
//
//	public void setPassword(String password) {
//		this.password = password;
//	}
//
//	public String getCofirmPassword() {
//		return cofirmPassword;
//	}
//
//	public void setCofirmPassword(String cofirmPassword) {
//		this.cofirmPassword = cofirmPassword;
//	}
}

