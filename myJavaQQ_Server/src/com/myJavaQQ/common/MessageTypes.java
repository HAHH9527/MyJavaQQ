/**
 * 消息类型的定义
 */
package com.myJavaQQ.common;

public interface MessageTypes {
	String message_Regist = "regist";// 注册信息包
	String message_Ret_Regist = "ret_regist";// 注册信息返回包
	String message_Login = "login";// 登录信息包
	String message_Ret_Login = "ret_Login";// 登录结果返回包
	String message_Chat = "chat";// 聊天信息包
	String message_Get_UserInfo = "get_UserInfo";// 取得用户信息信息包
	String message_Ret_UserInfo = "ret_UserInfo";// 返回用户信息包
	String message_Add_Friend = "add_Friend";// 添加好友请求包
	String message_Get_Friends = "get_Friends";// 取得好友列表包
	String message_Ret_Friends = "ret_Friends";// 返回好友列表包
	String message_Ret_Err = "ret_Err";// 返回错误提示包
	String message_Exit = "exit";// 离线包
	String message_Get_Online = "get_Online";// 取得在线情况
	String message_Ret_Online = "ret_Online";// 返回在线情况
}
