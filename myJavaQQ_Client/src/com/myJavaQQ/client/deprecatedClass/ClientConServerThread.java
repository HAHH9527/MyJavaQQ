/**
 * 服务端与客户端的连接线程
 */
package com.myJavaQQ.client.deprecatedClass;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import com.myJavaQQ.common.Message;
import com.myJavaQQ.common.MessageTypes;

@Deprecated
public class ClientConServerThread extends Thread {

	private Socket socket;

	@Deprecated
	public ClientConServerThread(Socket socket) {
		this.socket = socket;
	}

	@Deprecated
	public Socket getSocket() {
		return socket;
	}

	@Deprecated
	@Override
	public void run() {
		while (true) {
			try {
				// 不停的读取从服务器端发来的消息
				ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
				Message getMsg = (Message) ois.readObject();
				if (getMsg.getMessageType().equals(MessageTypes.message_Chat)) {// 如果是聊天消息
					// 把从服务器获得消息显示到该显示的聊天界面

				} else if (getMsg.getMessageType().equals(MessageTypes.message_Ret_Friends)) {
					// 从服务器接收到好友列表

				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	// 发送信息方法
	@Deprecated
	public void sendMsg(Message sendMsg) {
		try {
			ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
			oos.writeObject(sendMsg);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Deprecated
	public void setSocket(Socket socket) {
		this.socket = socket;
	}

}
