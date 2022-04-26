import java.awt.Color;
import java.awt.Graphics;

public class Altar extends Tile {
	
	private int maxPower = 1000;
	private int power = maxPower;
	
	public Altar() {
		block();
	}
	
	public void update() {
		power = Math.min(maxPower, power + 1);
	}
	
	public void onTouch(Player p) {
		p.heal(power / 2);
		power = 0;
	}
	
	public void draw(Graphics g, int x, int y) {
		g.setColor(new Color(40, 50, 70));
		g.fillRect(x * Tile.SIZE, y * Tile.SIZE, Tile.SIZE, Tile.SIZE);
		g.setColor(new Color(60, 70, 90));
		g.fillRect(x * Tile.SIZE, y * Tile.SIZE + Tile.SIZE / 8, Tile.SIZE, Tile.SIZE / 4 + Tile.SIZE / 8);
		g.setColor(new Color(10, 10, 10));
		g.fillRect(x * Tile.SIZE, y * Tile.SIZE + Tile.SIZE / 4, Tile.SIZE, Tile.SIZE / 8);
		g.setColor(new Color(120, 170, 240, (int) ((double) power / maxPower * 250)));
		g.fillRect(x * Tile.SIZE, y * Tile.SIZE + Tile.SIZE / 4, Tile.SIZE, Tile.SIZE / 8);
		g.setColor(new Color(60, 70, 90));
		g.fillRect(x * Tile.SIZE + Tile.SIZE / 4 + Tile.SIZE / 8, y * Tile.SIZE, Tile.SIZE / 4, Tile.SIZE - Tile.SIZE / 4);
		g.fillRect(x * Tile.SIZE, y * Tile.SIZE + Tile.SIZE - Tile.SIZE / 4, Tile.SIZE, Tile.SIZE / 4);
		g.setColor(new Color(40, 50, 70));
		g.fillRect(x * Tile.SIZE + Tile.SIZE / 8, y * Tile.SIZE + Tile.SIZE - Tile.SIZE / 4, Tile.SIZE - Tile.SIZE / 4, Tile.SIZE / 8);
	}
}
