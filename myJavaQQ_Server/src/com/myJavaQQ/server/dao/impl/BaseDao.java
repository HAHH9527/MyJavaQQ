/**
 * ͨ�õ����ݿ�����ӿ�
 */
package com.myJavaQQ.server.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public abstract class BaseDao {

	private static final String driver = "com.mysql.jdbc.Driver";
	private static final String url = "jdbc:mysql://127.0.0.1/MyJavaQQ_DB";// ���޸Ķ�Ӧ���ݿ�IP
	private static final String user = "root";// ���޸Ķ�Ӧ���ݿ��û���
	private static final String password = "root";// ���޸Ķ�Ӧ���ݿ�����
	private Connection conn;
	private PreparedStatement ps;
	private ResultSet rs;

	/**
	 * �ͷ���Դ
	 */
	public void closeAll() {
		if (rs != null) {
			try {
				rs.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		if (ps != null) {
			try {
				ps.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		if (conn != null) {
			try {
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * ���ݲ�ѯ����ͳ�Ƽ�¼��
	 * 
	 * @param sql
	 * @param params
	 * @return
	 */
	public int count(String sql, String[] params) {
		ps = null;
		int num = 0;
		try {
			conn = getConn();
			ps = conn.prepareStatement(sql);
			int i = 0;
			if (params != null) {
				for (String param : params) {
					ps.setString(++i, param);
				}
			}
			rs = ps.executeQuery();
			if (rs.next()) {
				num = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeAll();
		}
		return num;
	}

	/**
	 * �������ת��ΪList
	 * 
	 * @param rs
	 * @return
	 * @throws Exception
	 */
	public abstract List createObject(ResultSet rs) throws Exception;

	/**
	 * ִ��SQL��䣬���ؽ����
	 * 
	 * @param sql���-String-sql
	 * @param ����-List-params
	 * @return �����-ResultSet
	 * @throws Exception
	 */
	public ResultSet execute(String sql, String[] params) throws Exception {
		rs = null;
		try {
			conn = getConn();// ��ȡ����
			ps = conn.prepareStatement(sql);
			int i = 0;
			if (params != null) {
				for (String param : params) {
					ps.setString(++i, param);
				}
			}
			// ִ��SQL���
			rs = ps.executeQuery();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rs;
	}

	/**
	 * ִ��SQL��䣬����List
	 * 
	 * @param sql
	 * @param params
	 * @return
	 */
	public List executeQuery(String sql, String[] params) {
		List list = null;
		try {
			rs = execute(sql, params);
			if (rs != null) {
				list = createObject(rs);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeAll();
		}
		return list;
	}

	/**
	 * ��ɾ�ĵ�ͨ�÷���,������Ӱ������
	 * 
	 * @param sql���-String-sql
	 * @param ����-List-params
	 * @return ��Ӱ�������-int
	 */
	public int executeSQL(String sql, String[] params) {
		ps = null;
		int num = 0;
		try {
			conn = getConn();// ��ȡ����
			ps = conn.prepareStatement(sql);
			int i = 0;
			// ����������
			for (String param : params) {
				ps.setString(++i, (String) param);
			}
			// ִ��SQL���
			num = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeAll();
		}
		return num;
	}

	/**
	 * ��������
	 * 
	 * @return �����ݿ����ӵ�conn
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public Connection getConn() throws ClassNotFoundException, SQLException {
		// ������������
		Class.forName(driver);
		// ������ݿ�����
		conn = DriverManager.getConnection(url, user, password);
		return conn;
	}
}
