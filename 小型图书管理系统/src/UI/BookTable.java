package UI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.RowSorter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import DAO.BookDAO;
import test.Book;
public class BookTable extends JScrollPane {
		private int  MaxLine=80;
		String[] columnNames = {"id","书名","作者","价格","数量","类型","出版社","日期",};// 定义表格列名数组
		Object[][] tableValues=new Object[MaxLine][8];
		//DefaultTableModel jtbmodel;
		JTable table;
		JScrollPane scrollPane;
		//右键菜单---------
		JPopupMenu jpm;
		JMenuItem item1;
		JMenuItem item2;
		int height=15;
		int size=0;
	    public BookTable(){
	        super();
	        //jtbmodel=new DefaultTableModel(tableValues,columnNames);
	        table = new JTable(tableValues,columnNames);
	        //table=new JTable(jtbmodel);
	        scrollPane = new JScrollPane(table);
	        //getContentPane().add(scrollPane, BorderLayout.CENTER);
	        //添加右键菜单
	        table.addMouseListener(new MouseAdapter() {
	            public void mouseClicked(MouseEvent e){
	              if (e.getButton() == MouseEvent.BUTTON3&&UI.MainFrame.u.getisIsadmin()==true){
	                //在table显示
	                jpm = new JPopupMenu();
	                item1=new JMenuItem("修改");
	                item2=new JMenuItem("删除");
	                int i = table.rowAtPoint(e.getPoint());
	                item1.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							// TODO Auto-generated method stub
							 ReviseBookFrame b= new ReviseBookFrame();
							 b.idtxt.setText((String)tableValues[i][0]);
							 b.query.doClick();
						}
					});
	                item2.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							// TODO Auto-generated method stub
							DelectBookFrame d=new DelectBookFrame();
							 d.idtxt.setText((String)tableValues[i][0]);
							 d.query.doClick();
						}
					});
	                //表格 的rowAtPoint方法返回坐标所在的行号，参数为坐标类型，
	                jpm.add(item1);
	                jpm.add(item2);
	                if(tableValues[i][0]!=null) {
	                	jpm.show(table, e.getX(), e.getY());
	                }

	              }
	            }
	          });
	        ///------------------------------------
	        //changeAndSortTable(table, columnNames,tableValues);
	    }
	    public void addbook(ArrayList<Book> list) {
	    	size=list.size();
	    	for (int i = 0; i < size; i++) {
				tableValues[i][0]=list.get(i).getBook_id();
				tableValues[i][1]=list.get(i).getBook_name();
				tableValues[i][2]=list.get(i).getBook_author();
				tableValues[i][3]=list.get(i).getBook_price();
				tableValues[i][4]=list.get(i).getBook_count();
				tableValues[i][5]=list.get(i).getBook_type();
				tableValues[i][6]=list.get(i).getBook_product();
				tableValues[i][7]=list.get(i).getBook_date();
				//tableValues[i][8]=list.get(i).getBorrowed();
				//table.setRowHeight(height);
			}
	    	//table.setSize(400, size*height);
	    	if(MaxLine-size>0) {
	    		for (int i = size; i < MaxLine; i++) {
					tableValues[i][0]=null;
					tableValues[i][1]=null;
					tableValues[i][2]=null;
					tableValues[i][3]=null;
					tableValues[i][4]=null;
					tableValues[i][5]=null;
					tableValues[i][6]=null;
					tableValues[i][7]=null;
					//tableValues[i][8]=null;
				}
	    	}
	    	table.repaint();
	    }
  
}

