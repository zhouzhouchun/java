package UI;

import java.awt.Container;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import DAO.UserDAO;

public class ChangePWD extends JFrame implements ActionListener{
	JLabel oldtable=null;
	JLabel newtable=null;
	JTextField oldtxt=null;
	JTextField newtxt=null;
	Container cont=null;
	JButton surebt=null;
	JButton cancelbt=null;
	public  ChangePWD() {
		this.setSize(270, 250);
		this.setLocationRelativeTo(null);
		this.setTitle("修改密码");
		this.setResizable(false); 
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		oldtable =new JLabel("旧密码");
		newtable =new JLabel("新密码");
		oldtable.setBounds(40, 30, 50, 20);
		newtable.setBounds(40, 80, 50, 20);
		oldtxt=new JTextField();
		oldtxt.setBounds(90, 30, 120, 30);
		newtxt =new JTextField();
		newtxt.setBounds(90, 80, 120, 30);
		surebt =new JButton("确定");
		cancelbt =new JButton("取消");
		surebt.setBounds(60, 150, 40, 30);
		surebt.setMargin(new Insets(0,0,0,0));
		cancelbt.setBounds(150, 150, 40, 30);
		cancelbt.setMargin(new Insets(0,0,0,0));
		surebt.addActionListener(this);
		cancelbt.addActionListener(this);
		this.setVisible(true);
		
		//添加控件
		cont=this.getContentPane();
		cont.setLayout(null);
		cont.add(oldtable);
		cont.add(newtable);
		cont.add(oldtxt);
		cont.add(newtxt);
		cont.add(surebt);
		cont.add(cancelbt);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==surebt) {
			String newpwd=this.newtxt.getText();
			if(this.oldtxt.getText().equals(UI.MainFrame.u.getPwd())&&UserDAO.ChangePassword(UI.MainFrame.u, newpwd)==1 ) {
				JOptionPane.showMessageDialog(null, "修改成功");
				dispose();
			}else {
				JOptionPane.showMessageDialog(null, "修改失败");
			}
			}
		else if(e.getSource()==cancelbt) {
			dispose();
		}
	}
	
}
