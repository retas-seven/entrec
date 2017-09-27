package net.live_on.itariya.entrec.util;

import java.lang.reflect.InvocationTargetException;
import java.util.UUID;

import javax.ejb.EJBException;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.persistence.EntityExistsException;
import javax.persistence.OptimisticLockException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;

import net.live_on.itariya.entrec.constant.ApConst;
import net.live_on.itariya.entrec.constant.EjbExceptionCause;
import net.live_on.itariya.entrec.exception.SystemException;

public class ApUtil {

	/**
	 * フラッシュスコープにメッセージを設定する
	 * @param msg メッセージ
	 */
	public static void addMessage(String msg) {
    	FacesContext context = FacesContext.getCurrentInstance();
		// アプリケーションエラー画面で表示するメッセージをフラッシュスコープに設定する
    	context.getExternalContext().getFlash().put(ApConst.FLASH_SCOPE_MESSAGE_KEY, msg);
	}

	/**
	 * セッション情報を破棄する
	 */
	public static void invalidateSession() {
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
    	HttpSession session = (HttpSession) externalContext.getSession(false);

    	// セッション情報を破棄する
    	if (session != null) {
    	    try {
    	        session.invalidate();
    	    } catch (IllegalStateException ise) {
    	    	Log.out.error("セッション情報削除処理で例外発生");
    	    	throw new SystemException(ise);
    	    }
    	}
	}

	/**
	 * origからdestへのプロパティコピー
	 * @param orig コピー元
	 * @param dest コピー先
	 */
	public static void copyProperties(Object dest, Object orig) {
    	try {
    		BeanUtils.copyProperties(dest, orig);
    	} catch (InvocationTargetException | IllegalAccessException e) {
    		throw new SystemException(e);
    	}
	}

	/**
	 * 「次回から自動ログイン」に使用するクッキー値を作成する
	 * @return 作成したクッキー値
	 */
	public static String createAutoLoginCookieValue() {
		return UUID.randomUUID().toString();
	}

	/**
	 * HttpServletRequestを取得する
	 * @return HttpServletRequest
	 */
	public static HttpServletRequest getRequest() {
		FacesContext context = FacesContext.getCurrentInstance();
		ExternalContext exContext = context.getExternalContext();
		HttpServletRequest ret = (HttpServletRequest) exContext.getRequest();
		return ret;
	}

	/**
	 * HttpServletResponseを取得する
	 * @return HttpServletResponse
	 */
	public static HttpServletResponse getResponse() {
		FacesContext context = FacesContext.getCurrentInstance();
		ExternalContext exContext = context.getExternalContext();
		HttpServletResponse ret = (HttpServletResponse) exContext.getResponse();
		return ret;
	}


	/**
	 * EJBExceptionの発生原因を検証する
	 */
	public static EjbExceptionCause parseEjbException(EJBException e) {
		EjbExceptionCause ret = EjbExceptionCause.OTHER;

		if (e.getCausedByException() instanceof OptimisticLockException) {
			ret = EjbExceptionCause.UPDATED;

		} else if (e.getCausedByException() instanceof EntityExistsException) {
			ret = EjbExceptionCause.UNIQUE_CONSTRAINT;
		}

		return ret;
	}
}
