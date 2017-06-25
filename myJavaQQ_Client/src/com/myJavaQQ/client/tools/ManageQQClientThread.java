/**
 * 管理客户端线程【可能的思路】
 */
package com.myJavaQQ.client.tools;

import java.util.HashMap;

import com.myJavaQQ.client.model.QQClientUserThread;

public class ManageQQClientThread extends Thread {
	private static HashMap<String, QQClientUserThread> clientHM = new HashMap<String, QQClientUserThread>();

	// 将新的客户端线程加入此HashMap
	public static void addQQClientThread(String userId, QQClientUserThread qcst) {
		clientHM.put(userId, qcst);
	}

	// 可以通过userId取得该线程
	public static QQClientUserThread getQQClientThread(String userId) {
		return (QQClientUserThread) clientHM.get(userId);
	}

	// 移除该线程
	public static void removeQQClientThread(String userId) {
		clientHM.remove(userId);
	}

}
