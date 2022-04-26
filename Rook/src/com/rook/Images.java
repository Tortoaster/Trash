package com.rook;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.filechooser.FileSystemView;

import com.rook.world.Tile;

public class Images {
	
	private static final int VARIANTS = 48, QUARTERED_VARIANTS = 6;
	
	private static BufferedImage[][] tiles = new BufferedImage[Tile.AMOUNT_TILES][VARIANTS];
	private static BufferedImage[][] wideTiles = new BufferedImage[Tile.AMOUNT_WIDE_TILES][3];
	private static BufferedImage[][] tallTiles = new BufferedImage[Tile.AMOUNT_TALL_TILES][3];
	private static BufferedImage[] singleTiles = new BufferedImage[Tile.AMOUNT_SINGLE_TILES];
	
	public static void load() {
		try {
			BufferedImage tileset = ImageIO.read(new File("res/tileset.png"));
			for(int n = 0; n < Tile.AMOUNT_TILES; n++) {
				BufferedImage[][] quarters = new BufferedImage[QUARTERED_VARIANTS - 1][4];
				for(int i = 0; i < QUARTERED_VARIANTS - 1; i++) {
					for (int a = 0; a < 4; a++) {
						quarters[i][a] = tileset.getSubimage(i * Tile.SIZE + (a % 2) * Tile.SIZE / 2, n * Tile.SIZE + (a / 2) * Tile.SIZE / 2, Tile.SIZE / 2, Tile.SIZE / 2);
					}
				}
				for(int i = 0; i < VARIANTS - 1; i++) {
					BufferedImage tile = new BufferedImage(Tile.SIZE, Tile.SIZE, BufferedImage.TYPE_INT_ARGB);
					Graphics g = tile.getGraphics();
					for (int a = 0; a < 4; a++) {
						g.drawImage(quarters[getQuarterIndex(i, a)][a], (a % 2) * Tile.SIZE / 2, (a / 2) * Tile.SIZE / 2, null);
					}
					tiles[n][i] = tile;
				}
				tiles[n][VARIANTS - 1] = tileset.getSubimage((QUARTERED_VARIANTS - 1) * Tile.SIZE, n * Tile.SIZE, Tile.SIZE, Tile.SIZE);
			}
			tileset = ImageIO.read(new File("res/furniture.png"));
			int width = tileset.getWidth() / Tile.SIZE;
			int height = tileset.getHeight() / Tile.SIZE;
			BufferedImage[][] furniture = new BufferedImage[width][height];
			for(int y = 0; y < height; y++) for(int x = 0; x < width; x++) {
				furniture[x][y] = tileset.getSubimage(x * Tile.SIZE, y * Tile.SIZE, Tile.SIZE, Tile.SIZE);
			}
			for(int i = 0; i < Tile.AMOUNT_WIDE_TILES; i++) {
				int x = i / 3 * 3;
				int y = i % 3 + 3;
				wideTiles[i][0] = furniture[x][y];
				wideTiles[i][1] = furniture[x + 1][y];
				wideTiles[i][2] = furniture[x + 2][y];
			}
			for(int i = 0; i < Tile.AMOUNT_TALL_TILES; i++) {
				tallTiles[i][0] = furniture[i][0];
				tallTiles[i][1] = furniture[i][1];
				tallTiles[i][2] = furniture[i][2];
			}
			for(int i = 0; i < Tile.AMOUNT_SINGLE_TILES; i++) {
				singleTiles[i] = furniture[i % width][9 + i / width];
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// Saving to desktop
		for(int i = 0; i < Tile.AMOUNT_TILES; i++) {
			BufferedImage image = new BufferedImage(8 * Tile.SIZE, 8 * Tile.SIZE, BufferedImage.TYPE_INT_ARGB);
			Graphics g = image.getGraphics();
			
			int index = 0;
			
			for(int y = 0; y < 6; y++) {
				for(int x = 0; x < 8; x++) {
					g.drawImage(tiles[i][index], x * Tile.SIZE, y * Tile.SIZE, Tile.SIZE, Tile.SIZE, null);
					index = nextIndex(index);
				}
			}
			
			File out = new File(FileSystemView.getFileSystemView().getHomeDirectory() + File.separator + "new_image" + (i + 1) + ".png");
			try {
				ImageIO.write(image, "png", out);
			} catch(IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	private static int nextIndex(int index) {
		switch(index) {
			case 0: return 5;
			case 5: return 8;
			case 8: return 2;
			case 2: return 43;
			case 43: return 31;
			case 31: return 25;
			case 25: return 40;
			case 40: return 13;
			case 13: return 34;
			case 34: return 42;
			case 42: return 26;
			case 26: return 18;
			case 18: return 21;
			case 21: return 15;
			case 15: return 39;
			case 39: return 14;
			case 14: return 36;
			case 36: return 46;
			case 46: return 28;
			case 28: return 19;
			case 19: return 22;
			case 22: return 16;
			case 16: return 32;
			case 32: return 1;
			case 1: return 7;
			case 7: return 12;
			case 12: return 4;
			case 4: return 6;
			case 6: return 9;
			case 9: return 3;
			case 3: return 47;
			case 47: return 20;
			case 20: return 17;
			case 17: return 29;
			case 29: return 37;
			case 37: return 23;
			case 23: return 24;
			case 24: return 33;
			case 33: return 41;
			case 41: return 35;
			case 35: return 27;
			case 27: return 10;
			case 10: return 11;
			case 11: return 30;
			case 30: return 38;
			case 38: return 44;
			case 44: return 45;
			default: return 0;
		}
	}
	
	private static int getQuarterIndex(int index, int quarter) {
		if(quarter == 0) {
			switch(index) {
			case 0: case 5: case 13: case 18: case 34: return 0;
			case 1: case 6: case 7: case 14: case 19: case 20: case 35: case 36: return 1;
			case 2: case 8: case 15: case 21: case 26: case 29: case 37: case 42: return 2;
			case 3: case 9: case 11: case 16: case 22: case 24: case 27: case 30: case 32: case 38: case 40: case 43: case 45: return 3;
			default: return 4;
			}
		} else if(quarter == 1) {
			switch(index) {
			case 0: case 2: case 13: case 15: case 26: return 0;
			case 1: case 3: case 4: case 14: case 16: case 17: case 27: case 28: return 1;
			case 5: case 8: case 18: case 21: case 29: case 34: case 37: case 42: return 2;
			case 6: case 9: case 10: case 19: case 22: case 23: case 30: case 31: case 35: case 38: case 39: case 43: case 44: return 3;
			default: return 4;
			}
		} else if(quarter == 2) {
			if(index >= 15 && index < 18) return 3;
			if(index < 2 || (index >= 5 && index < 8)) {
				return 0;
			} else if(index < 13) {
				return 2;
			} else if(index < 21 || (index >= 34 && index < 37)) {
				return 1;
			} else if(index < 26 || (index >= 37 && index < 42)) {
				return 3;
			} else {
				return 4;
			}
		} else {
			if(index < 5) {
				return 0;
			} else if(index < 13) {
				return 2;
			} else if(index < 18 || (index >= 26 && index < 29)) {
				return 1;
			} else if(index >= 34) {
				return 4;
			} else {
				return 3;
			}
		}
	}
	
	private static int getIndex(int value) {
		switch(value) {
		case 0: case 1: case 4: case 5: case 32: case 33: case 36: case 37: case 128: case 129: case 132: case 133: case 160: case 161: case 164: case 165: return 0;
		case 2: case 3: case 6: case 7: case 34: case 35: case 38: case 39: case 130: case 131: case 134: case 135: case 162: case 163: case 166: case 167: return 1;
		case 8: case 9: case 12: case 13: case 40: case 41: case 44: case 45: case 136: case 137: case 140: case 141: case 168: case 169: case 172: case 173: return 2;
		case 10: case 14: case 42: case 46: case 138: case 142: case 170: case 174: return 3;
		case 11: case 15: case 43: case 47: case 139: case 143: case 171: case 175: return 4;
		case 16: case 17: case 20: case 21: case 48: case 49: case 52: case 53: case 144: case 145: case 148: case 149: case 176: case 177: case 180: case 181: return 5;
		case 18: case 19: case 50: case 51: case 146: case 147: case 178: case 179: return 6;
		case 22: case 23: case 54: case 55: case 150: case 151: case 182: case 183: return 7;
		case 24: case 25: case 28: case 29: case 56: case 57: case 60: case 61: case 152: case 153: case 156: case 157: case 184: case 185: case 188: case 189: return 8;
		case 26: case 58: case 154: case 186: return 9;
		case 27: case 59: case 155: case 187: return 10;
		case 30: case 62: case 158: case 190: return 11;
		case 31: case 63: case 159: case 191: return 12;
		case 64: case 65: case 68: case 69: case 96: case 97: case 100: case 101: case 192: case 193: case 196: case 197: case 224: case 225: case 228: case 229: return 13;
		case 66: case 67: case 70: case 71: case 98: case 99: case 102: case 103: case 194: case 195: case 198: case 199: case 226: case 227: case 230: case 231: return 14;
		case 72: case 73: case 76: case 77: case 200: case 201: case 204: case 205: return 15;
		case 74: case 78: case 202: case 206: return 16;
		case 75: case 79: case 203: case 207: return 17;
		case 80: case 81: case 84: case 85: case 112: case 113: case 116: case 117: return 18;
		case 82: case 83: case 114: case 115: return 19;
		case 86: case 87: case 118: case 119: return 20;
		case 88: case 89: case 92: case 93: return 21;
		case 90: return 22;
		case 91: return 23;
		case 94: return 24;
		case 95: return 25;
		case 104: case 105: case 108: case 109: case 232: case 233: case 236: case 237: return 26;
		case 106: case 110: case 234: case 238: return 27;
		case 107: case 111: case 235: case 239: return 28;
		case 120: case 121: case 124: case 125: return 29;
		case 122: return 30;
		case 123: return 31;
		case 126: return 32;
		case 127: return 33;
		case 208: case 209: case 212: case 213: case 240: case 241: case 244: case 245: return 34;
		case 210: case 211: case 242: case 243: return 35;
		case 214: case 215: case 246: case 247: return 36;
		case 216: case 217: case 220: case 221: return 37;
		case 218: return 38;
		case 219: return 39;
		case 222: return 40;
		case 223: return 41;
		case 248: case 249: case 252: case 253: return 42;
		case 250: return 43;
		case 251: return 44;
		case 254: return 45;
		default: return 46;
		}
	}
	
	private static int getHorizontalIndex(int value) {
		int index = getIndex(value);
		switch(index) {
		case 0: case 1: case 5: case 6: case 7: case 13: case 14: case 18: case 19: case 20: case 34: case 35: case 36: return 0;
		case 2: case 3: case 4: case 15: case 16: case 17: case 26: case 27: case 28: return 2;
		default: return 1;
		}
	}
	
	private static int getVerticalIndex(int value) {
		int index = getIndex(value);
		switch(index) {
		case 0: case 2: case 5: case 8: case 13: case 15: case 18: case 21: case 26: case 29: case 34: case 37: case 42: return 0;
		case 1: case 3: case 4: case 6: case 7: case 9: case 10: case 11: case 12: return 2;
		default: return 1;
		}
	}
	
	public static BufferedImage getTile(int id, int value) {
		if(id >= 0 && id < Tile.AMOUNT_TILES) {
			return tiles[id][getIndex(value)];
		}
		return null;
	}
	
	public static BufferedImage getWideTile(int id, int value) {
		if(id >= 0 && id < Tile.AMOUNT_WIDE_TILES) {
			return wideTiles[id][getHorizontalIndex(value)];
		}
		return null;
	}
	
	public static BufferedImage getTallTile(int id, int value) {
		if(id >= 0 && id < Tile.AMOUNT_TALL_TILES) {
			return tallTiles[id][getVerticalIndex(value)];
		}
		return null;
	}
	
	public static BufferedImage getSingleTile(int id) {
		if(id >= 0 && id < Tile.AMOUNT_SINGLE_TILES) {
			return singleTiles[id];
		}
		return null;
	}
	
	public static BufferedImage getBackground(int id) {
		if(id >= 0 && id < Tile.AMOUNT_TILES) {
			return tiles[id][VARIANTS - 1];
		}
		return null;
	}
	
	public static BufferedImage getPart(int id, int part) {
		return null;
	}
	
	public boolean isTop(int value) {
		if(getVerticalIndex(value) == 0) {
			return true;
		}
		return false;
	}
}
