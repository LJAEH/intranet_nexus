package com.ln.intranet.common.model.exception;

public class InsertFailException extends RuntimeException {

	public InsertFailException() {
		super("게시글 삽입 실패");
	}
	public InsertFailException(String message) {
		super(message);
	}

}
