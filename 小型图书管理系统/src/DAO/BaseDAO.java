package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BaseDAO {
	// ���ݿ�����
	private static String driver = "com.mysql.cj.jdbc.Driver";
	// ���ݿ����ӵ�ַ
	private static String url = "jdbc:mysql://localhost:3306/books?useSSL=false&serverTimezone=GMT";
	// ���ݿ��û���
	private static String user = "root";
	// ���ݿ�����
	private static String password = "123456";

	// ���ݿ�����
	private static Connection conn1 = null;
	private static PreparedStatement ps1=null;
	private static ResultSet rs1=null;
	/**
	 * �������ݿ�
	 *
	 * @return ���ݿ�����
	 * @throws ClassNotFoundException
	 *             δ�ҵ��������쳣
	 * @throws SQLException
	 *             ���ݿ��쳣
	 */
	public static Connection getConn()  {
		// ��������
		try {
			Class.forName(driver);
			conn1 = DriverManager.getConnection(url, user, password);
			return conn1;
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			return null;
		}
		// ��ȡ����
		
		
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
		ps1 = conn1.prepareStatement(sql);// ׼��ִ�е�sql���
		if (objs != null) {// ׼����sql����е�ÿ������Ӧ��ֵ
			for (int i = 0; i < objs.length; i++) {
				ps1.setObject(i + 1, objs[i]);
			}
		}
		rs1 = ps1.executeQuery();
		return rs1;
	}
	public static int extUpdate(String sql, Object[] objs) throws SQLException {//����
		int count = 0;
		conn1=BaseDAO.getConn();
		ps1 = conn1.prepareStatement(sql);// ׼��ִ�е�sql���
		if (objs != null) {// ׼����sql����е�ÿ������Ӧ��ֵ
			for (int i = 0; i < objs.length; i++) {
				ps1.setObject(i + 1, objs[i]);
			}
		}
		
		count = ps1.executeUpdate();
		return count;
	}
}
