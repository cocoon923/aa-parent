package cn.iaa.common.tcpip;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

public class IOSocket {
	private Socket socket;
	private InputStream input;
	private OutputStream output;
	private String ip;
	private int port;
	private int timeout = 1000000;

	public IOSocket() {
		socket = new Socket();
	}

	public IOSocket(String ip, int port) throws IOException {
		socket = new Socket(ip, port);
		socket.setSoTimeout(timeout);
		socket.setReuseAddress(true);
		this.ip = ip;
		this.port = port;
	}

	public void connect() throws IOException {
		socket.connect(new InetSocketAddress(ip, port), timeout);
	}

	public void connect(String ip, int port) throws IOException {
		socket.connect(new InetSocketAddress(ip, port), timeout);
	}

	public void connect(String ip, int port, int timeout) throws IOException {
		socket.connect(new InetSocketAddress(ip, port), timeout);
	}

	public int sendAndreceive(byte[] sendBuffer, byte[] receiveBuffer) throws IOException {
		send(sendBuffer);
		return receive(receiveBuffer);
	}

	private void send(byte[] sendBuffer) throws IOException {
		output = socket.getOutputStream();
		output.write(sendBuffer);
	}

	private int receive(byte[] receiveBuffer) throws IOException {
		input = socket.getInputStream();
		DataInputStream dis = new DataInputStream(input);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		int len = -1;

		while ((len = dis.read(receiveBuffer)) != -1) {
			baos.write(receiveBuffer, 0, len);
			if ((new String(receiveBuffer)).trim().lastIndexOf("XXEE") >= 0)
				break;
		}
		return len;
	}

	public void close() throws IOException {
		socket.close();
	}

	public boolean isClose() {
		if (socket == null)
			return false;
		return socket.isClosed();
	}

}