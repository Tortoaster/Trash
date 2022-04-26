package com.whale.animation.main;

import java.awt.Graphics;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Game extends State {
	
	private int current = 0;
	
	private Animation[] animations = new Animation[3];
	
	public Game(Manager manager) {
		super(manager);
		try {
			animations[0] = new Animation(ImageIO.read(new File("res/spritesheet.png")), 16, 16, 0, 16, 0.2, true);
			animations[1] = new Animation(ImageIO.read(new File("res/spritesheet.png")), 16, 16, 1, 9, 0.2, false);
			animations[2] = new Animation(ImageIO.read(new File("res/spritesheet.png")), 16, 16, 2, 16, 0.01, true);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void update() {
		
	}
	
	public void draw(Graphics g) {
		g.drawImage(animations[current].getImage(), 100, 100, 128, 128, null);
	}
}
