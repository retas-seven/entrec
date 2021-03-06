package net.live_on.itariya.entrec.controller;

import java.io.Serializable;
import java.util.Date;

import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.interceptor.Interceptors;

import lombok.Data;
import net.live_on.itariya.entrec.constant.EjbExceptionCause;
import net.live_on.itariya.entrec.form.ChecklistForm;
import net.live_on.itariya.entrec.interseptor.GeneralInterceptor;
import net.live_on.itariya.entrec.logic.ChecklistLogic;
import net.live_on.itariya.entrec.util.ApDateUtil;
import net.live_on.itariya.entrec.util.ApUtil;
import net.live_on.itariya.entrec.util.Log;

/**
 * チェックリスト画面ManagedBean
 */
@javax.inject.Named
@javax.enterprise.context.SessionScoped
@Data
public class ChecklistController implements Serializable {
	private static final long serialVersionUID = 1;

    /** チェックリスト画面フォーム */
	@Inject
	ChecklistForm checklistForm;

	/** チェックリスト処理ロジック */
	@EJB
	ChecklistLogic checklistLogic;

	/**
	 * チェックリスト画面表示時の初期処理
	 */
    @Interceptors(GeneralInterceptor.class)
    public void init() {
    	Date targetDate = null;

    	if (checklistForm.getCalendarDate() == null) {
    		targetDate = ApDateUtil.getSystemDate();
    	} else {
    		targetDate = checklistForm.getCalendarDate();
    	}

    	checklistLogic.initChecklist(targetDate);
    }

    /**
     * 画面で選択された月でチェックリストを再構築する
     */
    @Interceptors(GeneralInterceptor.class)
    public void changeMonth() {
    	checklistLogic.initChecklist(checklistForm.getCalendarDate());
    }

    /**
     * チェックリストの内容を保存する
     */
    @Interceptors(GeneralInterceptor.class)
    public void regist() {
    	try {
    		checklistLogic.registChecklist();
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "",  "保存しました。");
            FacesContext.getCurrentInstance().addMessage(null, message);

    	} catch (EJBException e) {
    		EjbExceptionCause cause = ApUtil.parseEjbException(e);

    		if (EjbExceptionCause.UPDATED == cause) {
	            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "",  "保存失敗。他ユーザにより更新済です。");
	            FacesContext.getCurrentInstance().addMessage(null, message);
	            FacesMessage message2 = new FacesMessage(FacesMessage.SEVERITY_WARN, "",  "再入力後、保存してください。");
	            FacesContext.getCurrentInstance().addMessage(null, message2);

    		} else if (EjbExceptionCause.UNIQUE_CONSTRAINT == cause) {
	            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "",  "保存失敗。他ユーザにより登録済です。");
	            FacesContext.getCurrentInstance().addMessage(null, message);
	            FacesMessage message2 = new FacesMessage(FacesMessage.SEVERITY_WARN, "",  "再入力後、保存してください。");
	            FacesContext.getCurrentInstance().addMessage(null, message2);

    		} else {
	            throw e;
    		}
    	}

    	// 保存後に再検索をしてVersion情報などを最新化する
        init();
    }

    /**
     * チェックリスト画面から一定間隔でサブミットする際に実行するメソッド<br>
     * セッションタイムアウトを回避するために使用する
     */
    @Interceptors(GeneralInterceptor.class)
    public void saveSession() {
    	Log.out.info(">>>>> savesession");
    }
}
