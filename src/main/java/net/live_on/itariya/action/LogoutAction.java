package net.live_on.itariya.action;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.interceptor.Interceptors;

import net.live_on.itariya.interseptor.GeneralInterceptor;
import net.live_on.itariya.logic.LogoutLogic;
import net.live_on.itariya.util.ApUtil;

/**
 * ログアウト処理ManagedBean
 */
@Named
@SessionScoped
public class LogoutAction implements Serializable {
	private static final long serialVersionUID = 1;

	/** ログイン処理ロジック */
	@EJB
	LogoutLogic logoutLogic;

    @Interceptors(GeneralInterceptor.class)
    public String logout() {
    	logoutLogic.logout(ApUtil.getRequest(), ApUtil.getResponse());
    	String ret = "/screen/login?faces-redirect=true";
    	return ret;
    }
}
