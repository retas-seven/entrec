package net.live_on.itariya.entrec.util;

import java.io.UnsupportedEncodingException;
import java.util.Properties;
import java.util.ResourceBundle;

import javax.mail.Address;
import javax.mail.AuthenticationFailedException;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailUtil {

	/** メールのエンコード指定 */
    private static final String ENCODE = "UTF-8";

    /**
     * メール送信元の情報を取得する
     * @return メール送信元情報
     */
    private static MimeMessage getMimeMessage() {
        Properties props = new Properties();
        ResourceBundle bundle = PropertiesUtil.getBundle();

        // SMTPサーバーの指定
        props.setProperty("mail.smtp.host", bundle.getString("mail.smtp.host"));

        // SSL用のポートを使用
        props.setProperty("mail.smtp.port", bundle.getString("mail.smtp.port"));

        // タイムアウト設定
        props.setProperty("mail.smtp.connectiontimeout", bundle.getString("mail.timeout"));
        props.setProperty("mail.smtp.timeout", bundle.getString("mail.timeout"));

        // 認証
        props.setProperty("mail.smtp.auth", "true");

        // SSLを使用するための設定
        props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.setProperty("mail.smtp.socketFactory.fallback", "false");
        props.setProperty("mail.smtp.socketFactory.port", bundle.getString("mail.smtp.port"));

        // sessionを作成する
        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(
                		bundle.getString("mailaddress.from"),
                		bundle.getString("mailaddress.from.password"));
            }
        });

        MimeMessage message = new MimeMessage(session);
        return message;
    }

    /**
     * メールのメッセージ内容を設定し、送信する
     * @param sendToMailaddress 送信先メールアドレス
     */
    public static void send(String sendToMailaddress) {
    	MimeMessage message = getMimeMessage();
        ResourceBundle bundle = PropertiesUtil.getBundle();

        try {
        	// 送信元アドレス、送信者名を設定
            Address addrFrom = new InternetAddress(bundle.getString("mailaddress.from"), "entrec", ENCODE);
            message.setFrom(addrFrom);

            // 送信先アドレス、送信先名を設定
            Address addrTo = new InternetAddress(sendToMailaddress, "entrec新規登録ユーザ", ENCODE);
            message.addRecipient(Message.RecipientType.TO, addrTo);

            // メールのタイトル
            message.setSubject("【entrec】登録確認メール", ENCODE);

            // メールの本文
            message.setText("entrecへの登録が完了しました。", ENCODE);

            // 付加情報
            message.addHeader("X-Mailer", "blancoMail 0.1");
            message.setSentDate(ApDateUtil.getSystemDate());

        } catch (MessagingException | UnsupportedEncodingException e) {
        	Log.out.error("メール作成時にエラー発生:" + e.getMessage());
        }

        try {
            // メール送信
            Transport.send(message);

        } catch (AuthenticationFailedException e) {
        	Log.out.error("認証失敗:" + e.getMessage());

        } catch (MessagingException e) {
        	Log.out.error("SMTPサーバへの接続失敗:" + e.getMessage());
        }
    }
}
