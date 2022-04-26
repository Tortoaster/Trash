package com.tortoaster.palettes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Main implements State {
	
	private static final int WIDTH = 5, HEIGHT = 100, SIZE = 8;
	
	private static final float YELLOW = 1F / 6, BLUE = 2F / 3;
	
	private BufferedImage image = new BufferedImage(WIDTH * SIZE, HEIGHT * SIZE, BufferedImage.TYPE_INT_ARGB);
	
	private Random random = new Random();
	
	public Main() {
		Color[][] palette = new Color[HEIGHT][WIDTH];
		
		for(int i = 0; i < HEIGHT; i++) {
			palette[i] = generateColorGradient(Type.NORMAL);
		}
		
		Graphics g = image.getGraphics();
		
		for(int y = 0; y < HEIGHT; y++) {
			for(int x = 0; x < WIDTH; x++) {
				g.setColor(palette[y][x]);
				g.fillRect(x * SIZE, y * SIZE, SIZE, SIZE);
			}
		}
	}
	
	@Override
	public void update() {
		
	}
	
	@Override
	public void draw(Graphics g) {
		g.drawImage(image, 0, 0, null);
	}
	
	private Color[] generateColorGradient(Type type) {
		Color[] colors = new Color[WIDTH];
		
		float hue = YELLOW + random.nextFloat() - 0.5F;
		
		float changingness;
		if(hue < YELLOW + random.nextFloat() / 2) changingness = 1 - random.nextFloat() / 5;
		else changingness = 1 + random.nextFloat() / 5;
		
		float brightness = random.nextFloat() / 2 + 0.5F;
		
		float difference = random.nextFloat() / 2 + 0.5F;
		
		for(int i = 0; i < WIDTH; i++) {
			brightness *= difference;
			hue *= changingness;
			colors[i] = Color.getHSBColor(hue, type.getSaturation(brightness), brightness);
		}
		
		return colors;
	}
	
	public enum Type {
		NORMAL,
		SHINY,
		MATTE;
		
		public float getSaturation(float brightness) {
			brightness %= 1;
			
			switch(this) {
				default: return -(brightness * brightness) + 1;
			}
		}
	}
}
