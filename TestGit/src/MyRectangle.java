import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;

public class MyRectangle extends JPanel implements MouseMotionListener, MouseListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1856256381119545815L;
	boolean lock = false;
	MyEnum clicked = MyEnum.ACTIVE;
	Waiter waiter;
	
	/**
	 * Class of my own rectangle - JPanel with MouseListener .
	 * @param y row
	 * @param x column
	 */
	public MyRectangle (Waiter waiter) {
		this.waiter = waiter;
		setBackground(Frame.primaryColor);
		addMouseListener(this);
		addMouseMotionListener(this);
	}
	
	/**
	 * Changes color of panel's background to new random color.
	 */
	public synchronized void randomColor () {
		this.setBackground(new Color(RandomGenerator.randomColorNumber(), RandomGenerator.randomColorNumber(), RandomGenerator.randomColorNumber()));
	}
	
	/**
	 * Changes color of panel's background to earlier calculated one.
	 * @param avarage new average color, based on neighbors 
	 */
	public synchronized void setColor (Color avarage) {
		this.setBackground(avarage);
	}
	
	public synchronized Color getColor () {
		return getBackground();
	}
	
	@Override
	public void paint(Graphics g) {
	    super.paint(g);
	}
	
	/**
	 * When clicked, changes status of the rectangle from Active to Paused and vice-versa.
	 * Apply/lift "lock" while doing so.
	 */
	@Override
	public synchronized void mouseClicked(MouseEvent e) {
		if (this.clicked == MyEnum.ACTIVE) {
			this.clicked = MyEnum.PAUSED;
		}
		
		else if (this.clicked == MyEnum.PAUSED) {
			this.clicked = MyEnum.ACTIVE;
		}
		
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void mouseDragged(MouseEvent e) {
	}

	@Override
	public void mouseMoved(MouseEvent e) {
	}

}
