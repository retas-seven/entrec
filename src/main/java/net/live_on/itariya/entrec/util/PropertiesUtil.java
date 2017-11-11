package net.live_on.itariya.entrec.util;

import java.util.ResourceBundle;

public class PropertiesUtil {
	/** Entrec共通プロパティファイル名 */
	private static final String PROPERTY_FILE = "entrec_general";

	/**
	 * entrec共通プロパティファイルのリソースバンドルを取得する
	 * @return プロパティファイルのリソースバンドル
	 */
	public static ResourceBundle getBundle() {
		ResourceBundle ret = ResourceBundle.getBundle(PROPERTY_FILE);
		return ret;
	}

	/**
	 * entrec共通プロパティファイルから値を取得する
	 * @param propertieFileKey プロパティファイルのキー
	 * @return プロパティファイルから取得した値
	 */
	public static String getString(String propertieFileKey) {
		String ret;
		ResourceBundle bundle = ResourceBundle.getBundle(PROPERTY_FILE);
		ret = bundle.getString(propertieFileKey);
		return ret;
	}

	/**
	 * entrec共通プロパティファイルから値を取得する
	 * @param propertieFileKey プロパティファイルのキー
	 * @return プロパティファイルから取得した値
	 */
	public static int getInt(String propertieFileKey) {
		int ret = Integer.parseInt(getString(propertieFileKey));
		return ret;
	}
}

