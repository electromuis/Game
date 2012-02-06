import java.awt.Rectangle;
import java.io.*;


public class MapLoader {
	public static String Map = "";
	public static Rectangle blocks[];
	public static Coin Coins[];
	public static boolean p=false;
	public static int px, py;
	
	public static String FileDir() {
		return System.getProperty("user.dir");
	}
	
	@SuppressWarnings("deprecation")
	public static String ReadFile(String readFile) {
		 File file = new File(readFile);
		 String text = "";
		    FileInputStream fis = null;
		    BufferedInputStream bis = null;
		    DataInputStream dis = null;
		    try {
		      fis = new FileInputStream(file);

		      // Here BufferedInputStream is added for fast reading.
		      bis = new BufferedInputStream(fis);
		      dis = new DataInputStream(bis);

		      // dis.available() returns 0 if the file does not have more lines.
		      while (dis.available() != 0) {
		      // this statement reads the line from the file and print it to
		        // the console.
		        text += dis.readLine()+"\n";
		      }
		      // dispose all the resources after using them.
		      fis.close();
		      bis.close();
		      dis.close();
		    } catch (FileNotFoundException e) {
		      e.printStackTrace();
		    } catch (IOException e) {
		      e.printStackTrace();
		    }
		    return text;
		  }
	
	public static void load() {
		
		
		Map += "00000000\n";
		Map += "08888880\n";
		Map += "08888880\n";
		Map += "08888880\n";
		Map += "00000000\n";
		
		System.out.println("Catching map file at "+FileDir()+"/Map.txt");
		
		if (new File(FileDir()+"/Map.txt").exists()) {
			Map = ReadFile(FileDir()+"/Map.txt");
			
			String parts[] = Map.split("\n");
			int blockCount = 0;
			int coinCount=0;
	
			for(int y = 0; y < parts.length; y++) {
				for(int x = 0; x < parts[y].length(); x++) {
					if (parts[y].charAt(x)=='0'){blockCount ++;}
					if (parts[y].charAt(x)=='c'){coinCount ++;}
				}
			}
			
			blocks = new Rectangle[blockCount];
			Coins = new Coin[coinCount];
			blockCount = 0;
			coinCount = 0;
			
			for(int y = 0; y < parts.length; y++) {
				for(int x = 0; x < parts[y].length(); x++) {
					if (parts[y].charAt(x)=='0'){
						blocks[blockCount] = new Rectangle(x*50, y*50, 50, 50);
						blockCount ++;
					}
					if (parts[y].charAt(x)=='p'){
						p=true;
						
						px=x*50;
						py=y*50;
						
						int midX=MainClass.w/2;
						int midY=MainClass.h/2;
						GameThread.ys -= midY-y*50;
						GameThread.xs -= midX-x*50;
						
					}
					if (parts[y].charAt(x)=='c'){
						Coins[coinCount] = new Coin(x*50, y*50);
						coinCount ++;
					}
				}
			}
		} else {
			System.out.println("Map file does not exist");
			blocks = null;
		}
	}
}