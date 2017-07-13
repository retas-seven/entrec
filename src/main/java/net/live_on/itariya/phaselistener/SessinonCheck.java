package net.live_on.itariya.phaselistener;

import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.inject.Inject;

import net.live_on.itariya.auth.LoginSession;

public class SessinonCheck implements PhaseListener {

	@Inject
	LoginSession loginSession;

	@Override
    public void afterPhase(PhaseEvent arg0) {}

    @Override
    public void beforePhase(PhaseEvent event) {
    	/*
        FacesContext context = event.getFacesContext();
        ExternalContext extContext = context.getExternalContext();
        String viewId = context.getViewRoot().getViewId();

        try {
	        if (!StringUtils.equalsAny(viewId, ApConst.LOGIN_PAGE, ApConst.REGIST_PAGE, ApConst.APP_ERROR_PAGE)) {
	            HttpSession session = (HttpSession) extContext.getSession(false);

	            if ((session == null) || loginSession.getLoginUser() == null) {
		            // セッション情報がない場合、無効ページへ遷移する
                	String next = extContext.getRequestContextPath() + ApConst.INVALID_PAGE;
                    extContext.redirect(next);
                    context.responseComplete();
                    return;
	            }
	        }

	        if (StringUtils.equals(viewId, ApConst.APP_ERROR_PAGE)) {
	        	Object flashMsg = context.getExternalContext().getFlash().get(ApConst.FLASH_KEY_MESSAGE);

	        	if (flashMsg == null || StringUtils.isEmpty(flashMsg.toString())) {
	        		// アプリエラーのページへ遷移する場合、フラッシュスコープにメッセージがなければ無効ページへ遷移する
	            	String next = extContext.getRequestContextPath() + ApConst.INVALID_PAGE;
	                extContext.redirect(next);
	                context.responseComplete();
	                return;
	        	}
	        }
        } catch (IOException e) {
            throw new SystemException(e);
        }
        */
    }

    @Override
    public PhaseId getPhaseId() {
        return PhaseId.RENDER_RESPONSE;
    }

}
