package package1;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class SnakeMap extends JPanel implements Runnable {
	public static int width = 15;
	public static int maxline = 20;
	boolean flag=true;
	Food f;
	Snake s;
	JLabel score=null;
	SnakeMap() {
		setFocusable(true);
		this.addKeyListener(new KeyListener() {

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
				switch (e.getKeyCode()) {
				case KeyEvent.VK_UP:
					if(s.direction!=Direction.down)
						s.direction = Direction.up;
					break;
				case KeyEvent.VK_DOWN:
					if(s.direction!=Direction.up)
						s.direction = Direction.down;
					break;
				case KeyEvent.VK_LEFT:
					if(s.direction!=Direction.right)
						s.direction = Direction.left;
					break;
				case KeyEvent.VK_RIGHT:
					if(s.direction!=Direction.left)
						s.direction = Direction.right;
					break;
				default:
					break;
				}

			}
		});
		f = new Food();
		s = new Snake();
		score=new JLabel("得分: 0");
	}

	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		super.paint(g);
		g.setColor(Color.BLACK);
		for (int i = 0; i < maxline + 1; i++) {
			g.drawLine(0, i * width, maxline * width, i * width);
			g.drawLine(i * width, 0, i * width, maxline * width);

		}
		drawSnake(g);
		drawfood(g, f);
		s.move();
		s.islive();
		if (s.eatfood(f) || flag) {
			score.setText("得分:"+s.length);
			f.newfood();
		}
	}

	public void drawSnake(Graphics g) {
		g.setColor(Color.BLUE);
		for (int j = 0; j < s.body.size(); j++) {
			g.fillRect(s.body.get(j).x * width, s.body.get(j).y * width, width, width);
		}
	}

	public void drawfood(Graphics g, Food f) {
		g.setColor(Color.red);
		g.fillRect(f.x * width, f.y * width, width, width);
		flag=false;
		
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
				while (true) {
					if(s.live) {
						Thread.sleep(200);//在此处睡眠一会，要不运动太快
						repaint();
					}else {
						JOptionPane.showMessageDialog(null, "游戏结束");
						break;
					}
				}
		} catch (InterruptedException e1) {
			// TODO 自动生成的 catch 块
			e1.printStackTrace();
		}

	}
}
