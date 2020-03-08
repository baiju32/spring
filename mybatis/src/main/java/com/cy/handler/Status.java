package com.cy.handler;

public enum Status {
	/**未发布*/
	UNPUBLISHED(1),
	/**已上线*/
	PUBLISHED(2),
	/**已下线*/
	DOWNLINE(3);
	private int code;
	private Status(int code) {
		this.code=code;
	}
	public int getCode() {
		return code;
	}
	public static Status valueOf(int code) {
		switch (code) {
		case 1:return UNPUBLISHED;
		case 2:return PUBLISHED;
		default:return DOWNLINE;
		}
	}
}
