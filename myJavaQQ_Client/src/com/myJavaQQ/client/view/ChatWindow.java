/**
 * ���촰��
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
	private String myself;// �ҵ�Id
	private String friend;// �����촰�ڵĺ��ѵ�Id
	private JTextArea text_ShowMsg;// ��ʾ��Ϣ���ı���
	private JTextArea text_SendMsg;// ���뷢����Ϣ�����ݵ��ı���

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
	 * �����ҵ�Id�ͺ��ѵ�Id����һ�������
	 * 
	 * @param �ҵ�Id��String-myself
	 * @param ���ѵ�Id��String-friend
	 */
	public ChatWindow(String myself, String friend) {
		this.myself = myself;
		this.friend = friend;
		// ���ô������弰����
		System.out.println("�Ѿ������촰��" + myself + "��" + friend);
		setTitle(myself + " ���ں� " + friend + " ����");
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
				 * �����Ͱ�ť��ӵļ����¼�
				 */
				new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						if (text_SendMsg.getText().trim().equals("")) {// ���ȥ��ͷβ�հ�Ϊ���ַ���
							System.out.println("��⵽�������û������");
						} else {
							// ���÷���������Ϣ
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
	 * �����������Ϣ
	 */
	public void sendChatMsg() {

		Message chatMsg = new Message(MessageTypes.message_Chat);
		chatMsg.setSender(myself);
		chatMsg.setGetter(friend);
		Date nowDate = new Date();// �õ���ǰ����ʱ��
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");// ���Է�����޸����ڸ�ʽ
		String nowDateStr = dateFormat.format(nowDate);// ������ʱ��ת����String
		chatMsg.setSenderTime(nowDateStr);
		chatMsg.setInfo((String) text_SendMsg.getText());
		text_SendMsg.setText("");
		System.out.println(chatMsg.getInfo());

		// ����ʾ���Լ����������
		String temp = text_ShowMsg.getText();
		text_ShowMsg.setText(temp + myself + "\t" + chatMsg.getSendTime() + "\n" + (String) chatMsg.getInfo() + "\n");

		// �ٷ���
		ManageQQClientThread.getQQClientThread(myself).sendMessage(chatMsg);
	}

	public void setText_ShowMsg(JTextArea text_ShowMsg) {
		this.text_ShowMsg = text_ShowMsg;
	}

}
