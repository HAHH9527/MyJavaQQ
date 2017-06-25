/**
 * �����������˿ڼ���
 */
package com.myJavaQQ.server.model;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import com.myJavaQQ.common.Message;
import com.myJavaQQ.common.MessageTypes;
import com.myJavaQQ.common.User;
import com.myJavaQQ.server.dao.impl.UserDao;
import com.myJavaQQ.server.tools.ManageServerConClientThread;

public class MyQQServer extends Thread {

	private boolean flag = true;
	private ServerSocket ss;

	public MyQQServer() {
	}

	public ServerSocket getSs() {
		return ss;
	}

	public boolean isFlag() {
		return flag;
	}

	@Override
	public void run() {
		try {
			ss = new ServerSocket(9527);
			System.out.println("����������9527�˿ڼ���");
			while (flag) {
				// �������ȴ�����
				Socket socket = ss.accept();
				// ���ܿͻ��˷�������Ϣ
				ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
				Message getMsg = (Message) ois.readObject();

				if (getMsg.getMessageType().equals(MessageTypes.message_Login)) {
					// �ӿͻ��˽��յ���¼����
					Message sendMsg = new Message(MessageTypes.message_Ret_Login);
					User user = (User) getMsg.getInfo();
					String userId = user.getUser_Number();
					UserDao userDao = new UserDao();// ����UserDao����
					User checkUser = userDao.getPasswordAndNicknameByNumber(userId);// �����ݿ��ѯ��ȡ���û�����
					if (checkUser != null && user.getUser_Password().equals(checkUser.getUser_Password())) {// ��֤�˺������Ƿ���ȷ
						checkUser.setUser_Password(null);// �ɹ����û�������������ֶ�ɾ��
						sendMsg.setInfo((User) checkUser);// ���û���ӽ���Ϣ
						ServerConClientThread scct = new ServerConClientThread(socket, userId);// ���ɷ������ͻ��˵������߳�
						scct.start();// �����߳�
						ManageServerConClientThread.addServerConClientThread(userId, scct);// ���̼߳���ͨ�Ź�����
						ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());// ���������
						oos.writeObject(sendMsg);// ����¼���ؽ����Ϣ���͸��ͻ���
						userDao.changeOnline(userId, "����");
					} else {
						sendMsg.setInfo(null);
						socket.close();
					}
					// } else if
					// (getMsg.getMessageType().equals(MessageTypes.message_Regist))
					// {
					// // ���յ�ע�������
					// UserDao userDao = new UserDao();// ����UserDao����
					// String ret_user_Number = userDao.regUser((User)
					// getMsg.getInfo());
					// Message sendMsg = new
					// Message(MessageTypes.message_Ret_Regist);
					// sendMsg.setInfo((String) ret_user_Number);
					// System.out.println("Server" + ret_user_Number);
					// ObjectOutputStream oos = new
					// ObjectOutputStream(socket.getOutputStream());// ���������
					// oos.writeObject(sendMsg);// ����¼���ؽ����Ϣ����ע�����
					// // ���ر�socket����
					// socket.close();
				} else {
					System.out.println("���յ��ǵ�¼��Ϣ��");
				}
			}
			ss.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	public void setSs(ServerSocket ss) {
		this.ss = ss;
	}

}
