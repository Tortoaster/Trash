public abstract class Creature extends Entity {
	
	private double xV, yV, speed;
	
	public Creature(double x, double y, int maxHealth, double speed, World world) {
		super(x, y, maxHealth, world);
		this.speed = speed * World.TILESIZE / getWorld().getGame().getRefreshRate();
		System.out.println(this.speed);
	}
	
	public void update() {
		yV = -speed;
		x += xV;
		y += yV;
	}
}
