import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JFrame;

public class Frame extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5151041547543472432L;
	
	static Thread arrayOfThreads [][] = new Thread [Main.height][Main.width];
	static MyRectangle arrayOfRectangles [][] = new MyRectangle [Main.height][Main.width];
	
	static Color primaryColor;
	MyRectangle newObject;
	Thread newThread;
	Waiter newWaiter = new Waiter();
	
	static boolean ready = false;
	
	public Frame () {
		
		super ("Plansza");
		
		setPreferredSize(new Dimension(1500, 1000));
		setResizable(true);
		
		int i=0, j=0;
		while (i<Main.height) {
			while (j<Main.width) {
				
				primaryColor = new Color((int)(Math.random() * 256), (int)(Math.random() * 256), (int)(Math.random() * 256));
				
				newObject = new MyRectangle(newWaiter);
				add(newObject);
				arrayOfRectangles[i][j]=newObject;
				
				newThread = new Thread (new MyThread(i, j, arrayOfRectangles[i][j], newWaiter));
				newThread.start();
				arrayOfThreads[i][j]=newThread;
				
				j++;
			}
			
			j=0;
			i++;
		}
		
		ready = true;
		
		setLayout(new GridLayout(Main.height, Main.width, 1, 1));
		pack();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	
}
