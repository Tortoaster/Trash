import java.awt.Graphics;

public class Bow {
	
	private int power;
	
	private Arrow[] arrow = new Arrow[10000];
	
	public Bow(int power) {
		this.power = power;
		for (int i = 0; i < arrow.length; i++) arrow[i] = new Arrow(0, 0, 0, 0, 0, 0, 0, 0, 0, false, arrow);
	}
	
	public void update() {
		for (int i = 0; i < arrow.length; i++) arrow[i].update();
	}
	
	public void fire(int x, int y) {
		for (int i = 0; i < arrow.length; i++) if (!arrow[i].isVisible()) {
			arrow[i] = new Arrow(GSM.WIDTH / 2, GSM.HEIGHT / 2, x, y, power, 50, 1, 5, 0.8, true, arrow);
			break;
		}
	}
	
	public void draw(Graphics g) {
		for (int i = 0; i < arrow.length; i++) arrow[i].draw(g);
	}
}
