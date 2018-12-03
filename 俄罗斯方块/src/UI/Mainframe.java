package UI;

import javax.swing.JFrame;

import 方块.Block;

public class Mainframe extends JFrame{
	Map m;
	public Mainframe() {
		this.setTitle("俄罗斯方块");
		this.setSize(310, 400);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(null);
		m=new Map();
		m.setBounds(0, 50, m.col*m.width+1, m.row*m.width+1);
		m.setFocusable(true);
		this.add(m);
		m.scoreLB.setBounds(20, 10, 80, 40);
		m.nb.setBounds(230, 100, m.nb.col*m.nb.width+1,m. nb.row*m.nb.width+1);
		m.nb.la1.setBounds(230, 50, 100, 40);
		this.add(m.nb.la1);
		this.add(m.nb);
		this.add(m.scoreLB);
		this.setVisible(true);
		new Thread(m).start();
	}
	public static void main(String[] args) {
		new Mainframe();

	}
}
