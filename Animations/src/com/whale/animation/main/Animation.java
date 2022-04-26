package com.whale.animation.main;

import java.awt.image.BufferedImage;

public class Animation {
	
	private boolean loop;
	
	private double time, speed;
	
	private int length;
	
	private BufferedImage[] frame;
	
	public Animation(BufferedImage spritesheet, int width, int height, int row, int length, double speed, boolean loop) {
		this.length = length;
		this.speed = speed;
		this.loop = loop;
		frame = new BufferedImage[length];
		for (int i = 0; i < length; i++) {
			frame[i] = spritesheet.getSubimage(i * width, row * height, width, height);
		}
	}
	
	public BufferedImage getImage() {
		time += speed;
		if (loop) {
			return frame[(int) time % length];
		} else {
			return frame[Math.min(length - 1, (int) time)];
		}
	}
}
