package UI;

import java.awt.Container;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import DAO.UserDAO;
import test.User;

public class RegisterFrame extends JFrame implements ActionListener{
	JLabel nametable=null;
	JLabel pwdtable=null;
	JTextField nametxt=null;
	JTextField pwdtxt=null;
	Container cont=null;
	JButton surebt=null;
	JButton cancelbt=null;
	public RegisterFrame() {
		this.setSize(270, 250);
		this.setLocationRelativeTo(null);
		this.setTitle("С��ͼ�����ϵͳ-ע�ᴰ��");
		this.setResizable(false); 
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		//���ÿؼ�
		nametable =new JLabel("�˺�");
		nametable.setBounds(40, 30, 50, 20);
		pwdtable =new JLabel("����");
		pwdtable.setBounds(40, 80, 50, 20);
		nametxt=new JTextField();
		nametxt.setBounds(90, 30, 120, 30);
		nametxt.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				nametxt.setText(nametxt.getText().replaceAll("\\s+", ""));
			}
		});
		pwdtxt =new JTextField();
		pwdtxt.setBounds(90, 80, 120, 30);
		surebt =new JButton("ȷ��");
		cancelbt =new JButton("ȡ��");
		surebt.setBounds(60, 150, 40, 30);
		surebt.setMargin(new Insets(0,0,0,0));
		cancelbt.setBounds(150, 150, 40, 30);
		cancelbt.setMargin(new Insets(0,0,0,0));
		surebt.addActionListener(this);
		cancelbt.addActionListener(this);
		this.setVisible(true);
		//��ӿؼ�
		cont=this.getContentPane();
		cont.setLayout(null);
		cont.add(nametable);
		cont.add(pwdtable);
		cont.add(nametxt);
		cont.add(pwdtxt);
		cont.add(surebt);
		cont.add(cancelbt);
}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==surebt) {
			User u=new User();
			u.setName(this.nametxt.getText());
			u.setPwd(this.pwdtxt.getText());
			if(u.getName().equals("")||u.getPwd().equals("")) {
				JOptionPane.showMessageDialog(null, "�����˺Ų���Ϊ��");
				return;
			}
			System.out.println(u.getisIsadmin());
			int flag=UserDAO.register(u);
			if(flag==1) {
				JOptionPane.showMessageDialog(null, "ע��ɹ�");
				dispose();
				new LoginFrame();
			}else if(flag==0){
				JOptionPane.showMessageDialog(null, "ע��ʧ��");
			}else if(flag==-1) {
				JOptionPane.showMessageDialog(null, "�û����Ѵ���");
			}
			}
		else if(e.getSource()==cancelbt) {
			dispose();
			new LoginFrame();
		}
		}
	}
