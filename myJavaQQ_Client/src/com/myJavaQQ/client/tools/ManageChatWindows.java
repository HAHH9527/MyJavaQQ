package com.myJavaQQ.client.tools;

import java.util.HashMap;

import com.myJavaQQ.client.view.ChatWindow;

public class ManageChatWindows {
	private HashMap<String, ChatWindow> cwHM = new HashMap<String, ChatWindow>();

	public void addChatWindow(String friendId, ChatWindow cw) {
		cwHM.put(friendId, cw);
	}

	public ChatWindow getChatWindow(String friendId) {
		return (ChatWindow) cwHM.get(friendId);
	}
}
