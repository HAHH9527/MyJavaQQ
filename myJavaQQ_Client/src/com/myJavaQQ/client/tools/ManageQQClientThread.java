/**
 * ����ͻ����̡߳����ܵ�˼·��
 */
package com.myJavaQQ.client.tools;

import java.util.HashMap;

import com.myJavaQQ.client.model.QQClientUserThread;

public class ManageQQClientThread extends Thread {
	private static HashMap<String, QQClientUserThread> clientHM = new HashMap<String, QQClientUserThread>();

	// ���µĿͻ����̼߳����HashMap
	public static void addQQClientThread(String userId, QQClientUserThread qcst) {
		clientHM.put(userId, qcst);
	}

	// ����ͨ��userIdȡ�ø��߳�
	public static QQClientUserThread getQQClientThread(String userId) {
		return (QQClientUserThread) clientHM.get(userId);
	}

	// �Ƴ����߳�
	public static void removeQQClientThread(String userId) {
		clientHM.remove(userId);
	}

}
