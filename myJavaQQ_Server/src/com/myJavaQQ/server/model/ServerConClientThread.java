package com.myJavaQQ.server.model;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;

import com.myJavaQQ.common.Message;
import com.myJavaQQ.common.MessageTypes;
import com.myJavaQQ.common.User;
import com.myJavaQQ.server.dao.impl.FriendsDao;
import com.myJavaQQ.server.dao.impl.UserDao;
import com.myJavaQQ.server.tools.ManageServerConClientThread;

public class ServerConClientThread extends Thread {
	private Socket socket;
	private String userId;
	private boolean flag = true;
	private UserDao userDao = new UserDao();
	private FriendsDao friendsDao = new FriendsDao();

	public ServerConClientThread() {
	}

	public ServerConClientThread(Socket socket) {
		this.socket = socket;
	}

	public ServerConClientThread(Socket socket, String userId) {
		this.socket = socket;
		this.userId = userId;
	}

	public ServerConClientThread(String userId) {
		this.userId = userId;
	}

	public Socket getSocket() {
		return socket;
	}

	public String getUserId() {
		return userId;
	}

	public void offline() {
		flag = false;
		ServerConClientThread scct = ManageServerConClientThread.getServerConClientThread(userId);
		ManageServerConClientThread.removeServerConClientThread(userId);
		Message exitMsg = new Message(MessageTypes.message_Exit);
		exitMsg.setSender(userId);
		sendMsg(exitMsg);
		userDao.changeOnline(userId, "离线");
		try {
			socket.close();
			this.stop();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 处理收到的好友请求
	 * 
	 * @param 请求好友列表的userId-String-userId
	 * @return 带有好友列表的信息包-Message
	 */
	private Message retFriendMsg(String userId) {
		Message retMsg = new Message(MessageTypes.message_Ret_Friends);
		ArrayList<User> friendList = friendsDao.getFriendsListByNumber(userId);
		retMsg.setGetter(userId);
		retMsg.setInfo(friendList);
		return retMsg;
	}

	@Override
	public void run() {
		while (flag) {
			try {
				// 不停 的读取从客户端接收到的信息
				ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
				Message getMsg = (Message) ois.readObject();
				if (getMsg.getMessageType().equals(MessageTypes.message_Chat)) {
					// 从客户端接收到普通聊天包
					System.out.println("接收到普通聊天包");
					System.out.println(getMsg.getSender() + ":" + getMsg.getGetter() + ":" + getMsg.getInfo());
					ManageServerConClientThread.getServerConClientThread(getMsg.getGetter()).sendMsg(getMsg);
				} else if (getMsg.getMessageType().equals(MessageTypes.message_Get_Friends)) {
					// 从客户端接收到好友列表请求
					System.out.println("接收到好友请求");
					sendMsg(retFriendMsg(getMsg.getSender()));
				} else if (getMsg.getMessageType().equals(MessageTypes.message_Exit)) {
					// 接收到客户端的离线请求
					System.out.println("离线请求");
					flag = false;
					offline();
				} else if (getMsg.getMessageType().equals(MessageTypes.message_Add_Friend)) {
					userDao.addFriend(getMsg.getSender(), getMsg.getGetter());
				}
			} catch (SocketException e) {
				System.out.println("断开连接");
				flag = false;
				offline();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 发送信息的方法
	 * 
	 * @param 需要发送的信息-Message-sendMsg
	 */
	public void sendMsg(Message sendMsg) {
		try {
			ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
			oos.writeObject(sendMsg);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void setSocket(Socket socket) {
		this.socket = socket;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
}
