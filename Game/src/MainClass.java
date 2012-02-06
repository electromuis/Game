import java.awt.Color;

import javax.swing.*;
public class MainClass {

	public static Frame f;
	public static int w=800;
	public static int h=600;
	
	
	public static void main(String[] args){
		MapLoader.load();
		f= new Frame();
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setResizable(false);
		f.setSize(w, h);
		f.setTitle("");
		f.setLocationRelativeTo(null);
		f.setBackground(Color.BLACK);

		
	}
	
}
