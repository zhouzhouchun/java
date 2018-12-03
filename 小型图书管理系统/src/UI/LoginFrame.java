package UI;

import java.awt.Container;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;

import javax.swing.JPasswordField;
import javax.swing.JTextField;
import DAO.UserDAO;

public class LoginFrame extends JFrame implements ActionListener{
	JLabel namelable=null;
	JLabel pwdlable=null;
	JTextField nametxt=null;
	JPasswordField pwdtxt=null;
	Container cont=null;
	JButton loginbt=null;
	JButton registerbt=null;
	public LoginFrame() {
		this.setSize(270, 250);
		this.setLocationRelativeTo(null);
		this.setTitle("小型图书管理系统-用户登入");
		this.setResizable(false); 
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//设置控件
		namelable =new JLabel("账号");
		namelable.setBounds(40, 35, 50, 20);
		namelable.setFont(new Font("宋体",Font.BOLD,18));;
		pwdlable =new JLabel("密码");
		pwdlable.setBounds(40, 85, 50, 20);
		pwdlable.setFont(new Font("宋体",Font.BOLD,18));
		nametxt=new JTextField();
		nametxt.setBounds(90, 30, 120, 30);
		pwdtxt =new JPasswordField();
		pwdtxt.setBounds(90, 80, 120, 30);
		loginbt =new JButton("登入");
		registerbt =new JButton("注册");
		loginbt.setBounds(60, 150, 40, 30);
		loginbt.setMargin(new Insets(0,0,0,0));
		registerbt.setBounds(150, 150, 40, 30);
		registerbt.setMargin(new Insets(0,0,0,0));
		loginbt.addActionListener(this);
		registerbt.addActionListener(this);
		this.setVisible(true);
		//添加控件
		cont=this.getLayeredPane();
		cont.setLayout(null);
		setbackground();
		cont.add(namelable,JLayeredPane.PALETTE_LAYER);
		cont.add(pwdlable,JLayeredPane.PALETTE_LAYER);
		cont.add(nametxt,JLayeredPane.PALETTE_LAYER);
		cont.add(pwdtxt,JLayeredPane.PALETTE_LAYER);
		cont.add(loginbt,JLayeredPane.PALETTE_LAYER);
		cont.add(registerbt,JLayeredPane.PALETTE_LAYER);
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		int flag;
		// TODO Auto-generated method stub
		if(e.getSource()==loginbt) {
			UI.MainFrame.u.setName(this.nametxt.getText());
			UI.MainFrame.u.setPwd(new String(this.pwdtxt.getPassword()));
			flag=UserDAO.signin(UI.MainFrame.u);
			if(flag==2) {
				JOptionPane.showMessageDialog(null, "登入成功");
				UI.MainFrame.u.setIsadmin(false);
				new MainFrame();
				dispose();
			}else if(flag==-1) {
				JOptionPane.showMessageDialog(null, "登入失败");
			}else if(flag==3) {
				JOptionPane.showMessageDialog(null, "登入成功");
				UI.MainFrame.u.setIsadmin(true);
				new  MainFrame();
				dispose();
			}
		}else if(e.getSource()==registerbt) {
			dispose();
			new RegisterFrame();
			
		}
	}
	private void setbackground() {
		ImageIcon img=new ImageIcon("pics/1.jpg");
		JLabel jl=new JLabel(img);
		jl.setBounds(0, 0, img.getIconWidth(), img.getIconHeight());
		jl.setOpaque(true);
		//background.setOpaque(true);
		cont.add(jl,JLayeredPane.DEFAULT_LAYER);
	}
}
