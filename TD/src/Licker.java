import java.awt.Color;
import java.awt.Graphics;

public class Licker extends Creature {
	
	public static final double SPEED = 0.1;
	
	public static final int HEALTH = 100;
	
	public Licker(double x, double y, World world) {
		super(x, y, HEALTH, SPEED, world);
	}
	
	public void draw(Graphics g) {
		g.setColor(Color.RED);
		g.fillOval((int) (x * World.TILESIZE), (int) (y * World.TILESIZE), World.TILESIZE, World.TILESIZE);
	}
}
       