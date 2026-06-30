package com.whale.gravity.effects;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Bolt {
	
	private int x1, y1, x2, y2;
	private static final int SPACING = 4;
	
	private static BufferedImage end;
	private static BufferedImage segment;
	
	public Bolt(int x1, int y1, int x2, int y2) {
		if (end == null || segment == null) load();
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
	}
	
	public void draw(Graphics g) {
		double a = Math.atan2(y2 - y1, x2 - x1);
		double length = Math.sqrt((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1));
		Graphics2D g2 = (Graphics2D) g;
		g.translate(x1, y1);
		g2.rotate(a);
		g.drawImage(end, -end.getWidth() + SPACING, -end.getHeight() / 2, null);
		for (int i = SPACING; i < (int) length - SPACING; i++) g.drawImage(segment, i, -segment.getHeight() / 2, null);
		g.drawImage(end, (int) length + end.getWidth() - SPACING, -end.getHeight() / 2, -end.getWidth(), end.getHeight(), null);
		g2.rotate(-a);
		g.translate(-x1, -y1);
	}
	
	public void load() {
		try {
			end = ImageIO.read(new File("res/lightning_end.png"));
			segment = ImageIO.read(new File("res/lightning_segment.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
