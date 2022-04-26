import java.util.List;

public abstract class Tower extends Entity {
	
	private int range, delay;
	
	private TargetStyle style;
	
	private Entity target;
	
	public Tower(int x, int y, int maxHealth, int range, int delay, TargetStyle style, World world) {
		super(x, y, maxHealth, world);
		this.range = range;
		this.delay = delay;
		this.style = style;
	}
	
	public void update() {
		target = getTarget();
		shoot();
	}
	
	public void shoot() {
		// Shoot with respect to delay with respect to RATE
	}
	
	public Entity getTarget() {
		List<Entity> entities = getWorld().getEntities();
		// Sort by distance travelled or health, looking at the style
		for(int i = 0; i < entities.size(); i++) {
			Entity e = entities.get(i);
			if(getDistance(e) <= range) {
				return e;
			}
		}
		return null;
	}
	
	// Maybe move this somewhere else?
	public double getDistance(Entity e) {
		return Math.sqrt(Math.pow(e.getX() - getX(), 2) + Math.pow(e.getY() - getY(), 2));
	}
}
