/**
 * 数据库用户表操作接口
 */
package com.myJavaQQ.server.dao;

import java.util.ArrayList;

import com.myJavaQQ.common.User;

public interface UserImpl {

	/**
	 * @param 我的账号-String-myselfId
	 * @param 好友的账号-String-friendId
	 */
	public void addFriend(String myselfId, String friendId);

	/**
	 * 使所有用户离线
	 */
	public void allUserOffline();

	/**
	 * 改变用户的在线状态
	 * 
	 * @param 需要改变状态的用户账号-String-userId
	 * @param 改变成什么状态-String-online
	 */
	public void changeOnline(String userId, String online);

	/**
	 * @return 所有在线用户-ArrayList<User>
	 */
	public ArrayList<User> getAllOnlineUser();

	/**
	 * 查询所有用户的账号，昵称，在线情况
	 * 
	 * @return 所有用户的账号，昵称，在线情况-ArrayList<User>
	 */
	public ArrayList<User> getAllUser();

	/**
	 * 用于验证登录，按账号查询密码，昵称
	 * 
	 * @param 请求登录的账号-String-user_Number
	 * @return 查询到的账号的账号、密码、昵称的User对象-User
	 */
	public User getPasswordAndNicknameByNumber(String user_Number);

	/**
	 * 根据昵称查询用户的账号，昵称，在线情况
	 * 
	 * @param 需要查询的昵称-String-user_Nickname
	 * @return 查找到的用户的账号，昵称，在线情况-ArrayList<User>
	 */
	public ArrayList<User> getUserByNickname(String user_Nickname);

	/**
	 * 根据账号查询用户的账号，昵称，在线情况
	 * 
	 * @param 需要查询的账号-String-user_Number
	 * @return 查找到的用户的账号，昵称，在线情况-ArrayList<User>
	 */
	public ArrayList<User> getUserByNumber(String user_Number);

	/**
	 * 查询单个用户所有信息
	 * 
	 * @param 需要查询的账号-String-user_Number
	 * @return 该用户的所有信息-User
	 */
	public User getUserInfo(String user_Number);

	/**
	 * 注册用户
	 * 
	 * @param 注册的用户对象-User-user
	 * @return 注册得到的账号-String
	 */
	public String regUser(User user);

	/**
	 * 更新用户信息
	 * 
	 * @param 需要更新信息的用户-User-user
	 */
	public void updateUser(User user);

}
