package UI;

import java.awt.Container;
import java.awt.Insets;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class QueryCombo extends JPanel {
	JComboBox<String> comboBox;
	JPanel jp;
	Container cont;
	JTextField jtxt;
	JButton querybt;
	public QueryCombo() {
		//this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(600, 100);
		cont=new Container();
		comboBox=new JComboBox<String>();
		comboBox.setBounds(350,10 ,70,30);
		comboBox.addItem("id");
		comboBox.addItem("书名");
		comboBox.addItem("作者");
		jtxt=new JTextField();
		jtxt.setBounds(50, 10,300,30);
		jtxt.addFocusListener(new FocusListener() {
			String str="请输入查询信息";
			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub
				if(jtxt.getText().equals("")) {
					jtxt.setText(str);
				}
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub
				if(jtxt.getText().equals(str)) {
					jtxt.setText("");
				}
			}
		});
		querybt=new JButton("查  询");
		querybt.setBounds(420, 10, 70, 30);
		querybt.setMargin(new Insets(0, 0, 0, 0));
		this.setLayout(null);
		this.add(comboBox);
		this.add(jtxt);
		this.add(querybt);
	}

}
