/**
 * �����������
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

	// �����������͵�¼��֤��Ϣ��������boolean����ֵ
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
				System.out.println("��¼�ɹ�");
				User myself = (User) getMsg.getInfo();
				// �����Ӵ���ͨѶ�߳�
				ClientConServerThread ccst = new ClientConServerThread(socket);

				// ��ͨѶ�̴߳���HashMap
				ManageClientConServerThread.addClientConServerThread(myself.getUser_Number(), ccst);
				new QQFriendList(myself);
			} else {
				System.out.println("��¼ʧ��");
				socket.close();
				System.out.println("�ѹر�����");
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
