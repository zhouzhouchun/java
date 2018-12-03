package UI;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Shape;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import 方块.Block;

public class Map extends JPanel implements Runnable {
	public int col = 10;//列
	public int row = 16;//行
	public int width = 20;
	boolean finish=false;
	int[][] value = new int[row][col];
	Block b = new Block();
	Block nextblock=null;
	JLabel scoreLB;
	NextBlock nb=null;
	int score=0;
	public Map() {
		nb=new NextBlock();
		nextblock = Block.newblock();
		b=Block.newblock();
		nb.b=nextblock;
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				value[i][j] = 0;
			}
		}
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
					changeblock(b);
					repaint();
					break;
				case KeyEvent.VK_DOWN:
					System.out.println("down");
					Blockmove(0, 1);
					repaint();
					break;
				case KeyEvent.VK_LEFT:
					System.out.println("left");
					Blockmove(-1, 0);	
					repaint();
					break;
				case KeyEvent.VK_RIGHT:
					System.out.println("right");
					Blockmove(1, 0);
					repaint();
					break;
				default:
					break;
				}

			}
		});
		scoreLB=new JLabel("分数：");
		
	}

	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		super.paint(g);
		

		BlockInMap(b);
		drawblock(g);
		g.setColor(Color.gray);
		for (int i = 0; i < row + 1; i++) {
			g.drawLine(0, i * width, width * col, i * width);
		}
		for (int i = 0; i < col + 1; i++) {
			g.drawLine(i * width, 0, i * width, width * row);
		}
	}

	public void BlockInMap(Block b) {
		for (int i = 0; i < 4; i++) {
				value[b.shape[i].y + b.y][b.shape[i].x + b.x] = b.type+1;
		} //System.out.println(value[0][4]);
	}

	public int Blockmove(int x, int y) {
		int flag = 1;
		//判断是否可以移动
		int arr[]=new int[4];
		int index=0;
		for (int i = 0; i < 4; i++) {
			int a=0;
			for (int j = 0; j < 4; j++) {
				if(b.shape[i].x+x==b.shape[j].x&&b.shape[i].y+y==b.shape[j].y)
					break;
				else {
					a++;
				}
			}
			if(a==4) {
				arr[index]=i;
				index++;
				//System.out.print(" i="+i);
			}
		}
		//
		//System.out.println("flag=" + flag);
		for (int i = 0; i < index; i++) {
			if(b.shape[arr[i]].x+x+b.x>=0&&b.shape[arr[i]].x+x+b.x<col&&b.shape[arr[i]].y+b.y+y<row) {
				if(value[b.shape[arr[i]].y+b.y+y][b.shape[arr[i]].x+x+b.x]==0)
					flag=1;
				else {
					flag=0;
					break;
				}
			}else {
				flag=0;
				break;
			}
		}
		if (flag ==1 ) {
			for (int i = 0; i < 4; i++) {
				value[b.shape[i].y + b.y][b.shape[i].x + b.x] = 0;
				value[b.shape[i].y + b.y + y][b.shape[i].x + b.x + x] = 1;

			}
			b.x = b.x + x;
			b.y = b.y + y;
			return 1;
		}
		return 0;

	}

	public void drawblock(Graphics g) {
		//g.setColor(Color.blue);
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				switch (value[i][j]) {
				case 0:
					break;
				case 1:
					g.setColor(Color.BLACK);
					g.fillRect(j * width, i * width, width, width);
					break;
				case 2:
					g.setColor(Color.blue);
					g.fillRect(j * width, i * width, width, width);
					break;
				case 3:
					g.setColor(Color.cyan);
					g.fillRect(j * width, i * width, width, width);
					break;
				case 4:
					g.setColor(Color.darkGray);
					g.fillRect(j * width, i * width, width, width);
					break;
				case 5:
					g.setColor(Color.green);
					g.fillRect(j * width, i * width, width, width);
					break;
				case 6:
					g.setColor(Color.magenta);
					g.fillRect(j * width, i * width, width, width);
					break;
				case 7:
					g.setColor(Color.red);
					g.fillRect(j * width, i * width, width, width);
					break;
				default:
					break;
				}
				
			}
		}
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			while (!finish) {
				if (b.life == 0) {
					romoveblock();
					b = nextblock;
					nextblock=Block.newblock();
					nb.b=nextblock;
					nb.repaint();
					if(value[b.shape[3].y+b.y][b.shape[3].x+b.x]!=0) {
						finish=true;
					}
					//BlockInMap(b);
				} else {	
					if (Blockmove(0, 1) == 0) {
						b.life = 0;
					}
					repaint();
					Thread.sleep(400);
				}
			}
			JOptionPane.showMessageDialog(null, "游戏结束");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void romoveblock() {

		for (int i = row-1; i >=0; i--) {
			int flag=0;
			for (int j=0; j < col; j++) {
				if(value[i][j]!=0) {
					flag++;
				}
			}
			if(flag==10) {
				score++;
				scoreLB.setText("分数："+score*10);
				for (int j = i; j>=0; j--) {
					for (int j2 = 0; j2 < col; j2++) {
						if(j!=0) {
							value[j][j2]=value[j-1][j2];	
						}else {
							value[j][j2]=0;	
						}
						
					}
				}
				i++;
			}
		}
		repaint();

	}
	public void changeblock(Block b) {
		Point[] temp=new Point[4];
		for (int i = 0; i < 4; i++) {
			temp[i]=new Point(b.shape[i].x, b.shape[i].y);
			value[temp[i].y+b.y][temp[i].x+b.x]=0;
		}
		int flag=0;
		b.change();
		for (int i = 0; i < 4; i++) {
			if(b.shape[i].y+b.y<row&&b.shape[i].x+b.x>0&&b.shape[i].x+b.x<col)
				if(value[b.shape[i].y+b.y][b.shape[i].x+b.x]==0)
					flag++;
		}
	
		System.out.println("flag="+flag);
		if(flag==4) {
			for (int i = 0; i < 4; i++) {
				value[b.shape[i].y+b.y][b.shape[i].x+b.x]=1;//给变化之后的位置标记
			}
			b.ct++;
		}else {
			for (int i = 0; i < 4; i++) {
				b.shape[i].setLocation(temp[i].x, temp[i].y);
			}
		}
		BlockInMap(b);
	}

}
