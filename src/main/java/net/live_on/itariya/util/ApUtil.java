package net.live_on.itariya.util;

import java.lang.reflect.InvocationTargetException;
import java.util.UUID;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import net.live_on.itariya.constant.ApConst;
import net.live_on.itariya.exception.SystemException;

public class ApUtil {

	/**
	 * フラッシュスコープにメッセージを設定する
	 * @param msg メッセージ
	 */
	public static void addMessage(String msg) {
    	FacesContext context = FacesContext.getCurrentInstance();
		// アプリケーションエラー画面で表示するメッセージをフラッシュスコープに設定する
    	context.getExternalContext().getFlash().put(ApConst.FLASH_KEY_MESSAGE, msg);
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

}
