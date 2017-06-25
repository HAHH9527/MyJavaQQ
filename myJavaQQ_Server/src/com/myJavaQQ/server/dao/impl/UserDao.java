package com.myJavaQQ.server.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.myJavaQQ.common.User;
import com.myJavaQQ.server.dao.UserImpl;

public class UserDao extends BaseDao implements UserImpl {

	@Override
	/**
	 * @param 我的账号-String-myselfId
	 * @param 好友的账号-String-friendId
	 */
	public void addFriend(String myselfId, String friendId) {
		String sql = "INSERT INTO user_Friends(myself_Number,friend_Number) VALUES(?,?)";
		String[] params = { myselfId, friendId };
		executeSQL(sql, params);
		String[] params2 = { friendId, myselfId };
		executeSQL(sql, params2);
	}

	@Override
	/**
	 * 使所有用户离线
	 */
	public void allUserOffline() {
		String sql = "UPDATE user_Info SET user_Online = ? WHERE user_Online = ?";
		String[] params = { "离线", "在线" };
		executeSQL(sql, params);
	}

	@Override
	/**
	 * 改变用户的在线状态
	 * 
	 * @param 需要改变状态的用户账号-String-userId
	 * @param 改变成什么状态-String-online
	 */
	public void changeOnline(String userId, String online) {
		// TODO 自动生成的方法存根
		String sql = "UPDATE user_Info SET user_Online = ? WHERE user_Number = ? ";
		String[] params = { online, userId };
		executeSQL(sql, params);
	}

	@Override
	public List createObject(ResultSet rs) throws Exception {
		List<User> users = new ArrayList<User>();
		while (rs.next()) {
			User user = new User();
			try {
				user.setUser_Number(rs.getString("user_Number"));
			} catch (SQLException e) {
				// System.out.println("没有user_Number");
			}

			try {
				user.setUser_Password(rs.getString("user_Password"));
			} catch (SQLException e) {
				// System.out.println("没有user_Password");
			}

			try {
				user.setUser_Regdate(rs.getString("user_Regdate"));
			} catch (SQLException e) {
				// System.out.println("没有user_Regdate");
			}

			try {
				user.setUser_Nickname(rs.getString("user_Nickname"));
			} catch (SQLException e) {
				// System.out.println("没有user_Nickname");
			}

			try {
				user.setUser_Online(rs.getString("user_Online"));
			} catch (SQLException e) {
				// System.out.println("没有user_Online");
			}

			try {
				user.setUser_Sex(rs.getString("user_Sex"));
			} catch (SQLException e) {
				// System.out.println("没有user_Sex");
			}

			try {
				user.setUser_Birthday(rs.getString("user_Birthday"));
			} catch (SQLException e) {
				// System.out.println("没有user_Birthday");
			}

			try {
				user.setUser_Phone(rs.getString("user_Phone"));
			} catch (SQLException e) {
				// System.out.println("没有user_Phone");
			}

			try {
				user.setUser_Address(rs.getString("user_Address"));
			} catch (SQLException e) {
				// System.out.println("没有user_Address");
			}

			try {
				user.setUser_Hometown(rs.getString("user_Hometown"));
			} catch (SQLException e) {
				// System.out.println("没有user_Hometown");
			}

			try {
				user.setUser_Other(rs.getString("user_Other"));
			} catch (SQLException e) {
				// System.out.println("没有user_Other");
			}

			users.add(user);

		}
		return users;
	}

	@Override
	/**
	 * @return 所有在线用户-ArrayList<User>
	 */
	public ArrayList<User> getAllOnlineUser() {
		String sql = "select * from user_Info where user_Online = ?";
		String[] params = { "在线" };
		return (ArrayList<User>) executeQuery(sql, params);
	}

	@Override
	/**
	 * 查询所有用户的账号，昵称，在线情况
	 * 
	 * @return 所有用户的账号，昵称，在线情况-ArrayList<User>
	 */
	public ArrayList<User> getAllUser() {
		String sql = "select user_Number,user_Nickname,user_Online from user_Info";
		return (ArrayList<User>) executeQuery(sql, null);
	}

	@Override
	/**
	 * 用于验证登录，按账号查询密码，昵称
	 * 
	 * @param 请求登录的账号-String-user_Number
	 * @return 查询到的账号的账号、密码、昵称的User对象-User
	 */
	public User getPasswordAndNicknameByNumber(String user_Number) {
		// 用于验证登录，按账号查询密码，昵称
		String sql = "select user_Number,user_Password,user_Nickname from user_Info where user_Number = ?";
		String[] params = { user_Number };
		User user = null;
		ArrayList<User> arrayList = (ArrayList<User>) executeQuery(sql, params);
		if (arrayList.isEmpty() == false) {
			user = arrayList.get(0);
		}
		if (user != null) {
			System.out.println("账号：" + user.getUser_Number() + "\n密码：" + user.getUser_Password() + "\n昵称："
					+ user.getUser_Nickname());
		}
		return user;
	}

	@Override
	/**
	 * 根据昵称查询用户的账号，昵称，在线情况
	 * 
	 * @param 需要查询的昵称-String-user_Nickname
	 * @return 查找到的用户的账号，昵称，在线情况-ArrayList<User>
	 */
	public ArrayList<User> getUserByNickname(String user_Nickname) {
		// 根据昵称查询用户的账号，昵称，在线情况
		String sql = "select user_Number,user_Nickname,user_Online from user_Info where user_Nickname LIKE ?";
		String[] params = { user_Nickname };
		return (ArrayList<User>) executeQuery(sql, params);
	}

	@Override
	/**
	 * 根据账号查询用户的账号，昵称，在线情况
	 * 
	 * @param 需要查询的账号-String-user_Number
	 * @return 查找到的用户的账号，昵称，在线情况-ArrayList<User>
	 */
	public ArrayList<User> getUserByNumber(String user_Number) {
		// 根据账号查询用户的账号，昵称，在线情况
		String sql = "select user_Number,user_Nickname,user_Online from user_Info where user_Number = ?";
		String[] params = { user_Number };
		return (ArrayList<User>) executeQuery(sql, params);
	}

	@Override
	/**
	 * 查询单个用户所有信息
	 * 
	 * @param 需要查询的账号-String-user_Number
	 * @return 该用户的所有信息-User
	 */
	public User getUserInfo(String user_Number) {
		// 查询单个用户所有信息
		String sql = "select * from user_Info where user_Number = ?";
		String[] params = { user_Number };
		User user = null;
		ArrayList<User> arrayList = (ArrayList<User>) executeQuery(sql, params);
		if (arrayList.isEmpty() == false) {
			user = arrayList.get(0);
		}
		return user;
	}

	@Override
	/**
	 * 注册用户
	 * 
	 * @param 注册的用户对象-User-user
	 * @return 注册得到的账号-String
	 */
	public String regUser(User user) {
		// String ret_Number = "0";
		// String sql = "INSERT INTO
		// user_Info(user_Nickname,user_Password,user_Sex,user_Birthday,user_Phone,user_Address,user_Hometown,user_Other)
		// VALUES(?,?,?,?,?,?,?,?);\n SELECT @@IDENTITY AS group_Number";
		// String[] params = { user.getUser_Nickname(), user.getUser_Password(),
		// user.getUser_Sex(),
		// user.getUser_Birthday(), user.getUser_Phone(),
		// user.getUser_Address(), user.getUser_Hometown(),
		// user.getUser_Other() };
		// executeSQLNoClose(sql, params);
		// sql = "SELECT @@IDENTITY AS group_Number";
		// params = null;
		// ArrayList<User> arr = (ArrayList<User>) executeQueryNoNewConn(sql,
		// params);
		// if (arr.isEmpty() == false) {
		// ret_Number = arr.get(0).getUser_Number();
		// }
		// closeAll();
		// System.out.println("UserDao" + ret_Number);
		// return ret_Number;
		return "";
	}

	@Override
	/**
	 * 更新用户信息
	 * 
	 * @param 需要更新信息的用户-User-user
	 */
	public void updateUser(User user) {
		// TODO 自动生成的方法存根
		System.out.println("修改用户信息");
		String sql = "UPDATE user_Info SET user_Password = ?,user_Nickname = ?,user_Sex = ?,user_Birthday = ?,user_Phone = ?,user_Address = ?,user_Hometown = ?,user_Other = ? WHERE user_Number = ?";
		String[] params = { user.getUser_Password(), user.getUser_Nickname(), user.getUser_Sex(),
				user.getUser_Birthday(), user.getUser_Phone(), user.getUser_Address(), user.getUser_Hometown(),
				user.getUser_Other(), user.getUser_Number() };
		executeSQL(sql, params);
	}

}
