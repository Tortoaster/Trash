import java.awt.Color;
import java.awt.Graphics;

public class Door extends Tile {
	
	private boolean open;
	
	public void update() {
		open = false;
	}
	
	public void onTouch(Player p) {
		open = true;
	}
	
	public void draw(Graphics g, int x, int y) {
		g.setColor(new Color(160, 120, 80));
		g.fillRect(x * Tile.SIZE, y * Tile.SIZE, Tile.SIZE, Tile.SIZE);
		g.setColor(new Color(170, 130, 90));
		g.fillRect(x * Tile.SIZE, y * Tile.SIZE, Tile.SIZE / 2 - Tile.SIZE / 8, Tile.SIZE - Tile.SIZE / 4);
		g.fillRect(x * Tile.SIZE, y * Tile.SIZE + Tile.SIZE - Tile.SIZE / 8, Tile.SIZE / 2 - Tile.SIZE / 8, Tile.SIZE / 8);
		g.fillRect(x * Tile.SIZE + Tile.SIZE / 2, y * Tile.SIZE + Tile.SIZE / 4, Tile.SIZE / 2 - Tile.SIZE / 8, Tile.SIZE - Tile.SIZE / 4);
		g.fillRect(x * Tile.SIZE + Tile.SIZE / 2, y * Tile.SIZE, Tile.SIZE / 2 - Tile.SIZE / 8, Tile.SIZE / 8);
		if (!open) {
			g.setColor(new Color(80, 60, 50));
			g.fillRect(x * Tile.SIZE, y * Tile.SIZE, Tile.SIZE, Tile.SIZE / 2 + Tile.SIZE / 4 - Tile.SIZE / 8);
			g.setColor(new Color(100, 80, 70));
			g.fillRect(x * Tile.SIZE, y * Tile.SIZE - Tile.SIZE / 8, Tile.SIZE, Tile.SIZE / 4);
			g.setColor(new Color(150, 150, 60));
			g.fillRect(x * Tile.SIZE + Tile.SIZE / 2 + Tile.SIZE / 8, y * Tile.SIZE + Tile.SIZE / 4, Tile.SIZE / 4, Tile.SIZE / 8);
		}
	}
}
