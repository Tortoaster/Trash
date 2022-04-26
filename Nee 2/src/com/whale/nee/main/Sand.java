package com.whale.nee.main;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Sand extends Entity {
	
	BufferedImage image;
	
	public Sand(double x, double y, int z, double xV, double yV, Handler handler) {
		super(x, y, z, xV, yV, handler);
		try {
			image = ImageIO.read(new File("res/Tileset.png")).getSubimage(56, 0, 8, 8);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void update() {
		super.update();
		if (handler.returnClass().Map[(int) x][Math.min(15, (int) y + 1)][z] != 0) {
			handler.remove(this);
			handler.returnClass().Map[(int) x][(int) y][z] = 7;
		}
	}
	
	public void draw(Graphics g) {
		g.drawImage(image, (int) (x * 64), (int) (y * 64), 64, 64, null);
	}
}
