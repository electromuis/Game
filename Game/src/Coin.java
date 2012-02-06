import java.awt.*;

public class Coin {
	public int x,y;
	public Rectangle Coin;
	public boolean is = true;
	
	public Coin (int mx, int my) {
		x=mx+12;
		y=my+12;
		Coin = new Rectangle(x, y, 25, 25);
	}
	
	public void draw (Graphics g) {
		if(is){
			g.setColor(Color.red);
			g.fillOval(x- GameThread.xs,y- GameThread.ys, Coin.width, Coin.height);
		}
	}
	
	public boolean checkPlayerCollide () {
		boolean cb=false;
		if (GameThread.Player != null) {
			if(Collider.colll(Coin, GameThread.Player.player)) {
				cb=true;
			}
		}
		return cb;
	}
	
	public void Score () {
		if(checkPlayerCollide() && is) {
			GameThread.Score +=10;
			is=false;
		}
	}

}