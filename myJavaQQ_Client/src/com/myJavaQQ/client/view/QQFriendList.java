/**
 * QQ好友列表
 */
package com.myJavaQQ.client.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import com.myJavaQQ.client.model.QQClientUserThread;
import com.myJavaQQ.client.tools.ManageChatWindows;
import com.myJavaQQ.client.tools.ManageQQClientThread;
import com.myJavaQQ.common.Message;
import com.myJavaQQ.common.MessageTypes;
import com.myJavaQQ.common.User;

public class QQFriendList extends JFrame {

	private JPanel contentPane;
	private JPanel panel_FriendsList;// 显示好友列表的Panel
	private String userId;// 此好友列表窗口的userId
	private QQClientUserThread thisClient;// 此好友窗口对应的客户端线程
	private User myself;// 存着我的账号昵称的
	private ManageChatWindows mcw;// 管理此用户的聊天窗口

	/**
	 * 以对应用户创建一个好友列表
	 * 
	 * @param 对应用户User-myself
	 */
	public QQFriendList(User myself) {
		this.myself = myself;
		this.userId = myself.getUser_Number();
		mcw = new ManageChatWindows();

		// 设置窗口主体以及属性
		setAlwaysOnTop(true);
		setTitle(myself.getUser_Nickname());

		// 关闭窗口事件监听器
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				super.windowClosing(e);
				System.out.println("退出");
				exit();
			}
		});

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 200, 500);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(102, 204, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		// 中部好友列表
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);
		panel_FriendsList = new JPanel();
		panel_FriendsList.setBackground(new Color(102, 204, 255));
		scrollPane.setViewportView(panel_FriendsList);
		panel_FriendsList.setLayout(new BoxLayout(panel_FriendsList, BoxLayout.Y_AXIS));

		// 底部两个按钮
		JPanel panel_Down = new JPanel();
		panel_Down.setBackground(new Color(102, 204, 255));
		contentPane.add(panel_Down, BorderLayout.SOUTH);
		panel_Down.setLayout(new GridLayout(2, 1, 0, 0));
		JButton btn_ReFriendsList = new JButton("刷新好友列表");
		btn_ReFriendsList.addActionListener(
				/**
				 * 点击刷新好友列表进行处理
				 */
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						getFriendsList();
					}
				});
		panel_Down.add(btn_ReFriendsList);
		JButton btn_AddFriend = new JButton("添加好友");
		btn_AddFriend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new AddFriend(userId);
			}
		});
		panel_Down.add(btn_AddFriend);

		// 顶部个人信息
		JPanel panel_Top = new JPanel();
		panel_Top.addMouseListener(new MouseAdapter() {
			/**
			 * 给顶部个人信息添加点击事件监听
			 */
			@Override
			public void mouseClicked(MouseEvent arg0) {
				System.out.println("点击了个人信息");
			}
		});
		panel_Top.setBackground(new Color(102, 204, 255));
		panel_Top.setSize(195, 100);
		contentPane.add(panel_Top, BorderLayout.NORTH);
		panel_Top.setLayout(new BorderLayout(0, 0));
		JLabel lbl_touxiang = new JLabel(new ImageIcon("Image/touxiang.png"));
		panel_Top.add(lbl_touxiang, BorderLayout.WEST);
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(new Color(102, 204, 255));
		panel_Top.add(panel_3, BorderLayout.CENTER);
		panel_3.setLayout(new BorderLayout(0, 0));
		JLabel lbl_myNickname = new JLabel("昵称");
		panel_3.add(lbl_myNickname, BorderLayout.NORTH);
		JLabel lbl_myNumber = new JLabel("账号");
		panel_3.add(lbl_myNumber, BorderLayout.CENTER);

		// 显示昵称账号在顶部个人信息中
		if (myself.getUser_Nickname() != null) {
			lbl_myNickname.setText(myself.getUser_Nickname());
		}
		if (myself.getUser_Number() != null) {
			lbl_myNumber.setText(myself.getUser_Number());
		}

		this.setVisible(true);
	}

	public void exit() {
		QQClientUserThread qct = ManageQQClientThread.getQQClientThread(userId);
		ManageQQClientThread.removeQQClientThread(userId);
		qct.setFlag(false);
		Message exitMsg = new Message(MessageTypes.message_Exit);
		exitMsg.setSender(userId);
		qct.sendMessage(exitMsg);
		try {
			qct.getSocket().close();
			qct.stop();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			System.exit(0);
		}
	}

	/**
	 * 发送好友验证请求
	 */
	public void getFriendsList() {
		Message sendMsg = new Message(MessageTypes.message_Get_Friends);
		sendMsg.setSender(userId);
		// ClientConServerThread ccst =
		// ManageClientConServerThread.getClientConServerThread(userId);
		// ccst.sendMsg(sendMsg);
		this.sendMessage(sendMsg);
	}

	public ManageChatWindows getMcw() {
		return mcw;
	}

	/**
	 * 发送消息，调用客户端线程里的方法
	 * 
	 * @param 需要发送的信息Message-sendMsg
	 */
	private void sendMessage(Message sendMsg) {
		System.out.println(sendMsg.getMessageType());
		QQClientUserThread qcut = ManageQQClientThread.getQQClientThread(userId);
		qcut.sendMessage(sendMsg);
	}

	public void setMcw(ManageChatWindows mcw) {
		this.mcw = mcw;
	}

	/**
	 * 显示一个20 人的好友列表
	 */
	@Deprecated
	private void showFriends() {
		panel_FriendsList.setLayout(new GridLayout(20, 1, 5, 5));
		for (int i = 1; i <= 20; i++) {
			JLabel label = new JLabel(i + "", new ImageIcon("Image/touxiang20px.png"), JLabel.LEFT);
			panel_FriendsList.add(label);
		}
	}

	/**
	 * 显示好友列表
	 * 
	 * @param 好友列表ArrayList<User>-friendList
	 */
	public void showFriends(ArrayList<User> friendList) {
		System.out.println("显示好友列表");
		panel_FriendsList.removeAll();
		for (int i = 0; i < friendList.size(); i++) {
			User friend = friendList.get(i);
			JButton btn_Friend = new JButton(friend.getUser_Nickname() + "(" + friend.getUser_Number() + ")",
					new ImageIcon("Image/touxiang20px.png"));
			panel_FriendsList.add(btn_Friend);
			btn_Friend.setToolTipText(userId + " " + friend.getUser_Number());
			btn_Friend.addActionListener(
					/**
					 * 给每个好友按钮添加监听事件
					 */
					new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							JButton temp = (JButton) e.getSource();
							System.out.println(temp.getToolTipText());
							String IandFstr = temp.getToolTipText();
							String[] IandFarr = IandFstr.split(" ");
							ChatWindow cw = new ChatWindow(IandFarr[0], IandFarr[1]);
							mcw.addChatWindow(IandFarr[1], cw);
						}
					});
		}
		panel_FriendsList.updateUI();
	}

}
