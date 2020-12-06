package main.socket;

import java.net.Socket;
import java.net.UnknownHostException;
import java.util.StringTokenizer;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

class Client {
	private Socket socket;

	public Client(String ipAddress, int port) throws UnknownHostException, IOException {
		socket = new Socket(ipAddress, port);
	}

	public void stopClient() {
		try {
			if (socket != null && !socket.isClosed()) {
				socket.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public StringTokenizer request(String message) {
		send(message);
		return receive();
	}

	private void send(String message) {
		try {
			OutputStream out = socket.getOutputStream();
			byte[] buffer = message.getBytes("UTF-8");
			out.write(buffer);
			out.flush();
		} catch (Exception e) {
			stopClient();
		}
	}

	private StringTokenizer receive() {
		try {
			InputStream in = socket.getInputStream();
			byte[] buffer = new byte[4096];
			int length = in.read(buffer);
			if (length == -1)
				throw new IOException();
			String responseData = (new String(buffer, 0, length, "UTF-8"));
			return (new StringTokenizer(responseData, "\n"));

		} catch (Exception e) {
			stopClient();
		}
		return null;
	}

}