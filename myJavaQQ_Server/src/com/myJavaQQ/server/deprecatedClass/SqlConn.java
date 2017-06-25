/**
 * 数据库连接类
 */
package com.myJavaQQ.server.deprecatedClass;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@Deprecated
public class SqlConn {
	private static final String url = "jdbc:mysql://172.104.74.239/MyJavaQQ_DB";
	private static final String name = "com.mysql.jdbc.Driver";
	private static final String user = "root";
	private static final String password = "huangmingjue2333";
	private static Connection conn;

	private static PreparedStatement ps;
	private static CallableStatement cs;
	private static ResultSet rs;
	private static Statement st;

	private static void close() {
		try {

			if (rs != null) {
				rs.close();
			}
			if (ps != null) {
				ps.close();
			}
			if (st != null) {
				st.close();
			}
			if (cs != null) {
				cs.close();
			}
			if (conn != null) {
				conn.close();
			}

			if (rs != null) {
				rs.close();
			}
			if (st != null) {
				st.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (Exception ex) {
			System.out.println("关闭失败");
		}
	}

	public SqlConn() throws SQLException {
		conn = DriverManager.getConnection(url, user, password);
	}

	/**
	 * 执行sql语句的增删改
	 * 
	 * @param sql
	 * @param param
	 * @return
	 */
	public Integer executeSQL(String sql, String[] param) throws SQLException {
		Integer result = 0;
		ps = null;
		try {
			ps = conn.prepareStatement(sql);
			if (param != null) {
				for (int i = 0; i < param.length; i++) {
					ps.setString(i + 1, param[i]);
				}
			}
			result = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public Connection getConn() {
		return conn;
	}

	public void setConn(Connection conn) {
		this.conn = conn;
	}

}
