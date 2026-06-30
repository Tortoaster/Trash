package net.melting;

import java.awt.Graphics;
import java.util.Random;

public class World {
	
	public static final int SCALE = 50;
	
	private int width, height, rooms, floors;
	
	private Random random = new Random();
	
	private Tile[][] map;
	
	private Adventurer player;
	
	public World(int width, int height, int rooms, int floors) {
		this.width = width;
		this.height = height;
		this.rooms = rooms;
		this.floors = floors;
		map = new Tile[width][height];
		generate();
	}
	
	public void update() {
		player.update();
	}
	
	public void draw(Graphics g) {
		g.translate(Window.WIDTH / 2 - (int) (player.getX() * SCALE) - SCALE / 2, Window.HEIGHT / 2 - (int) (player.getY() * SCALE) - SCALE / 2);
		for(int y = 0; y < height; y++) for(int x = 0; x < width; x++) {
			g.drawImage(Visual.getTile(2, 256), x * SCALE, y * SCALE, SCALE, SCALE, null);
			if(map[x][y].getId() >= 0) g.drawImage(Visual.getTile(map[x][y].getId(), getValue(x, y)), x * SCALE, y * SCALE, SCALE, SCALE, null);
		}
		player.draw(g);
	}
	
	public void generate() {
		fill(0, 0, width, height, Tile.TUT);
		
		int start = -1, end = 0;
		
		for(int f = 0; f < floors; f++) {
			
			int[] size = new int[rooms];
			int total = 0;
			
			for(int i = 0; i < rooms; i++) {
				size[i] = random.nextInt(6) + 6;
				total += size[i];
			}
			
			int offset = random.nextInt(width - total) + 1;
			
			if(start != -1) {
				if(offset + total < start + 2) {
					offset = start - total + random.nextInt(3) + 2;
				} else if (offset > end - random.nextInt(3) - 2) {
					offset = end - 2;
				}
			}
			
			start = offset;
			end = offset + total - 2;
			int current = 0;
			
			for(int i = 0; i < rooms; i++) {
				fill(offset + current, f * 6 + 2, size[i] - 1, 5, Tile.AIR);
				if(random.nextInt(rooms - i) == 0 && player == null) {
					player = new Adventurer(offset + current + size[i] / 2, f * 6 + 6);
				}
				current += size[i];
			}
			fill(start, f * 6 + 6, end - start, 1, Tile.AIR);
		}
	}
	
	public void fill(int x, int y, int width, int height, Tile tile) {
		for(int c = y; c < y + height; c++) for(int r = x; r < x + width; r++) {
			map[r][c] = tile;
		}
	}
	
	public int getValue(int x, int y) {
		int index = 0;
		int increase = 1;
		for(int c = y - 1; c <= y + 1; c++) for(int r = x - 1; r <= x + 1; r++) {
			if(!(r == x && c == y)) {
				if(r < 0 || r >= width || c < 0 || c >= height || map[r][c].getId() == map[x][y].getId()) {
					index += increase;
				}
				increase *= 2;
			}
		}
		return index;
	}
}
