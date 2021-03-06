package net.live_on.itariya.entrec.exception;

import lombok.Data;

@Data
public class ApplicationException extends RuntimeException {

	private String errMsg;

	public ApplicationException(String errMsg) {
		super();
		this.errMsg = errMsg;
	}

	private ApplicationException() {}
}
