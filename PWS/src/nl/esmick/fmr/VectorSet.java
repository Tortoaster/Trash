package nl.esmick.fmr;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

public class VectorSet {
	
	private Color color;
	
	private Vector[] set = new Vector[8];
	
	public VectorSet(int x, int y, int width, int height, Color color) {
		set[0] = new Vector(x, y);
		set[1] = new Vector(x + width / 2, y);
		set[2] = new Vector(x + width, y);
		set[3] = new Vector(x + width, y + height / 2);
		set[4] = new Vector(x + width, y + height);
		set[5] = new Vector(x + width / 2, y + height);
		set[6] = new Vector(x, y + height);
		set[7] = new Vector(x, y + height / 2);
		this.color = color;
	}
	
	public BufferedImage getImage() {
		BufferedImage image = new BufferedImage(Window.WIDTH, Window.HEIGHT, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = image.createGraphics();
		for(int i = 0; i < set.length; i++) {
			g.setStroke(new BasicStroke(2, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[] {4}, 0));
			g.setColor(color);
			g.drawLine(set[i].getX(), set[i].getY(), set[(i + 1) % set.length].getX(), set[(i + 1) % set.length].getY());
		}
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		for(int i = 0; i < set.length; i++) {
			g.setColor(Color.LIGHT_GRAY);
			g.fillOval(set[i].getX() - 5, set[i].getY() - 4, 10, 10);
			g.setColor(Color.WHITE);
			g.fillOval(set[i].getX() - 5, set[i].getY() - 5, 10, 10);
			g.setColor(color);
			g.fillOval(set[i].getX() - 2, set[i].getY() - 2, 4, 4);
		}
		g.dispose();
		return image;
	}
	
	public int getX() {
		return set[7].getX();
	}
	
	public int getY() {
		return set[1].getY();
	}
	
	public int getWidth() {
		return set[3].getX() - set[7].getX();
	}
	
	public int getHeight() {
		return set[5].getY() - set[1].getY();
	}
	
	public Vector getVector(int x, int y) {
		for(int i = 0; i < set.length; i++) {
			if(Math.sqrt(Math.pow(set[i].getX() - x, 2) + Math.pow(set[i].getY() - y, 2)) < 5) return set[i];
		}
		return null;
	}
	
	public void setPos(Vector vector, int x, int y, boolean right) {
		if(!right) {
			int index = 0;
			for(int i = 0; i < set.length; i++) {
				if(set[i] == vector) index = i;
			}
			if(index % 2 == 1) {
				if((index - 1) % 4 == 0) {
					set[index - 1].setY(y);
					set[(index + 1) % 8].setY(y);
				} else {
					set[index - 1].setX(x);
					set[(index + 1) % 8].setX(x);
				}
			} else {
				if(index % 4 == 0) {
					set[(index + 6) % 8].setX(x);
					set[(index + 7) % 8].setX(x);
					set[(index + 1) % 8].setY(y);
					set[(index + 2) % 8].setY(y);
				} else {
					set[(index + 6) % 8].setY(y);
					set[(index + 7) % 8].setY(y);
					set[(index + 1) % 8].setX(x);
					set[(index + 2) % 8].setX(x);
				}
			}
		}
		vector.setX(x);
		vector.setY(y);
	}
}
