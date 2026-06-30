package net.melting;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Visual {
	
	private static final int AMOUNT = 1, VARIANTS = 48;
	
	private static BufferedImage[][] tiles = new BufferedImage[AMOUNT][VARIANTS];
	
	public static void load() {
		try {
			BufferedImage image;
			image = ImageIO.read(new File("res/tileset.png"));
			for(int y = 0; y < AMOUNT; y++) for(int x = 0; x < VARIANTS; x++) {
				tiles[y][x] = image.getSubimage(x * Tile.SIZE, y * Tile.SIZE, Tile.SIZE, Tile.SIZE);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static BufferedImage getTile(int id, int value) {
		if(id >= 0 && id < tiles.length) {
			switch(value) {
			case 0: case 1: case 4: case 5: case 32: case 33: case 36: case 37: case 128: case 129: case 132: case 133: case 160: case 161: case 164: case 165: value = 0; break;
			case 2: case 3: case 6: case 7: case 34: case 35: case 38: case 39: case 130: case 131: case 134: case 135: case 162: case 163: case 166: case 167: value = 1; break;
			case 8: case 9: case 12: case 13: case 40: case 41: case 44: case 45: case 136: case 137: case 140: case 141: case 168: case 169: case 172: case 173: value = 2; break;
			case 10: case 14: case 42: case 46: case 138: case 142: case 170: case 174: value = 3; break;
			case 11: case 15: case 43: case 47: case 139: case 143: case 171: case 175: value = 4; break;
			case 16: case 17: case 20: case 21: case 48: case 49: case 52: case 53: case 144: case 145: case 148: case 149: case 176: case 177: case 180: case 181: value = 5; break;
			case 18: case 19: case 50: case 51: case 146: case 147: case 178: case 179: value = 6; break;
			case 22: case 23: case 54: case 55: case 150: case 151: case 182: case 183: value = 7; break;
			case 24: case 25: case 28: case 29: case 56: case 57: case 60: case 61: case 152: case 153: case 156: case 157: case 184: case 185: case 188: case 189: value = 8; break;
			case 26: case 58: case 154: case 186: value = 9; break;
			case 27: case 59: case 155: case 187: value = 10; break;
			case 30: case 62: case 158: case 190: value = 11; break;
			case 31: case 63: case 159: case 191: value = 12; break;
			case 64: case 65: case 68: case 69: case 96: case 97: case 100: case 101: case 192: case 193: case 196: case 197: case 224: case 225: case 228: case 229: value = 13; break;
			case 66: case 67: case 70: case 71: case 98: case 99: case 102: case 103: case 194: case 195: case 198: case 199: case 226: case 227: case 230: case 231: value = 14; break;
			case 72: case 73: case 76: case 77: case 200: case 201: case 204: case 205: value = 15; break;
			case 74: case 78: case 202: case 206: value = 16; break;
			case 75: case 79: case 203: case 207: value = 17; break;
			case 80: case 81: case 84: case 85: case 112: case 113: case 116: case 117: value = 18; break;
			case 82: case 83: case 114: case 115: value = 19; break;
			case 86: case 87: case 118: case 119: value = 20; break;
			case 88: case 89: case 92: case 93: value = 21; break;
			case 90: value = 22; break;
			case 91: value = 23; break;
			case 94: value = 24; break;
			case 95: value = 25; break;
			case 104: case 105: case 108: case 109: case 232: case 233: case 236: case 237: value = 26; break;
			case 106: case 110: case 234: case 238: value = 27; break;
			case 107: case 111: case 235: case 239: value = 28; break;
			case 120: case 121: case 124: case 125: value = 29; break;
			case 122: value = 30; break;
			case 123: value = 31; break;
			case 126: value = 32; break;
			case 127: value = 33; break;
			case 208: case 209: case 212: case 213: case 240: case 241: case 244: case 245: value = 34; break;
			case 210: case 211: case 242: case 243: value = 35; break;
			case 214: case 215: case 246: case 247: value = 36; break;
			case 216: case 217: case 220: case 221: value = 37; break;
			case 218: value = 38; break;
			case 219: value = 39; break;
			case 222: value = 40; break;
			case 223: value = 41; break;
			case 248: case 249: case 252: case 253: value = 42; break;
			case 250: value = 43; break;
			case 251: value = 44; break;
			case 254: value = 45; break;
			case 255: value = 46; break;
			default: value = 47; break;
			}
			return tiles[id][value];
		}
		return null;
	}
}
