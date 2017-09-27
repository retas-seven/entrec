package net.live_on.itariya.entrec.util;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Base64.Encoder;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import net.live_on.itariya.entrec.exception.SystemException;

public class CipherUtil {
	/** 共通キー */
	public static final String ENCRYPT_KEY = "1234567890123456";

	/** 初期値 */
	public static final String ENCRYPT_IV = "abcdefghijklmnop";

    /**
     * AES/CBC/PKCS5Paddingの方式で暗号化処理を行う
     * @param text 暗号化対象文字列
     * @return 暗号化した文字列
     */
    public static String encrypt(String text) {
		String ret = null;

    	try {
			// 文字列をバイト配列へ変換
			byte[] byteText = text.getBytes("UTF-8");

			// 暗号化キーと初期化ベクトルをバイト配列へ変換
			byte[] byteKey = ENCRYPT_KEY.getBytes("UTF-8");
			byte[] byteIv = ENCRYPT_IV.getBytes("UTF-8");

			// 暗号化キーと初期化ベクトルのオブジェクト生成
			SecretKeySpec key = new SecretKeySpec(byteKey, "AES");
			IvParameterSpec iv = new IvParameterSpec(byteIv);

			// Cipherオブジェクトの初期化
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			cipher.init(Cipher.ENCRYPT_MODE, key, iv);

			// 暗号化の結果格納
			byte[] byteResult = cipher.doFinal(byteText);

			// Base64へエンコード
			Encoder encoder = Base64.getEncoder();
			ret = encoder.encodeToString(byteResult);

		} catch (UnsupportedEncodingException | GeneralSecurityException e) {
			throw new SystemException(e);
		}

    	System.out.println("暗号化済文字列：" + ret);

		return ret;
    }

    /**
     * AES/CBC/PKCS5Paddingの方式で復号処理を行う
     * @param encryptedStr 復号する文字列
     * @return 復号した文字列
     */
    public static String decrypt(String encryptedStr) {
    	String ret = null;

    	try {
			// Base64をデコード
			Decoder decoder = Base64.getDecoder();
			byte[] byteText = decoder.decode(encryptedStr);

			// 暗号化キーと初期化ベクトルをバイト配列へ変換
			byte[] byteKey = ENCRYPT_KEY.getBytes("UTF8");
			byte[] byteIv = ENCRYPT_IV.getBytes("UTF8");

			// 復号化キーと初期化ベクトルのオブジェクト生成
			SecretKeySpec key = new SecretKeySpec(byteKey, "AES");
			IvParameterSpec iv = new IvParameterSpec(byteIv);

			// Cipherオブジェクトの初期化
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			cipher.init(Cipher.DECRYPT_MODE, key, iv);

			// 復号化の結果格納
			byte[] byteResult = cipher.doFinal(byteText);

			// バイト配列を文字列へ変換
			ret = new String(byteResult, "UTF-8");

    	} catch (UnsupportedEncodingException | GeneralSecurityException e) {
    		throw new SystemException(e);
    	}

    	return ret;
    }
}
