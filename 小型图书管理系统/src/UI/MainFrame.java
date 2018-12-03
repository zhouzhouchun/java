package UI;

import java.awt.Container;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import DAO.BookDAO;
import DAO.UserDAO;
import test.Book;
import test.User;

public class MainFrame extends JFrame {
	public static User u=new User();
	JMenuBar menuBar;
	// 创建一级菜单
	JMenu userMenu;
	JMenu queryMenu;
	JMenu glMenu;
	// 创建菜单选项
	JMenuItem exitMenuItem;
	JMenuItem changepwdMenuItem;
	JMenuItem queryborrowedbookItem;
	JMenuItem borrowbookItem;
	JMenuItem add;
	JMenuItem delect;
	JMenuItem revise;
	JMenuItem querybyname;
	JMenuItem querybyid;
	JMenuItem queryall;
	Container cont = null;
	BookTable booktable;
	ArrayList<Book> list;
	QueryCombo qc;
	JLabel booknum;
	public MainFrame() {
		this.setTitle("小型图书管理系统---" + UI.MainFrame.u.getName());
		this.setSize(600, 500);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		cont = getContentPane();
		cont.setLayout(null);
		setmenuBar();
		booknum=new JLabel("QAQ");
		booknum.setBounds(400, 400, 100, 30);
		qc = new QueryCombo();
		booktable = new BookTable();
		booktable.scrollPane.setBounds(0, 50, 580, 350);
		setAction();
		cont.add(booktable.scrollPane);
		cont.add(qc);
		cont.add(booknum);
		this.setVisible(true);
	}

	private void setmenuBar() {
		menuBar = new JMenuBar();
		userMenu = new JMenu("用户");
		queryMenu = new JMenu("图书查询");
		glMenu = new JMenu("图书管理");
		menuBar.add(userMenu);
		menuBar.add(queryMenu);
		if(u.getisIsadmin()==true)
			menuBar.add(glMenu);
		// 用户
		exitMenuItem = new JMenuItem("退出登入");
		changepwdMenuItem = new JMenuItem("修改密码");
		queryborrowedbookItem=new JMenuItem("借书查询");
		borrowbookItem = new JMenuItem("借书");
		userMenu.add(changepwdMenuItem);
		userMenu.add(exitMenuItem);
		userMenu.add(queryborrowedbookItem);
		userMenu.add(borrowbookItem);
		// 图书管理
		add = new JMenuItem("添加图书");
		revise = new JMenuItem("修改图书");
		delect = new JMenuItem("删除图书");
		glMenu.add(add);
		glMenu.add(delect);
		glMenu.add(revise);
		// 查询
		querybyname = new JMenuItem("按书名查询");
		querybyid = new JMenuItem("按id查询");
		queryall = new JMenuItem("查询所有");
		queryMenu.add(querybyid);
		queryMenu.add(querybyname);
		queryMenu.add(queryall);
		this.setJMenuBar(menuBar);
	}

	private void setAction() {
		exitMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dispose();
				new LoginFrame();
			}
		});
		changepwdMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new ChangePWD();
			}
		});
		queryall.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					list = BookDAO.queryallbook();
					booktable.addbook(list);
					booknum.setText("查询到图书:"+list.size()+"本");
				} catch (SQLException e1) {

				}
			}
		});
		querybyid.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				// BookDAO.querybyid(id);
			}
		});
		revise.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new ReviseBookFrame();
			}
		});
		add.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new AddBookFrame();
			}
		});
		qc.querybt.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (qc.comboBox.getSelectedItem().equals("id")) {
					try {
						list = BookDAO.querybyid(qc.jtxt.getText());
						booktable.addbook(list);
						booknum.setText("查询到图书:"+list.size()+"本");
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else if (qc.comboBox.getSelectedItem().equals("书名")) {
					try {
						list = BookDAO.querybyname(qc.jtxt.getText());
						booktable.addbook(list);
						booknum.setText("查询到图书:"+list.size()+"本");
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else if (qc.comboBox.getSelectedItem().equals("作者")) {
					try {
						list = BookDAO.querybyauthor(qc.jtxt.getText());
						booktable.addbook(list);
						booknum.setText("查询到图书:"+list.size()+"本");
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		delect.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new DelectBookFrame();
			}
		});
		queryborrowedbookItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				list =UserDAO.queryborrowedbook(u);
				booktable.addbook(list);
				booknum.setText("查询到图书:"+list.size()+"本");
			}
		});
		borrowbookItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new borrowbook();
			}
		});
	}

}
