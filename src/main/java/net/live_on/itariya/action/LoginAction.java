package net.live_on.itariya.action;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.interceptor.Interceptors;

import lombok.Data;
import net.live_on.itariya.constant.ApConst;
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

	/**
	 * 新規登録画面に遷移する
	 */
    @Interceptors(GeneralInterceptor.class)
    public String regist() {
    	String ret = ApConst.REGIST_PAGE;
        return ret;
    }

    /**
     * ログイン処理
     */
    @Interceptors(GeneralInterceptor.class)
    public String login() {
    	String ret;
    	boolean loginResult = loginLogic.login();

    	if (loginResult) {
    		ret = ApConst.CHECKLIST_PAGE;
    	} else {
    		// アプリケーションエラー動作確認用
    		// throw new ApplicationException("メールアドレスまたはパスワードが無効です。");

            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "メールアドレスまたはパスワードが無効です。",  "");
            FacesContext.getCurrentInstance().addMessage(null, message);

            // 元画面に戻る
            return null;
    	}

        return ret;
    }

    /**
     * 自動ログイン処理
     */
    @Interceptors(GeneralInterceptor.class)
    public String autoLogin() {
    	String ret = null;
    	boolean isAutoLoginSuccess = loginLogic.cookieLogin(ApUtil.getRequest(), ApUtil.getResponse());

    	if (isAutoLoginSuccess) {
    		ret = ApConst.CHECKLIST_PAGE;
    	}

        return ret;
    }
}
