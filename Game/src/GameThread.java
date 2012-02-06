import java.awt.*;
import java.awt.event.*;
import java.util.Random;

import javax.swing.*;

@SuppressWarnings("serial")
public class GameThread extends JPanel implements Runnable {

	public Thread game;
	
	public Rectangle star[];
	
	public int kJ = KeyEvent.VK_SPACE;
	public int kL = KeyEvent.VK_A;
	public int kR = KeyEvent.VK_D;
	public int kP = KeyEvent.VK_P;
	public int kQ = KeyEvent.VK_Q;
	
	public int fps = 1000;
	
	
	public static int xs = 0;
	public static int ys = 0;
	public int cStarSize = 0;
	public static int Score = 0;	
	public static Player Player;
	
	public boolean mr = false;
	public boolean ml = false;
	public boolean run = true;
	public boolean objectsDefined = false;
	public boolean falling = false;
	public boolean jumping = false;
	public boolean ingame = true;
	
	
	public GameThread(Frame f){
		defineObjects();
		game = new Thread(this);
		game.start();
		
		
		f.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == kL){
					ml = true;
				}
				
				if(e.getKeyCode() == kR){
					mr = true;
				}
				
				if(e.getKeyCode() == kQ){
					Player.shooting = true;
				} else {
					Player.shooting = false;
				}
				
				if(e.getKeyCode() == kJ){
					if (!Player.falling){
						Player.jumping = true;
						}
				}
				
				if(e.getKeyCode() == kP){
					if (ingame) {
						ingame = false;
					} else {
						ingame = true;
					}
				}
				
			}
			
			public void keyReleased(KeyEvent e) {
				if(e.getKeyCode() == kL){
					ml = false;
				}
				
				if(e.getKeyCode() == kR){
					mr = false;
				}
				
			}
			
		});
	}
	
	public void defineObjects() {
		
		if (!MapLoader.p) {
			Player = null;
		} else {
			Player = new Player();
			Player.x = MapLoader.px;
			Player.y = MapLoader.py;
		}
		
		
		star = new Rectangle[150];
		Random r = new Random();
		
		for(int i = 0; i < star.length; i++) {
			cStarSize = r.nextInt(5);
			star[i] = new Rectangle(r.nextInt(MainClass.w)*2 - (MainClass.w/2), r.nextInt(MainClass.h)*2 - (MainClass.h/2), cStarSize, cStarSize);
		}
		
		repaint();
		objectsDefined = true;
	}
	
	public void paintComponent (Graphics g) {
		super.paintComponents(g);
		if(objectsDefined){
			
			for (int i = 0;i < star.length; i++) {
				g.setColor(Color.CYAN);
				g.fill3DRect(star[i].x-(xs/4), star[i].y-(ys/4), star[i].width, star[i].height, true);
			}
			if (Player!=null){
			Player.draw(g);
			}
			
			g.setColor(Color.darkGray);
			
			g.setColor(Color.yellow);
			for(int i = 0; i < MapLoader.blocks.length; i++) {
				g.fillRect(MapLoader.blocks[i].x-xs, MapLoader.blocks[i].y-ys, MapLoader.blocks[i].width, MapLoader.blocks[i].height);
			}
			
			for(int i = 0; i < MapLoader.Coins.length; i++) {
				MapLoader.Coins[i].draw(g);
			}
			
			g.setColor(Color.white);
			g.drawString("Your score is : "+Score, 10, 30);
			MainClass.f.setTitle("Your score is : "+Score);
			
			if(!ingame) {
				g.setColor(Color.red);
				g.drawString("Paused! Press P to play again.", 10, 20);
			}
			MainClass.f.repaint();
		}
	}
	
	
	
	public void run() {
			while (run){
				if(ingame) {
					if(Player!=null) {
					Player.move(ml, mr);
					}
					
					for(int i = 0; i < MapLoader.Coins.length; i++) {
						MapLoader.Coins[i].Score();
					}
					
					fpsSetter();
					rp();
				}
			}
		}
	
	@SuppressWarnings("static-access")
	public void fpsSetter() {
		try{
			
			game.sleep(fps/1000);
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void rp() {
		if (run) {
		repaint();
		}
	}
	
}
