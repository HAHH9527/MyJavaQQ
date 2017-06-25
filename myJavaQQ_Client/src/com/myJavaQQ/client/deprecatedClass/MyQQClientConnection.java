/**
 * 与服务器连接
 */
package com.myJavaQQ.client.deprecatedClass;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import com.myJavaQQ.client.view.QQFriendList;
import com.myJavaQQ.common.Message;
import com.myJavaQQ.common.MessageTypes;
import com.myJavaQQ.common.User;

@Deprecated
public class MyQQClientConnection {
	private Socket socket;

	@Deprecated
	public Socket getSocket() {
		return socket;
	}

	// 给服务器发送登录验证信息，并接收boolean返回值
	@Deprecated
	public boolean sendLoginInfoToServer(Message sendMsg) {
		boolean ret = false;
		try {
			socket = new Socket("127.0.0.1", 9527);
			ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
			oos.writeObject(sendMsg);
			ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
			Message getMsg = (Message) ois.readObject();
			if (getMsg.getMessageType().equals(MessageTypes.message_Ret_Login) && getMsg.getInfo() != null) {
				ret = true;
				System.out.println("登录成功");
				User myself = (User) getMsg.getInfo();
				// 将连接传给通讯线程
				ClientConServerThread ccst = new ClientConServerThread(socket);

				// 将通讯线程存入HashMap
				ManageClientConServerThread.addClientConServerThread(myself.getUser_Number(), ccst);
				new QQFriendList(myself);
			} else {
				System.out.println("登录失败");
				socket.close();
				System.out.println("已关闭连接");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return ret;
	}

	@Deprecated
	public void setSocket(Socket socket) {
		this.socket = socket;
	}

}
