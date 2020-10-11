import java.util.Random;

/**
 * @author Ania PC
 *	
 */
public class RandomGenerator {
	
	static Random rand = new Random();
	
	/**
	 * 
	 * @return random integral between 0 and 255
	 */
	public static int randomColorNumber () {
		return (rand.nextInt(256));
	}
	/**
	 * @return random double (between 0.0 and 1.0)
	 */
	public static double randomDoubleNumber () {
		return rand.nextDouble();
	}
}
