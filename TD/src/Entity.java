import java.awt.Graphics;

public abstract class Entity {
	
	protected double x, y;
	
	private int health, maxHealth;
	
	private World world;
	
	public Entity(double x, double y, int maxHealth, World world) {
		this.x = x;
		this.y = y;
		this.maxHealth = maxHealth;
		health = maxHealth;
		this.world = world;
	}
	
	public abstract void update();
	
	public abstract void draw(Graphics g);
	
	public World getWorld() {
		return world;
	}
	
	public double getX() {
		return x + 0.5;
	}
	
	public double getY() {
		return y + 1;
	}
}
