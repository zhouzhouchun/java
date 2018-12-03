package 方块;

import java.awt.Point;
import java.util.Random;

public class Block {
	public int x=4;
	public int y=0;
	public int type=0;
	public int ct=0;
	public int life=1;
	public Point[] shape=new Point[4];
	public Block() {
		shape[0]=new Point();
		shape[1]=new Point();
		shape[2]=new Point();
		shape[3]=new Point();
	}
	/*type=0 00   type=1  0000
	 *       00   
	 * type=2   0     type=3        0
	 * 			000               000
	 * type =4 00		type=5  00
	 *    		00 			   00
	 *    
	 *type =6  0
	 *        000
	 */ 
	public  static Block newblock() {
		Block b=new Block();
		Random r=new Random();
		b.type=r.nextInt(7);
		switch (b.type) {
		case 0:
			b.shape[0].setLocation(0, 0);
			b.shape[1].setLocation(1, 0);
			b.shape[2].setLocation(0, 1);
			b.shape[3].setLocation(1, 1);
			break;
		case 1:
			b.shape[0].setLocation(0, 0);
			b.shape[1].setLocation(1, 0);
			b.shape[2].setLocation(2, 0);
			b.shape[3].setLocation(3, 0);
			break;
		case 2:
			b.shape[0].setLocation(0, 0);
			b.shape[1].setLocation(0, 1);
			b.shape[2].setLocation(1, 1);
			b.shape[3].setLocation(2, 1);
			break;
		case 3:
			b.shape[0].setLocation(2, 0);
			b.shape[1].setLocation(0, 1);
			b.shape[2].setLocation(1, 1);
			b.shape[3].setLocation(2, 1);
			break;
		case 4:
			b.shape[0].setLocation(0, 0);
			b.shape[1].setLocation(1, 0);
			b.shape[2].setLocation(1, 1);
			b.shape[3].setLocation(2, 1);
			break;
		case 5:
			b.shape[0].setLocation(2, 0);
			b.shape[1].setLocation(1, 0);
			b.shape[2].setLocation(1, 1);
			b.shape[3].setLocation(0, 1);
			break;
		case 6:
			b.shape[0].setLocation(1, 0);
			b.shape[1].setLocation(0, 1);
			b.shape[2].setLocation(1, 1);
			b.shape[3].setLocation(2, 1);
			break;
		default:
			break;
		}
		return b;
	}
	public void change() {
		System.out.println("方块类型:"+type+" 变化次数:"+ct);
		switch (type) {
		case 0:
			break;
		case 1:
		{
			switch (ct%2) {
			case 0:
				shape[0].setLocation(0, 0);
				shape[1].setLocation(0, 1);
				shape[2].setLocation(0, 2);
				shape[3].setLocation(0, 3);
				break;
			case 1:
				shape[0].setLocation(0, 0);
				shape[1].setLocation(1, 0);
				shape[2].setLocation(2, 0);
				shape[3].setLocation(3, 0);
				break;
			default:
				break;
			}
			break;
		}
		case 2:
		{
			switch(ct%4) {
			case 0:
				shape[0].setLocation(1, 0);
				shape[1].setLocation(0, 0);
				shape[2].setLocation(0, 1);
				shape[3].setLocation(0, 2);
				break;
			case 1:
				shape[0].setLocation(0, 0);
				shape[1].setLocation(1, 0);
				shape[2].setLocation(2, 0);
				shape[3].setLocation(2, 1);
				break;
			case 2:
				shape[0].setLocation(1, 0);
				shape[1].setLocation(1, 1);
				shape[2].setLocation(1, 2);
				shape[3].setLocation(0, 2);
				break;
			case 3:
				shape[0].setLocation(0, 0);
				shape[1].setLocation(0, 1);
				shape[2].setLocation(1, 1);
				shape[3].setLocation(2, 1);
				break;
			default:
				break;
			}
			break;
		}
		case 3:{
			switch (ct%4) {
			case 0:
				shape[0].setLocation(0, 0);
				shape[1].setLocation(0, 1);
				shape[2].setLocation(0, 2);
				shape[3].setLocation(1, 2);
				break;
			case 1:
				shape[0].setLocation(0, 1);
				shape[1].setLocation(0, 0);
				shape[2].setLocation(1, 0);
				shape[3].setLocation(2, 0);
				break;
			case 2:
				shape[0].setLocation(0, 0);
				shape[1].setLocation(1, 0);
				shape[2].setLocation(1, 1);
				shape[3].setLocation(1, 2);
				break;
			case 3:
				shape[0].setLocation(2, 0);
				shape[1].setLocation(0, 1);
				shape[2].setLocation(1, 1);
				shape[3].setLocation(2, 1);
				break;
			default:
				break;
			}
			break;
		}
		case 4:{
			switch (ct%2) {
			case 0:
				shape[0].setLocation(1, 0);
				shape[1].setLocation(1, 1);
				shape[2].setLocation(0, 1);
				shape[3].setLocation(0, 2);
				break;
			case 1:
				shape[0].setLocation(0, 0);
				shape[1].setLocation(1, 0);
				shape[2].setLocation(1, 1);
				shape[3].setLocation(2, 1);
				break;
			default:
				break;
			}
			break;
		}
		case 5:{
			switch (ct%2) {
			case 0:
				shape[0].setLocation(0, 0);
				shape[1].setLocation(0, 1);
				shape[2].setLocation(1, 1);
				shape[3].setLocation(1, 2);
				break;
			case 1:
				shape[0].setLocation(2, 0);
				shape[1].setLocation(1, 0);
				shape[2].setLocation(1, 1);
				shape[3].setLocation(0, 1);
				break;
			default:
				break;
			}
			break;
		}
		case 6:{
			switch (ct%4) {
			case 0:
				shape[0].setLocation(0, 0);
				shape[1].setLocation(0, 1);
				shape[2].setLocation(1, 1);
				shape[3].setLocation(0, 2);
				break;
			case 1:
				shape[0].setLocation(0, 0);
				shape[1].setLocation(1, 0);
				shape[2].setLocation(2, 0);
				shape[3].setLocation(1, 1);
				break;
			case 2:
				shape[0].setLocation(1, 0);
				shape[1].setLocation(0, 1);
				shape[2].setLocation(1, 1);
				shape[3].setLocation(1, 2);
				break;
			case 3:
				shape[0].setLocation(1, 0);
				shape[1].setLocation(0, 1);
				shape[2].setLocation(1, 1);
				shape[3].setLocation(2, 1);
				break;
			default:
				break;
			}
			break;
		}
		default:
			break;
		}
		
	}

}
