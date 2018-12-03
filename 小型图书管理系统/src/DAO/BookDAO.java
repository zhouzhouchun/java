package DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


import test.Book;

public class BookDAO {
	static Connection conn = null;
	static PreparedStatement pst = null;
	static ResultSet rs = null;

	public static int addbook(Book b) {
		String sql = "insert into books values(?,?,?,?,?,?,?,?,?)";
		Object[] objs = { b.getBook_id(), b.getBook_name(), b.getBook_author(), b.getBook_price(), b.getBook_count(),
				b.getBook_type(), b.getBook_product(), (Date) b.getBook_date() ,b.getBorrowed()};
		try {
			BaseDAO.extUpdate(sql, objs);
			return 1;
		} catch (SQLException e) {
			return 0;
		} finally {
			BaseDAO.closeALL(conn, pst, rs);
		}
	}

	public static int delectbook(String book_id) {
		String sql = "delete from books where book_id=?";
		Object[] objs = { book_id };
		try {
			BaseDAO.extUpdate(sql, objs);
			return 1;
		} catch (SQLException e) {
			return 0;
		} finally {
			BaseDAO.closeALL(conn, pst, rs);
		}
	}

	public static int revisebook(Book b,String id) {
		String sql2 = "update books set book_name=?,Book_author=?,Book_price=?,Book_count=?,Book_type=?,Book_product=?,Book_date=?  where book_id=?";
		try {
			Object[] objs2 = { b.getBook_name(), b.getBook_author(), b.getBook_price(), b.getBook_count(),
					b.getBook_type(), b.getBook_product(), (Date) b.getBook_date(), id };
			BaseDAO.extUpdate(sql2, objs2);
		} catch (SQLException e) {
			return 0;
		}
		return 1;
	}

	public static ArrayList<Book> queryallbook() throws SQLException {
		String sql = "select *from books";
		ArrayList<Book> lb = null;
		rs = BaseDAO.exeQuery(sql, null);
		lb = Book.getbook(rs);
		BaseDAO.closeALL(conn, pst, rs);
		return lb;
	}

	public static ArrayList<Book> querybyid(String id) throws SQLException {
		String sql = "select *from books where book_id=?";
		Object[] objs = { id };
		rs = BaseDAO.exeQuery(sql, objs);
		ArrayList<Book> books = Book.getbook(rs);
		BaseDAO.closeALL(conn, pst, rs);
		return books;
	}

	public static ArrayList<Book> querybyname(String book_name) throws SQLException {
		String sql = "select *from books where book_name like ?";
		Object[] objs = { "%" + book_name + "%" };
		rs = BaseDAO.exeQuery(sql, objs);
		ArrayList<Book> books = Book.getbook(rs);
		BaseDAO.closeALL(conn, pst, rs);
		return books;
	}
	public static ArrayList<Book> querybyauthor(String book_author) throws SQLException{
		String sql = "select *from books where book_author like ?";
		Object[] objs = { "%" + book_author + "%" };
		rs = BaseDAO.exeQuery(sql, objs);
		ArrayList<Book> books = Book.getbook(rs);
		BaseDAO.closeALL(conn, pst, rs);
		return books;
	}
	public static ArrayList<Book> querybyprice(float price,int index) throws SQLException{
		String sql1 = "select *from books where book_price < ?";
		String sql2 = "select *from books where book_price > ?";
		String sql3= "select *from books where book_price = ? ";
		String sql=null;
		if(index==1) {
			sql=sql1;
		}else  if (index==2){
			sql=sql2;
		}else if(index==3){
			sql=sql3;
		}
		Object[] objs = { price };
		rs = BaseDAO.exeQuery(sql, objs);
		ArrayList<Book> books = Book.getbook(rs);
		BaseDAO.closeALL(conn, pst, rs);
		return books;	
	}
	

}
