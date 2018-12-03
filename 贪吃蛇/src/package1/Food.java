package package1;

import java.util.Random;

public class Food {
	int x;
	int y;
	Random random=new Random();
	public Food() {
		this.x=5;
		this.y=5;
	}
	public void newfood() {
		this.x=random.nextInt(SnakeMap.maxline);
		this.y=random.nextInt(SnakeMap.maxline);
	}
}
