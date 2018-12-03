package package1;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;


public class Snake {
	ArrayList<Point> body = new ArrayList<>();
	int length;
	int head;
	int width;
	Direction direction;
	boolean live=true;
	public Snake() {
		length = 1;
		head = 0;
		width = 30;
		direction = Direction.right;
		body.add(new Point(3, 0));
		/*body.add(new Point(2, 0));
		body.add(new Point(1, 0));*/

	}
	public void move() {
		for (int i = length - 1; i > 0; i--) {
			body.get(i).x = body.get(i - 1).x;
			body.get(i).y = body.get(i - 1).y;
		}
		switch (this.direction) {
		case right:
			body.get(head).x = body.get(head).x + 1;
			break;
		case left:
			body.get(head).x = body.get(head).x -1 ;
			break;
		case up:
			body.get(head).y = body.get(head).y - 1;
		break;
		case down:
			body.get(head).y = body.get(head).y+1;
			break;
		default:
			break;
		}
	}
	public boolean eatfood(Food f) {
		if(f==null) {
			return false;
		}
		if(body.get(head).x==f.x&&body.get(head).y==f.y) {
			length++;
			body.add(new Point(f.x, f.y));
			return true;
		}else 
			return false;
	}
	public void islive() {
		for (int i = 1; i < body.size(); i++) {
			if(body.get(head).x==body.get(i).x&&body.get(head).y==body.get(i).y)
				live=false;
			
		}
		if(body.get(head).x<0||body.get(head).x>SnakeMap.maxline-1||body.get(head).y<0||body.get(head).y>SnakeMap.maxline-1)
			live=false;
	}
	
}
