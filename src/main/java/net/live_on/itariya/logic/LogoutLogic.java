package net.live_on.itariya.logic;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.live_on.itariya.auth.LoginSession;
import net.live_on.itariya.auth.LoginUser;
import net.live_on.itariya.constant.ApConst;
import net.live_on.itariya.dao.CookieDao;
import net.live_on.itariya.entity.UserCookie;
import net.live_on.itariya.entity.UserCookiePK;
import net.live_on.itariya.util.CookieUtil;

@Stateless
public class LogoutLogic {

	@Inject
	LoginSession loginSession;

	@EJB
	CookieDao cookieDao;

	public void logout (HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession(false);

    	if (session != null) {
    		LoginUser loginUser = loginSession.getLoginUser();

    		if (loginUser != null) {
    			deleteCookie(loginUser.getUserId(), request, response);
    		}

    		// セッション情報を破棄する
    		session.invalidate();
    	}
	}

	private void deleteCookie(String userId, HttpServletRequest request, HttpServletResponse response) {
		// ブラウザのクッキーを削除
		String deleteCookieValue = CookieUtil.deleteCookie(ApConst.AUTO_LOGIN_COOKIE_NAME, request, response);

		// クッキーを削除した場合、DBのクッキー情報を削除
		if (deleteCookieValue != null) {
			UserCookiePK pk = new UserCookiePK();
			pk.setUserId(userId);
			pk.setCookieName(ApConst.AUTO_LOGIN_COOKIE_NAME);
			pk.setCookieValue(deleteCookieValue);
			UserCookie userCookie = cookieDao.find(pk);

	        cookieDao.remove(userCookie);
		}
	}
}
