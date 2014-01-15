package cn.iaa.common.json.beans;

import cn.iaa.common.json.JSONBean;

public class Session extends JSONBean {

	private static final long serialVersionUID = 505852158152911467L;

	private static final int SESSION_DEFAULT_CAPACITY = 30;

	public Session() {
		super(SESSION_DEFAULT_CAPACITY);
	}

	public Session(String str) {
		super(SESSION_DEFAULT_CAPACITY, str);
	}

}
