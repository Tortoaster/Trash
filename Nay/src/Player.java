import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class Player {
	
	public int x;
	public int y;
	private int maxHealth = 1000;
	private int health = maxHealth;
	
	private World world;
	
	private Random r = new Random();
	
	public Player(int x, int y, World world) {
		this.x = x;
		this.y = y;
		this.world = world;
	}
	
	public void update() {
		world.map[x][y].onTouch(this);
		if (health == 0) {
			world.map[x][y] = new Gravestone();
			respawn();
		}
		heal(1);
	}
	
	public void damage(int amount) {
		health = Math.max(0, health - amount);
	}
	
	public void heal(int amount) {
		health = Math.min(maxHealth, health + amount);
	}
	
	public void respawn() {
		for (int i = 0; i < 1000; i++) {
			int x = r.nextInt(world.map.length);
			int y = r.nextInt(world.map[0].length);
			if (!world.map[x][y].isBlocked()) {
				this.x = x;
				this.y = y;
				heal(maxHealth);
				break;
			}
			if (i == 999) {
				world.map[x][y] = new Grass();
				this.x = x;
				this.y = y;
				heal(maxHealth);
			}
		}
	}
	
	public void draw(Graphics g) {
		g.setColor(new Color(90, 50, 50));
		g.fillRect(x * Tile.SIZE + Tile.SIZE - Tile.SIZE / 8, y * Tile.SIZE + Tile.SIZE / 8, Tile.SIZE / 8, Tile.SIZE - Tile.SIZE / 8);
		g.setColor(new Color(110, 110, 120));
		g.fillRect(x * Tile.SIZE + Tile.SIZE - Tile.SIZE / 8, y * Tile.SIZE + Tile.SIZE / 8, Tile.SIZE / 8, Tile.SIZE / 8);
		g.setColor(new Color(200, 150, 50));
		g.fillRect(x * Tile.SIZE, y * Tile.SIZE + Tile.SIZE / 2, Tile.SIZE, Tile.SIZE / 8);
		g.fillRect(x * Tile.SIZE, y * Tile.SIZE + Tile.SIZE / 2 + Tile.SIZE / 8, Tile.SIZE / 8, Tile.SIZE / 8);
		g.setColor(new Color(20, 50, 100));
		g.fillRect(x * Tile.SIZE + Tile.SIZE / 8, y * Tile.SIZE + Tile.SIZE / 2 + Tile.SIZE / 8, Tile.SIZE - Tile.SIZE / 4 - Tile.SIZE / 8, Tile.SIZE / 4);
		g.fillRect(x * Tile.SIZE + Tile.SIZE / 8, y * Tile.SIZE + Tile.SIZE - Tile.SIZE / 8, Tile.SIZE / 8, Tile.SIZE / 8);
		g.fillRect(x * Tile.SIZE + Tile.SIZE / 2, y * Tile.SIZE + Tile.SIZE - Tile.SIZE / 8, Tile.SIZE / 8, Tile.SIZE / 8);
		g.fillRect(x * Tile.SIZE + Tile.SIZE / 4, y * Tile.SIZE + Tile.SIZE / 2, Tile.SIZE / 8, Tile.SIZE / 8);
		g.fillRect(x * Tile.SIZE + Tile.SIZE / 2, y * Tile.SIZE + Tile.SIZE / 2, Tile.SIZE / 8, Tile.SIZE / 8);
		g.setColor(new Color(200, 160, 120));
		g.fillRect(x * Tile.SIZE + Tile.SIZE / 8, y * Tile.SIZE, Tile.SIZE - Tile.SIZE / 4 - Tile.SIZE / 8, Tile.SIZE - Tile.SIZE / 2);
		g.fillRect(x * Tile.SIZE + Tile.SIZE - Tile.SIZE / 8, y * Tile.SIZE + Tile.SIZE / 2, Tile.SIZE / 8, Tile.SIZE / 8);
		g.fillRect(x * Tile.SIZE, y * Tile.SIZE + Tile.SIZE / 2 + Tile.SIZE / 4, Tile.SIZE / 8, Tile.SIZE / 8);
		g.setColor(new Color(200, 150, 50));
		g.fillRect(x * Tile.SIZE + Tile.SIZE / 8, y * Tile.SIZE, Tile.SIZE - Tile.SIZE / 4 - Tile.SIZE / 8, Tile.SIZE / 8);
		g.fillRect(x * Tile.SIZE, y * Tile.SIZE + Tile.SIZE / 8, Tile.SIZE - Tile.SIZE / 8, Tile.SIZE / 8);
		g.setColor(new Color(10, 20, 40));
		g.fillRect(x * Tile.SIZE + Tile.SIZE / 2 - Tile.SIZE / 8, y * Tile.SIZE + Tile.SIZE / 4, Tile.SIZE / 8, Tile.SIZE / 8);
		g.fillRect(x * Tile.SIZE + Tile.SIZE / 2 + Tile.SIZE / 8, y * Tile.SIZE + Tile.SIZE / 4, Tile.SIZE / 8, Tile.SIZE / 8);
		g.setColor(new Color(0, 0, 0));
		g.fillRect(x * Tile.SIZE, y * Tile.SIZE - Tile.SIZE / 4, Tile.SIZE, Tile.SIZE / 8);
		g.setColor(new Color(250 - (int) ((double) health / maxHealth * 8) * 250 / 8, (int) ((double) health / maxHealth * 8) * 250 / 8, 0));
		g.fillRect(x * Tile.SIZE, y * Tile.SIZE - Tile.SIZE / 4, (int) ((double) health / maxHealth * 8) * Tile.SIZE / 8, Tile.SIZE / 8);
	}
}
