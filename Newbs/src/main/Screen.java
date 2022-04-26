package main;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

public class Screen extends JPanel {
	
	private static final long serialVersionUID = 1L;
	
	public static int width;
	public static int height;
	public static final int scale = 8;
	
	private BufferedImage image;
	
	private GSM gsm;
	
	public Screen(GSM gsm) {
		this.gsm = gsm;
		width = Window.width / scale;
		height = Window.height / scale;
		image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
	}
	
	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2 = image.createGraphics();
		gsm.draw(g2);
		g.drawImage(image, 0, 0, width * scale, height * scale, null);
		image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		g2.dispose();
	}
}
