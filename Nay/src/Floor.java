import java.awt.Color;
import java.awt.Graphics;

public class Floor extends Tile {
	
	public void draw(Graphics g, int x, int y) {
		g.setColor(new Color(160, 120, 80));
		g.fillRect(x * Tile.SIZE, y * Tile.SIZE, Tile.SIZE, Tile.SIZE);
		g.setColor(new Color(170, 130, 90));
		g.fillRect(x * Tile.SIZE, y * Tile.SIZE, Tile.SIZE / 2 - Tile.SIZE / 8, Tile.SIZE - Tile.SIZE / 4);
		g.fillRect(x * Tile.SIZE, y * Tile.SIZE + Tile.SIZE - Tile.SIZE / 8, Tile.SIZE / 2 - Tile.SIZE / 8, Tile.SIZE / 8);
		g.fillRect(x * Tile.SIZE + Tile.SIZE / 2, y * Tile.SIZE + Tile.SIZE / 4, Tile.SIZE / 2 - Tile.SIZE / 8, Tile.SIZE - Tile.SIZE / 4);
		g.fillRect(x * Tile.SIZE + Tile.SIZE / 2, y * Tile.SIZE, Tile.SIZE / 2 - Tile.SIZE / 8, Tile.SIZE / 8);
	}
}
