/**
 * 聊天窗口
 */
package com.myJavaQQ.client.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import com.myJavaQQ.client.tools.ManageQQClientThread;
import com.myJavaQQ.common.Message;
import com.myJavaQQ.common.MessageTypes;

public class ChatWindow extends JFrame {

	private JPanel contentPane;
	private String myself;// 我的Id
	private String friend;// 此聊天窗口的好友的Id
	private JTextArea text_ShowMsg;// 显示消息的文本框
	private JTextArea text_SendMsg;// 输入发送消息的内容的文本框

	// @Deprecated
	// public static void main(String[] args) {
	// ChatWindow frame = new ChatWindow();
	// }
	//
	// @Deprecated
	// public ChatWindow() {
	//
	// }

	/**
	 * 输入我的Id和好友的Id生成一个聊天框
	 * 
	 * @param 我的Id：String-myself
	 * @param 好友的Id：String-friend
	 */
	public ChatWindow(String myself, String friend) {
		this.myself = myself;
		this.friend = friend;
		// 设置窗口主体及属性
		System.out.println("已经打开聊天窗口" + myself + "：" + friend);
		setTitle(myself + " 正在和 " + friend + " 聊天");
		System.out.println(getTitle());
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 500, 400);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(102, 204, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 5));

		JScrollPane scrollPane_1 = new JScrollPane();
		contentPane.add(scrollPane_1, BorderLayout.CENTER);

		text_ShowMsg = new JTextArea();
		text_ShowMsg.setEditable(false);
		text_ShowMsg.setText("");
		scrollPane_1.setViewportView(text_ShowMsg);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(102, 204, 255));
		contentPane.add(panel, BorderLayout.SOUTH);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] { 0, 50, 0 };
		gbl_panel.rowHeights = new int[] { 50, 0 };
		gbl_panel.columnWeights = new double[] { 1.0, 0.0, Double.MIN_VALUE };
		gbl_panel.rowWeights = new double[] { 1.0, Double.MIN_VALUE };
		panel.setLayout(gbl_panel);

		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.insets = new Insets(0, 0, 0, 5);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 0;
		panel.add(scrollPane, gbc_scrollPane);

		text_SendMsg = new JTextArea();
		scrollPane.setViewportView(text_SendMsg);

		JButton button = new JButton("\u53D1\u9001");
		button.addActionListener(
				/**
				 * 给发送按钮添加的监听事件
				 */
				new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						if (text_SendMsg.getText().trim().equals("")) {// 如果去掉头尾空白为空字符串
							System.out.println("检测到输入框内没有内容");
						} else {
							// 调用发送聊天信息
							sendChatMsg();
						}
					}
				});
		GridBagConstraints gbc_button = new GridBagConstraints();
		gbc_button.fill = GridBagConstraints.VERTICAL;
		gbc_button.gridx = 1;
		gbc_button.gridy = 0;
		panel.add(button, gbc_button);

		setVisible(true);
	}

	public JTextArea getText_ShowMsg() {
		return text_ShowMsg;
	}

	/**
	 * 发送聊天的信息
	 */
	public void sendChatMsg() {

		Message chatMsg = new Message(MessageTypes.message_Chat);
		chatMsg.setSender(myself);
		chatMsg.setGetter(friend);
		Date nowDate = new Date();// 得到当前日期时间
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");// 可以方便地修改日期格式
		String nowDateStr = dateFormat.format(nowDate);// 将日期时间转换成String
		chatMsg.setSenderTime(nowDateStr);
		chatMsg.setInfo((String) text_SendMsg.getText());
		text_SendMsg.setText("");
		System.out.println(chatMsg.getInfo());

		// 先显示在自己的聊天框中
		String temp = text_ShowMsg.getText();
		text_ShowMsg.setText(temp + myself + "\t" + chatMsg.getSendTime() + "\n" + (String) chatMsg.getInfo() + "\n");

		// 再发送
		ManageQQClientThread.getQQClientThread(myself).sendMessage(chatMsg);
	}

	public void setText_ShowMsg(JTextArea text_ShowMsg) {
		this.text_ShowMsg = text_ShowMsg;
	}

}
