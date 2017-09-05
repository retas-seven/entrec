package net.live_on.itariya.util;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import net.live_on.itariya.constant.ApConst;
import net.live_on.itariya.entity.SystemDate;

/**
 * 日付取得ユーティリティ
 */
public class ApDateUtil implements Serializable {

	/**
	 * SessionCheckServletFilterでリクエストに設定したシステム日付を取得する。
	 * @return システム日付オブジェクト
	 */
	public static SystemDate getSystemDateObject() {
		HttpServletRequest req = ApUtil.getRequest();
		SystemDate systemDate = (SystemDate) req.getAttribute(ApConst.REQUEST_KEY_SYSTEM_DATE);
		return systemDate;
	}

	/**
	 * SessionCheckServletFilterでリクエストに設定したシステム日付を取得する。
	 * @return システム日付
	 */
	public static Date getSystemDate() {
		return getSystemDateObject().getSystemDate();
	}

	/**
	 * SessionCheckServletFilterでリクエストに設定したシステム日付を取得する。
	 * @return システム日付
	 */
	public static Calendar getSystemDateCalendar() {
		Calendar ret = Calendar.getInstance();
		ret.setTime(getSystemDateObject().getSystemDate());
		return ret;
	}

	public static Calendar getCalendar(Date date) {
		Calendar ret = Calendar.getInstance();
		ret.setTime(date);
		return ret;
	}

	public static Calendar getCalendar(int year, int month) {
		Calendar ret = Calendar.getInstance();
		ret.set(year, month - 1, 1);
		return ret;
	}

	public static String formatDateTime(Date date) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		String ret = format.format(date.getTime());
		return ret;
	}

	public static String formatDate(Date date) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
		String ret = format.format(date.getTime());
		return ret;
	}
}
