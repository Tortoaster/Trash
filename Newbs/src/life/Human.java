package life;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

import main.Screen;

public class Human extends Humanoid {
	
	private boolean NPC;
	private boolean first = true;
	
	private int position, front;
	
	
	private Creature[] array;
	
	private Color c;
	
	private BufferedImage[] images = new BufferedImage[parts.length];
	
	private String name;
	
	public Human(int x, int y, Color c, String name, boolean NPC, Creature[] array) {
		super(x, y);
		this.c = c;
		this.name = name;
		this.NPC = NPC;
		this.array = array;
		try {
		BufferedImage image = ImageIO.read(new File("res/human.png"));
		images[head] = image.getSubimage(0, 0, 8, 6);
		images[body] = image.getSubimage(0, 6, 8, 6);
		images[rightHand] = image.getSubimage(8, 0, 4, 4);
		images[leftHand] = image.getSubimage(8, 0, 4, 4);
		images[rightFoot] = image.getSubimage(8, 4, 4, 4);
		images[leftFoot] = image.getSubimage(8, 4, 4, 4);
		} catch (IOException e) {
			e.printStackTrace();
		}
		setActivity(Activity.STANDING);
	}
	
	public void update() {
		super.update();
		if (y >= Screen.height - 12 && activity == Activity.JUMPING) if (xV != 0) activity = Activity.WALKING; else activity = Activity.STANDING;
		if (NPC) {
			switch (AI) {
			case 0: if (first) {
				int f = 0;
				for (int i = 0; i < array.length; i++) if (array[i].x > x) {
					position++;
					if (array[f].x < array[i].x) f = i;
				}
				if (position > 0) front = array[f].x; else front = x;
				first = false;
			}
			for (int i = 0; i < array.length; i++) if (activity != Activity.WAVING) if (x - (front - position * 12) < 0) {
				xV = 1;
				activity = Activity.WALKING;
			} else if (x - (front - position * 12) > 0) {
				xV = -1;
				activity = Activity.WALKING;
			} else {
				xV = 0;
				activity = Activity.WAVING2;
			}
			break;
			case 1: activity = Activity.WALKING;
			if (xV == 0) xV = new Random().nextInt(3) - 1;
			if (x > Screen.width - 12) xV = -1; else if (x < 0) xV = 1;
			}
		}
	}
	
	public void draw(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		if (activity == Activity.FLYING && (xV != 0 || yV != 0)) {
			g.translate(x + 6, y + 6);
			g2.rotate(Math.atan2(yV, xV) + Math.PI / 2);
			g.translate(-x - 6, -y - 6);
		}
		for (int i = 0; i < parts.length; i++) {
			g.setColor(c);
			g.fillRect(parts[i].x, parts[i].y, parts[i].width, parts[i].height);
			g.drawImage(images[i], parts[i].x, parts[i].y, parts[i].width, parts[i].height, null);
		}
		if (activity == Activity.FLYING && (xV != 0 || yV != 0)) {
			g.translate(x + 6, y + 6);
			g2.rotate(-Math.atan2(yV, xV) - Math.PI / 2);
			g.translate(-x - 6, -y - 6);
		}
		g.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		g.setColor(Color.black);
		g.drawString("" + name, x - (int) g.getFontMetrics(g.getFont()).getStringBounds(name, null).getWidth() / 2 + 12 / 2, y - 12 / 2);
	}
}
