package com.whale.gravity.effects;

import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Lightning {
	
	private Bolt[] bolt;
	
	private List<Lightning> branch = new ArrayList<Lightning>();
	
	private Random random = new Random();
	
	public Lightning(int x1, int y1, int x2, int y2) {
		double a = Math.atan2(y2 - y1, x2 - x1);
		double length = Math.sqrt((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1));
		double[] pos = new double[(int) Math.max(2, Math.sqrt((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1)) / 40)];
		pos[0] = 0;
		pos[1] = 1;
		for (int i = 2; i < pos.length; i++) pos[i] = random.nextDouble();
		Arrays.sort(pos);
		Point[] point = new Point[pos.length];
		point[0] = new Point(x1, y1);
		point[point.length - 1] = new Point(x2, y2);
		int displacement = 0;
		for (int i = 1; i < point.length - 1; i++) {
			double x = x1 + Math.cos(a) * pos[i] * length;
			double y = y1 + Math.sin(a) * pos[i] * length;
			int max = (int) Math.max(-30, Math.min(30, Math.sqrt((x - point[i - 1].x) * (x - point[i - 1].x) + (y - point[i - 1].y) * (y - point[i - 1].y))));
			displacement -= Math.max(-Math.abs(max), Math.min(Math.abs(max), random.nextInt(max * 2 + 1) - max));
			x += Math.sin(a) * displacement;
			y += Math.cos(a) * -displacement;
			point[i] = new Point((int) x, (int) y);
		}
		bolt = new Bolt[point.length - 1];
		for (int i = 0; i < bolt.length; i++) {
			bolt[i] = new Bolt(point[i].x, point[i].y, point[i + 1].x, point[i + 1].y);
			for (int n = 0; n < 2; n++) if (random.nextInt(20) == 0) branch.add(new Lightning(point[i].x, point[i].y, point[i].x + (int) (Math.cos(a + (random.nextBoolean() ? -1 : 1) * Math.toRadians(random.nextInt(15) + 15)) * random.nextDouble() * Math.sqrt((point[i].x - point[point.length - 1].x) * (point[i].x - point[point.length - 1].x) + (point[i].y - point[point.length - 1].y) * (point[i].y - point[point.length - 1].y))), point[i].y + (int) (Math.sin(a + (random.nextBoolean() ? -1 : 1) * Math.toRadians(random.nextInt(15) + 15)) * random.nextDouble() * Math.sqrt((point[i].x - point[point.length - 1].x) * (point[i].x - point[point.length - 1].x) + (point[i].y - point[point.length - 1].y) * (point[i].y - point[point.length - 1].y)))));
		}
	}
	
	public void draw(Graphics g) {
		for (int i = 0; i < bolt.length; i++) bolt[i].draw(g);
		for (Lightning l: branch) l.draw(g);
	}
}
