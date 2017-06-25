/**
 * ���ݿ��û�������ӿ�
 */
package com.myJavaQQ.server.dao;

import java.util.ArrayList;

import com.myJavaQQ.common.User;

public interface UserImpl {

	/**
	 * @param �ҵ��˺�-String-myselfId
	 * @param ���ѵ��˺�-String-friendId
	 */
	public void addFriend(String myselfId, String friendId);

	/**
	 * ʹ�����û�����
	 */
	public void allUserOffline();

	/**
	 * �ı��û�������״̬
	 * 
	 * @param ��Ҫ�ı�״̬���û��˺�-String-userId
	 * @param �ı��ʲô״̬-String-online
	 */
	public void changeOnline(String userId, String online);

	/**
	 * @return ���������û�-ArrayList<User>
	 */
	public ArrayList<User> getAllOnlineUser();

	/**
	 * ��ѯ�����û����˺ţ��ǳƣ��������
	 * 
	 * @return �����û����˺ţ��ǳƣ��������-ArrayList<User>
	 */
	public ArrayList<User> getAllUser();

	/**
	 * ������֤��¼�����˺Ų�ѯ���룬�ǳ�
	 * 
	 * @param �����¼���˺�-String-user_Number
	 * @return ��ѯ�����˺ŵ��˺š����롢�ǳƵ�User����-User
	 */
	public User getPasswordAndNicknameByNumber(String user_Number);

	/**
	 * �����ǳƲ�ѯ�û����˺ţ��ǳƣ��������
	 * 
	 * @param ��Ҫ��ѯ���ǳ�-String-user_Nickname
	 * @return ���ҵ����û����˺ţ��ǳƣ��������-ArrayList<User>
	 */
	public ArrayList<User> getUserByNickname(String user_Nickname);

	/**
	 * �����˺Ų�ѯ�û����˺ţ��ǳƣ��������
	 * 
	 * @param ��Ҫ��ѯ���˺�-String-user_Number
	 * @return ���ҵ����û����˺ţ��ǳƣ��������-ArrayList<User>
	 */
	public ArrayList<User> getUserByNumber(String user_Number);

	/**
	 * ��ѯ�����û�������Ϣ
	 * 
	 * @param ��Ҫ��ѯ���˺�-String-user_Number
	 * @return ���û���������Ϣ-User
	 */
	public User getUserInfo(String user_Number);

	/**
	 * ע���û�
	 * 
	 * @param ע����û�����-User-user
	 * @return ע��õ����˺�-String
	 */
	public String regUser(User user);

	/**
	 * �����û���Ϣ
	 * 
	 * @param ��Ҫ������Ϣ���û�-User-user
	 */
	public void updateUser(User user);

}
