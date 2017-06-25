# MyJavaQQ
  个人邮箱：1024830255@qq.com<br>
  有看不懂的问题可以来问我<br>
  可以转载，但是请注明来源<br>

## 项目介绍
  这是我利用JAVA实现的一个简易的山寨QQ<br>
  功能实现比较少，可能不会再有更新了，因为这只是一个期末的项目<br>
  
## 测试环境
  Eclipse Neon.3 Release (4.6.3)<br>
  Java 1.8.0_131<br>
  MySQL 5.6.36<br>

## 实现功能

### 客户端

  #### 登录功能
    验证登录
    
  #### 好友请求功能
    发送好友请求自动同意（往数据库中增加数据）
  
  #### 收发消息功能
    必须双方都在线，而且都打开了对方的对话窗口
    
  #### 主要类说明
  	其中
    com.myJavaQQ.client.view下的QQLogin类为客户端主类（登录界面）
		com.myJavaQQ.client.model下的QQClientUserThread类为保持通讯，收发信息类
		com.myJavaQQ.client.tools下的ManageQQClientThread类用HashMap储存每一个用户的QQClientUserThread线程
		com.myJavaQQ.client.tools下的ManageChatWindow类用HashMap储存一个用户的多个对话窗口
  
  -------
  
### 服务端

  #### 查询用户
    其中按昵称查询使用MySQL中的LIKE语法进行模糊查询
    
  #### 查看/修改用户信息
    选择列表中的用户（只有在选择一个的时候才会响应按钮事件）
    查看/修改对应用户信息
    
  #### 使用户强制下线
    选择列表中的用户（只有在选择一个的时候才会响应按钮事件）
    使其强制下线
    
  #### 主要类说明
  	其中
    com.myJavaQQ.server.view下的MyJavaQQ_ServerFrame类为服务端主类（服务端管理界面）
		com.myJavaQQ.server.model下的MyQQServer类为服务端打开新通讯的核心类
		com.myJavaQQ.server.model下的ServerConClientThread类为与已登录用户保持通讯，收发信息的线程类
		com.myJavaQQ.server.tools下的ManageServerConClientThread类用HashMap储存服务端与多个用户通讯的ServerConClientThread类
		com.myJavaQQ.server.dao储存数据库操作接口
		com.myJavaQQ.server.dao.impl储存数据库操作实现功能

## 结语
  这只是一个学生党花了一个半星期完成的项目，<br>
  许多功能（比如注册，新消息提醒，好友是否在线，群聊天）都还没有实现<br>
  以后有机会的话可能来完善这个项目（目测没有机会吧）<br>
  个人邮箱：1024830255@qq.com<br>
  有看不懂的问题可以来问我<br>
  可以转载，但是请注明来源<br>
  最后，如果有在此源码上实现新功能/改进现有功能的话请把源码发我一份，感激不尽！<br>
  
