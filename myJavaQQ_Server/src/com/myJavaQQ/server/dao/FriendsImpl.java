/**
 * 数据库好友表操作接口
 */
package com.myJavaQQ.server.dao;

import java.util.ArrayList;

import com.myJavaQQ.common.User;

public interface FriendsImpl {

	/**
	 * 按账号查找好友列表
	 * 
	 * @param 需要查找好友列表的用户的user_Number-String-user_Number
	 * @return 好友列表-ArrayList<User>
	 */
	public ArrayList<User> getFriendsListByNumber(String user_Number);

}
