import java.awt.Color;

public class MyThread implements Runnable { //aka Phil
	
	int vertical, horizontal;
	int NX, NY, SX, SY, EX, EY, WX, WY;
	double probability;
	int averageRed, averageBlue, averageGreen;
	MyRectangle rectangle;
	Waiter waiter;
	
	/**
	 * Creates object of type MyThread, one for every rectangle shown
	 * on the screen. Then, finds neighbors of said rectangle.
	 * @param i row
	 * @param j column
	 * @param object corresponding rectangle
	 * @param waiter waiter object, passed from main class
	 */
	public MyThread(int i, int j, MyRectangle object, Waiter waiter) {
		
		this.rectangle=object;
		this.waiter = waiter;
		
		this.vertical = i;
		this.horizontal = j;
		
		findNeighbours ();
	}

	/**
	 * Running a thread starts with waiting for the last one to start.
	 * Then, based on generated random number it changes it color - 
	 * randomly, or based on neighbors' colors.
	 * Thread waits until notified, then "locks" relevant rectangles
	 * and changes color. Lifts "locks" and then, waits for a certain,
	 * random amount of time.
	 */
	@Override
	public void run() {
		while (Frame.ready==false) {
			try {
				Thread.sleep(1);
			}
			catch (InterruptedException e) {
			}
		}
		
		while (true) { 
			
			this.probability=Math.random();
						
			if (Frame.arrayOfRectangles[vertical][horizontal].clicked == MyEnum.ACTIVE) {
				//RANDOM COLOR
				if (this.probability<=Main.probability) {
					
					Frame.arrayOfRectangles[vertical][horizontal].randomColor();
					
				}
				
				//COLOR BASED ON OTHERS COLORS
				else {
					int mediana = 0;
					averageRed = 0;
					averageBlue = 0;
					averageGreen = 0;
					
					//checks locks of surrounding rectangles
					if (Frame.arrayOfRectangles[NY][NX].clicked == MyEnum.ACTIVE) {
						mediana++;
						Color north = Frame.arrayOfRectangles[NY][NX].getColor();
						averageRed+=north.getRed();
						averageBlue+=north.getBlue();
						averageGreen+=north.getGreen();
					}
					if (Frame.arrayOfRectangles[SY][SX].clicked == MyEnum.ACTIVE) {
						mediana++;
						Color south = Frame.arrayOfRectangles[SY][SX].getColor();
						averageRed+=south.getRed();
						averageBlue+=south.getBlue();
						averageGreen+=south.getGreen();
					}						
					if (Frame.arrayOfRectangles[WY][WX].clicked == MyEnum.ACTIVE) {
						mediana++;
						Color west = Frame.arrayOfRectangles[WY][WX].getColor();
						averageRed+=west.getRed();
						averageBlue+=west.getBlue();
						averageGreen+=west.getGreen();
					}						
					if (Frame.arrayOfRectangles[EY][EX].clicked == MyEnum.ACTIVE) {
						mediana++;
						Color east = Frame.arrayOfRectangles[EY][EX].getColor();
						averageRed+=east.getRed();
						averageBlue+=east.getBlue();
						averageGreen+=east.getGreen();
					}					
					if (mediana!=0) {
						averageRed/=mediana;
						averageBlue/=mediana;
						averageGreen/=mediana;
						
						Frame.arrayOfRectangles[vertical][horizontal].setColor(new Color (averageRed, averageGreen, averageBlue));
					}
				}
			}
			try {
				Thread.sleep((int)((RandomGenerator.randomDoubleNumber() + 0.5)*Main.speed));
			}
			catch (InterruptedException e) {
                e.printStackTrace();
			}
		}		
	}
	/**
	 * Finds coordinates of rectangle's neighbors.
	 */
	private void findNeighbours () {

		//NORTH
		this.NX=this.horizontal;
		if (this.vertical==0) {
			this.NY=Main.height-1;
		}
		else {
			this.NY=this.vertical-1;
		}
		
		//SOUTH
		this.SX=this.horizontal;
		if (this.vertical+1==Main.height) {
			this.SY=0;
		}
		else {
			SY=this.vertical+1;
		}
		
		//EAST
		if (this.horizontal+1==Main.width) {
			this.EX=0;
		}
		else {
			this.EX=this.horizontal+1;
		}
		this.EY=this.vertical;

		//WEST
		if (this.horizontal==0) {
			this.WX=Main.width-1;
		}
		else {
			this.WX=this.horizontal-1;
		}
		WY=this.vertical;
	}

}
