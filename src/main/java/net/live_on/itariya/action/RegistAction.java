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
import net.live_on.itariya.form.RegistForm;
import net.live_on.itariya.interseptor.GeneralInterceptor;
import net.live_on.itariya.logic.RegistLogic;

/**
 * 登録画面ManagedBean
 */
@Named
@ViewScoped
@Data
public class RegistAction implements Serializable {
	private static final long serialVersionUID = 1;

    /** 登録画面フォーム */
	@Inject
    RegistForm form;

	/** 登録処理ロジック */
	@EJB
	RegistLogic registLogic;

	/**
	 * 新規ユーザ登録処理
	 */
    @Interceptors(GeneralInterceptor.class)
    public String regist() {
    	if (!inputCheck()) {
    		// 元画面に戻る
    		return null;
    	}

    	registLogic.registNewUser();
    	String ret = ApConst.LOGIN_PAGE;
        return ret;
    }

    /**
     * パスワードと確認用パスワードの一致チェック
     * @return チェック結果
     */
    private boolean inputCheck() {
    	boolean ret = true;

    	// パスワードの一致チェック
    	if (!form.getPassword().equals(form.getCofirmPassword())) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "パスワードと確認用パスワードが不一致です。",  "");
            FacesContext.getCurrentInstance().addMessage(null, message);
            ret = false;
    	}

    	// メールアドレス登録済チェック
    	if (registLogic.isRegisteredMail()) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "既に登録されているメールアドレスです。",  "");
            FacesContext.getCurrentInstance().addMessage(null, message);
    		ret = false;
    	}

    	return ret;
    }

    /**
     * ログイン画面に戻る
     */
    @Interceptors(GeneralInterceptor.class)
    public String back() {
    	String ret = ApConst.LOGIN_PAGE;
        return ret;
    }
}
