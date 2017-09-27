package net.live_on.itariya.entrec.controller;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.interceptor.Interceptors;

import net.live_on.itariya.entrec.constant.ApConst;
import net.live_on.itariya.entrec.interseptor.GeneralInterceptor;
import net.live_on.itariya.entrec.logic.LogoutLogic;
import net.live_on.itariya.entrec.util.ApUtil;

/**
 * ログアウト処理ManagedBean
 */
@Named
@SessionScoped
public class LogoutController implements Serializable {
	private static final long serialVersionUID = 1;

	/** ログアウト処理ロジック */
	@EJB
	LogoutLogic logoutLogic;

	/**
	 * ログアウト処理
	 */
    @Interceptors(GeneralInterceptor.class)
    public String logout() {
    	logoutLogic.logout(ApUtil.getRequest(), ApUtil.getResponse());
    	String ret = ApConst.LOGIN_PAGE;
    	return ret;
    }
}
