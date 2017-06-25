/**
 * QQ�����б�
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
	private JPanel panel_FriendsList;// ��ʾ�����б��Panel
	private String userId;// �˺����б��ڵ�userId
	private QQClientUserThread thisClient;// �˺��Ѵ��ڶ�Ӧ�Ŀͻ����߳�
	private User myself;// �����ҵ��˺��ǳƵ�
	private ManageChatWindows mcw;// ������û������촰��

	/**
	 * �Զ�Ӧ�û�����һ�������б�
	 * 
	 * @param ��Ӧ�û�User-myself
	 */
	public QQFriendList(User myself) {
		this.myself = myself;
		this.userId = myself.getUser_Number();
		mcw = new ManageChatWindows();

		// ���ô��������Լ�����
		setAlwaysOnTop(true);
		setTitle(myself.getUser_Nickname());

		// �رմ����¼�������
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				super.windowClosing(e);
				System.out.println("�˳�");
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

		// �в������б�
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);
		panel_FriendsList = new JPanel();
		panel_FriendsList.setBackground(new Color(102, 204, 255));
		scrollPane.setViewportView(panel_FriendsList);
		panel_FriendsList.setLayout(new BoxLayout(panel_FriendsList, BoxLayout.Y_AXIS));

		// �ײ�������ť
		JPanel panel_Down = new JPanel();
		panel_Down.setBackground(new Color(102, 204, 255));
		contentPane.add(panel_Down, BorderLayout.SOUTH);
		panel_Down.setLayout(new GridLayout(2, 1, 0, 0));
		JButton btn_ReFriendsList = new JButton("ˢ�º����б�");
		btn_ReFriendsList.addActionListener(
				/**
				 * ���ˢ�º����б���д���
				 */
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						getFriendsList();
					}
				});
		panel_Down.add(btn_ReFriendsList);
		JButton btn_AddFriend = new JButton("��Ӻ���");
		btn_AddFriend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new AddFriend(userId);
			}
		});
		panel_Down.add(btn_AddFriend);

		// ����������Ϣ
		JPanel panel_Top = new JPanel();
		panel_Top.addMouseListener(new MouseAdapter() {
			/**
			 * ������������Ϣ��ӵ���¼�����
			 */
			@Override
			public void mouseClicked(MouseEvent arg0) {
				System.out.println("����˸�����Ϣ");
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
		JLabel lbl_myNickname = new JLabel("�ǳ�");
		panel_3.add(lbl_myNickname, BorderLayout.NORTH);
		JLabel lbl_myNumber = new JLabel("�˺�");
		panel_3.add(lbl_myNumber, BorderLayout.CENTER);

		// ��ʾ�ǳ��˺��ڶ���������Ϣ��
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
	 * ���ͺ�����֤����
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
	 * ������Ϣ�����ÿͻ����߳���ķ���
	 * 
	 * @param ��Ҫ���͵���ϢMessage-sendMsg
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
	 * ��ʾһ��20 �˵ĺ����б�
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
	 * ��ʾ�����б�
	 * 
	 * @param �����б�ArrayList<User>-friendList
	 */
	public void showFriends(ArrayList<User> friendList) {
		System.out.println("��ʾ�����б�");
		panel_FriendsList.removeAll();
		for (int i = 0; i < friendList.size(); i++) {
			User friend = friendList.get(i);
			JButton btn_Friend = new JButton(friend.getUser_Nickname() + "(" + friend.getUser_Number() + ")",
					new ImageIcon("Image/touxiang20px.png"));
			panel_FriendsList.add(btn_Friend);
			btn_Friend.setToolTipText(userId + " " + friend.getUser_Number());
			btn_Friend.addActionListener(
					/**
					 * ��ÿ�����Ѱ�ť��Ӽ����¼�
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
