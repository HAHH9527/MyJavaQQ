/**
 * ����ͻ��������̵߳���
 */
package com.myJavaQQ.client.deprecatedClass;

import java.util.HashMap;

@Deprecated
public class ManageClientConServerThread extends Thread {
	private static HashMap<String, ClientConServerThread> hmCCST = new HashMap<String, ClientConServerThread>();

	// ���µ�ͨѶ�̼߳����HashMap
	@Deprecated
	public static void addClientConServerThread(String userId, ClientConServerThread ccst) {
		hmCCST.put(userId, ccst);
	}

	// ����ͨ��userIdȡ�ø��߳�
	@Deprecated
	public static ClientConServerThread getClientConServerThread(String userId) {
		return (ClientConServerThread) hmCCST.get(userId);
	}
}
