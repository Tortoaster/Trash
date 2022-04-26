import java.awt.Color;
import java.awt.Graphics;

public class Tree extends Tile {
	
	public Tree() {
		block();
	}
	
	public void draw(Graphics g, int x, int y) {
		g.setColor(new Color(20, 160, 90));
		g.fillRect(x * Tile.SIZE, y * Tile.SIZE, Tile.SIZE, Tile.SIZE);
		g.setColor(new Color(160, 100, 70));
		g.fillRect(x * Tile.SIZE + Tile.SIZE / 2 - Tile.SIZE / 8, y * Tile.SIZE + Tile.SIZE / 8, Tile.SIZE / 4, Tile.SIZE - Tile.SIZE / 4);
		g.setColor(new Color(40, 100, 80));
		g.fillRect(x * Tile.SIZE + Tile.SIZE / 8, y * Tile.SIZE + Tile.SIZE / 8, Tile.SIZE - Tile.SIZE / 4, Tile.SIZE / 2);
		g.fillRect(x * Tile.SIZE + Tile.SIZE / 4, y * Tile.SIZE, Tile.SIZE - Tile.SIZE / 2, Tile.SIZE / 2);
	}
}
