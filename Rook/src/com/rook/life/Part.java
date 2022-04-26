package com.rook.life;

import java.awt.Graphics;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

import com.rook.Images;
import com.rook.state.Game;

public class Part {
	
	public static final int HEAD = 0, BODY = 1, RIGHT_ARM = 2, LEFT_ARM = 3, RIGHT_LEG = 4, LEFT_LEG = 5;
	
	private int x, y, width, height, xCenter, yCenter, a, type, id, part;
	
	public Part(int x, int y, int width, int height, int xCenter, int yCenter, int a, int type, int id, int part) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.xCenter = xCenter;
		this.yCenter = yCenter;
		this.a = a;
		this.type = type;
		this.id = id;
		this.part = part;
	}
	
	public void move(int t) {
		switch(type) {
		case Animation.IDLE:
			break;
		case Animation.ATTACKING:
			x = (int) (20 * Math.sin(Math.toRadians(t / 360)));
		}
	}
	
	public void draw(int creatureX, int creatureY, int t, Graphics g) {
		move(t);
		BufferedImage image = Images.getPart(id, part);
		AffineTransform transform = new AffineTransform();
		transform.rotate(Math.toRadians(a), xCenter, yCenter);
		AffineTransformOp op = new AffineTransformOp(transform, AffineTransformOp.TYPE_BILINEAR);
		image = op.filter(image, null);
		g.drawImage(Images.getPart(id, part), (creatureX + x) * Game.SCALE, (creatureY + y) * Game.SCALE, width * Game.SCALE, height * Game.SCALE, null);
	}
}
