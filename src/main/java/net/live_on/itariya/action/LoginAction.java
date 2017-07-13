package net.live_on.itariya.action;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.interceptor.Interceptors;

import lombok.Data;
import net.live_on.itariya.form.LoginForm;
import net.live_on.itariya.interseptor.GeneralInterceptor;
import net.live_on.itariya.logic.LoginLogic;
import net.live_on.itariya.util.ApUtil;

/**
 * ログイン画面ManagedBean
 */
@Named
@ViewScoped
@Data
public class LoginAction implements Serializable {
	private static final long serialVersionUID = 1;

    /** ログイン画面フォーム */
	@Inject
    LoginForm form;

	/** ログイン処理ロジック */
	@EJB
	LoginLogic loginLogic;

    @Interceptors(GeneralInterceptor.class)
    public String regist() {
    	String ret = "/screen/regist?faces-redirect=true";
        return ret;
    }

    @Interceptors(GeneralInterceptor.class)
    public String login() {
    	String ret;
    	boolean loginResult = loginLogic.login();

    	if (loginResult) {
    		ret = "/screen/check_list?faces-redirect=true";
    	} else {
    		ApUtil.addMessage("メールアドレスまたはパスワードが無効です。");
    		ret = "/screen/login?faces-redirect=true";
    	}

        return ret;
    }

    @Interceptors(GeneralInterceptor.class)
    public String autoLogin() {
    	String ret = null;
    	boolean isAutoLoginSuccess = loginLogic.cookieLogin(ApUtil.getRequest(), ApUtil.getResponse());

    	if (isAutoLoginSuccess) {
    		ret = "/screen/check_list?faces-redirect=true";
    	}

        return ret;
    }

//    @Interceptors(GeneralInterceptor.class)
//    public String logout() {
//    	loginLogic.logout(ApUtil.getRequest(), ApUtil.getResponse());
//    	String ret = "/screen/login?faces-redirect=true";
//    	return ret;
//    }

//	public LoginForm getForm() {
//		return form;
//	}
//
//	public void setForm(LoginForm form) {
//		this.form = form;
//	}
}
