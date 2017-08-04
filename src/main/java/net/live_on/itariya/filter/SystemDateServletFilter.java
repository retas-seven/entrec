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

@WebFilter(filterName="SystemDateServletFilter", urlPatterns="/*")
public final class SystemDateServletFilter implements Filter{

	/** システム日付取得用DAO */
	@Inject
    SystemDateDao systemDateDao;

	/** ログイン処理ロジック */
	@EJB
	LoginLogic loginLogic;

	@Inject
	LoginSession loginSession;

    public void init(FilterConfig config) throws ServletException {}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
		Log.out.info("SystemDateServletFilter#doFilter() start");

		// セッションチェックを実行する
		boolean sessionCheckResult = sessionCheck(request, response);

		if (!sessionCheckResult) {
//			RequestDispatcher rd = request.getRequestDispatcher("/faces/screen/login.xhtml");
//			rd.forward(request, response);
//			((HttpServletResponse) response).sendRedirect("/entrec/faces/screen/login.xhtml");
			((HttpServletResponse) response).sendRedirect("/entrec/login");
		} else {
			// 後続の業務処理で使用するシステム日付を取得する
			SystemDate systemDate = systemDateDao.getSystemDate();
			request.setAttribute(ApConst.REQUEST_KEY_SYSTEM_DATE, systemDate);
			Log.out.info("システム日付：" + ApDateUtil.formatDateTime(systemDate.getSystemDate()));

			chain.doFilter(request,response);
		}

	    Log.out.info("SystemDateServletFilter#doFilter() end");
	}

	public void destroy() {}

	private boolean sessionCheck(ServletRequest servletRequest, ServletResponse servletResponse) {
	    if (servletRequest instanceof HttpServletRequest && servletResponse instanceof HttpServletResponse) {
	        HttpServletRequest request = (HttpServletRequest) servletRequest;
	        boolean execSessionCheck = false;
	        String servletPath = request.getServletPath();
	        String pathInfo = request.getPathInfo();
	        Log.out.info("servletPath:" + servletPath);
	        Log.out.info("pathInfo:" + pathInfo);

            // URLがJSF本来のものである場合
            if ("/faces".equals(servletPath) && pathInfo != null && pathInfo.matches("^/screen/.+\\.xhtml$")) {
            	if (!StringUtils.equalsAny(pathInfo, "/screen/login.xhtml", "/screen/regist.xhtml")) {
                	execSessionCheck = true;
            	}
            }

            // URLがPrettyFacesのものである場合
            if (!"/faces".equals(servletPath)) {
            	if (!StringUtils.equalsAny(servletPath, "/login", "/regist", "/doc/contact.html", "/doc/contact.html", "/doc/privacy_policy.html", "/doc/rule.html")) {
                	execSessionCheck = true;
            	}
            }

            // セッションチェック実行
            if (execSessionCheck) {
		        if (loginSession.getLoginUser() == null) {
		        	Log.out.info("セッション切れ");

		        	// セッション切れの場合、クッキーログインを試みる
		        	boolean cookieLoginSuccess = loginLogic.cookieLogin(
		        			request, (HttpServletResponse) servletResponse);

		        	if (cookieLoginSuccess) {
		        		Log.out.info("クッキーログインOK");
		        	} else {
		        		System.out.println("クッキーログインせずログインページに遷移する");
		        		return false;
		        	}

		        } else {
		        	System.out.println( "セッションあり（特にやることなし）");
		        }
            }
	    } else {
	    	// ここに来ることはない想定
	    	Log.out.info("セッションチェック時の想定外ルート");
	    	return false;
	    }

	    return true;
	}
}