import java.awt.Graphics;

public class Game extends State {
	
	private World world;
	
	public Game(Window window) {
		super(window);
		world = new World("res/Spiral.txt", this);
	}
	
	public void update() {
		world.update();
	}
	
	public void draw(Graphics g) {
		g.setColor(Colors.BACKGROUND);
		g.fillRect(0, 0, getScreenWidth(), getScreenHeight());
		g.translate((getScreenWidth() - world.getWidth() * World.TILESIZE) / 2, (getScreenHeight() - world.getHeight() * World.TILESIZE) / 2);
		world.draw(g);
		g.translate(-(getScreenWidth() - world.getWidth() * World.TILESIZE) / 2, (getScreenHeight() - world.getHeight() * World.TILESIZE) / 2);
	}
}
