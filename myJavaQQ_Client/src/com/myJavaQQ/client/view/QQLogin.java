/**
 * QQ登录界面
 */
package com.myJavaQQ.client.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.myJavaQQ.client.model.QQClientUserThread;
import com.myJavaQQ.client.tools.ManageQQClientThread;
import com.myJavaQQ.common.Message;
import com.myJavaQQ.common.MessageTypes;
import com.myJavaQQ.common.User;

public class QQLogin extends JFrame {

	public static void main(String[] args) {
		new QQLogin();
	}
	private JPanel contentPane;
	private JTextField text_myNumber;
	private JPasswordField pass_myPassword;
	private String ServerIP = "127.0.0.1";

	private QQLogin frame;
	private JPanel panelCenter, panelQQ;

	private JLabel lblNewLabel_1, QQtextImg;

	public QQLogin() {
		frame = this;
		setAlwaysOnTop(true);
		setTitle("欢迎使用JQQ");
		setResizable(false);
		// 设置窗口主体及属性
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 250);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(102, 204, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		// 处理中间账号密码部分
		panelCenter = new JPanel();
		panelCenter.setBackground(new Color(102, 204, 255));
		contentPane.add(panelCenter, BorderLayout.CENTER);
		GridBagLayout gbl_panelCenter = new GridBagLayout();
		gbl_panelCenter.columnWidths = new int[] { 50, 200, 50 };
		gbl_panelCenter.rowHeights = new int[] { 0 };
		gbl_panelCenter.columnWeights = new double[] { 0.0, 0.0, 0.0 };
		gbl_panelCenter.rowWeights = new double[] { 0.0, 0.0, 0.0 };
		panelCenter.setLayout(gbl_panelCenter);
		// 上方QQ图标
		panelQQ = new JPanel();
		GridBagConstraints gbc_panelQQ = new GridBagConstraints();
		gbc_panelQQ.insets = new Insets(0, 0, 5, 5);
		gbc_panelQQ.gridx = 1;
		gbc_panelQQ.gridy = 0;
		panelCenter.add(panelQQ, gbc_panelQQ);
		panelQQ.setBackground(new Color(102, 204, 255));
		QQtextImg = new JLabel(new ImageIcon("Image/dengluTop.png"));
		panelQQ.add(QQtextImg);
		// lbl账号
		lblNewLabel_1 = new JLabel("账号：");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 0;
		gbc_lblNewLabel_1.gridy = 1;
		panelCenter.add(lblNewLabel_1, gbc_lblNewLabel_1);
		// text账号框
		text_myNumber = new JTextField();
		GridBagConstraints gbc_text_myNumber = new GridBagConstraints();
		gbc_text_myNumber.fill = GridBagConstraints.HORIZONTAL;
		gbc_text_myNumber.insets = new Insets(0, 0, 5, 5);
		gbc_text_myNumber.gridx = 1;
		gbc_text_myNumber.gridy = 1;
		panelCenter.add(text_myNumber, gbc_text_myNumber);
		// 重填按钮
		JButton btn_refill = new JButton("重填");
		GridBagConstraints gbc_btn_refill = new GridBagConstraints();
		gbc_btn_refill.fill = GridBagConstraints.HORIZONTAL;
		gbc_btn_refill.insets = new Insets(0, 0, 5, 0);
		gbc_btn_refill.gridx = 2;
		gbc_btn_refill.gridy = 1;
		panelCenter.add(btn_refill, gbc_btn_refill);
		// 点击重填按钮执行动作
		btn_refill.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				text_myNumber.setText("");
				pass_myPassword.setText("");
			}
		});
		// lbl密码
		JLabel lblNewLabel = new JLabel("密码：");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 0, 5);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 2;
		panelCenter.add(lblNewLabel, gbc_lblNewLabel);
		// pass密码框
		pass_myPassword = new JPasswordField();
		GridBagConstraints gbc_pass_myPassword = new GridBagConstraints();
		gbc_pass_myPassword.fill = GridBagConstraints.HORIZONTAL;
		gbc_pass_myPassword.insets = new Insets(0, 0, 0, 5);
		gbc_pass_myPassword.gridx = 1;
		gbc_pass_myPassword.gridy = 2;
		panelCenter.add(pass_myPassword, gbc_pass_myPassword);
		// lbl忘记密码
		JLabel lbl_forgetPassword = new JLabel("忘记密码");
		lbl_forgetPassword.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				System.out.println("点击忘记密码");
			}
		});
		lbl_forgetPassword.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lbl_forgetPassword = new GridBagConstraints();
		gbc_lbl_forgetPassword.fill = GridBagConstraints.HORIZONTAL;
		gbc_lbl_forgetPassword.gridx = 2;
		gbc_lbl_forgetPassword.gridy = 2;
		panelCenter.add(lbl_forgetPassword, gbc_lbl_forgetPassword);

		// 左边QQ标志
		JPanel panelWest = new JPanel();
		panelWest.setBackground(new Color(102, 204, 255));
		contentPane.add(panelWest, BorderLayout.WEST);
		panelWest.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
		JLabel QQicoImg = new JLabel(new ImageIcon("Image/dengluLeft.png"));
		panelWest.add(QQicoImg);

		// 下方三个按钮
		JPanel panelSouth = new JPanel();
		panelSouth.setBackground(new Color(102, 204, 255));
		contentPane.add(panelSouth, BorderLayout.SOUTH);
		panelSouth.setLayout(new GridLayout(0, 2, 0, 0));
		// 注册按钮
		// JButton btn_register = new JButton("注册");
		// panelSouth.add(btn_register);
		// 登录按钮
		JButton btn_login = new JButton("登录");
		panelSouth.add(btn_login);
		// 点击登录时执行动作
		btn_login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 新建一个用户对象myself，把账号密码加进myself中
				User myself = new User(text_myNumber.getText().trim(), new String(pass_myPassword.getPassword()));
				// 生成一个登录验证包
				if (myself.getUser_Number().trim().equals("") != true && myself.getUser_Password().equals("") != true) {// 验证账号密码都不为空
					Message loginCheck = new Message(MessageTypes.message_Login);
					loginCheck.setSender(myself.getUser_Number());
					loginCheck.setInfo(myself);
					QQClientUserThread qqClientUser = new QQClientUserThread();
					System.out.println("发送登录请求");
					if (qqClientUser.checkUser(loginCheck)) {
						String userId = myself.getUser_Number();
						qqClientUser.start();
						ManageQQClientThread.addQQClientThread(userId, qqClientUser);
						frame.dispose();
					} else {
						JOptionPane.showMessageDialog(null, "用户名或密码错误！");
					}
				} else {
					JOptionPane.showMessageDialog(null, "请填写账号密码");
				}
			}
		});
		// 退出按钮
		JButton btn_exit = new JButton("退出");
		panelSouth.add(btn_exit);
		// 点击退出按钮执行动作
		btn_exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});

		this.setVisible(true);
	}

}
