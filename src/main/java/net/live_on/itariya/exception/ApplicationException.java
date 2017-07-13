package net.live_on.itariya.exception;

public class ApplicationException extends RuntimeException {

	private String errMsgId;

	public ApplicationException(String errMsgId) {
		super();
		this.errMsgId = errMsgId;
	}

	public String getErrMsgId() {
		return errMsgId;
	}

	public void setErrMsgId(String errMsgId) {
		this.errMsgId = errMsgId;
	}


}
