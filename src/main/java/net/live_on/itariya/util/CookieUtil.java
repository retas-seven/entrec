package net.live_on.itariya.util;

import javax.faces.context.FacesContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieUtil {

	public static void setCookie(String name, String value, int expiry, HttpServletRequest request, HttpServletResponse response) {
	    FacesContext facesContext = FacesContext.getCurrentInstance();
	    Cookie cookie = null;
	    Cookie[] userCookies = request.getCookies();

	    if (userCookies != null && userCookies.length > 0 ) {
	        for (int i = 0; i < userCookies.length; i++) {
	            if (userCookies[i].getName().equals(name)) {
	                cookie = userCookies[i];
	                break;
	            }
	        }
	    }

	    if (cookie != null) {
	        cookie.setValue(value);
	    } else {
	        cookie = new Cookie(name, value);
	        cookie.setPath(request.getContextPath());
	    }

	    cookie.setMaxAge(expiry);
	    response.addCookie(cookie);
	}

	public static Cookie getCookie(String name, HttpServletRequest request) {
	    Cookie cookie = null;

	    Cookie[] userCookies = request.getCookies();
	    if (userCookies != null && userCookies.length > 0 ) {
	        for (int i = 0; i < userCookies.length; i++) {
	            if (userCookies[i].getName().equals(name)) {
	                cookie = userCookies[i];

	                return cookie;
	            }
	        }
	    }
	    return null;
	}

	public static String getCookieValue(String name, HttpServletRequest request) {
		String ret = null;
		Cookie c = getCookie(name, request);
		if (c != null) {
			ret = c.getValue();
		}
		return ret;
	}

	public static String deleteCookie(String name, HttpServletRequest request, HttpServletResponse response) {
		String deleteCookieValue = null;
		Cookie cookie = getCookie(name, request);

		if (cookie != null) {
			deleteCookieValue = cookie.getValue();

			// クッキーを有効期限切れにしてレスポンスに設定
			cookie.setMaxAge(0);
			response.addCookie(cookie);
		}

		return deleteCookieValue;
	}
}