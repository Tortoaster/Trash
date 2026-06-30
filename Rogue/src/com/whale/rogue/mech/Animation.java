package com.whale.rogue.mech;

import java.awt.image.BufferedImage;

import com.whale.rogue.main.Window;

public class Animation {
	
	private double speed;
	
	private BufferedImage[] frames;
	
	public Animation(BufferedImage[] frames, double speed) {
		this.frames = frames;
		this.speed = speed / Window.RATE * frames.length;
	}
	
	public BufferedImage getImage(int frame) {
		return frames[(int) ((frame * speed) % frames.length)];
	}
}
