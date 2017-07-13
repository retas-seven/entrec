package net.live_on.itariya.constant;

public class ApConst {
	/** システム日付オブジェクトをリクエストに設定するためのキー */
	public static final String REQUEST_KEY_SYSTEM_DATE = "request_key_system_date";

	/** メッセージをフラッシュスコープに設定するためのキー */
	public static final String FLASH_KEY_MESSAGE = "flash_key_message";

	/** 初期ページファイル名 */
	public static final String LOGIN_PAGE = "/screen/login.xhtml";

	/** アプリケーションエラーページファイル名 */
	public static final String APP_ERROR_PAGE = "/screen/app_error.xhtml";

	/** 新規登録ページファイル名 */
	public static final String REGIST_PAGE = "/screen/regist.xhtml";

	/** 無効ページファイル名 */
	public static final String INVALID_PAGE = "/doc/invalid.html";

	/** 自動ログイン用クッキー名 */
	public static final String AUTO_LOGIN_COOKIE_NAME = "enter_record";

	/** 自動ログイン用クッキーの有効期限（30日） */
	public static final int AUTO_LOGIN_COOKIE_AGE = 2592000;
}
