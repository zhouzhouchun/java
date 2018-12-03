package test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class Book {
	private String Book_id=null;
	private String Book_name=null;
	private String Book_author=null;
	private float Book_price=0;
	private int Book_count=0;
	private String Book_type=null;
	private String Book_product=null;
	private Date Book_date=null;
	private int borrowed=0;
	public int getBorrowed() {
		return borrowed;
	}
	public void setBorrowed(int borrowed) {
		this.borrowed = borrowed;
	}
	public String getBook_id() {
		return Book_id;
	}
	public void setBook_id(String book_id) {
		Book_id = book_id;
	}
	public String getBook_name() {
		return Book_name;
	}
	public void setBook_name(String book_name) {
		Book_name = book_name;
	}
	public String getBook_author() {
		return Book_author;
	}
	public void setBook_author(String book_author) {
		Book_author = book_author;
	}
	public float getBook_price() {
		return Book_price;
	}
	public void setBook_price(float book_price) {
		Book_price = book_price;
	}
	public int getBook_count() {
		return Book_count;
	}
	public void setBook_count(int book_count) {
		Book_count = book_count;
	}
	public String getBook_type() {
		return Book_type;
	}
	public void setBook_type(String book_type) {
		Book_type = book_type;
	}
	public String getBook_product() {
		return Book_product;
	}
	public void setBook_product(String book_product) {
		Book_product = book_product;
	}
	public Date getBook_date() {
		return Book_date;
	}
	public void setBook_date(Date book_date) {
		Book_date = book_date;
	}
	public Book(String id,String name,String author) {
		this.Book_author=author;
		this.Book_id=id;
		this.Book_name=name;
	}
	public Book() {};
	public static ArrayList<Book> getbook(ResultSet rs) throws SQLException {
		ArrayList<Book> lb=new ArrayList<>();
		while(rs.next()) {
			Book b=new Book();
			b.setBook_id(rs.getString(1));
			b.setBook_name(rs.getString(2));
			b.setBook_author(rs.getString(3));
			b.setBook_price(rs.getFloat(4));
			b.setBook_count(rs.getInt(5));
			b.setBook_type(rs.getString(6));
			b.setBook_product(rs.getString(7));
			b.setBook_date(rs.getDate(8));
			b.setBorrowed(rs.getInt(9));
			lb.add(b);
		}
		return lb;
	} 
	/*public static ArrayList<String> StringToBook(String s){
		String[] split = s.split("\\,");
	}*/
}
