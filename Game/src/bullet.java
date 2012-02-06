import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class bullet {
	public int x, y;
	public boolean mLeft = false;
	public boolean mRight = false;
	public Rectangle bullet;
	public int speed;
	
	public boolean hit = false;
	
	public bullet(int dir, int mspeed) {
		speed=mspeed;
		if (dir == 0) {
			bullet = new Rectangle(x, y, 12, 3);
			mLeft = true;
		}
		
		if (dir == 1) {
			bullet = new Rectangle(x, y, 12, 3);
			mRight = true;
		}
	}
	
	public void draw (Graphics g){
		g.setColor(Color.MAGENTA);
		g.fillRect(x-GameThread.xs, y-GameThread.ys, bullet.width, bullet.height);
	}
	
	public void move (){
		if(mLeft){
			x -= speed;
			if (checkbulletCollideSidesLeft()){
				Player.bCount --;
				hit=true;
			}
		}
		
		if(mLeft){
			x += speed;
			if (checkbulletCollideSidesRight()){
				Player.bCount --;
				hit=true;
			}
		}
		
		bullet.x = x;
		bullet.y = y;
	}
	
	public boolean checkbulletCollideSidesLeft () {
		boolean cb=false;
		
		for(int i = 0; i < MapLoader.blocks.length; i++) {
			if(Collider.collSidesLeft(MapLoader.blocks[i], bullet)) {
				cb=true;
			}
		}
		
		
		return cb;
	}
	
	public boolean checkbulletCollideSidesRight () {
		boolean cb=false;
		for(int i = 0; i < MapLoader.blocks.length; i++) {
			if(Collider.collSidesRight(MapLoader.blocks[i], bullet)) {
				cb=true;
			}
		}
		return cb;
	}
	
	
	
	public boolean checkbulletCollideDown () {
		boolean cb=false;
		for(int i = 0; i < MapLoader.blocks.length; i++) {
			if(Collider.collDown(bullet, MapLoader.blocks[i])) {
				cb=true;
			}
		}
		return cb;
	}
	
	public boolean checkbulletCollideUp () {
		boolean cb=false;
		for(int i = 0; i < MapLoader.blocks.length; i++) {
			if(Collider.collUp(bullet, MapLoader.blocks[i])) {
				cb=true;
			}
		}
		return cb;
	}
	
}
