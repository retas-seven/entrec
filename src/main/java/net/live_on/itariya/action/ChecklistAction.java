package net.live_on.itariya.action;

import java.io.Serializable;
import java.util.Date;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.interceptor.Interceptors;

import lombok.Data;
import net.live_on.itariya.form.ChecklistForm;
import net.live_on.itariya.interseptor.GeneralInterceptor;
import net.live_on.itariya.logic.ChecklistLogic;
import net.live_on.itariya.util.ApDateUtil;

/**
 * チェックリスト画面ManagedBean
 */
@javax.inject.Named
@javax.enterprise.context.SessionScoped
@Data
public class ChecklistAction implements Serializable {
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
    	checklistLogic.registChecklist();

    	// 保存後に再検索をしてVersion情報などを最新化する
        init();

        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "",  "保存しました。");
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
}
