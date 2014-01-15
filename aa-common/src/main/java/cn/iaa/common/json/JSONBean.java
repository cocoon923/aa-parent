package cn.iaa.common.json;

import java.util.HashMap;
import java.util.Iterator;

import net.sf.json.JSONObject;
import cn.iaa.common.string.StringUtil;

public class JSONBean extends HashMap<String, String> implements Cloneable {

	private static final long serialVersionUID = 1154936899953328326L;

	public JSONBean() {
		super();
	}

	public JSONBean(String str) {
		super();
		initialFromJSONStr(str);
	}

	public JSONBean(int initialCapacity, String str) {
		super(initialCapacity);
		initialFromJSONStr(str);
	}

	public JSONBean(int initialCapacity, float loadFactor) {
		super(initialCapacity, loadFactor);
	}

	public JSONBean(int initialCapacity) {
		super(initialCapacity);
	}

	/**
	 * Returns a {@link JSONObject} representation of this {@link JSONBean}.
	 * 
	 * @return {@link JSONObject}
	 */
	public JSONObject toJSON() {
		JSONObject obj = new JSONObject();
		for (String key : this.keySet()) {
			obj.element(key, this.get(key));
		}
		return obj;
	}

	@Override
	public String toString() {
		return this.toJSON().toString();
	}

	private void initialFromJSONStr(String str) {
		if (StringUtil.assertNotNull(str)) {
			JSONObject obj = JSONObject.fromObject(str);
			for (Iterator<?> keys = obj.keys(); keys.hasNext();) {
				String key = (String) keys.next();
				this.put(key, obj.getString(key));
			}
		}
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
	public Object clone() {
		JSONBean target = (JSONBean) super.clone();
		for (Iterator<String> keyIt = this.keySet().iterator(); keyIt.hasNext();) {
			String key = keyIt.next();
			target.put(key, this.get(key));
		}
		return target;
	}

}
