import java.awt.*;

public class Player {
public int x, y;
public int playerW = 24;
public int playerH = 36;

public int fSpeed = 2;
public int fFrame = 0;
public int fH = 50;

public int mSpeed=1;
public int mFrame=0;
public int mResetSpeed = 1;
public int mFallingSpeed = 1;

public int jFar = 100; //in pixels
public int jFrame = 0;
public int jCountFrame = 0;
public int jCountSpeed = fSpeed;

public int bSpeed = 2;
public int bFrame = 0;
public int bReloadTime = 20;
public static int bCount = 0;
public int bC = 0;

public boolean falling = false;
public boolean jumping = false;
public boolean shooting = false;


public Rectangle player;
public bullet Bullets[];


	public Player() {
		player = new Rectangle(x, y, playerW, playerH);
		Bullets = new bullet[3];
	}
	
	public void draw (Graphics g) {
		g.setColor(Color.green);
		g.fillRect(x - GameThread.xs, player.y - GameThread.ys, player.width, player.height);
		
		for(int i = 0; i < Bullets.length; i++) {
			if (Bullets[i] != null){
				Bullets[i].draw(g);
			}
		}
		
	}
	
	public void shoot() {
		for(int i = 0; i < Bullets.length; i++) {
			if(Bullets[i]!=null){
				if (Bullets[i].hit){
					Bullets[i] = null;
				} else {
					Bullets[i].move();
				}
			}
		}
		
		if (shooting && bFrame <= bReloadTime && bCount <3) {
			Bullets[bC] = new bullet(1, 3);
			bCount ++;
			bC ++;
		}
	}
	
	public void move (boolean ml, boolean mr) {
		shoot();
		
		if (!jumping) {
			if(fFrame>=fSpeed){
				
				if(checkPlayerCollideDown()) {
						falling = false;
					} else {
						falling = true;
					}
			
				if (falling){
					y+=1;
					GameThread.ys++;
				}
			fFrame=0;
		} else {
			fFrame++;
		}
		}
		
		if(jCountFrame>=jCountSpeed){
			if (jumping) {
				if(!checkPlayerCollideUp()){
					if(jFrame <= jFar) {
						y -=1;
						GameThread.ys -=1;
						
						jFrame += 1;
					} else {
						jFrame =0;
						jumping = false;
					}
				} else {
					jumping = false;
					jFrame=0;
				}
			}
			
			
			jCountFrame=0;
		} else {
			jCountFrame +=1;
		}
		
		//Movement speed check
		if(falling){
			mSpeed = mFallingSpeed;
		} else {
			mSpeed = mResetSpeed;
		}
		
		
		//Movements
		if(mFrame>=mSpeed){
			
			if(mr && !checkPlayerCollideSidesLeft()){
				x += 1;
				GameThread.xs+=1;
			}
			
			if(ml && !checkPlayerCollideSidesRight()){
				x -= 1;
				GameThread.xs-=1;
			}
			
			mFrame=0;
		} else {
			mFrame += 1;
		}
		player.x = x;
		player.y=y;
	}
	
	
	public boolean checkPlayerCollideSidesLeft () {
		boolean cb=false;
		
		for(int i = 0; i < MapLoader.blocks.length; i++) {
			if(Collider.collSidesLeft(MapLoader.blocks[i], player)) {
				cb=true;
			}
		}
		
		
		return cb;
	}
	
	public boolean checkPlayerCollideSidesRight () {
		boolean cb=false;
		for(int i = 0; i < MapLoader.blocks.length; i++) {
			if(Collider.collSidesRight(MapLoader.blocks[i], player)) {
				cb=true;
			}
		}
		return cb;
	}
	
	
	
	public boolean checkPlayerCollideDown () {
		boolean cb=false;
		for(int i = 0; i < MapLoader.blocks.length; i++) {
			if(Collider.collDown(player, MapLoader.blocks[i])) {
				cb=true;
			}
		}
		return cb;
	}
	
	public boolean checkPlayerCollideUp () {
		boolean cb=false;
		for(int i = 0; i < MapLoader.blocks.length; i++) {
			if(Collider.collUp(player, MapLoader.blocks[i])) {
				cb=true;
			}
		}
		return cb;
	}
}
