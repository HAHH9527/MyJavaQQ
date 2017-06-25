/**
 * 管理服务器与客户端通信的线程
 */
package com.myJavaQQ.server.tools;

import java.util.HashMap;

import com.myJavaQQ.server.model.ServerConClientThread;

public class ManageServerConClientThread {
	private static HashMap<String, ServerConClientThread> hmSCCT = new HashMap<String, ServerConClientThread>();

	// 将新的通讯线程加入此HashMap
	public static void addServerConClientThread(String userId, ServerConClientThread scct) {
		hmSCCT.put(userId, scct);
	}

	public static HashMap<String, ServerConClientThread> getHmSCCT() {
		return hmSCCT;
	}

	// 可以通过userId取得该线程
	public static ServerConClientThread getServerConClientThread(String userId) {
		return (ServerConClientThread) hmSCCT.get(userId);
	}

	// 移除该线程
	public static void removeServerConClientThread(String userId) {
		hmSCCT.remove(userId);
	}

	public static void setHmSCCT(HashMap<String, ServerConClientThread> hmSCCT) {
		ManageServerConClientThread.hmSCCT = hmSCCT;
	}

}
