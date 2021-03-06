package cn.iaa.core.tcp;

import cn.iaa.core.StringUtil;
import cn.iaa.core.log.LogUtil;

public class UdpSupport {

	public static void send(String ip, String port, String sendMessage) {
		UdpClientSocket client = null;
		try {
			client = new UdpClientSocket();
			if (!StringUtil.isDigit(port)) {
				LogUtil.error(UdpSupport.class, "Port is not Digit");
				throw new Exception("Port is not Digit");
			}
			client.send(ip, Integer.parseInt(port), sendMessage);
		} catch (Exception e) {
			LogUtil.error(UdpSupport.class, "UDP Send Error", e);
		} finally {
			if (client != null) {
				client.close();
			}
		}
	}
}
