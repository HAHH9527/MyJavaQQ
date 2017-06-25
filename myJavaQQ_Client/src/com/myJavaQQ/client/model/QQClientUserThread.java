/**
 * �ͻ����߳�
 */
package com.myJavaQQ.client.model;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;

import com.myJavaQQ.client.deprecatedClass.ClientConServerThread;
import com.myJavaQQ.client.deprecatedClass.MyQQClientConnection;
import com.myJavaQQ.client.view.QQFriendList;
import com.myJavaQQ.common.Message;
import com.myJavaQQ.common.MessageTypes;
import com.myJavaQQ.common.User;

public class QQClientUserThread extends Thread {
	private static String serverIP = "127.0.0.1";// ���޸�Ϊ����˵�IP
	private String userId;// �˿ͻ����̶߳�Ӧ���û�ID
	private QQFriendList friendListUI;// �˿ͻ����̶߳�Ӧ�ĺ����б�
	private Socket socket;// �˿ͻ����̶߳�Ӧ��socket����
	private boolean flag = true;

	@Deprecated
	private ClientConServerThread ccst;

	@Deprecated
	private Message loginCheck;

	/**
	 * ��ʼ��QQ�ͻ����߳�
	 */
	public QQClientUserThread() {
	}

	@Deprecated
	public QQClientUserThread(Message loginCheck) {
		this.loginCheck = loginCheck;
	}

	/**
	 * �����������͵�¼��֤��Ϣ��������boolean����ֵ
	 * 
	 * @param ��¼��Ϣ����info�ֶΰ���������û��˺ŵ�User����
	 * @return ��¼�Ƿ�ɹ�
	 */
	public boolean checkUser(Message loginCheck) {
		boolean ret = false;
		try {
			socket = new Socket(serverIP, 9527);// �����������socket����
			ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());// �����socket�ϴ��������
			oos.writeObject(loginCheck);// ���������д���¼��Ϣ
			ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());// �����socket�ϴ���������
			Message getMsg = (Message) ois.readObject();// ���������л�ȡ����˷�������Ϣ
			if (getMsg.getMessageType().equals(MessageTypes.message_Ret_Login) && getMsg.getInfo() != null) {// ����ǵ�¼������ذ������ҵõ���¼���
				ret = true;
				System.out.println("��¼�ɹ�");
				User myself = (User) getMsg.getInfo();
				friendListUI = new QQFriendList(myself);
			} else {// �����¼ʧ��
				System.out.println("��¼ʧ��");
				socket.close();// �ر�socket����
				System.out.println("�ѹر�����");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret;
	}

	// ��֤��¼
	@Deprecated
	public boolean checkUser1(Message loginCheck) {
		boolean ret = new MyQQClientConnection().sendLoginInfoToServer(loginCheck);
		if (ret) {// �����֤��¼Ϊ�棬�������ѽ����
			userId = ((User) loginCheck.getInfo()).getUser_Number();
		}
		return ret;
	}

	@Deprecated
	public ClientConServerThread getCcst() {
		return ccst;
	}

	public QQFriendList getFriendListUI() {
		return friendListUI;
	}

	@Deprecated
	public Message getLoginCheck() {
		return loginCheck;
	}

	public Socket getSocket() {
		return socket;
	}

	public String getUserId() {// ȡ�ø��̶߳�Ӧ���û�ID
		return userId;
	}

	public boolean isFlag() {
		return flag;
	}

	/**
	 * ������������ע����Ϣ��
	 * 
	 * @param ע����Ϣ��-Message-regMsg
	 * @return ע����˺�-String
	 */
	// public static String reg(Message regMsg) {
	// String ret_Number = "0";
	// try {
	// Socket socket = new Socket(serverIP, 9527);// �����������socket����
	// ObjectOutputStream oos = new
	// ObjectOutputStream(socket.getOutputStream());// �����socket�ϴ��������
	// oos.writeObject(regMsg);
	// ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());//
	// �����socket�ϴ���������
	// Message getMsg = (Message) ois.readObject();// ���������л�ȡ����˷�������Ϣ
	// if (getMsg.getMessageType().equals(MessageTypes.message_Ret_Regist)) {
	// ret_Number = (String) getMsg.getInfo();
	// }
	// System.out.println("Ct" + ret_Number);
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// return ret_Number;
	// }

	@Override
	public void run() {
		while (true) {
			try {
				// ��ͣ�Ķ�ȡ�ӷ������˷�������Ϣ
				ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
				Message getMsg = (Message) ois.readObject();
				System.out.println("�յ��ӷ�������������Ϣ��");
				if (getMsg.getMessageType().equals(MessageTypes.message_Chat)) {// �����������Ϣ
					// �Ѵӷ����������Ϣ��ʾ������ʾ���������
					String temp = friendListUI.getMcw().getChatWindow(getMsg.getSender()).getText_ShowMsg().getText();
					friendListUI.getMcw().getChatWindow(getMsg.getSender()).getText_ShowMsg()
							.setText(temp + getMsg.getSender() + "\t" + getMsg.getSendTime() + "\n"
									+ (String) getMsg.getInfo() + "\n");
				} else if (getMsg.getMessageType().equals(MessageTypes.message_Ret_Friends)) {
					// �ӷ��������յ������б�
					System.out.println("������Ϣ��");
					ArrayList<User> friendList = (ArrayList<User>) getMsg.getInfo();
					friendListUI.showFriends(friendList);
				} else if (getMsg.getMessageType().equals(MessageTypes.message_Exit)) {
					flag = false;
					friendListUI.exit();
				}
			} catch (SocketException e) {
				flag = false;
				friendListUI.exit();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	/**
	 * ������Ϣ�������
	 * 
	 * @param sendMsg
	 */
	public void sendMessage(Message sendMsg) {
		try {
			ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
			oos.writeObject(sendMsg);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Deprecated
	public void setCcst(ClientConServerThread ccst) {
		this.ccst = ccst;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	public void setFriendListUI(QQFriendList friendListUI) {
		this.friendListUI = friendListUI;
	}

	@Deprecated
	public void setLoginCheck(Message loginCheck) {
		this.loginCheck = loginCheck;
	}

	public void setSocket(Socket socket) {
		this.socket = socket;
	}

	public void setUserId(String userId) {// ���ø��̵߳��û�ID
		this.userId = userId;
	}

}
