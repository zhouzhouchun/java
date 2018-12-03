package UI;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JLabel;
import javax.swing.JPanel;

import 方块.Block;

public class NextBlock extends JPanel {
	Block b = null;
	public int col = 4;//列
	public int row = 4;//行
	int map[][] = new int[row][col];
	public JLabel la1 = null;
	public int width = 17;

	public NextBlock() {
		la1 = new JLabel("下一个方块");
	}

	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		super.paint(g);
		setnextblock();
		drawblock(g);
		g.setColor(Color.gray);
	}

	public void setnextblock() {
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				map[i][j] = 0;
			}
		}
		for (int i = 0; i < 4; i++) {
			map[b.shape[i].x][b.shape[i].y] = 1;
		}
		System.out.println(b.type);
	}

	public void drawblock(Graphics g) {
		//g.setColor(Color.blue);
		switch (b.type+1) {
		case 0:
			break;
		case 1:
			g.setColor(Color.BLACK);
			break;
		case 2:
			g.setColor(Color.blue);
			break;
		case 3:
			g.setColor(Color.cyan);
			break;
		case 4:
			g.setColor(Color.darkGray);
			break;
		case 5:
			g.setColor(Color.green);
			break;
		case 6:
			g.setColor(Color.magenta);
			break;
		case 7:
			g.setColor(Color.red);
			break;
		default:
			break;
		}

		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				if (map[j][i] != 0)
					g.fillRect(j * width, i * width, width, width);
			}

		}
	}
}
