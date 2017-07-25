package net.live_on.itariya.form;

import java.io.Serializable;

import javax.faces.view.ViewScoped;

import lombok.Data;
import net.live_on.itariya.validator.NameNotEmpty;

@ViewScoped
@Data
public class LoginForm implements Serializable {
	private static final long serialVersionUID = 1;

    /** メールアドレス */
	@NameNotEmpty(name = "メールアドレス")
    private String mail;

    /** パスワード */
	@NameNotEmpty(name = "パスワード")
    private String password;

    /** 自動ログイン */
    private boolean autoLogin;
}

