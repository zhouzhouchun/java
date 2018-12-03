package package1;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class MainFrame extends JFrame{
	SnakeMap sm;
	JButton startbt=null;
	Container con=null;
	public MainFrame() {
		this.setTitle("贪吃蛇");
		this.setSize(310, 400);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(null);
		con=this.getContentPane();
		startbt=new JButton("开始");
		startbt.setBounds(120, 120, 100, 50);
		this.add(startbt);
		sm=new SnakeMap();
		sm.setBounds(0, 60, sm.width*sm.maxline+1, sm.width*sm.maxline+1);
		sm.score.setBounds(50, 10, 60, 40);
		startbt.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				con.remove(startbt);
				con.add(sm);
				sm.requestFocus();
				con.add(sm.score);
				con.repaint();
				new Thread(sm).start();
			}
		});
		this.setVisible(true);
	}
	public static void main(String[] args) {
		new MainFrame();
	}
	
}
