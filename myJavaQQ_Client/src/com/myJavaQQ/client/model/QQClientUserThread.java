/**
 * 客户端线程
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
	private static String serverIP = "127.0.0.1";// 请修改为服务端的IP
	private String userId;// 此客户端线程对应的用户ID
	private QQFriendList friendListUI;// 此客户端线程对应的好友列表
	private Socket socket;// 此客户端线程对应的socket连接
	private boolean flag = true;

	@Deprecated
	private ClientConServerThread ccst;

	@Deprecated
	private Message loginCheck;

	/**
	 * 初始化QQ客户端线程
	 */
	public QQClientUserThread() {
	}

	@Deprecated
	public QQClientUserThread(Message loginCheck) {
		this.loginCheck = loginCheck;
	}

	/**
	 * 给服务器发送登录验证信息，并接收boolean返回值
	 * 
	 * @param 登录信息包，info字段包含输入的用户账号的User对象
	 * @return 登录是否成功
	 */
	public boolean checkUser(Message loginCheck) {
		boolean ret = false;
		try {
			socket = new Socket(serverIP, 9527);// 与服务器建立socket连接
			ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());// 在这个socket上创建输出流
			oos.writeObject(loginCheck);// 往输出流中写入登录信息
			ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());// 在这个socket上创建输入流
			Message getMsg = (Message) ois.readObject();// 从输入流中获取服务端发来的信息
			if (getMsg.getMessageType().equals(MessageTypes.message_Ret_Login) && getMsg.getInfo() != null) {// 如果是登录结果返回包，并且得到登录结果
				ret = true;
				System.out.println("登录成功");
				User myself = (User) getMsg.getInfo();
				friendListUI = new QQFriendList(myself);
			} else {// 如果登录失败
				System.out.println("登录失败");
				socket.close();// 关闭socket连接
				System.out.println("已关闭连接");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret;
	}

	// 验证登录
	@Deprecated
	public boolean checkUser1(Message loginCheck) {
		boolean ret = new MyQQClientConnection().sendLoginInfoToServer(loginCheck);
		if (ret) {// 如果验证登录为真，启动好友界面等
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

	public String getUserId() {// 取得该线程对应的用户ID
		return userId;
	}

	public boolean isFlag() {
		return flag;
	}

	/**
	 * 给服务器发送注册信息包
	 * 
	 * @param 注册信息包-Message-regMsg
	 * @return 注册的账号-String
	 */
	// public static String reg(Message regMsg) {
	// String ret_Number = "0";
	// try {
	// Socket socket = new Socket(serverIP, 9527);// 与服务器建立socket连接
	// ObjectOutputStream oos = new
	// ObjectOutputStream(socket.getOutputStream());// 在这个socket上创建输出流
	// oos.writeObject(regMsg);
	// ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());//
	// 在这个socket上创建输入流
	// Message getMsg = (Message) ois.readObject();// 从输入流中获取服务端发来的信息
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
				// 不停的读取从服务器端发来的消息
				ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
				Message getMsg = (Message) ois.readObject();
				System.out.println("收到从服务器发来的消息包");
				if (getMsg.getMessageType().equals(MessageTypes.message_Chat)) {// 如果是聊天消息
					// 把从服务器获得消息显示到该显示的聊天界面
					String temp = friendListUI.getMcw().getChatWindow(getMsg.getSender()).getText_ShowMsg().getText();
					friendListUI.getMcw().getChatWindow(getMsg.getSender()).getText_ShowMsg()
							.setText(temp + getMsg.getSender() + "\t" + getMsg.getSendTime() + "\n"
									+ (String) getMsg.getInfo() + "\n");
				} else if (getMsg.getMessageType().equals(MessageTypes.message_Ret_Friends)) {
					// 从服务器接收到好友列表
					System.out.println("好友信息包");
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
	 * 发送信息给服务端
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

	public void setUserId(String userId) {// 设置该线程的用户ID
		this.userId = userId;
	}

}
