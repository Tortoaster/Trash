import java.awt.Color;
import java.awt.Graphics;

public class Spikes extends Tile {
	
	public void onTouch(Player p) {
		p.damage(10);
	}
	
	public void draw(Graphics g, int x, int y) {
		g.setColor(new Color(20, 160, 90));
		g.fillRect(x * Tile.SIZE, y * Tile.SIZE, Tile.SIZE, Tile.SIZE);
		g.setColor(new Color(150, 140, 170));
		for (int c = 1; c < 4; c++) for (int r = 1; r < 4; r++) {
			g.fillRect(x * Tile.SIZE + r * Tile.SIZE / 4 - c % 2 * Tile.SIZE / 8, y * Tile.SIZE + c * Tile.SIZE / 4 - r % 2 * Tile.SIZE / 8, Tile.SIZE / 8, Tile.SIZE / 8);
		}
	}
}
