package cn.iaa.common.json.beans;

import cn.iaa.common.json.JSONBean;
import cn.iaa.common.string.StringUtil;

public class Message extends JSONBean implements Cloneable {

	private static final long serialVersionUID = -8833759172447315535L;

	private Message() {
		super();
	}

	public static Message getMessage() {
		Message message = new Message();
		return message;
	}

	@Override
	public String get(Object key) {
		String value = super.get(key);
		if (!StringUtil.assertNotNull(value)) {
			value = "";
		}
		return value;
	}

	@Override
	public Message clone() {
		return (Message) super.clone();
	}

}
