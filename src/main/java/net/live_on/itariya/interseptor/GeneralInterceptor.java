package net.live_on.itariya.interseptor;

import java.io.Serializable;
import java.lang.reflect.Method;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

import net.live_on.itariya.constant.ApConst;
import net.live_on.itariya.exception.ApplicationException;
import net.live_on.itariya.exception.SystemException;
import net.live_on.itariya.util.ApUtil;
import net.live_on.itariya.util.Log;

@Interceptor
public class GeneralInterceptor implements Serializable {
	/** SerialVersionUID */
	private static final long serialVersionUID = 1;

	/** コンストラクタ */
    public GeneralInterceptor(){}

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
    		ApUtil.invalidateSession();

    		return null;

    	} catch (SystemException se) {
    		Log.out.error("SystemException発生：" + se.getMessage());
    		ApUtil.invalidateSession();
    		throw se;
    	}

        // 終了ログ
        Log.out.info(clazz.getCanonicalName() + "#" + method.getName() + "() end return：" + ret);

        return ret;
    }
}
