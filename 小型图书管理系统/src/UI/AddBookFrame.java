package UI;

import java.awt.Container;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.text.SimpleDateFormat;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import DAO.BookDAO;
import test.Book;

public class AddBookFrame extends JFrame implements ActionListener{

	JLabel id = null;
	JLabel name = null;
	JLabel author = null;
	JLabel price = null;
	JLabel count = null;
	JLabel type = null;
	JLabel product = null;
	JLabel date = null;
	JTextField idtxt = null;
	JTextField nametxt = null;
	JTextField authortxt = null;
	JTextField pricetxt = null;
	JTextField counttxt = null;
	JTextField typetxt = null;
	JTextField producttxt = null;
	JTextField datetxt = null;
	Container cont = null;
	JButton surebt = null;
	JButton cancelbt = null;

	public AddBookFrame() {
		this.setSize(300, 360);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setTitle("添加图书");
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		id = new JLabel("id*");
		name = new JLabel("书名*");
		author = new JLabel("作者*");
		price = new JLabel("价格");
		count = new JLabel("数量");
		type = new JLabel("类型");
		product = new JLabel("出版社");
		date = new JLabel("日期");
		idtxt = new JTextField();
		nametxt = new JTextField();
		authortxt = new JTextField();
		pricetxt = new JTextField();
		counttxt = new JTextField();
		typetxt = new JTextField();
		producttxt = new JTextField();
		datetxt = new JTextField();
		surebt = new JButton("确定");
		surebt.setMargin(new Insets(0, 0, 0, 0));
		cancelbt = new JButton("取消");
		cancelbt.setMargin(new Insets(0, 0, 0, 0));
		surebt.addActionListener(this);
		cancelbt.addActionListener(this);
		id.setBounds(30, 20, 50, 30);
		name.setBounds(30, 50, 50, 30);
		author.setBounds(30, 80, 50, 30);
		price.setBounds(30, 110, 50, 30);
		count.setBounds(30, 140, 50, 30);
		type.setBounds(30, 170, 50, 30);
		product.setBounds(30, 200, 50, 30);
		date.setBounds(30, 230, 50, 30);
		idtxt.setBounds(100, 20, 150, 30);
		nametxt.setBounds(100, 50, 150, 30);
		authortxt.setBounds(100, 80, 150, 30);
		pricetxt.setBounds(100, 110, 150, 30);
		counttxt.setBounds(100, 140, 150, 30);
		typetxt.setBounds(100, 170, 150, 30);
		producttxt.setBounds(100, 200, 150, 30);
		datetxt.setBounds(100, 230, 150, 30);
		surebt.setBounds(50, 280, 50, 30);
		cancelbt.setBounds(180, 280, 50, 30);
		this.setVisible(true);
		// 添加控件
		cont = this.getContentPane();
		cont.setLayout(null);
		cont.add(id);
		cont.add(name);
		cont.add(author);
		cont.add(price);
		cont.add(count);
		cont.add(type);
		cont.add(product);
		cont.add(date);
		cont.add(idtxt);
		cont.add(nametxt);
		cont.add(authortxt);
		cont.add(pricetxt);
		cont.add(counttxt);
		cont.add(typetxt);
		cont.add(producttxt);
		cont.add(datetxt);
		cont.add(surebt);
		cont.add(cancelbt);
	}
	Book b;
	int flag=1;
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource()==surebt) {
			b=new Book();
			if(idtxt.getText().equals("")||nametxt.getText().equals("")||authortxt.getText().equals("")) {
				flag=0;
			}else {
				b.setBook_id(idtxt.getText());
				b.setBook_name(nametxt.getText());
				b.setBook_author(authortxt.getText());
			}
			System.out.println(flag);
			try {	
				b.setBook_price(Float.parseFloat(pricetxt.getText()));
				b.setBook_count(Integer.parseInt(counttxt.getText()));
				b.setBook_type(typetxt.getText());
				b.setBook_product(producttxt.getText());
				b.setBook_date(new Date(new SimpleDateFormat("yyyy-mm-dd").parse(datetxt.getText()).getTime()));
			} catch (Exception e1) {
			}
			if(flag==1) {
				int i=BookDAO.addbook(b);
				if(i==1) {
				JOptionPane.showMessageDialog(null, "添加成功");
				}else if(i==0) {
				JOptionPane.showMessageDialog(null, "id已存在.添加失败");
				}
			}
			else{
				JOptionPane.showMessageDialog(null, "缺少必要数据");
			}
		} else if (e.getSource()==cancelbt) {
			this.dispose();
		}
	}
}
