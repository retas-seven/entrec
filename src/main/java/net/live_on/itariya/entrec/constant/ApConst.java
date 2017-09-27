package net.live_on.itariya.entrec.constant;

/**
 * entrec共通定数
 */
public class ApConst {
	/** システム日付オブジェクトをリクエストに設定するためのキー */
	public static final String REQUEST_KEY_SYSTEM_DATE = "request_key_system_date";

	/** メッセージをフラッシュスコープに設定するためのキー */
	public static final String FLASH_SCOPE_MESSAGE_KEY = "flash_scope_message_key";

	/** 初期ページファイル名 */
	public static final String LOGIN_PAGE = "/screen/login.xhtml?faces-redirect=true";

	/** 初期ページファイル名 */
	public static final String SESSION_CHECK_RESULT_LOGIN_PAGE = "/entrec/screen/login.xhtml";

	/** アプリケーションエラーページファイル名 */
	public static final String APP_ERROR_PAGE = "/screen/notice.xhtml";

	/** 新規登録ページファイル名 */
	public static final String REGIST_PAGE = "/screen/regist.xhtml?faces-redirect=true";

	/** チェックリストページファイル名 */
	public static final String CHECKLIST_PAGE = "/screen/check_list.xhtml?faces-redirect=true";

	/** 無効ページファイル名 */
	public static final String INVALID_PAGE = "/doc/invalid.html?faces-redirect=true";

	/** 自動ログイン用クッキー名 */
	public static final String AUTO_LOGIN_COOKIE_NAME = "enter_record";

	/** 自動ログイン用クッキーの有効期限（30日） */
	public static final int AUTO_LOGIN_COOKIE_AGE = 2592000;
}
