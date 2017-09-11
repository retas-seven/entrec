package net.live_on.itariya.filter;

import java.io.IOException;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import net.live_on.itariya.auth.LoginSession;
import net.live_on.itariya.constant.ApConst;
import net.live_on.itariya.dao.SystemDateDao;
import net.live_on.itariya.entity.SystemDate;
import net.live_on.itariya.logic.LoginLogic;
import net.live_on.itariya.util.ApDateUtil;
import net.live_on.itariya.util.Log;

@WebFilter(filterName="SessionCheckServletFilter", urlPatterns="/*")
public final class SessionCheckServletFilter implements Filter{

	/** システム日付取得用DAO */
	@Inject
    SystemDateDao systemDateDao;

	/** ログイン処理ロジック */
	@EJB
	LoginLogic loginLogic;

	@Inject
	LoginSession loginSession;

    public void init(FilterConfig config) throws ServletException {}
	public void destroy() {}

    /**
     * entrec標準のフィルタ処理
     */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
		Log.out.info("SessionCheckServletFilter#doFilter() start");

		// セッションチェックを実行する
		boolean sessionCheckResult = sessionCheck(request, response);

		if (!sessionCheckResult) {
			// セッション情報がない場合（＝未ログインの場合）はログイン画面へ遷移する
			((HttpServletResponse) response).sendRedirect(ApConst.SESSION_CHECK_RESULT_LOGIN_PAGE);

		} else {
			chain.doFilter(request,response);
		}

	    Log.out.info("SessionCheckServletFilter#doFilter() end");
	}

	/**
	 * セッション情報の有無をチェックする
	 */
	private boolean sessionCheck(ServletRequest servletRequest, ServletResponse servletResponse) {
	    if (servletRequest instanceof HttpServletRequest && servletResponse instanceof HttpServletResponse) {
	        HttpServletRequest request = (HttpServletRequest) servletRequest;
	        boolean execSessionCheck = false;
	        String servletPath = request.getServletPath();
	        String pathInfo = request.getPathInfo();
	        Log.out.info("servletPath:" + servletPath);
	        Log.out.info("pathInfo:" + pathInfo);

	        // ログイン画面と新規ユーザ登録画面の場合はセッションチェックをしない
            if (servletPath.matches("^/screen/.+\\.xhtml$")) {
            	// 業務ロジックで使用するためのシステム日付を取得しておく
        		retrieveSystemDate(servletRequest);

            	if (!StringUtils.equalsAny(servletPath, "/screen/login.xhtml", "/screen/regist.xhtml", "/screen/notice.xhtml")) {
                	execSessionCheck = true;
            	}
            }

            // セッションチェック実行
            if (execSessionCheck) {
		        if (loginSession.getLoginUser() == null) {
		        	Log.out.info("セッション情報なし");

		        	// セッション情報なしの場合、クッキーログインを試みる
		        	boolean cookieLoginSuccess = loginLogic.cookieLogin(
		        			request, (HttpServletResponse) servletResponse);

		        	if (cookieLoginSuccess) {
		        		Log.out.info("クッキーログインOK");

		        	} else {
		        		Log.out.info("クッキーログインせずログインページに遷移する");
		        		return false;
		        	}

		        } else {
		        	Log.out.info("セッション情報あり");
		        }
            }
	    } else {
	    	// ここに来ることはない想定
	    	Log.out.info("セッションチェック時の想定外ルート");
	    	return false;
	    }

	    return true;
	}

	/**
	 * 後続の業務処理で使用するシステム日付を取得する
	 */
	private void retrieveSystemDate(ServletRequest servletRequest) {
		SystemDate systemDate = systemDateDao.getSystemDate();
		servletRequest.setAttribute(ApConst.REQUEST_KEY_SYSTEM_DATE, systemDate);
		Log.out.info("システム日付：" + ApDateUtil.formatDateTime(systemDate.getSystemDate()));
	}
}