import javax.swing.*;
import java.awt.*;

@SuppressWarnings("serial")
public class Frame extends JFrame{
	public GameThread p;
	
	public Frame() {
		p=new GameThread(this);
		setLayout(new GridLayout(1,1,0,0));
		add(p);
		
	}
	    
}