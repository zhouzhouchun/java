package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BaseDAO {
	// 数据库驱动
	private static String driver = "com.mysql.cj.jdbc.Driver";
	// 数据库连接地址
	private static String url = "jdbc:mysql://localhost:3306/books?useSSL=false&serverTimezone=GMT";
	// 数据库用户名
	private static String user = "root";
	// 数据库密码
	private static String password = "123456";

	// 数据库连接
	private static Connection conn1 = null;
	private static PreparedStatement ps1=null;
	private static ResultSet rs1=null;
	/**
	 * 连接数据库
	 *
	 * @return 数据库连接
	 * @throws ClassNotFoundException
	 *             未找到驱动类异常
	 * @throws SQLException
	 *             数据库异常
	 */
	public static Connection getConn()  {
		// 加载驱动
		try {
			Class.forName(driver);
			conn1 = DriverManager.getConnection(url, user, password);
			return conn1;
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			return null;
		}
		// 获取连接
		
		
	}
	public static void closeALL(Connection conn, PreparedStatement ps, ResultSet rs) {
		try {
			if (rs != null) {
				rs.close();
			}
			if (ps != null) {
				ps.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public static ResultSet exeQuery(String sql, Object[] objs) throws SQLException {
		conn1=BaseDAO.getConn();
		ps1 = conn1.prepareStatement(sql);// 准备执行的sql语句
		if (objs != null) {// 准备好sql语句中的每个？对应的值
			for (int i = 0; i < objs.length; i++) {
				ps1.setObject(i + 1, objs[i]);
			}
		}
		rs1 = ps1.executeQuery();
		return rs1;
	}
	public static int extUpdate(String sql, Object[] objs) throws SQLException {//更新
		int count = 0;
		conn1=BaseDAO.getConn();
		ps1 = conn1.prepareStatement(sql);// 准备执行的sql语句
		if (objs != null) {// 准备好sql语句中的每个？对应的值
			for (int i = 0; i < objs.length; i++) {
				ps1.setObject(i + 1, objs[i]);
			}
		}
		
		count = ps1.executeUpdate();
		return count;
	}
}
