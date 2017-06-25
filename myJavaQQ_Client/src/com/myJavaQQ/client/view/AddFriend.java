package com.myJavaQQ.client.view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.myJavaQQ.client.tools.ManageQQClientThread;
import com.myJavaQQ.common.Message;
import com.myJavaQQ.common.MessageTypes;

public class AddFriend extends JFrame {

	private String userId;
	private JPanel contentPane;
	private JTextField text_Number;
	private JButton btn_OK;

	public AddFriend(String userId) {
		this.userId = userId;
		setTitle("ÃÌº”∫√”—");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 70);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(102, 204, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.X_AXIS));

		JLabel lbl = new JLabel("’À∫≈£∫");
		lbl.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lbl);

		text_Number = new JTextField();
		text_Number.setHorizontalAlignment(SwingConstants.LEFT);
		contentPane.add(text_Number);
		text_Number.setColumns(1);

		btn_OK = new JButton("ÃÌº”");
		btn_OK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AddFriend();
			}
		});
		contentPane.add(btn_OK);

		setVisible(true);
	}

	private void AddFriend() {
		Message sendMsg = new Message(MessageTypes.message_Add_Friend);
		sendMsg.setSender(userId);
		sendMsg.setGetter(text_Number.getText());
		ManageQQClientThread.getQQClientThread(userId).sendMessage(sendMsg);
		dispose();
	}

}
