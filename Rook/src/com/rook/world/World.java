package com.rook.world;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.rook.Images;
import com.rook.Window;
import com.rook.life.Creature;
import com.rook.life.Entity;
import com.rook.life.Human;
import com.rook.life.Life;
import com.rook.life.Skeleton;
import com.rook.mechanics.Light;
import com.rook.mechanics.Ray;
import com.rook.state.Game;

public class World {
	
	private double gravity, quake;
	
	private int width, height, rooms, floors, cameraX, cameraY, cameraSpeed = 10, renderX, renderY, renderWidth, renderHeight;
	
	public static final Color SHADOW = new Color(0, 0, 0, 100);
	
	private Life life = new Life(this);
	
	private Random random;
	
	// Map
	private Tile[][] map;
	private int[][] values;
	private boolean[][] light;
	private boolean[][] seen;
	
	private BufferedImage shadow;
	
	private List<Light> lights = new ArrayList<>();
	
	private Creature player;
	
	public World(double gravity, int width, int height, int rooms, int floors, int seed) {
		this.gravity = gravity;
		this.width = width;
		this.height = height;
		this.rooms = rooms;
		this.floors = floors;
		random = new Random(seed);
		map = new Tile[width][height];
		values = new int[width][height];
		light = new boolean[width][height];
		seen = new boolean[width][height];
		shadow = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);///////////////
		generate();
		calculate();
	}
	
	public void generate() {
		fill(0, 0, width, height, Tile.BRICKS);
		int start = -1, end = 0;
		for(int f = 0; f < floors; f++) {
			int[] size = new int[rooms];
			int total = 0;
			for(int i = 0; i < rooms; i++) {
				size[i] = random.nextInt(3) + 9;
				total += size[i];
			}
			int offset = random.nextInt(Math.max(1, width - total)) + 1;
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
				fill(offset + current, f * 6 + 1, size[i] - 1, 5, Tile.AIR);
				fill(offset + current, f * 6 + 1, 1, 5, Tile.PILLAR);
				fill(offset + current + size[i] - 2, f * 6 + 1, 1, 5, Tile.PILLAR);
				fill(offset + current + size[i] / 2 - 1, f * 6 + 2, 2 - ((size[i] - 1) % 2), 2, Tile.WINDOW);
				fill(offset + current + 2, f * 6 + 5, size[i] - 5, 1, Tile.FANCY_TABLE);
				fill(offset + current + 2, f * 6 + 1, 1, 3, Tile.BANNER);
				fill(offset + current + size[i] - 4, f * 6 + 1, 1, 3, Tile.BANNER);
				fill(offset + current + 1, f * 6 + 3, 1, 3, Tile.FANCY_CHAIR_RIGHT);
				fill(offset + current + size[i] - 3, f * 6 + 3, 1, 3, Tile.FANCY_CHAIR_LEFT);
				map[offset + current + size[i] / 2 - 1][f * 6 + 4] = Tile.TURKEY;
				map[offset + current + size[i] / 2 - 2][f * 6 + 4] = Tile.CANDLE;
				map[offset + current + size[i] / 2][f * 6 + 4] = Tile.CANDLE;
				map[offset + current + 2][f * 6 + 4] = Tile.PLATE;
				map[offset + current + size[i] - 4][f * 6 + 4] = Tile.PLATE;
				if(i < rooms - 1) map[offset + current + size[i] - 1][f * 6 + 5] = Tile.LOCKED_DOOR;
				if(player == null) {
					player = new Human((int) ((offset + current + ((double) size[i] - 1) / 2 - 0.5) * Tile.SIZE), (int) ((f * 6 + 3) * Tile.SIZE), Human.WARRIOR, this);
					player.setIndex(1);
					life.addCreature(player);
					Light light = new Light(0, 0, 6, this);
					light.follow(player);
					lights.add(light);
				} else {
					Creature c = new Skeleton((int) ((offset + current + ((double) size[i] - 1) / 2 - 0.5) * Tile.SIZE), (int) ((f * 6 + 3) * Tile.SIZE), this);
					c.setTeam(random.nextInt());
					life.addCreature(c);
				}
				current += size[i];
			}
		}
	}
	
	public void illuminate() {
		for(int y = 0; y < light[0].length; y++) for(int x = 0; x < light.length; x++) {
			light[x][y] = false;
		}
		for(int i = 0; i < lights.size(); i++) {
			Light l = lights.get(i);
			List<Point> points = l.cast(map);
			for(int n = 0; n < points.size(); n++) {
				Point p = points.get(n);
				light[p.x][p.y] = true;
				seen[p.x][p.y] = true; 
			}
		}
	}
	
	public double getMaxHorizontalMovement(Entity e, double dX) {
		double maxDeltaX;
		double x = e.getX();
		double y = e.getY();
		int width = e.getWidth();
		int height = e.getHeight();
		int startRow = Math.max(0, (int) (y / Tile.SIZE));
		int endRow = Math.min(this.height - 1, (int) ((y + height - 1) / Tile.SIZE));
		if(dX > 0) {
			int startCol = Math.max(0, (int) Math.ceil((x + width) / Tile.SIZE));
			int endCol = Math.min(this.width - 1, (int) (x + width + dX) / Tile.SIZE);
			for(int c = startCol; c <= endCol; c++) for(int r = startRow; r <= endRow; r++) {
				if(map[c][r].isSolid(Tile.SIDE_LEFT, e)) {
					maxDeltaX = c * Tile.SIZE - x - width;
					return Math.min(dX, maxDeltaX);
				}
			}
		} else {
			int startCol = Math.max(0, (int) (x / Tile.SIZE) - 1);
			int endCol = Math.min(this.width - 1, (int) ((x + dX) / Tile.SIZE) - 1);
			for(int c = startCol; c >= endCol; c--) for(int r = startRow; r <= endRow; r++) {
				if(map[c][r].isSolid(Tile.SIDE_RIGHT, e)) {
					maxDeltaX = (c + 1) * Tile.SIZE - x;
					return Math.max(dX, maxDeltaX);
				}
			}
		}
		return dX;
	}
	
	public double getMaxVerticalMovement(Entity e, double dY) {
		double maxDeltaY;
		double x = e.getX();
		double y = e.getY();
		int width = e.getWidth();
		int height = e.getHeight();
		int startCol = Math.max(0, (int) (x / Tile.SIZE));
		int endCol = Math.min(this.width - 1, (int) ((x + width - 1) / Tile.SIZE));
		if(dY > 0) {
			int startRow = Math.max(0, (int) Math.ceil((y + height) / Tile.SIZE));
			int endRow = Math.min(this.height - 1, (int) (y + height + dY) / Tile.SIZE);
			for(int r = startRow; r <= endRow; r++) for(int c = startCol; c <= endCol; c++) {
				if(map[c][r].isSolid(Tile.SIDE_TOP, e)) {
					maxDeltaY = r * Tile.SIZE - y - height;
					return Math.min(dY, maxDeltaY);
				}
			}
		} else {
			int startRow = Math.max(0, (int) (y / Tile.SIZE) - 1);
			int endRow = Math.min(this.height - 1, (int) ((y + dY) / Tile.SIZE) - 1);
			for(int r = startRow; r >= endRow; r--) for(int c = startCol; c <= endCol; c++) {
				if(map[c][r].isSolid(Tile.SIDE_BOTTOM, e)) {
					maxDeltaY = (r + 1) * Tile.SIZE - y;
					return Math.max(dY, maxDeltaY);
				}
			}
		}
		return dY;
	}
	
	public void calculate() {
		for(int y = 0; y < height; y++) for(int x = 0; x < width; x++) {
			if(map[x][y].getType() == Tile.SINGLE_TILE) {
				values[x][y] = 0;
			} else {
				values[x][y] = getValue(x, y);
			}
		}
	}
	
	public void fill(int x, int y, int width, int height, Tile tile) {
		for(int c = Math.max(0, y); c < Math.min(this.height, y + height); c++) for(int r = Math.max(0, x); r < Math.min(this.width, x + width); r++) {
			map[r][c] = tile;
		}
	}
	
	public void setCamera() {
		cameraX = (int) ((player.getX() + player.getWidth() / 2) * Tile.SIZE / Game.SCALE - Window.WIDTH / 2);
		cameraY = (int) ((player.getY() + player.getHeight() / 2) * Tile.SIZE / Game.SCALE - Window.HEIGHT / 2);
		cameraX = Math.max(0, Math.min(width * Tile.SIZE * Game.SCALE - Window.WIDTH, cameraX));
		cameraY = Math.max(0, Math.min(height * Tile.SIZE * Game.SCALE - Window.HEIGHT, cameraY));
		renderX = cameraX / Tile.SIZE / Game.SCALE;
		renderY = cameraY / Tile.SIZE / Game.SCALE;
		renderWidth = Math.min(width - 1, (int) Math.ceil((double) Window.WIDTH / Tile.SIZE / Game.SCALE) + renderX + 1) - renderX;
		renderHeight = Math.min(height - 1, (int) Math.ceil((double) Window.HEIGHT / Tile.SIZE / Game.SCALE) + renderY + 1) - renderY;
	}
	
	public void moveCamera() {
		cameraX += (int) (((player.getX() + player.getWidth() / 2) * Tile.SIZE / Game.SCALE - Window.WIDTH / 2 - cameraX) * cameraSpeed / Window.RATE + quake * Math.sin(random.nextDouble() * 2 * Math.PI));
		cameraY += (int) (((player.getY() + player.getHeight() / 2) * Tile.SIZE / Game.SCALE - Window.HEIGHT / 2 - cameraY) * cameraSpeed / Window.RATE + quake * Math.cos(random.nextDouble() * 2 * Math.PI));
		cameraX = Math.max(0, Math.min(width * Tile.SIZE * Game.SCALE - Window.WIDTH, cameraX));
		cameraY = Math.max(0, Math.min(height * Tile.SIZE * Game.SCALE - Window.HEIGHT, cameraY));
		renderX = cameraX / Tile.SIZE / Game.SCALE;
		renderY = cameraY / Tile.SIZE / Game.SCALE;
		renderWidth = Math.min(width - 1, (int) Math.ceil((double) Window.WIDTH / Tile.SIZE / Game.SCALE) + renderX + 1) - renderX;
		renderHeight = Math.min(height - 1, (int) Math.ceil((double) Window.HEIGHT / Tile.SIZE / Game.SCALE) + renderY + 1) - renderY;
		if(quake > 0) {
			quake = Math.max(0, quake - 100.0 / Window.RATE);
		}
	}
	
	public void update() {
		boolean changed = false;
		for(int i = 0; i < lights.size(); i++) {
			if(lights.get(i).update()) {
				if(!changed) {
					illuminate();
					changed = true;
				}
			}
		}
		life.update();
	}
	
	public void draw(Graphics g) {
		if(cameraX == 0 && cameraY == 0) {///////////
			setCamera();
		}
		moveCamera();////////////
		g.translate(-cameraX, -cameraY);
		for(int y = renderY; y < renderY + renderHeight; y++) for(int x = renderX; x < renderX + renderWidth; x++) {
			g.drawImage(Images.getBackground(Tile.BRICKS.getIndex()), x * Tile.SIZE * Game.SCALE, y * Tile.SIZE * Game.SCALE, Tile.SIZE * Game.SCALE, Tile.SIZE * Game.SCALE, null);
			map[x][y].draw(x, y, values[x][y], g);
		}
		life.draw(g);
		drawShadows();
		g.drawImage(shadow, 0, 0, width * Tile.SIZE * Game.SCALE, height * Tile.SIZE * Game.SCALE, null);
		g.translate(cameraX, cameraY);
	}
	
	public void drawShadows() {
		Graphics2D g = shadow.createGraphics();
		g.setBackground(new Color(0, true));
		g.clearRect(0, 0, width, height);
		for(int y = renderY; y < renderY + renderHeight; y++) for(int x = renderX; x < renderX + renderWidth; x++) {
			if(!light[x][y]) {
				g.setColor(SHADOW);
				g.drawLine(x, y, x, y);
			}
		}
		Kernel kernel = new Kernel(3, 3, new float[] { 1f / 9f, 1f / 9f, 1f / 9f, 1f / 9f, 1f / 9f, 1f / 9f, 1f / 9f, 1f / 9f, 1f / 9f });///////////////
		BufferedImageOp op = new ConvolveOp(kernel);
		shadow = op.filter(shadow, null);///////////////
		g = shadow.createGraphics();
		for(int y = renderY; y < renderY + renderHeight; y++) for(int x = renderX; x < renderX + renderWidth; x++) {
			if(!seen[x][y]) {
				g.setColor(Color.BLACK);
				g.drawLine(x, y, x, y);
			}
		}
		g.dispose();
	}
	
	public void add(Light l) {
		lights.add(l);
	}
	
	public void remove(Light l) {
		if(lights.contains(l)) {
			lights.remove(l);
		}
	}
	
	public boolean canSee(int startX, int startY, int endX, int endY) {
		List<Point> ray = Ray.cast(startX, startY, endX, endY, true);
		for(int i = 0; i < ray.size(); i++) {
			Point p = ray.get(i);
			if(!map[p.x][p.y].isTranslucent()) {
				return false;
			}
		}
		return true;
	}
	
	public double getGravity() {
		return gravity;
	}
	
	public int getValue(int x, int y) {
		int value = 0;
		int increase = 1;
		for(int c = y - 1; c <= y + 1; c++) for(int r = x - 1; r <= x + 1; r++) {
			if(!(r == x && c == y)) {
				if(r < 0 || r >= width || c < 0 || c >= height || map[x][y].willMerge(map[r][c].getId())) {
					value += increase;
				}
				increase *= 2;
			}
		}
		return value;
	}
	
	public Creature getPlayer() {
		return player;
	}
	
	public Life getLife() {
		return life;
	}
}
