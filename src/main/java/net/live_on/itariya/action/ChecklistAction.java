package net.live_on.itariya.action;

import java.io.Serializable;
import java.util.Date;

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
//@javax.faces.view.ViewScoped
@Data
public class ChecklistAction implements Serializable {
	private static final long serialVersionUID = 1;

    /** チェックリスト画面フォーム */
	@Inject
	ChecklistForm checklistForm;

	/** チェックリスト処理ロジック */
	//@EJB
	@Inject
	ChecklistLogic checklistLogic;

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

    @Interceptors(GeneralInterceptor.class)
    public String changeMonth() {
    	System.out.println(">>>>>" + checklistForm.getCalendarDate());
    	String ret = "/screen/check_list?faces-redirect=true";
        return ret;
    }

    @Interceptors(GeneralInterceptor.class)
    public String regist() {
    	checklistLogic.registChecklist();
    	String ret = "/screen/check_list?faces-redirect=true";
        return ret;
    }

//    @Interceptors(GeneralInterceptor.class)
//    public void onCellEdit(CellEditEvent event) {
//        Object oldValue = event.getOldValue();
//        Object newValue = event.getNewValue();
//
//        if(newValue != null && !newValue.equals(oldValue)) {
//            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cell Changed", "Old: " + oldValue + ", New:" + newValue);
//            FacesContext.getCurrentInstance().addMessage(null, msg);
//        }
//    }

//	public ChecklistForm getChecklistForm() {
//		return checklistForm;
//	}
//
//	public void setChecklistForm(ChecklistForm checklistForm) {
//		this.checklistForm = checklistForm;
//	}
//
//	public ChecklistLogic getChecklistLogic() {
//		return checklistLogic;
//	}
//
//	public void setChecklistLogic(ChecklistLogic checklistLogic) {
//		this.checklistLogic = checklistLogic;
//	}

}
