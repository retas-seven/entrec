package net.live_on.itariya.logic;

import java.util.Date;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.live_on.itariya.auth.LoginSession;
import net.live_on.itariya.auth.LoginUser;
import net.live_on.itariya.constant.ApConst;
import net.live_on.itariya.dao.CookieDao;
import net.live_on.itariya.dao.UserDao;
import net.live_on.itariya.entity.User;
import net.live_on.itariya.entity.UserCookie;
import net.live_on.itariya.entity.UserCookiePK;
import net.live_on.itariya.form.LoginForm;
import net.live_on.itariya.util.ApUtil;
import net.live_on.itariya.util.CipherUtil;
import net.live_on.itariya.util.CookieUtil;
import net.live_on.itariya.util.Log;

@Stateless
public class LoginLogic {

	@Inject
    LoginForm form;

	@Inject
	LoginSession loginSession;

	@EJB
	UserDao userDao;

	@EJB
	CookieDao cookieDao;

	public boolean login() {
		String encryptPassword = CipherUtil.encrypt(form.getPassword());

        // ユーザ情報検索
    	User user = userDao.searchUser(form.getMail(), encryptPassword);
    	if (user == null) {
    		return false;
    	}

    	// セッションにログイン情報を設定
    	addLoginInfoToSession(user);

    	// 次回から自動ログイン
    	if (form.isAutoLogin()) {
    		Log.out.info("次回から自動ログインする");
    		registCookie(user.getUserId(), ApUtil.getRequest(), ApUtil.getResponse());
    	} else {
			Log.out.info("次回から自動ログインしない");
			deleteCookie(user.getUserId(), ApUtil.getRequest(), ApUtil.getResponse());
    	}

    	return true;
	}

	private void addLoginInfoToSession(User user) {
    	LoginUser loginUser = new LoginUser();
    	ApUtil.copyProperties(loginUser, user);
    	loginSession.setLoginUser(loginUser);
	}

	private void registCookie(String userId, HttpServletRequest request, HttpServletResponse response) {
		Date now = new Date();
        String cookieValue = ApUtil.createAutoLoginCookieValue();

        // ブラウザに渡すクッキーを作成
        CookieUtil.setCookie(ApConst.AUTO_LOGIN_COOKIE_NAME, cookieValue, ApConst.AUTO_LOGIN_COOKIE_AGE, request, response);

        // クッキー情報登録
        UserCookiePK pk = new UserCookiePK();
        pk.setUserId(userId);
        pk.setCookieName(ApConst.AUTO_LOGIN_COOKIE_NAME);
        pk.setCookieValue(cookieValue);
        UserCookie userCookie = new UserCookie(pk);
        userCookie.setRegistDate(now);
        userCookie.setRegistUserId(userId);
        userCookie.setUpdateDate(now);
        userCookie.setUpdateUserId(userId);

        cookieDao.create(userCookie);
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

	public boolean cookieLogin(HttpServletRequest request, HttpServletResponse response) {
		String cookieValue = CookieUtil.getCookieValue(ApConst.AUTO_LOGIN_COOKIE_NAME, request);
		if (cookieValue == null) {
			return false;
		}

		UserCookie userCookie = cookieDao.findByCookieValue(cookieValue);
		if (userCookie == null) {
			return false;
		}

		String userId = userCookie.getUserCookiePK().getUserId();
		User user = userDao.find(userId);
		if (user == null) {
			return false;
		}

		// セッションにログイン情報を設定
		addLoginInfoToSession(user);

		// クッキーを洗い替え
		deleteCookie(userId, request, response);
		registCookie(userId, request, response);

		return true;
	}
}
