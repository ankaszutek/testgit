import java.awt.EventQueue;
public class Main {
	public static int width, height, speed;
	public static double probability;
	public static void main(String[] args) {
		try {
			width=Integer.parseInt(args[0]);
			height=Integer.parseInt(args[1]);
			speed=Integer.parseInt(args[2]);
			probability=Double.parseDouble(args[3]);
			if (width < 1 || height < 1 || speed < 1 || probability < 0 || probability >1) {
				throw new Exception();
			}
			EventQueue.invokeLater(new Runnable() {
				@Override
				public void run() {
					new Frame();
				}
			});
		}
		catch (NumberFormatException ex) {
			System.out.println("Podano zle liczby");
		} catch (Exception e) {
			System.out.println("Please enter appropriate data.");
		}
	}
}
