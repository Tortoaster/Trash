package com.whale.rogue.mech;

import java.awt.image.BufferedImage;

public class Animator {
	
	private Animation[] animations;
	
	public Animator(Animation[] animations) {
		this.animations = animations;
	}
	
	public BufferedImage getImage(int index, int frame) {
		return animations[index].getImage(frame);
	}
}
