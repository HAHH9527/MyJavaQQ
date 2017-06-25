package com.myJavaQQ.server.view;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.myJavaQQ.common.User;
import com.myJavaQQ.server.dao.impl.UserDao;

public class UserInfoFrame extends JFrame {

	private JPanel contentPane;
	private JLabel lbl_user_Number, lbl_user_Password, lbl_user_Regdate, lbl_user_Nickname, lbl_user_Online,
			lbl_user_Sex, lbl_user_Birthday, lbl_user_Phone, lbl_user_Address, lbl_user_Hometown, lbl_user_Other;
	private JTextField text_user_Number, text_user_Password, text_user_Regdate, text_user_Nickname, text_user_Online,
			text_user_Sex, text_user_Birthday, text_user_Phone, text_user_Address, text_user_Hometown;
	private JScrollPane scrollPane;
	private JTextArea text_user_Other;

	private JButton btn_update, btn_EXIT;

	public UserInfoFrame() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 290, 660);
		setTitle("用户信息");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(12, 2, 0, 10));

		lbl_user_Number = new JLabel("账号：");
		contentPane.add(lbl_user_Number);

		text_user_Number = new JTextField();
		text_user_Number.setEditable(false);
		contentPane.add(text_user_Number);

		lbl_user_Password = new JLabel("密码*：");
		contentPane.add(lbl_user_Password);

		text_user_Password = new JTextField();
		contentPane.add(text_user_Password);

		lbl_user_Regdate = new JLabel("注册日期：");
		contentPane.add(lbl_user_Regdate);

		text_user_Regdate = new JTextField();
		text_user_Regdate.setEditable(false);
		contentPane.add(text_user_Regdate);

		lbl_user_Nickname = new JLabel("昵称*：");
		contentPane.add(lbl_user_Nickname);

		text_user_Nickname = new JTextField();
		contentPane.add(text_user_Nickname);

		lbl_user_Online = new JLabel("在线情况：");
		contentPane.add(lbl_user_Online);

		text_user_Online = new JTextField();
		text_user_Online.setEditable(false);
		contentPane.add(text_user_Online);

		lbl_user_Sex = new JLabel("性别*：");
		contentPane.add(lbl_user_Sex);

		text_user_Sex = new JTextField();
		contentPane.add(text_user_Sex);
		text_user_Sex.setColumns(10);

		lbl_user_Birthday = new JLabel("生日：");
		contentPane.add(lbl_user_Birthday);

		text_user_Birthday = new JTextField();
		contentPane.add(text_user_Birthday);

		lbl_user_Phone = new JLabel("手机：");
		contentPane.add(lbl_user_Phone);

		text_user_Phone = new JTextField();
		contentPane.add(text_user_Phone);

		lbl_user_Address = new JLabel("地址：");
		contentPane.add(lbl_user_Address);

		text_user_Address = new JTextField();
		contentPane.add(text_user_Address);

		lbl_user_Hometown = new JLabel("家乡：");
		contentPane.add(lbl_user_Hometown);

		text_user_Hometown = new JTextField();
		contentPane.add(text_user_Hometown);

		lbl_user_Other = new JLabel("其他：");
		contentPane.add(lbl_user_Other);

		scrollPane = new JScrollPane();
		contentPane.add(scrollPane);

		text_user_Other = new JTextArea();
		scrollPane.setViewportView(text_user_Other);

		btn_update = new JButton("确认修改");
		btn_update.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ok();
			}
		});
		contentPane.add(btn_update);

		btn_EXIT = new JButton("退出");
		btn_EXIT.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				exit();
			}
		});
		contentPane.add(btn_EXIT);

		setVisible(true);
	}

	private void exit() {
		dispose();
	}

	public JTextField getText_user_Address() {
		return text_user_Address;
	}

	public JTextField getText_user_Birthday() {
		return text_user_Birthday;
	}

	public JTextField getText_user_Hometown() {
		return text_user_Hometown;
	}

	public JTextField getText_user_Nickname() {
		return text_user_Nickname;
	}

	public JTextField getText_user_Number() {
		return text_user_Number;
	}

	public JTextField getText_user_Online() {
		return text_user_Online;
	}

	public JTextArea getText_user_Other() {
		return text_user_Other;
	}

	public JTextField getText_user_Password() {
		return text_user_Password;
	}

	public JTextField getText_user_Phone() {
		return text_user_Phone;
	}

	public JTextField getText_user_Regdate() {
		return text_user_Regdate;
	}

	public JTextField getText_user_Sex() {
		return text_user_Sex;
	}

	private void ok() {
		User user = new User();
		user.setUser_Number(text_user_Number.getText());
		user.setUser_Password(text_user_Password.getText());
		user.setUser_Nickname(text_user_Nickname.getText());
		user.setUser_Sex(text_user_Sex.getText());
		user.setUser_Birthday(text_user_Birthday.getText());
		user.setUser_Phone(text_user_Phone.getText());
		user.setUser_Address(text_user_Address.getText());
		user.setUser_Hometown(text_user_Hometown.getText());
		user.setUser_Other(text_user_Other.getText());
		UserDao userDao = new UserDao();
		userDao.updateUser(user);
		dispose();
	}

}
