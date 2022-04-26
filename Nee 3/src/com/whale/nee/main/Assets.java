package com.whale.nee.main;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.whale.nee.life.Creature;
import com.whale.nee.world.Tile;

public class Assets {
	
	public static BufferedImage[] TILES, SPRITES;
	
	public static void load() {
		try {
			BufferedImage tileset = ImageIO.read(new File("res/Tileset.png"));
			TILES = new BufferedImage[(tileset.getWidth() / Tile.SIZE) * (tileset.getHeight() / Tile.SIZE)];
			for (int y = 0; y < tileset.getHeight() / Tile.SIZE; y++) for (int x = 0; x < tileset.getWidth() / Tile.SIZE; x++) {
				TILES[y * (tileset.getWidth() / Tile.SIZE) + x] = tileset.getSubimage(x * Tile.SIZE, y * Tile.SIZE, Tile.SIZE, Tile.SIZE);
			}
			BufferedImage spritesheet = ImageIO.read(new File("res/Spritesheet.png"));
			SPRITES = new BufferedImage[(spritesheet.getWidth() / Creature.SIZE) * (spritesheet.getHeight() / Creature.SIZE)];
			for (int y = 0; y < spritesheet.getHeight() / Creature.SIZE; y++) for (int x = 0; x < spritesheet.getWidth() / Creature.SIZE; x++) {
				SPRITES[y * (spritesheet.getWidth() / Creature.SIZE) + x] = spritesheet.getSubimage(x * Creature.SIZE, y * Creature.SIZE, Creature.SIZE, Creature.SIZE);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
