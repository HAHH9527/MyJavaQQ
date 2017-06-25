/**
 * 管理客户端连接线程的类
 */
package com.myJavaQQ.client.deprecatedClass;

import java.util.HashMap;

@Deprecated
public class ManageClientConServerThread extends Thread {
	private static HashMap<String, ClientConServerThread> hmCCST = new HashMap<String, ClientConServerThread>();

	// 将新的通讯线程加入此HashMap
	@Deprecated
	public static void addClientConServerThread(String userId, ClientConServerThread ccst) {
		hmCCST.put(userId, ccst);
	}

	// 可以通过userId取得该线程
	@Deprecated
	public static ClientConServerThread getClientConServerThread(String userId) {
		return (ClientConServerThread) hmCCST.get(userId);
	}
}
