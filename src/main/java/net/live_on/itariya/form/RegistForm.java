package net.live_on.itariya.form;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.validation.constraints.Pattern;

import lombok.Data;
import net.live_on.itariya.validator.NameNotEmpty;


@ViewScoped
@Data
public class RegistForm implements Serializable {
	private static final long serialVersionUID = 1;
    /** メールアドレス */
    @NameNotEmpty(name = "メールアドレス")
    @Pattern(regexp = "^([\"*+!.&#$|\\'\\\\%\\/0-9a-z^_`{}=?~:-]*)@(([0-9a-z-]+\\.)+[0-9a-z]{2,})$", message = "{pattern.email}")
    private String mail;

    /** パスワード */
    @NameNotEmpty(name = "パスワード")
    private String password;

    /** 確認用パスワード（一致チェック用） */
    @NameNotEmpty(name = "確認用パスワード")
    private String cofirmPassword;
}

