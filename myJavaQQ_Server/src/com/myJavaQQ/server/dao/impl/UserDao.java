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
	 * @param �ҵ��˺�-String-myselfId
	 * @param ���ѵ��˺�-String-friendId
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
	 * ʹ�����û�����
	 */
	public void allUserOffline() {
		String sql = "UPDATE user_Info SET user_Online = ? WHERE user_Online = ?";
		String[] params = { "����", "����" };
		executeSQL(sql, params);
	}

	@Override
	/**
	 * �ı��û�������״̬
	 * 
	 * @param ��Ҫ�ı�״̬���û��˺�-String-userId
	 * @param �ı��ʲô״̬-String-online
	 */
	public void changeOnline(String userId, String online) {
		// TODO �Զ����ɵķ������
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
				// System.out.println("û��user_Number");
			}

			try {
				user.setUser_Password(rs.getString("user_Password"));
			} catch (SQLException e) {
				// System.out.println("û��user_Password");
			}

			try {
				user.setUser_Regdate(rs.getString("user_Regdate"));
			} catch (SQLException e) {
				// System.out.println("û��user_Regdate");
			}

			try {
				user.setUser_Nickname(rs.getString("user_Nickname"));
			} catch (SQLException e) {
				// System.out.println("û��user_Nickname");
			}

			try {
				user.setUser_Online(rs.getString("user_Online"));
			} catch (SQLException e) {
				// System.out.println("û��user_Online");
			}

			try {
				user.setUser_Sex(rs.getString("user_Sex"));
			} catch (SQLException e) {
				// System.out.println("û��user_Sex");
			}

			try {
				user.setUser_Birthday(rs.getString("user_Birthday"));
			} catch (SQLException e) {
				// System.out.println("û��user_Birthday");
			}

			try {
				user.setUser_Phone(rs.getString("user_Phone"));
			} catch (SQLException e) {
				// System.out.println("û��user_Phone");
			}

			try {
				user.setUser_Address(rs.getString("user_Address"));
			} catch (SQLException e) {
				// System.out.println("û��user_Address");
			}

			try {
				user.setUser_Hometown(rs.getString("user_Hometown"));
			} catch (SQLException e) {
				// System.out.println("û��user_Hometown");
			}

			try {
				user.setUser_Other(rs.getString("user_Other"));
			} catch (SQLException e) {
				// System.out.println("û��user_Other");
			}

			users.add(user);

		}
		return users;
	}

	@Override
	/**
	 * @return ���������û�-ArrayList<User>
	 */
	public ArrayList<User> getAllOnlineUser() {
		String sql = "select * from user_Info where user_Online = ?";
		String[] params = { "����" };
		return (ArrayList<User>) executeQuery(sql, params);
	}

	@Override
	/**
	 * ��ѯ�����û����˺ţ��ǳƣ��������
	 * 
	 * @return �����û����˺ţ��ǳƣ��������-ArrayList<User>
	 */
	public ArrayList<User> getAllUser() {
		String sql = "select user_Number,user_Nickname,user_Online from user_Info";
		return (ArrayList<User>) executeQuery(sql, null);
	}

	@Override
	/**
	 * ������֤��¼�����˺Ų�ѯ���룬�ǳ�
	 * 
	 * @param �����¼���˺�-String-user_Number
	 * @return ��ѯ�����˺ŵ��˺š����롢�ǳƵ�User����-User
	 */
	public User getPasswordAndNicknameByNumber(String user_Number) {
		// ������֤��¼�����˺Ų�ѯ���룬�ǳ�
		String sql = "select user_Number,user_Password,user_Nickname from user_Info where user_Number = ?";
		String[] params = { user_Number };
		User user = null;
		ArrayList<User> arrayList = (ArrayList<User>) executeQuery(sql, params);
		if (arrayList.isEmpty() == false) {
			user = arrayList.get(0);
		}
		if (user != null) {
			System.out.println("�˺ţ�" + user.getUser_Number() + "\n���룺" + user.getUser_Password() + "\n�ǳƣ�"
					+ user.getUser_Nickname());
		}
		return user;
	}

	@Override
	/**
	 * �����ǳƲ�ѯ�û����˺ţ��ǳƣ��������
	 * 
	 * @param ��Ҫ��ѯ���ǳ�-String-user_Nickname
	 * @return ���ҵ����û����˺ţ��ǳƣ��������-ArrayList<User>
	 */
	public ArrayList<User> getUserByNickname(String user_Nickname) {
		// �����ǳƲ�ѯ�û����˺ţ��ǳƣ��������
		String sql = "select user_Number,user_Nickname,user_Online from user_Info where user_Nickname LIKE ?";
		String[] params = { user_Nickname };
		return (ArrayList<User>) executeQuery(sql, params);
	}

	@Override
	/**
	 * �����˺Ų�ѯ�û����˺ţ��ǳƣ��������
	 * 
	 * @param ��Ҫ��ѯ���˺�-String-user_Number
	 * @return ���ҵ����û����˺ţ��ǳƣ��������-ArrayList<User>
	 */
	public ArrayList<User> getUserByNumber(String user_Number) {
		// �����˺Ų�ѯ�û����˺ţ��ǳƣ��������
		String sql = "select user_Number,user_Nickname,user_Online from user_Info where user_Number = ?";
		String[] params = { user_Number };
		return (ArrayList<User>) executeQuery(sql, params);
	}

	@Override
	/**
	 * ��ѯ�����û�������Ϣ
	 * 
	 * @param ��Ҫ��ѯ���˺�-String-user_Number
	 * @return ���û���������Ϣ-User
	 */
	public User getUserInfo(String user_Number) {
		// ��ѯ�����û�������Ϣ
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
	 * ע���û�
	 * 
	 * @param ע����û�����-User-user
	 * @return ע��õ����˺�-String
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
	 * �����û���Ϣ
	 * 
	 * @param ��Ҫ������Ϣ���û�-User-user
	 */
	public void updateUser(User user) {
		// TODO �Զ����ɵķ������
		System.out.println("�޸��û���Ϣ");
		String sql = "UPDATE user_Info SET user_Password = ?,user_Nickname = ?,user_Sex = ?,user_Birthday = ?,user_Phone = ?,user_Address = ?,user_Hometown = ?,user_Other = ? WHERE user_Number = ?";
		String[] params = { user.getUser_Password(), user.getUser_Nickname(), user.getUser_Sex(),
				user.getUser_Birthday(), user.getUser_Phone(), user.getUser_Address(), user.getUser_Hometown(),
				user.getUser_Other(), user.getUser_Number() };
		executeSQL(sql, params);
	}

}
