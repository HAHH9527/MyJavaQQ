/**
 * �����������ͻ���ͨ�ŵ��߳�
 */
package com.myJavaQQ.server.tools;

import java.util.HashMap;

import com.myJavaQQ.server.model.ServerConClientThread;

public class ManageServerConClientThread {
	private static HashMap<String, ServerConClientThread> hmSCCT = new HashMap<String, ServerConClientThread>();

	// ���µ�ͨѶ�̼߳����HashMap
	public static void addServerConClientThread(String userId, ServerConClientThread scct) {
		hmSCCT.put(userId, scct);
	}

	public static HashMap<String, ServerConClientThread> getHmSCCT() {
		return hmSCCT;
	}

	// ����ͨ��userIdȡ�ø��߳�
	public static ServerConClientThread getServerConClientThread(String userId) {
		return (ServerConClientThread) hmSCCT.get(userId);
	}

	// �Ƴ����߳�
	public static void removeServerConClientThread(String userId) {
		hmSCCT.remove(userId);
	}

	public static void setHmSCCT(HashMap<String, ServerConClientThread> hmSCCT) {
		ManageServerConClientThread.hmSCCT = hmSCCT;
	}

}
