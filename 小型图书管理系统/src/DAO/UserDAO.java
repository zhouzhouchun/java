package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


import test.Book;
import test.User;

public class UserDAO {
	private static Connection conn = null;
	private static PreparedStatement pst = null;
	private static ResultSet rs = null;

	public static int signin(User u) {
		String sql = "select *from users where name=? and pwd =?";
		Object[] objs = { u.getName(), u.getPwd() };
		try {
			rs = BaseDAO.exeQuery(sql, objs);
			if (rs.next()) {
				if(rs.getBoolean(3)==true)
					return 3;
				else
					return 2;
				
			} else {
				return -1;
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}finally {
			BaseDAO.closeALL(conn, pst, rs);
		}
		return -1;
	}

	public static int register(User u) {
		String sql = "insert into users values(?,?,?,?)";
		Object[] objs = { u.getName(), u.getPwd() ,u.getisIsadmin(),""};
		try {
			if (BaseDAO.extUpdate(sql, objs) == 1)
				return 1;
		} catch (SQLException e) {
			return -1;
		} finally {
			BaseDAO.closeALL(conn, pst, rs);
		}
		return 0;
	}

	public static int ChangePassword(User u, String newpwd) {
		String sql = "UPDATE users SET pwd=? WHERE name = ?";
		Object[] objs = { newpwd, u.getName() };
		try {
			BaseDAO.extUpdate(sql, objs);
		} catch (SQLException e) {
			System.out.println("密码修改失败");
			return 0;
		} finally {
			BaseDAO.closeALL(conn, pst, rs);
		}
		return 1;
	}

	public static String[] queryBorrowbooks_id(User u) {
		String sql = "select userbooks from users where name=?";
		Object[] objs = { u.getName() };
		try {
			rs = BaseDAO.exeQuery(sql, objs);
			if (rs.next()) {
				String s = rs.getString(1);
				if(s!=null) {
					String[] books_id = s.split("\\,");
					return books_id;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public static ArrayList<Book> queryborrowedbook(User u) {
		String[] books_id=queryBorrowbooks_id(u);
		ArrayList<Book> list = new ArrayList<>();
		if(books_id!=null) {
		String sql = "select * from books where book_id in (";
		StringBuffer sb = new StringBuffer();
		sb.append(sql);
		for (int i = 0; i < books_id.length; i++) {
			sb.append("'");
			sb.append(books_id[i]);
			sb.append("'");
			if (i < books_id.length - 1)
				sb.append(",");
		}
		sb.append(")");
		System.out.println(sb.toString());
		Statement st;
		try {
			conn=BaseDAO.getConn();
			st=conn.createStatement();
			rs = st.executeQuery(sb.toString());
			list = Book.getbook(rs);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}}
		return list;
	
	}
	public static int borrowbook(User u, String Book_id) {
		String[] books_id = queryBorrowbooks_id(u);
		StringBuffer sb = new StringBuffer();
		if(books_id!=null) {
		for (int i = 0; i < books_id.length; i++) {
			System.out.println(books_id[i]);
		}
		
		for (int i = 0; i < books_id.length; i++) {
				sb.append(books_id[i]);
				sb.append(",");
		}}
		sb.append(Book_id);
		System.out.println(sb.toString());
		try {
			conn = BaseDAO.getConn();
			conn.setAutoCommit(false);
			String sql = "UPDATE users SET userbooks=? WHERE name = ?";
			Object[] objs = { sb.toString(), u.getName() };
			pst = conn.prepareStatement(sql);// 准备执行的sql语句
			//System.out.println(sql);
			if (objs != null) {// 准备好sql语句中的每个？对应的值
				for (int i = 0; i < objs.length; i++) {
					pst.setObject(i + 1, objs[i]);
				}
			}
			int count = pst.executeUpdate();
			if(count!=1)
				System.out.println("给用户增加书 有问题");
			//给书借出数量+1
			String sql2 = "update books set borrowed = borrowed +1 where book_id=?";
			Object[] objs2 = {  Book_id };
			pst.clearBatch();
			pst = conn.prepareStatement(sql2);// 准备执行的sql语句
			if (objs2 != null) {// 准备好sql语句中的每个？对应的值
				for (int i = 0; i < objs2.length; i++) {
					pst.setObject(i + 1, objs2[i]);
				}
			}
			int count2 = pst.executeUpdate();
			if(count2!=1)
				System.out.println("+1 操作有问题");
			conn.commit();
		} catch (SQLException e) {
			try {
                conn.rollback();
            } catch (SQLException e1) {
                e.printStackTrace();
            }
            System.out.println("借书事物处理失败");
            e.printStackTrace();
            return -1;
		}
		return 1;
	}
}
