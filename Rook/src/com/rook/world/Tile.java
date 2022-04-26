package com.rook.world;

import java.awt.Graphics;

import com.rook.Images;
import com.rook.life.Entity;
import com.rook.state.Game;

public class Tile {
	
	private boolean translucent;
	
	public static final int SIZE = 16;
	public static final int SOLID = 0, PASSABLE = 1, PLATEAU = 2;
	public static final int TILE = 0, WIDE_TILE = 1, TALL_TILE = 2, SINGLE_TILE = 3;
	public static final int SIDE_TOP = 0, SIDE_RIGHT = 1, SIDE_BOTTOM = 2, SIDE_LEFT = 3;
	public static final int AMOUNT_TILES = 5, AMOUNT_WIDE_TILES = 3, AMOUNT_TALL_TILES = 6, AMOUNT_SINGLE_TILES = 4;
	
	protected int id, type, index, solidity;
	
	public static final Tile
	AIR = new Tile(0, TILE, -1, PASSABLE, true),
	ROCKS = new Tile(1, TILE, 0, SOLID, false),
	BRICKS = new Tile(2, TILE, 1, SOLID, false),
	PILLAR = new Tile(3, TILE, 2, PASSABLE, true),
	WINDOW = new Tile(4, TILE, 3, PASSABLE, true),
	PLATFORM = new Tile(5, WIDE_TILE, 0, PLATEAU, true),
	LADDER = new Tile(6, TALL_TILE, 0, PLATEAU, true),
	TABLE = new Tile(7, WIDE_TILE, 1, PLATEAU, true),
	FANCY_TABLE = new Tile(7, WIDE_TILE, 2, PLATEAU, true),
	CHAIR_RIGHT = new Tile(8, TALL_TILE, 1, PASSABLE, true),
	CHAIR_LEFT = new Tile(8, TALL_TILE, 2, PASSABLE, true),
	FANCY_CHAIR_RIGHT = new Tile(8, TALL_TILE, 3, PASSABLE, true),
	FANCY_CHAIR_LEFT = new Tile(8, TALL_TILE, 4, PASSABLE, true),
	TURKEY = new Tile(9, SINGLE_TILE, 1, PASSABLE, true),
	PLATE = new Tile(9, SINGLE_TILE, 2, PASSABLE, true),
	CANDLE = new Tile(9, SINGLE_TILE, 3, PASSABLE, true),
	LOCKED_DOOR = new Tile(10, SINGLE_TILE, 0, PASSABLE, false),
	BANNER = new Tile(11, TALL_TILE, 5, PASSABLE, true);
	
	public Tile(int id, int type, int index, int solidity, boolean translucent) {
		this.id = id;
		this.type = type;
		this.index = index;
		this.solidity = solidity;
		this.translucent = translucent;
	}
	
	public void bump(Entity e) {}
	
	public void pass(Entity e) {}
	
	public void draw(int x, int y, int value, Graphics g) {
		switch(type) {
		case TILE: g.drawImage(Images.getTile(index, value), x * SIZE * Game.SCALE, y * SIZE * Game.SCALE, SIZE * Game.SCALE, SIZE * Game.SCALE, null);
		break;
		case WIDE_TILE: g.drawImage(Images.getWideTile(index, value), x * SIZE * Game.SCALE, y * SIZE * Game.SCALE, SIZE * Game.SCALE, SIZE * Game.SCALE, null);
		break;
		case TALL_TILE: g.drawImage(Images.getTallTile(index, value), x * SIZE * Game.SCALE, y * SIZE * Game.SCALE, SIZE * Game.SCALE, SIZE * Game.SCALE, null);
		break;
		case SINGLE_TILE: g.drawImage(Images.getSingleTile(index), x * SIZE * Game.SCALE, y * SIZE * Game.SCALE, SIZE * Game.SCALE, SIZE * Game.SCALE, null);
		break;
		}
	}
	
	public boolean isSolid(int side, Entity e) {
		if(solidity == SOLID) {
			return true;
		} else if(solidity == PLATEAU && side == 0) {
			return true;
		}
		return false;
	}
	
	public boolean isTranslucent() {
		return translucent;
	}
	
	public boolean willMerge(int id) {
		if(this.id == id) {
			return true;
		}
		return false;
	}
	
	public int getId() {
		return id;
	}
	
	public int getIndex() {
		return index;
	}
	
	public int getType() {
		return type;
	}
}
