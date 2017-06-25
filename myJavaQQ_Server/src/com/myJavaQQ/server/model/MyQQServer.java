/**
 * 启动服务器端口监听
 */
package com.myJavaQQ.server.model;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import com.myJavaQQ.common.Message;
import com.myJavaQQ.common.MessageTypes;
import com.myJavaQQ.common.User;
import com.myJavaQQ.server.dao.impl.UserDao;
import com.myJavaQQ.server.tools.ManageServerConClientThread;

public class MyQQServer extends Thread {

	private boolean flag = true;
	private ServerSocket ss;

	public MyQQServer() {
	}

	public ServerSocket getSs() {
		return ss;
	}

	public boolean isFlag() {
		return flag;
	}

	@Override
	public void run() {
		try {
			ss = new ServerSocket(9527);
			System.out.println("服务器已在9527端口监听");
			while (flag) {
				// 阻塞，等待连接
				Socket socket = ss.accept();
				// 接受客户端发来的消息
				ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
				Message getMsg = (Message) ois.readObject();

				if (getMsg.getMessageType().equals(MessageTypes.message_Login)) {
					// 从客户端接收到登录请求
					Message sendMsg = new Message(MessageTypes.message_Ret_Login);
					User user = (User) getMsg.getInfo();
					String userId = user.getUser_Number();
					UserDao userDao = new UserDao();// 生成UserDao对象
					User checkUser = userDao.getPasswordAndNicknameByNumber(userId);// 从数据库查询，取得用户对象
					if (checkUser != null && user.getUser_Password().equals(checkUser.getUser_Password())) {// 验证账号密码是否正确
						checkUser.setUser_Password(null);// 成功后将用户对象里的密码字段删除
						sendMsg.setInfo((User) checkUser);// 将用户添加进信息
						ServerConClientThread scct = new ServerConClientThread(socket, userId);// 生成服务端与客户端的连接线程
						scct.start();// 启动线程
						ManageServerConClientThread.addServerConClientThread(userId, scct);// 将线程加入通信管理中
						ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());// 创建输出流
						oos.writeObject(sendMsg);// 将登录返回结果信息发送给客户端
						userDao.changeOnline(userId, "在线");
					} else {
						sendMsg.setInfo(null);
						socket.close();
					}
					// } else if
					// (getMsg.getMessageType().equals(MessageTypes.message_Regist))
					// {
					// // 接收到注册的请求
					// UserDao userDao = new UserDao();// 生成UserDao对象
					// String ret_user_Number = userDao.regUser((User)
					// getMsg.getInfo());
					// Message sendMsg = new
					// Message(MessageTypes.message_Ret_Regist);
					// sendMsg.setInfo((String) ret_user_Number);
					// System.out.println("Server" + ret_user_Number);
					// ObjectOutputStream oos = new
					// ObjectOutputStream(socket.getOutputStream());// 创建输出流
					// oos.writeObject(sendMsg);// 将登录返回结果信息发送注册界面
					// // 最后关闭socket连接
					// socket.close();
				} else {
					System.out.println("接收到非登录信息包");
				}
			}
			ss.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	public void setSs(ServerSocket ss) {
		this.ss = ss;
	}

}
