package cn.iaa.redis.core;

import java.nio.charset.Charset;

import net.sf.json.JSONObject;

import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;
import org.springframework.util.Assert;

import cn.iaa.core.json.JSONBean;

/**
 * {@link JSONObject} to byte[] (and back) serializer. Converts
 * {@link JSONObject} into bytes and vice-versa using the specified charset (by
 * default UTF-8).
 * <p/>
 * Useful when the interaction with the Redis happens mainly through Strings.
 * 
 * <p/>
 * Does not perform any null conversion since empty strings are valid
 * keys/values.
 * 
 * @author chenmm
 * 
 */
public class JSONBeanRedisSerializer implements RedisSerializer<JSONBean> {

	private final Charset charset;

	public JSONBeanRedisSerializer() {
		this(Charset.forName("UTF8"));
	}

	public JSONBeanRedisSerializer(Charset charset) {
		Assert.notNull(charset);
		this.charset = charset;
	}

	@Override
	public byte[] serialize(JSONBean jsonBean) throws SerializationException {
		return (jsonBean == null ? null : jsonBean.toString().getBytes(charset));
	}

	@Override
	public JSONBean deserialize(byte[] bytes) throws SerializationException {
		return (bytes == null ? null : new JSONBean(new String(bytes, charset)));
	}

}
