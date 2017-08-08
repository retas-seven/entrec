package net.live_on.itariya.interseptor;

import java.io.Serializable;
import java.lang.reflect.Method;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

import net.live_on.itariya.exception.ApplicationException;
import net.live_on.itariya.exception.SystemException;
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

    	} catch (ApplicationException e) {
    		Log.out.error("ApplicationException発生：" + e.getMessage());
    		throw e;

    	} catch (SystemException e) {
    		Log.out.error("SystemException発生：" + e.getMessage());
    		throw e;
    	}

        // 終了ログ
        Log.out.info(clazz.getCanonicalName() + "#" + method.getName() + "() end return：" + ret);

        return ret;
    }
}
