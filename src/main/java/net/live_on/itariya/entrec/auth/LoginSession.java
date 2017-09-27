package net.live_on.itariya.entrec.auth;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import lombok.Data;

@Named
@SessionScoped
@Data
/**
 * ログインユーザのセッション情報
 */
public class LoginSession implements Serializable {
	private static final long serialVersionUID = 1;

	/** ユーザ情報 */
    private LoginUser loginUser;
}
