import java.awt.Color;
import java.awt.Graphics;

public class Wall extends Tile {
	
	public Wall() {
		block();
	}
	
	public void draw(Graphics g, int x, int y) {
		g.setColor(new Color(100, 110, 100));
		g.fillRect(x * Tile.SIZE, y * Tile.SIZE - Tile.SIZE / 2, Tile.SIZE, Tile.SIZE);
		g.setColor(new Color(170, 180, 170));
		g.fillRect(x * Tile.SIZE + Tile.SIZE / 4, y * Tile.SIZE - Tile.SIZE / 2, Tile.SIZE - Tile.SIZE / 4, Tile.SIZE / 2 - Tile.SIZE / 8);
		g.fillRect(x * Tile.SIZE, y * Tile.SIZE - Tile.SIZE / 2, Tile.SIZE / 8, Tile.SIZE / 2 - Tile.SIZE / 8);
		g.fillRect(x * Tile.SIZE, y * Tile.SIZE, Tile.SIZE - Tile.SIZE / 4, Tile.SIZE / 2 - Tile.SIZE / 8);
		g.fillRect(x * Tile.SIZE + Tile.SIZE - Tile.SIZE / 8, y * Tile.SIZE, Tile.SIZE / 8, Tile.SIZE / 2 - Tile.SIZE / 8);
		g.setColor(new Color(70, 80, 70));
		g.fillRect(x * Tile.SIZE, y * Tile.SIZE + Tile.SIZE / 2, Tile.SIZE, Tile.SIZE / 2);
		g.setColor(new Color(100, 110, 100));
		g.fillRect(x * Tile.SIZE + Tile.SIZE / 8, y * Tile.SIZE + Tile.SIZE / 2 + Tile.SIZE / 8, Tile.SIZE, Tile.SIZE / 2 - Tile.SIZE / 8);
	}
}
