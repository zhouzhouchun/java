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
	// ����һ���˵�
	JMenu userMenu;
	JMenu queryMenu;
	JMenu glMenu;
	// �����˵�ѡ��
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
		this.setTitle("С��ͼ�����ϵͳ---" + UI.MainFrame.u.getName());
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
		userMenu = new JMenu("�û�");
		queryMenu = new JMenu("ͼ���ѯ");
		glMenu = new JMenu("ͼ�����");
		menuBar.add(userMenu);
		menuBar.add(queryMenu);
		if(u.getisIsadmin()==true)
			menuBar.add(glMenu);
		// �û�
		exitMenuItem = new JMenuItem("�˳�����");
		changepwdMenuItem = new JMenuItem("�޸�����");
		queryborrowedbookItem=new JMenuItem("�����ѯ");
		borrowbookItem = new JMenuItem("����");
		userMenu.add(changepwdMenuItem);
		userMenu.add(exitMenuItem);
		userMenu.add(queryborrowedbookItem);
		userMenu.add(borrowbookItem);
		// ͼ�����
		add = new JMenuItem("���ͼ��");
		revise = new JMenuItem("�޸�ͼ��");
		delect = new JMenuItem("ɾ��ͼ��");
		glMenu.add(add);
		glMenu.add(delect);
		glMenu.add(revise);
		// ��ѯ
		querybyname = new JMenuItem("��������ѯ");
		querybyid = new JMenuItem("��id��ѯ");
		queryall = new JMenuItem("��ѯ����");
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
					booknum.setText("��ѯ��ͼ��:"+list.size()+"��");
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
						booknum.setText("��ѯ��ͼ��:"+list.size()+"��");
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else if (qc.comboBox.getSelectedItem().equals("����")) {
					try {
						list = BookDAO.querybyname(qc.jtxt.getText());
						booktable.addbook(list);
						booknum.setText("��ѯ��ͼ��:"+list.size()+"��");
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else if (qc.comboBox.getSelectedItem().equals("����")) {
					try {
						list = BookDAO.querybyauthor(qc.jtxt.getText());
						booktable.addbook(list);
						booknum.setText("��ѯ��ͼ��:"+list.size()+"��");
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
				booknum.setText("��ѯ��ͼ��:"+list.size()+"��");
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
