package com.myJavaQQ.server.dao.impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.myJavaQQ.common.User;
import com.myJavaQQ.server.dao.FriendsImpl;

public class FriendsDao extends BaseDao implements FriendsImpl {

	@Override
	public List createObject(ResultSet rs) throws Exception {
		List<User> friendList = new ArrayList<User>();
		while (rs.next()) {
			User friend = new User();
			friend.setUser_Number(rs.getString("user_Number"));
			friend.setUser_Nickname(rs.getString("user_Nickname"));
			friend.setUser_Online(rs.getString("user_Online"));
			friendList.add(friend);
		}
		return friendList;
	}

	@Override
	/**
	 * 按账号查找好友列表
	 * 
	 * @param 需要查找的账号-String-user_Number
	 * @return 好友列表-ArrayList<User>
	 */
	public ArrayList<User> getFriendsListByNumber(String user_Number) {
		String sql = "SELECT I.user_Number,I.user_Nickname,I.user_Online FROM user_Info AS I INNER JOIN user_Friends AS F ON F.friend_Number = I.user_Number WHERE F.myself_Number = ?";
		String[] params = { user_Number };
		return (ArrayList<User>) executeQuery(sql, params);
	}

}
