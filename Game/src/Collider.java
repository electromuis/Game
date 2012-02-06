import java.awt.*;


public class Collider {
	
	public Collider () {
		
	}

	public static Point[] getPoints (Rectangle rect) {
		Point pt[] = new Point[(rect.height*rect.width)];
		int c = 0;
		
		for(int y = 0; y < (rect.height); y++) {
			for(int x = 0; x < (rect.width); x++) {
				pt[c] = new Point(x, y);
				c +=1;
			}
		}
		return pt;
	}
	
	public static boolean collides (Rectangle rect1, Rectangle rect2) {
		boolean coll = false;
		Point pt[] = getPoints(rect1);
		
		for(int i = 0; i < (pt.length); i++) {
			if (rect2.contains(pt[i])) {
				coll = true;
			}
		}
		
		return coll;
	}
	
	public static boolean colll (Rectangle rect1, Rectangle rect2) {
		boolean col = false;
		Rectangle rect = new Rectangle(rect1.x-1, rect1.y-1, rect1.width+2, rect1.height+2);
		
		col = rect2.intersects(rect);
		
		return col;
	}
	
	public static boolean collSides (Rectangle rect1, Rectangle rect2) {
		boolean col = false;
		Rectangle rect = new Rectangle(rect1.x-1, rect1.y, rect1.width+2, rect1.height);
		
		col = rect2.intersects(rect);
		
		return col;
	}
	
	public static boolean collSidesLeft (Rectangle rect1, Rectangle rect2) {
		boolean col = false;
		Rectangle rect = new Rectangle(rect1.x-1, rect1.y, rect1.width+1, rect1.height);
		
		col = rect2.intersects(rect);
		
		return col;
	}
	
	public static boolean collSidesRight (Rectangle rect1, Rectangle rect2) {
		boolean col = false;
		Rectangle rect = new Rectangle(rect1.x, rect1.y, rect1.width+1, rect1.height);
		
		col = rect2.intersects(rect);
		
		return col;
	}
	
	public static boolean collDown (Rectangle rect1, Rectangle rect2) {
		boolean col = false;
		Rectangle rect = new Rectangle(rect1.x, rect1.y, rect1.width, rect1.height+1);
		
		col = rect2.intersects(rect);
		
		return col;
	}
	
	public static boolean collUp (Rectangle rect1, Rectangle rect2) {
		boolean col = false;
		Rectangle rect = new Rectangle(rect1.x, rect1.y-1, rect1.width, rect1.height+1);
		
		col = rect2.intersects(rect);
		
		return col;
	}
}
