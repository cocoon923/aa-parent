package cn.iaa.core.tcp;

import java.io.IOException;
import java.net.ConnectException;
import java.net.SocketTimeoutException;

import cn.iaa.core.StringUtil;
import cn.iaa.core.log.LogUtil;

public class IOSocketClient {

	public String sendStr(String ip, String port, String sendStr, String serialNo) throws Exception {
		return this.sendStr(ip, port, sendStr, true, serialNo);
	}

	public String sendStr(String ip, String port, String sendStr, boolean isLog, String serialNo) throws Exception {
		IOSocket socket = null;
		String retStr = "";

		if (!StringUtil.isDigit(port)) {
			LogUtil.error(getClass(), "Port is not Digit");
			throw new Exception("Port is not Digit");
		}

		try {
			if (isLog) {
				LogUtil.debug(getClass(), "Send [" + ip + ":" + port + "] :" + sendStr + " " + serialNo);
			}
			socket = new IOSocket(ip, Integer.parseInt(port));
			byte[] receiveBuffer = new byte[4096];
			socket.sendAndreceive(sendStr.getBytes(StringUtil.getDefaultCharset()), receiveBuffer);
			retStr = new String(receiveBuffer);
			if (StringUtil.assertNotNull(retStr)) {
				retStr = retStr.trim();
			}
			if (isLog) {
				LogUtil.debug(getClass(), "Receive [" + ip + ":" + port + "] :" + retStr + " " + serialNo);
			}
		} catch (ConnectException ce) {
			LogUtil.error(getClass(), "Connect Error [" + ip + ":" + port + "] " + serialNo, ce);
			throw ce;
		} catch (SocketTimeoutException se) {
			LogUtil.error(getClass(), "Connect Timeout [" + ip + ":" + port + "] " + serialNo, se);
			throw se;
		} catch (IOException ioe) {
			LogUtil.error(getClass(), "IO Exception [" + ip + ":" + port + "] " + serialNo, ioe);
			throw ioe;
		} catch (Exception ex) {
			LogUtil.error(getClass(), "Exception [" + ip + ":" + port + "] " + serialNo, ex);
			throw ex;
		} finally {
			if (socket != null)
				try {
					socket.close();
				} catch (IOException ioe) {
					LogUtil.error(getClass(), "Socket Close Exception [" + ip + ":" + port + "] " + serialNo, ioe);
					throw ioe;
				}

		}
		return retStr;
	}

}
