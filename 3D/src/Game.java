import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Game extends State {
	
	private int size = 16;
	
	private double xPos;
	private double yPos = size;
	private double zPos;
	private double r;
	private double xV;
	private double yV;
	private double zV;
	private double rV;
	private double speed = .25;
	
	private BufferedImage block;
	
	public Game(Class c) {
		this.c = c;
		try {
			block = ImageIO.read(new File("res/Block.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_W: zV = speed;
		break;
		case KeyEvent.VK_A: xV = -speed;
		break;
		case KeyEvent.VK_S: zV = -speed;
		break;
		case KeyEvent.VK_D: xV = speed;
		break;
		case KeyEvent.VK_SPACE: if (yPos == size) yV = speed * 2;
		break;
		case KeyEvent.VK_Q: rV = -speed * 2;
		break;
		case KeyEvent.VK_E: rV = speed * 2;
		}
	}
	
	public void keyReleased(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_W: zV = 0;
		break;
		case KeyEvent.VK_A: xV = 0;
		break;
		case KeyEvent.VK_S: zV = 0;
		break;
		case KeyEvent.VK_D: xV = 0;
		break;
		case KeyEvent.VK_Q: rV = 0;
		break;
		case KeyEvent.VK_E: rV = 0;
		}
	}
	
	public void update() {
		xPos += Math.sin(r) * zV + Math.cos(r) * xV;
		yPos += yV;
		zPos += Math.sin(r) * xV + Math.cos(r) * zV;
		r += rV / 64;
		if (yPos > size) {
			yV -= .0125;
		} else {
			yV = 0;
			yPos = size;
		}
	}
	
	public void draw(Graphics g) {
		g.setColor(Color.black);
		g.fillRect(0, 0, Class.width, Class.height);
		for (int y = 0; y < Class.height; y++) {
			double height = yPos / (y - Class.height / 4.) * Class.height / 2;
			if (height > 0) {
				for (int x = 0; x < Class.width; x++) {
					double depth = (x - Class.width / 2.) / Class.height * 2 * height;
					double xx = (depth + xPos) * Math.cos(r) + height * Math.sin(r);
					double yy = (height + zPos) * Math.cos(r) - depth * Math.sin(r);
					if (height < 100) {
						g.setColor(new Color((int) Math.max(0, Math.min(255, getRed((int) xx & 15, (int) yy & 15) - height * 2)), (int) Math.max(0, Math.min(255, getGreen((int) xx & 15, (int) yy & 15) - height * 2)), (int) Math.max(0, Math.min(255, getBlue((int) xx & 15, (int) yy & 15) - height * 2))));
						g.drawLine(x, y, x, y);
					}
				}
			}
		}
	}
	
	public int getRed(int x, int y) {
		return block.getRGB(x, y) & 0x00ff0000 >> 16;
	}
	
	public int getGreen(int x, int y) {
		return block.getRGB(x, y) & 0x0000ff00 >> 8;
	}
	
	public int getBlue(int x, int y) {
		return block.getRGB(x, y) & 0x000000ff;
	}
}
