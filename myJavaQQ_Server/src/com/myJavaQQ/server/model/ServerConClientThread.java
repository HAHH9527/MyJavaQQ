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
		userDao.changeOnline(userId, "����");
		try {
			socket.close();
			this.stop();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * �����յ��ĺ�������
	 * 
	 * @param ��������б��userId-String-userId
	 * @return ���к����б����Ϣ��-Message
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
				// ��ͣ �Ķ�ȡ�ӿͻ��˽��յ�����Ϣ
				ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
				Message getMsg = (Message) ois.readObject();
				if (getMsg.getMessageType().equals(MessageTypes.message_Chat)) {
					// �ӿͻ��˽��յ���ͨ�����
					System.out.println("���յ���ͨ�����");
					System.out.println(getMsg.getSender() + ":" + getMsg.getGetter() + ":" + getMsg.getInfo());
					ManageServerConClientThread.getServerConClientThread(getMsg.getGetter()).sendMsg(getMsg);
				} else if (getMsg.getMessageType().equals(MessageTypes.message_Get_Friends)) {
					// �ӿͻ��˽��յ������б�����
					System.out.println("���յ���������");
					sendMsg(retFriendMsg(getMsg.getSender()));
				} else if (getMsg.getMessageType().equals(MessageTypes.message_Exit)) {
					// ���յ��ͻ��˵���������
					System.out.println("��������");
					flag = false;
					offline();
				} else if (getMsg.getMessageType().equals(MessageTypes.message_Add_Friend)) {
					userDao.addFriend(getMsg.getSender(), getMsg.getGetter());
				}
			} catch (SocketException e) {
				System.out.println("�Ͽ�����");
				flag = false;
				offline();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * ������Ϣ�ķ���
	 * 
	 * @param ��Ҫ���͵���Ϣ-Message-sendMsg
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
