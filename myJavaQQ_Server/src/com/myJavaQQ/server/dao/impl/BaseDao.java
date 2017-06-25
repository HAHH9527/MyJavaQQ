/**
 * 通用的数据库操作接口
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
	private static final String url = "jdbc:mysql://127.0.0.1/MyJavaQQ_DB";// 请修改对应数据库IP
	private static final String user = "root";// 请修改对应数据库用户名
	private static final String password = "root";// 请修改对应数据库密码
	private Connection conn;
	private PreparedStatement ps;
	private ResultSet rs;

	/**
	 * 释放资源
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
	 * 根据查询条件统计记录数
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
	 * 将结果集转换为List
	 * 
	 * @param rs
	 * @return
	 * @throws Exception
	 */
	public abstract List createObject(ResultSet rs) throws Exception;

	/**
	 * 执行SQL语句，返回结果集
	 * 
	 * @param sql语句-String-sql
	 * @param 条件-List-params
	 * @return 结果集-ResultSet
	 * @throws Exception
	 */
	public ResultSet execute(String sql, String[] params) throws Exception {
		rs = null;
		try {
			conn = getConn();// 获取连接
			ps = conn.prepareStatement(sql);
			int i = 0;
			if (params != null) {
				for (String param : params) {
					ps.setString(++i, param);
				}
			}
			// 执行SQL语句
			rs = ps.executeQuery();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rs;
	}

	/**
	 * 执行SQL语句，返回List
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
	 * 增删改的通用方法,返回受影响行数
	 * 
	 * @param sql语句-String-sql
	 * @param 条件-List-params
	 * @return 受影响的行数-int
	 */
	public int executeSQL(String sql, String[] params) {
		ps = null;
		int num = 0;
		try {
			conn = getConn();// 获取连接
			ps = conn.prepareStatement(sql);
			int i = 0;
			// 设置语句参数
			for (String param : params) {
				ps.setString(++i, (String) param);
			}
			// 执行SQL语句
			num = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeAll();
		}
		return num;
	}

	/**
	 * 创建连接
	 * 
	 * @return 与数据库连接的conn
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public Connection getConn() throws ClassNotFoundException, SQLException {
		// 加载驱动程序
		Class.forName(driver);
		// 获得数据库连接
		conn = DriverManager.getConnection(url, user, password);
		return conn;
	}
}
