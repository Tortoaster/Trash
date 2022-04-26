package life;

import java.awt.Rectangle;

import main.Screen;

public class Humanoid extends Creature {
	
	protected int head = 4, body = 2, rightHand = 5, leftHand = 0, rightFoot = 3, leftFoot = 1;
	
	protected Rectangle[] parts = new Rectangle[6];
	
	public Humanoid(int x, int y) {
		super(x, y);
		parts[head] = new Rectangle(x, y - 1, 8, 6);
		parts[body] = new Rectangle(x, y + 4, 8, 6);
		parts[rightHand] = new Rectangle(x - 2, y + 5, 4, 4);
		parts[leftHand] = new Rectangle(x + 6, y + 5, 4, 4);
		parts[rightFoot] = new Rectangle(x - 1, y + 8, 4, 4);
		parts[leftFoot] = new Rectangle(x + 5, y + 8, 4, 4);
	}
	
	public void update() {
		animate();
		if (activity != Activity.FLYING) yV = Math.min(2, yV + 1);
		if (y + yV >= Screen.height - 12) {
			yV = 0;
			y = Screen.height - 12;
		}
		x += xV;
		y += yV;
	}
	
	public void animate() {
		switch (activity) {
		case STANDING: parts[head] = new Rectangle(x, y - 1 + (int) (2 * Math.sin(System.currentTimeMillis() / 100.0)), 8, 6);
		parts[body] = new Rectangle(x, y + 4 + (int) (2 * Math.sin(System.currentTimeMillis() / 100.0)), 8, 6);
		parts[rightHand] = new Rectangle(x - 2, y + 5 + (int) (2 * Math.sin(System.currentTimeMillis() / 100.0)), 4, 4);
		parts[leftHand] = new Rectangle(x + 6, y + 5+ (int) (2 * Math.sin(System.currentTimeMillis() / 100.0)), 4, 4);
		parts[rightFoot] = new Rectangle(x - 1, y + 8, 4, 4);
		parts[leftFoot] = new Rectangle(x + 5, y + 8, 4, 4);
		break;
		case WALKING: parts[head] = new Rectangle(x, y - 1 + (int) (2 * Math.sin(System.currentTimeMillis() / (50.0 / xV))), 8, 6);
		parts[body] = new Rectangle(x, y + 4 + (int) (2 * Math.sin(System.currentTimeMillis() / (50.0 / xV))), 8, 6);
		parts[rightHand] = new Rectangle(x - 2 + (int) (2 * Math.sin(System.currentTimeMillis() / (100.0 / xV))), y + 5 + (int) (2 * Math.cos(System.currentTimeMillis() / (50.0 / xV))), 4, 4);
		parts[leftHand] = new Rectangle(x + 6 - (int) (2 * Math.sin(System.currentTimeMillis() / (100.0 / xV))), y + 5 + (int) (2 * Math.cos(System.currentTimeMillis() / (50.0 / xV))), 4, 4);
		parts[rightFoot] = new Rectangle(x + 2 + (int) (4 * Math.sin(System.currentTimeMillis() / (100.0 / xV))), y + 8 - (int) (2 * Math.cos(System.currentTimeMillis() / (100.0 / xV))), 4, 4);
		parts[leftFoot] = new Rectangle(x + 2 - (int) (4 * Math.sin(System.currentTimeMillis() / (100.0 / xV))), y + 8 + (int) (2 * Math.cos(System.currentTimeMillis() / (100.0 / xV))), 4, 4);
		break;
		case JUMPING: parts[head] = new Rectangle(x, y - 1, 8, 6);
		parts[body] = new Rectangle(x, y + 4, 8, 6);
		parts[rightHand] = new Rectangle(x - 2, y + 2 + (int) (2 * Math.sin(System.currentTimeMillis() / 100.0)), 4, 4);
		parts[leftHand] = new Rectangle(x + 6, y + 3 + (int) (2 * Math.sin(System.currentTimeMillis() / 100.0)), 4, 4);
		parts[rightFoot] = new Rectangle(x - 1, y + 10, 4, 4);
		parts[leftFoot] = new Rectangle(x + 5, y + 9, 4, 4);
		break;
		case SWIMMING: parts[head] = new Rectangle(x, y - 1, 8, 6);
		parts[body] = new Rectangle(x, y + 4, 8, 6);
		parts[rightHand] = new Rectangle(x - 2, y + 5, 4, 4);
		parts[leftHand] = new Rectangle(x + 6, y + 5, 4, 4);
		parts[rightFoot] = new Rectangle(x - 1, y + 8, 4, 4);
		parts[leftFoot] = new Rectangle(x + 5, y + 8, 4, 4);
		break;
		case FLYING: parts[head] = new Rectangle(x, y - 1, 8, 6);
		parts[body] = new Rectangle(x, y + 4, 8, 6);
		parts[rightHand] = new Rectangle(x - 2, y + 2 + (int) (2 * Math.sin(System.currentTimeMillis() / 100.0)), 4, 4);
		parts[leftHand] = new Rectangle(x + 6, y + 3 + (int) (2 * Math.sin(System.currentTimeMillis() / 100.0)), 4, 4);
		parts[rightFoot] = new Rectangle(x - 1, y + 9, 4, 4);
		parts[leftFoot] = new Rectangle(x + 5, y + 8, 4, 4);
		for (int i = 0; i < parts.length; i++) parts[i].y += (int) (2 * Math.sin(System.currentTimeMillis() / 100.0));
		break;
		case DANCING: parts[head] = new Rectangle(x - + (int) (2 * Math.sin(System.currentTimeMillis() / 100.0)), y - 1 + (int) (2 * Math.sin(System.currentTimeMillis() / 100.0)), 8, 6);
		parts[body] = new Rectangle(x - + (int) (2 * Math.sin(System.currentTimeMillis() / 100.0)), y + 4 + (int) (2 * Math.sin(System.currentTimeMillis() / 100.0)), 8, 6);
		parts[rightHand] = new Rectangle(x - 2 + (int) (2 * Math.sin(System.currentTimeMillis() / 100.0)), y + 5 - (int) (2 * Math.sin(System.currentTimeMillis() / 100.0)), 4, 4);
		parts[leftHand] = new Rectangle(x + 6 + (int) (2 * Math.sin(System.currentTimeMillis() / 100.0)), y + 5 - (int) (2 * Math.sin(System.currentTimeMillis() / 100.0)), 4, 4);
		parts[rightFoot] = new Rectangle(x, y + 8, 4, 4);
		parts[leftFoot] = new Rectangle(x + 4, y + 8, 4, 4);
		break;
		case WAVING: parts[head] = new Rectangle(x, y - 1 + (int) (2 * Math.sin((System.currentTimeMillis() + 5 * x) / 100.0)), 8, 6);
		parts[body] = new Rectangle(x, y + 4 + (int) (2 * Math.sin((System.currentTimeMillis() + 5 * x) / 100.0)), 8, 6);
		parts[rightHand] = new Rectangle(x - 2, y + (int) (6 * Math.sin((System.currentTimeMillis() + 5 * x) / 100.0)), 4, 4);
		parts[leftHand] = new Rectangle(x + 6, y + (int) (6 * Math.sin((System.currentTimeMillis() + 5 * x) / 100.0)), 4, 4);
		parts[rightFoot] = new Rectangle(x - 1, y + 8, 4, 4);
		parts[leftFoot] = new Rectangle(x + 5, y + 8, 4, 4);
		break;
		case WAVING2: parts[head] = new Rectangle(x + (int) (2 * Math.sin((System.currentTimeMillis() + 10 * x) / 200.0)), y - 1 - (int) (2 * Math.cos((System.currentTimeMillis() + 10 * x) / 100.0)), 8, 6);
		parts[body] = new Rectangle(x + (int) (2 * Math.sin((System.currentTimeMillis() + 10 * x) / 200.0)), y + 4 - (int) (2 * Math.cos((System.currentTimeMillis() + 10 * x) / 100.0)), 8, 6);
		parts[rightHand] = new Rectangle(x - 2 + (int) (5 * Math.sin((System.currentTimeMillis() + 10 * x) / 200.0)), y - 2 - (int) (3 * Math.cos((System.currentTimeMillis() + 10 * x) / 100.0)), 4, 4);
		parts[leftHand] = new Rectangle(x + 6 + (int) (5 * Math.sin((System.currentTimeMillis() + 10 * x) / 200.0)), y - 2 - (int) (3 * Math.cos((System.currentTimeMillis() + 10 * x) / 100.0)), 4, 4);
		parts[rightFoot] = new Rectangle(x - 1, y + 8, 4, 4);
		parts[leftFoot] = new Rectangle(x + 5, y + 8, 4, 4);
		break;
		case MILKING: parts[head] = new Rectangle(x + (int) (2 * Math.sin(System.currentTimeMillis() / 200.0)), y - 1 + (int) (2 * Math.sin((System.currentTimeMillis()) / 100.0)), 8, 6);
		parts[body] = new Rectangle(x + (int) (2 * Math.sin(System.currentTimeMillis() / 200.0)), y + 4 + (int) (2 * Math.sin((System.currentTimeMillis()) / 100.0)), 8, 6);
		parts[rightHand] = new Rectangle(x + 2 + (int) (3 * Math.cos(System.currentTimeMillis() / 50.0)) + (int) (2 * Math.sin(System.currentTimeMillis() / 200.0)), y + 5 - (int) (5 * Math.sin(System.currentTimeMillis() / 100.0)), 4, 4);
		parts[leftHand] = new Rectangle(x + 6 + (int) (3 * Math.cos(System.currentTimeMillis() / 50.0)) + (int) (2 * Math.sin(System.currentTimeMillis() / 200.0)), y + 5 + (int) (5 * Math.sin(System.currentTimeMillis() / 100.0)), 4, 4);
		parts[rightFoot] = new Rectangle(x - 1, y + 8, 4, 4);
		parts[leftFoot] = new Rectangle(x + 5, y + 8, 4, 4);
		break;
		case STYLING: parts[head] = new Rectangle(x, y - 1, 8, 6);
		parts[body] = new Rectangle(x, y + 4, 8, 6);
		parts[rightHand] = new Rectangle(x + 4, y + 4, 4, 4);
		parts[leftHand] = new Rectangle(x + 6, y + 4, 4, 4);
		parts[rightFoot] = new Rectangle(x - 1, y + 8, 4, 4);
		parts[leftFoot] = new Rectangle(x + 5, y + 8, 4, 4);
		for (int i = 0; i < parts.length; i++) parts[i].y += Math.sin(System.currentTimeMillis() / 75.0 + 8 * parts[i].x);
		break;
		case PUSHING: parts[head] = new Rectangle(x + 4, y + 3 + (int) (3 * Math.sin(System.currentTimeMillis() / 100.0)), 8, 6);
		parts[body] = new Rectangle(x + 2, y + 5 + (int) (2 * Math.sin(System.currentTimeMillis() / 100.0)), 8, 6);
		parts[rightHand] = new Rectangle(x + 2, y + 8, 4, 4);
		parts[leftHand] = new Rectangle(x + 10, y + 8, 4, 4);
		parts[rightFoot] = new Rectangle(x - 1, y + 8, 4, 4);
		parts[leftFoot] = new Rectangle(x + 1, y + 8, 4, 4);
		}
	}
}
