package com.whale.nope.world;

import java.awt.Graphics;
import java.util.Random;
import com.whale.nope.main.Game;
import com.whale.nope.main.Screen;

public class World {
	
	public static int WIDTH, HEIGHT;
	
	private Game game;
	
	private Random random = new Random();
	
	private Tile[][] map;
	
	public World(int width, int height, Game game) {
		WIDTH = width;
		HEIGHT = height;
		this.game = game;
		map = new Tile[width][height];
		generate(height / 2, height / 2 + 80);
	}
	
	public void generate(int min, int max) {
		int[] height = new int[WIDTH];
		for (int i = 0; i < height.length; i++) {
			height[i] = random.nextInt(max - min) + min;
		}
		int[] copy = new int[height.length];
		for (int x = 0; x < 15; x++) {
			for (int i = 0; i < copy.length; i++) {
				int value = 0;
				for (int n = i - 1; n <= i + 1; n++) {
					if (n >= 0 && n < copy.length) {
						value += height[n];
					} else {
						if (n < 0) {
							n += copy.length;
							value += height[n];
							n -= copy.length;
						} else if (n >= copy.length) {
							n -= copy.length;
							value += height[n];
							n += copy.length;
						}
					}
				}
				copy[i] = value / 3;
			}
			for (int i = 0; i < height.length; i++) {
				height[i] = copy[i];
			}
		}
		for (int y = 0; y < HEIGHT; y++) for (int x = 0; x < WIDTH; x++) {
			if (y == HEIGHT -1) {
				map[x][y] = new Bedrock(this);
			} else if (y == height[x]) {
				map[x][y] = new Grass(this);
			} else if (y > height[x] + 8) {
				map[x][y] = new Rock(this);
			} else if (y > height[x]) {
				map[x][y] = new Earth(this);
			} else {
				map[x][y] = new Air(this);
			}
		}
		int i = 3;
		while (i < WIDTH - 3) {
			switch (random.nextInt(10)) {
			case 0: case 1: case 2: case 3: case 4: case 5: int length = random.nextInt(5) + 4;
			for (int n = 0; n < length; n++) {
				if (height[i] - n - 1 >= 0) {
					map[i][height[i] - n - 1] = new Log(this);
				}
			}
			int size = Math.max(3, random.nextInt(3) * 2);
			for (int y = Math.max(0, height[i] - size + 1 - length); y < Math.min(HEIGHT, height[i] + size - length); y++) {
				for (int x = Math.max(0, i - size + 1); x < Math.min(WIDTH, i + size); x++) {
					if (Math.sqrt((i - x) * (i - x) + (height[i] - length - y) * (height[i] - length - y)) < size - 0.2) {
						if (map[x][y] instanceof Air || map[x][y] instanceof Log) {
							map[x][y] = new Leaves(this);
						}
					}
				}
			}
			break;
			case 6: map[i][height[i] - 1] = new Bubbler(this);
			break;
			case 7: map[i][height[i] - 1] = new Fountain(this);
			break;
			case 8: map[i][height[i] - 1] = new Sparkler(this);
			break;
			case 9: map[i][height[i] - 1] = new Firepit(i, height[i] - 1, this);
			}
			i += random.nextInt(6) + 5;
		}
		game.getPlayer().setX(WIDTH * Tile.SIZE / 2);
		game.getPlayer().setY((height[game.getPlayer().getX() / Tile.SIZE] - 1) * Tile.SIZE);
	}
	
	public void update() {
		for (int y = game.getYOffset() / Tile.SIZE; y <= (game.getYOffset() + Screen.HEIGHT) / Tile.SIZE; y++) {
			for (int x = game.getXOffset() / Tile.SIZE; x <= (game.getXOffset() + Screen.WIDTH) / Tile.SIZE; x++) {
				if (y >= 0 && y < HEIGHT) {
					if (x < 0) {
						map[x + WIDTH][y].update(x, y);
					} else if (x >= WIDTH) {
						map[x - WIDTH][y].update(x, y);
					} else {
						map[x][y].update(x, y);
					}
				}
			}
		}
	}
	
	public void draw(Graphics g) {
		for (int y = game.getYOffset() / Tile.SIZE - 1; y <= (game.getYOffset() + Screen.HEIGHT) / Tile.SIZE + 1; y++) {
			for (int x = game.getXOffset() / Tile.SIZE - 1; x <= (game.getXOffset() + Screen.WIDTH) / Tile.SIZE + 1; x++) {
				if (y >= 0 && y < HEIGHT) {
					if (x < 0) {
						map[x + WIDTH][y].draw(x, y, g);
					} else if (x >= WIDTH) {
						map[x - WIDTH][y].draw(x, y, g);
					} else {
						map[x][y].draw(x, y, g);
					}
				}
			}
		}
	}
	
	public void setTile(int x, int y, Tile tile) {
		map[x][y] = tile;
	}
	
	public Tile getTile(int x, int y) {
		return map[x][y];
	}
	
	public boolean isBlocked(int x, int y) {
		if (x < 0) x += WIDTH * Tile.SIZE; else if (x >= WIDTH * Tile.SIZE) x -= WIDTH * Tile.SIZE;
		if (x >= 0 && x < WIDTH * Tile.SIZE && y >= 0 && y < HEIGHT * Tile.SIZE) return map[x / Tile.SIZE][y / Tile.SIZE].isBlocked(); else return false;
	}
	
	public Game getGame() {
		return game;
	}
}
