/**
 * QQ��¼����
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
		setTitle("��ӭʹ��JQQ");
		setResizable(false);
		// ���ô������弰����
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 250);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(102, 204, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		// �����м��˺����벿��
		panelCenter = new JPanel();
		panelCenter.setBackground(new Color(102, 204, 255));
		contentPane.add(panelCenter, BorderLayout.CENTER);
		GridBagLayout gbl_panelCenter = new GridBagLayout();
		gbl_panelCenter.columnWidths = new int[] { 50, 200, 50 };
		gbl_panelCenter.rowHeights = new int[] { 0 };
		gbl_panelCenter.columnWeights = new double[] { 0.0, 0.0, 0.0 };
		gbl_panelCenter.rowWeights = new double[] { 0.0, 0.0, 0.0 };
		panelCenter.setLayout(gbl_panelCenter);
		// �Ϸ�QQͼ��
		panelQQ = new JPanel();
		GridBagConstraints gbc_panelQQ = new GridBagConstraints();
		gbc_panelQQ.insets = new Insets(0, 0, 5, 5);
		gbc_panelQQ.gridx = 1;
		gbc_panelQQ.gridy = 0;
		panelCenter.add(panelQQ, gbc_panelQQ);
		panelQQ.setBackground(new Color(102, 204, 255));
		QQtextImg = new JLabel(new ImageIcon("Image/dengluTop.png"));
		panelQQ.add(QQtextImg);
		// lbl�˺�
		lblNewLabel_1 = new JLabel("�˺ţ�");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 0;
		gbc_lblNewLabel_1.gridy = 1;
		panelCenter.add(lblNewLabel_1, gbc_lblNewLabel_1);
		// text�˺ſ�
		text_myNumber = new JTextField();
		GridBagConstraints gbc_text_myNumber = new GridBagConstraints();
		gbc_text_myNumber.fill = GridBagConstraints.HORIZONTAL;
		gbc_text_myNumber.insets = new Insets(0, 0, 5, 5);
		gbc_text_myNumber.gridx = 1;
		gbc_text_myNumber.gridy = 1;
		panelCenter.add(text_myNumber, gbc_text_myNumber);
		// ���ť
		JButton btn_refill = new JButton("����");
		GridBagConstraints gbc_btn_refill = new GridBagConstraints();
		gbc_btn_refill.fill = GridBagConstraints.HORIZONTAL;
		gbc_btn_refill.insets = new Insets(0, 0, 5, 0);
		gbc_btn_refill.gridx = 2;
		gbc_btn_refill.gridy = 1;
		panelCenter.add(btn_refill, gbc_btn_refill);
		// ������ťִ�ж���
		btn_refill.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				text_myNumber.setText("");
				pass_myPassword.setText("");
			}
		});
		// lbl����
		JLabel lblNewLabel = new JLabel("���룺");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 0, 5);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 2;
		panelCenter.add(lblNewLabel, gbc_lblNewLabel);
		// pass�����
		pass_myPassword = new JPasswordField();
		GridBagConstraints gbc_pass_myPassword = new GridBagConstraints();
		gbc_pass_myPassword.fill = GridBagConstraints.HORIZONTAL;
		gbc_pass_myPassword.insets = new Insets(0, 0, 0, 5);
		gbc_pass_myPassword.gridx = 1;
		gbc_pass_myPassword.gridy = 2;
		panelCenter.add(pass_myPassword, gbc_pass_myPassword);
		// lbl��������
		JLabel lbl_forgetPassword = new JLabel("��������");
		lbl_forgetPassword.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				System.out.println("�����������");
			}
		});
		lbl_forgetPassword.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lbl_forgetPassword = new GridBagConstraints();
		gbc_lbl_forgetPassword.fill = GridBagConstraints.HORIZONTAL;
		gbc_lbl_forgetPassword.gridx = 2;
		gbc_lbl_forgetPassword.gridy = 2;
		panelCenter.add(lbl_forgetPassword, gbc_lbl_forgetPassword);

		// ���QQ��־
		JPanel panelWest = new JPanel();
		panelWest.setBackground(new Color(102, 204, 255));
		contentPane.add(panelWest, BorderLayout.WEST);
		panelWest.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
		JLabel QQicoImg = new JLabel(new ImageIcon("Image/dengluLeft.png"));
		panelWest.add(QQicoImg);

		// �·�������ť
		JPanel panelSouth = new JPanel();
		panelSouth.setBackground(new Color(102, 204, 255));
		contentPane.add(panelSouth, BorderLayout.SOUTH);
		panelSouth.setLayout(new GridLayout(0, 2, 0, 0));
		// ע�ᰴť
		// JButton btn_register = new JButton("ע��");
		// panelSouth.add(btn_register);
		// ��¼��ť
		JButton btn_login = new JButton("��¼");
		panelSouth.add(btn_login);
		// �����¼ʱִ�ж���
		btn_login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// �½�һ���û�����myself�����˺�����ӽ�myself��
				User myself = new User(text_myNumber.getText().trim(), new String(pass_myPassword.getPassword()));
				// ����һ����¼��֤��
				if (myself.getUser_Number().trim().equals("") != true && myself.getUser_Password().equals("") != true) {// ��֤�˺����붼��Ϊ��
					Message loginCheck = new Message(MessageTypes.message_Login);
					loginCheck.setSender(myself.getUser_Number());
					loginCheck.setInfo(myself);
					QQClientUserThread qqClientUser = new QQClientUserThread();
					System.out.println("���͵�¼����");
					if (qqClientUser.checkUser(loginCheck)) {
						String userId = myself.getUser_Number();
						qqClientUser.start();
						ManageQQClientThread.addQQClientThread(userId, qqClientUser);
						frame.dispose();
					} else {
						JOptionPane.showMessageDialog(null, "�û������������");
					}
				} else {
					JOptionPane.showMessageDialog(null, "����д�˺�����");
				}
			}
		});
		// �˳���ť
		JButton btn_exit = new JButton("�˳�");
		panelSouth.add(btn_exit);
		// ����˳���ťִ�ж���
		btn_exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});

		this.setVisible(true);
	}

}
