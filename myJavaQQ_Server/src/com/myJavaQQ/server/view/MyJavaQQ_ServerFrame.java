/**
 * ���������洰��
 */
package com.myJavaQQ.server.view;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Map.Entry;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.myJavaQQ.common.Message;
import com.myJavaQQ.common.MessageTypes;
import com.myJavaQQ.common.User;
import com.myJavaQQ.server.dao.impl.UserDao;
import com.myJavaQQ.server.model.MyQQServer;
import com.myJavaQQ.server.model.ServerConClientThread;
import com.myJavaQQ.server.tools.ManageServerConClientThread;

public class MyJavaQQ_ServerFrame extends JFrame {
	public static void main(String[] args) {
		new MyJavaQQ_ServerFrame();
	}
	private JTable table;// ��ʾ�û���table
	private JTextField textField;// �������������
	private MyQQServer myQQserver;
	private boolean serverRunning = false;// ����������״̬
	private UserDao userDao = new UserDao();

	private JFrame frame;

	public MyJavaQQ_ServerFrame() {
		frame = this;
		// ���ô������弰Ĭ������
		setResizable(false);
		setTitle("MyJavaQQ�����");

		// �رմ����¼�������
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				super.windowClosing(e);
				System.out.println("�˳�");
				exit();
			}
		});

		// setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 500);
		getContentPane().setLayout(new BorderLayout(0, 0));

		// �в���ʾ���ݱ��panel
		JScrollPane scrollPane = new JScrollPane();
		getContentPane().add(scrollPane, BorderLayout.CENTER);
		table = new JTable();
		table.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "�˺�", "�ǳ�", "�Ƿ�����" }));
		scrollPane.setViewportView(table);

		// �ײ�������������رշ�������ť
		JPanel panel_btn = new JPanel();
		getContentPane().add(panel_btn, BorderLayout.SOUTH);
		GridBagLayout gbl_panel_btn = new GridBagLayout();
		gbl_panel_btn.columnWidths = new int[] { 390, 0, 390 };
		gbl_panel_btn.rowHeights = new int[] { 0 };
		gbl_panel_btn.columnWeights = new double[] { 0.0, 0.0, 0.0 };
		gbl_panel_btn.rowWeights = new double[] { 0.0 };
		panel_btn.setLayout(gbl_panel_btn);
		// ������������ť
		JButton btn_StartServer = new JButton("����������");
		btn_StartServer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				serverStart();
			}
		});
		GridBagConstraints gbc_btn_StartServer = new GridBagConstraints();
		gbc_btn_StartServer.fill = GridBagConstraints.BOTH;
		gbc_btn_StartServer.insets = new Insets(0, 0, 0, 5);
		gbc_btn_StartServer.gridx = 0;
		gbc_btn_StartServer.gridy = 0;
		panel_btn.add(btn_StartServer, gbc_btn_StartServer);
		// �رշ�������ť
		JButton btn_CloseServer = new JButton("�رշ�����");
		btn_CloseServer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				closeServer();
			}
		});
		GridBagConstraints gbc_btn_CloseServer = new GridBagConstraints();
		gbc_btn_CloseServer.fill = GridBagConstraints.BOTH;
		gbc_btn_CloseServer.gridx = 2;
		gbc_btn_CloseServer.gridy = 0;
		panel_btn.add(btn_CloseServer, gbc_btn_CloseServer);

		// �Ҳ��û�����ť
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.EAST);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] { 0 };
		gbl_panel.rowHeights = new int[] { 50, 50, 100, 50, 10, 50, 100, 50, 50 };
		gbl_panel.columnWeights = new double[] { 1.0 };
		gbl_panel.rowWeights = new double[] { 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0 };
		panel.setLayout(gbl_panel);
		// ��ʾ�����û���ť
		JButton btn_ShowAllUser = new JButton("��ʾ�����û�");
		btn_ShowAllUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showAllUser();
			}
		});
		GridBagConstraints gbc_btn_ShowAllUser = new GridBagConstraints();
		gbc_btn_ShowAllUser.fill = GridBagConstraints.BOTH;
		gbc_btn_ShowAllUser.insets = new Insets(0, 0, 5, 0);
		gbc_btn_ShowAllUser.gridx = 0;
		gbc_btn_ShowAllUser.gridy = 0;
		panel.add(btn_ShowAllUser, gbc_btn_ShowAllUser);
		// �鿴ѡ���û���Ϣ��ť
		JButton btn_ShowSelectedUserInfo = new JButton("�鿴ѡ���û���Ϣ");
		btn_ShowSelectedUserInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				showSelectedUserInfo();
			}
		});

		JButton btn_ShowAllOnlineUser = new JButton("��ʾ���������û�");
		btn_ShowAllOnlineUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				showAllOnlineUser();
			}
		});
		GridBagConstraints gbc_btn_ShowAllOnlineUser = new GridBagConstraints();
		gbc_btn_ShowAllOnlineUser.fill = GridBagConstraints.BOTH;
		gbc_btn_ShowAllOnlineUser.insets = new Insets(0, 0, 5, 0);
		gbc_btn_ShowAllOnlineUser.gridx = 0;
		gbc_btn_ShowAllOnlineUser.gridy = 1;
		panel.add(btn_ShowAllOnlineUser, gbc_btn_ShowAllOnlineUser);
		// ���ڷָ���ť��panel
		JPanel panel_2 = new JPanel();
		GridBagConstraints gbc_panel_2 = new GridBagConstraints();
		gbc_panel_2.insets = new Insets(0, 0, 5, 0);
		gbc_panel_2.fill = GridBagConstraints.BOTH;
		gbc_panel_2.gridx = 0;
		gbc_panel_2.gridy = 2;
		panel.add(panel_2, gbc_panel_2);

		JButton btn_ShowUserByNickname = new JButton("���ǳƲ���");
		btn_ShowUserByNickname.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (textField.getText().equals("") == false) {
					showUserByNickname();
				}
			}
		});
		GridBagConstraints gbc_btn_ShowUserByNickname = new GridBagConstraints();
		gbc_btn_ShowUserByNickname.fill = GridBagConstraints.BOTH;
		gbc_btn_ShowUserByNickname.insets = new Insets(0, 0, 5, 0);
		gbc_btn_ShowUserByNickname.gridx = 0;
		gbc_btn_ShowUserByNickname.gridy = 3;
		panel.add(btn_ShowUserByNickname, gbc_btn_ShowUserByNickname);
		// ��ѯ�˺ŵ�lbl
		textField = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.anchor = GridBagConstraints.SOUTH;
		gbc_textField.insets = new Insets(0, 0, 5, 0);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 0;
		gbc_textField.gridy = 4;
		panel.add(textField, gbc_textField);
		textField.setColumns(10);
		// ���˺Ų����û���ť
		JButton btn_ShowUserByNumber = new JButton("���˺Ų����û�");
		btn_ShowUserByNumber.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (textField.getText().equals("") == false) {
					showUserByNumber();
				}
			}
		});
		GridBagConstraints gbc_btn_ShowUserByNumber = new GridBagConstraints();
		gbc_btn_ShowUserByNumber.fill = GridBagConstraints.BOTH;
		gbc_btn_ShowUserByNumber.insets = new Insets(0, 0, 5, 0);
		gbc_btn_ShowUserByNumber.gridx = 0;
		gbc_btn_ShowUserByNumber.gridy = 5;
		panel.add(btn_ShowUserByNumber, gbc_btn_ShowUserByNumber);
		// ���ڷָ���ť��panel
		JPanel panel_1 = new JPanel();
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.insets = new Insets(0, 0, 5, 0);
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 0;
		gbc_panel_1.gridy = 6;
		panel.add(panel_1, gbc_panel_1);
		GridBagConstraints gbc_btn_ShowSelectedUserInfo = new GridBagConstraints();
		gbc_btn_ShowSelectedUserInfo.fill = GridBagConstraints.BOTH;
		gbc_btn_ShowSelectedUserInfo.insets = new Insets(0, 0, 5, 0);
		gbc_btn_ShowSelectedUserInfo.gridx = 0;
		gbc_btn_ShowSelectedUserInfo.gridy = 7;
		panel.add(btn_ShowSelectedUserInfo, gbc_btn_ShowSelectedUserInfo);
		// ǿ�����߰�ť
		JButton btn_Offline = new JButton("ѡ���û�ǿ������");
		btn_Offline.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectedUserExit();
			}
		});
		GridBagConstraints gbc_btn_Offline = new GridBagConstraints();
		gbc_btn_Offline.fill = GridBagConstraints.BOTH;
		gbc_btn_Offline.gridx = 0;
		gbc_btn_Offline.gridy = 8;
		panel.add(btn_Offline, gbc_btn_Offline);

		setVisible(true);

	}

	/**
	 * �رշ�����
	 */
	private void closeServer() {
		try {
			serverRunning = false;
			if (myQQserver != null) {
				myQQserver.setFlag(false);
				myQQserver.getSs().close();
				myQQserver.stop();
			}
		} catch (Exception e) {
		} finally {
			exitAllUser();
			myQQserver = null;
			System.out.println("�������ѹر�");
		}
	}

	/**
	 * �˳�����������
	 */
	private void exit() {
		try {
			dispose();
			closeServer();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			System.exit(0);
		}
	}

	/**
	 * ʹ�����û��˳�
	 */
	private void exitAllUser() {
		for (Entry<String, ServerConClientThread> entry : ManageServerConClientThread.getHmSCCT().entrySet()) {
			Message sendMsg = new Message(MessageTypes.message_Exit);
			entry.getValue().sendMsg(sendMsg);
			entry.getValue().offline();
		}
		userDao.allUserOffline();
		ManageServerConClientThread.getHmSCCT().clear();
	}

	private void selectedUserExit() {
		int selectRows = table.getSelectedRows().length;// ȡ���û���ѡ�е�����
		DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
		if (selectRows == 1) {
			int selectedRowIndex = table.getSelectedRow();// ȡ���û���ѡ����

			// ������ش���
			int rowNumber = table.getSelectedRow();
			String selectedUserNumber = (String) tableModel.getValueAt(rowNumber, 0);
			ManageServerConClientThread.getServerConClientThread(selectedUserNumber).offline();
		}
	}

	/**
	 * ����������
	 */
	private void serverStart() {
		myQQserver = new MyQQServer();
		myQQserver.start();
		System.out.println("������������");
	}

	/**
	 * ��ʾ���������û�
	 */
	private void showAllOnlineUser() {
		DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
		tableModel.setRowCount(0);
		ArrayList<User> userList = userDao.getAllOnlineUser();// �������������û�
		for (User user : userList) {
			String[] aUserInfo = new String[3];
			aUserInfo[0] = user.getUser_Number();
			aUserInfo[1] = user.getUser_Nickname();
			aUserInfo[2] = user.getUser_Online();
			tableModel.addRow(aUserInfo);// ������ݵ�table
		}
	}

	/**
	 * ��ʾ�����û�
	 */
	private void showAllUser() {
		DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
		tableModel.setRowCount(0);
		ArrayList<User> userList = userDao.getAllUser();// ���������û�
		for (User user : userList) {
			String[] aUserInfo = new String[3];
			aUserInfo[0] = user.getUser_Number();
			aUserInfo[1] = user.getUser_Nickname();
			aUserInfo[2] = user.getUser_Online();
			tableModel.addRow(aUserInfo);// ������ݵ�table
		}
	}

	/**
	 * ��ʾѡ�����û���Ϣ
	 */
	private void showSelectedUserInfo() {
		int selectRows = table.getSelectedRows().length;// ȡ���û���ѡ�е�����
		DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
		if (selectRows == 1) {
			int selectedRowIndex = table.getSelectedRow();// ȡ���û���ѡ����

			// ������ش���
			int rowNumber = table.getSelectedRow();
			String selectedUserNumber = (String) tableModel.getValueAt(rowNumber, 0);
			User selectedUser = userDao.getUserInfo(selectedUserNumber);
			UserInfoFrame userInfoFrame = new UserInfoFrame();
			{
				userInfoFrame.getText_user_Number().setText(selectedUser.getUser_Number());
				userInfoFrame.getText_user_Password().setText(selectedUser.getUser_Password());
				userInfoFrame.getText_user_Regdate().setText(selectedUser.getUser_Regdate());
				userInfoFrame.getText_user_Nickname().setText(selectedUser.getUser_Nickname());
				userInfoFrame.getText_user_Online().setText(selectedUser.getUser_Online());
				userInfoFrame.getText_user_Sex().setText(selectedUser.getUser_Sex());
				userInfoFrame.getText_user_Birthday().setText(selectedUser.getUser_Birthday());
				userInfoFrame.getText_user_Phone().setText(selectedUser.getUser_Phone());
				userInfoFrame.getText_user_Address().setText(selectedUser.getUser_Address());
				userInfoFrame.getText_user_Hometown().setText(selectedUser.getUser_Hometown());
				userInfoFrame.getText_user_Other().setText(selectedUser.getUser_Other());
			}
		}
	}

	private void showUserByNickname() {
		DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
		tableModel.setRowCount(0);
		ArrayList<User> userList = userDao.getUserByNickname(textField.getText());// ���Ҹ��˺��û�
		for (User user : userList) {
			String[] aUserInfo = new String[3];
			aUserInfo[0] = user.getUser_Number();
			aUserInfo[1] = user.getUser_Nickname();
			aUserInfo[2] = user.getUser_Online();
			tableModel.addRow(aUserInfo);// ������ݵ�table
		}
	}

	/**
	 * ���˺���ʾ�û�
	 */
	private void showUserByNumber() {
		DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
		tableModel.setRowCount(0);
		ArrayList<User> userList = userDao.getUserByNumber(textField.getText());// ���Ҹ��˺��û�
		for (User user : userList) {
			String[] aUserInfo = new String[3];
			aUserInfo[0] = user.getUser_Number();
			aUserInfo[1] = user.getUser_Nickname();
			aUserInfo[2] = user.getUser_Online();
			tableModel.addRow(aUserInfo);// ������ݵ�table
		}
	}

}
