/**
 * ���ݿ���ѱ�����ӿ�
 */
package com.myJavaQQ.server.dao;

import java.util.ArrayList;

import com.myJavaQQ.common.User;

public interface FriendsImpl {

	/**
	 * ���˺Ų��Һ����б�
	 * 
	 * @param ��Ҫ���Һ����б���û���user_Number-String-user_Number
	 * @return �����б�-ArrayList<User>
	 */
	public ArrayList<User> getFriendsListByNumber(String user_Number);

}
