/**
 * �������ͻ��˵������߳�
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
				// ��ͣ�Ķ�ȡ�ӷ������˷�������Ϣ
				ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
				Message getMsg = (Message) ois.readObject();
				if (getMsg.getMessageType().equals(MessageTypes.message_Chat)) {// �����������Ϣ
					// �Ѵӷ����������Ϣ��ʾ������ʾ���������

				} else if (getMsg.getMessageType().equals(MessageTypes.message_Ret_Friends)) {
					// �ӷ��������յ������б�

				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	// ������Ϣ����
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
