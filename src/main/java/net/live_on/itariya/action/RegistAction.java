package net.live_on.itariya.action;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.interceptor.Interceptors;

import lombok.Data;
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

    @Interceptors(GeneralInterceptor.class)
    public String regist() {
    	registLogic.registNewUser();
    	String ret = "/screen/login?faces-redirect=true";
        return ret;
    }

    @Interceptors(GeneralInterceptor.class)
    public String back() {
    	String ret = "/screen/login?faces-redirect=true";
        return ret;
    }

//	public RegistForm getForm() {
//		return form;
//	}
//
//	public void setForm(RegistForm form) {
//		this.form = form;
//	}
//
//	public RegistLogic getRegistLogic() {
//		return registLogic;
//	}
//
//	public void setRegistLogic(RegistLogic registLogic) {
//		this.registLogic = registLogic;
//	}
}
