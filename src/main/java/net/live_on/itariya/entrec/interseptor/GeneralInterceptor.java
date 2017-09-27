package net.live_on.itariya.entrec.interseptor;

import java.io.Serializable;
import java.lang.reflect.Method;

import javax.ejb.EJB;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

import net.live_on.itariya.entrec.constant.ApConst;
import net.live_on.itariya.entrec.exception.ApplicationException;
import net.live_on.itariya.entrec.exception.SystemException;
import net.live_on.itariya.entrec.logic.LogoutLogic;
import net.live_on.itariya.entrec.util.ApUtil;
import net.live_on.itariya.entrec.util.Log;

@Interceptor
public class GeneralInterceptor implements Serializable {
	/** SerialVersionUID */
	private static final long serialVersionUID = 1;

	/** コンストラクタ */
    public GeneralInterceptor(){}

	/** ログアウト処理ロジック */
	@EJB
	LogoutLogic logoutLogic;

    /**
     * 処理前後のログ出力、実行した処理のエラーハンドリングを行う
     * @param context 実行コンテキスト
     * @throws Exception
     */
    @AroundInvoke
    public Object intercept(InvocationContext context) throws Exception {
    	Class clazz = context.getMethod().getDeclaringClass();
    	Method method = context.getMethod();
    	Object ret;

        // 業務処理開始ログ
    	Log.out.info(clazz.getCanonicalName() + "#" + method.getName() + "() start");

    	try {
    		ret = context.proceed();

    	} catch (ApplicationException ae) {
    		Log.out.error("ApplicationException発生：" + ae.getMessage());

    		// アプリケーションエラー画面で表示するメッセージをフラッシュスコープに設定する
    		FacesContext facesContext = FacesContext.getCurrentInstance();
    		facesContext.getExternalContext().getFlash().put(ApConst.FLASH_SCOPE_MESSAGE_KEY, ae.getErrMsg());

    		// アプリケーションエラー画面に遷移する
    		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
    		String next = externalContext.getRequestContextPath() + ApConst.APP_ERROR_PAGE;
    		externalContext.redirect(next);
    		logoutLogic.logout(ApUtil.getRequest(), ApUtil.getResponse());
    		// ApUtil.invalidateSession();

    		return null;

    	} catch (SystemException se) {
    		Log.out.error("SystemException発生：" + se.getMessage());
    		logoutLogic.logout(ApUtil.getRequest(), ApUtil.getResponse());
    		// ApUtil.invalidateSession();
    		throw se;
    	}

        // 終了ログ
        Log.out.info(clazz.getCanonicalName() + "#" + method.getName() + "() end return：" + ret);

        return ret;
    }
}
