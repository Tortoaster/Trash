package com.rick9399.clothing;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.SwingUtilities;

import com.rick9399.clothing.BlendComposite.BlendingMode;

public class Editor extends State {
	
	private final File dirClothes = new File("Dump hier alle foto's");
	private final File dirDesigns = new File("Dump hier alle ontwerpen");
	private final File dirOutput = new File("Hier is het eindproduct na het programma gebruikt te hebben");
	
	private final String[] EXTENSIONS = new String[] { ".jpg", ".jpeg", ".png", ".gif", ".bmp" };
	
	private final FilenameFilter IMAGE_FILTER = new FilenameFilter() {
		public boolean accept(final File dir, final String name) {
			for (final String ext : EXTENSIONS) {
				if (name.endsWith(ext)) {
					return true;
				}
			}
			return false;
		}
	};
	
	private BufferedImage[] clothing, designs;
	private BufferedImage editedDesign;
	private List<Color> colors = new ArrayList<>();
	private int indexClothing, indexDesigns, indexColors;
	private Rectangle[] ranges;
	private double scaleImage, scaleDesign;
	private boolean multiply = true;
	
	private int mouseX, mouseY;
	
	public Editor() {
		if (dirClothes.isDirectory()) {
			File[] images = dirClothes.listFiles(IMAGE_FILTER);
			clothing = new BufferedImage[images.length];
			for (int i = 0; i < images.length; i++) {
				try {
					clothing[i] = ImageIO.read(images[i]);
				} catch (final IOException e) {
					e.printStackTrace();
				}
			}
		}
		if (dirDesigns.isDirectory()) {
			File[] images = dirDesigns.listFiles(IMAGE_FILTER);
			designs = new BufferedImage[images.length];
			for (int i = 0; i < images.length; i++) {
				try {
					designs[i] = ImageIO.read(images[i]);
				} catch (final IOException e) {
					e.printStackTrace();
				}
			}
		}
		ranges = new Rectangle[clothing.length];
		for (int i = 0; i < ranges.length; i++) {
			BufferedImage image = clothing[i];
			ranges[i] = new Rectangle(0, 0, image.getWidth(), image.getHeight());
		}
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader("Kleuren.txt"));
			StringBuilder sb = new StringBuilder();
			String line = br.readLine();
			while (line != null) {
				sb.append(line);
				sb.append(System.lineSeparator());
				line = br.readLine();
			}
			String lines = sb.toString();
			int index = 0;
			while(true) {
				index = lines.indexOf("#", index);
				if(index > -1) {
					String color = lines.substring(index, index + 7);
					colors.add(hexToRGB(color));
				} else {
					break;
				}
				index += 9;
			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		editDesign();
	}
	
	public void keyPressed(KeyEvent e) {
		switch(e.getKeyCode()) {
		case KeyEvent.VK_UP: case KeyEvent.VK_LEFT:
			indexDesigns = (indexDesigns - 1 + designs.length) % designs.length;
			editDesign();
			break;
		case KeyEvent.VK_DOWN: case KeyEvent.VK_RIGHT:
			indexDesigns = (indexDesigns + 1 + designs.length) % designs.length;
			editDesign();
			break;
		case KeyEvent.VK_ENTER:
			save();
			break;
		case KeyEvent.VK_SPACE:
			next();
			break;
		}
	}
	
	public void mousePressed(MouseEvent e) {
		if(SwingUtilities.isLeftMouseButton(e)) {
			Rectangle rect = ranges[indexClothing];
			rect.x = (int) (e.getX() / scaleImage);
			rect.y = (int) (e.getY() / scaleImage);
			rect.width = (int) (e.getX() / scaleImage) - rect.x;
			rect.height = (int) (e.getY() / scaleImage) - rect.y;
		} else if(SwingUtilities.isRightMouseButton(e)) {
			mouseX = e.getX();
			mouseY = e.getY();
		} else if(SwingUtilities.isMiddleMouseButton(e)) {
			if(multiply) {
				multiply = false;
			} else {
				multiply = true;
			}
			editDesign();
		}
	}
	
	public void mouseDragged(MouseEvent e) {
		Rectangle rect = ranges[indexClothing];
		if(SwingUtilities.isLeftMouseButton(e)) {
			rect.width = (int) (e.getX() / scaleImage) - rect.x;
			rect.height = (int) (e.getY() / scaleImage) - rect.y;
		} else if(SwingUtilities.isRightMouseButton(e)) {
			rect.x += (e.getX() - mouseX) / scaleImage;
			rect.y += (e.getY() - mouseY) / scaleImage;
			mouseX = e.getX();
			mouseY = e.getY();
		}
	}
	
	public void mouseWheelMoved(MouseWheelEvent e) {
		indexColors = (indexColors + (e.getWheelRotation() % colors.size()) + colors.size()) % colors.size();
		editDesign();
	}
	
	public void update() {}
	
	public void draw(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		BufferedImage image = clothing[indexClothing];
		Rectangle range = ranges[indexClothing];
		scaleImage = Math.min((double) Window.WIDTH / image.getWidth(), (double) Window.HEIGHT / image.getHeight());
		scaleDesign = Math.min(range.getWidth() / editedDesign.getWidth(), range.getHeight() / editedDesign.getHeight()) * scaleImage;
		g.drawImage(image, 0, 0, (int) (image.getWidth() * scaleImage), (int) (image.getHeight() * scaleImage), null);
		if(multiply) {
			g2d.setComposite(new BlendComposite(BlendingMode.MULTIPLY));
		} else {
			g2d.setComposite(new BlendComposite(BlendingMode.SCREEN));
		}
		int offsetX = (int) (range.x * scaleImage - range.x + (range.width * scaleImage - editedDesign.getWidth() * scaleDesign) / 2);
		int offsetY = (int) (range.y * scaleImage - range.y + (range.height * scaleImage - editedDesign.getHeight() * scaleDesign) / 2);
		g.drawImage(editedDesign, range.x + offsetX, range.y + offsetY, (int) (editedDesign.getWidth() * scaleDesign), (int) (editedDesign.getHeight() * scaleDesign), null);
		g2d.setComposite(new BlendComposite(BlendingMode.NORMAL));
		g.setColor(Color.CYAN);
		g.drawRect((int) (range.x * scaleImage), (int) (range.y * scaleImage), (int) (range.width * scaleImage) - 1, (int) (range.height * scaleImage) - 1);
	}
	
	public void save() {
		BufferedImage image = clothing[indexClothing];
		Rectangle range = ranges[indexClothing];
		int color = indexColors;
		for(int i = 0; i < colors.size(); i++) {
			BufferedImage export = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_ARGB);
			Graphics2D g = export.createGraphics();
			indexColors = i;
			editDesign();
			g.drawImage(image, 0, 0, null);
			if(multiply) {
				g.setComposite(new BlendComposite(BlendingMode.MULTIPLY));
			} else {
				g.setComposite(new BlendComposite(BlendingMode.SCREEN));
			}
			int width = (int) (1.0 / scaleImage * editedDesign.getWidth() * scaleDesign);
			int height = (int) (1.0 / scaleImage * editedDesign.getHeight() * scaleDesign);
			int x = range.x + (range.width - width) / 2;
			int y = range.y + (range.height - height) / 2;
			g.drawImage(editedDesign, x, y, width, height, null);
			g.setComposite(new BlendComposite(BlendingMode.NORMAL));
			try {
			    ImageIO.write(export, "PNG", new File(dirOutput.getPath() + "/IMG_" + indexClothing + "_" + indexDesigns + "_" + i + ".png"));
			} catch (IOException e) {
			    e.printStackTrace();
			}
		}
		indexColors = color;
		editDesign();
	}
	
	public void next() {
		indexClothing++;
		if(indexClothing >= clothing.length) {
			System.exit(0);
		}
	}
	
	public void editDesign() {
		if(multiply) {
			editedDesign = changeColor(designs[indexDesigns], colors.get(indexColors));
		} else {
			editedDesign = invertImage(changeColor(designs[indexDesigns], colors.get(indexColors)));
		}
	}
	
	public BufferedImage changeColor(BufferedImage image, Color color) {
		BufferedImage img = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_RGB);
		Graphics g = img.getGraphics();
		for(int y = 0; y < img.getHeight(); y++) for(int x = 0; x < img.getWidth(); x++) {
			Color c = new Color(image.getRGB(x, y));
			int red = (int) ((1.0 - c.getRed() / 256.0) * color.getRed() + c.getRed());
			int green = (int) ((1.0 - c.getGreen() / 256.0) * color.getGreen() + c.getGreen());
			int blue = (int) ((1.0 - c.getBlue() / 256.0) * color.getBlue() + c.getBlue());
			g.setColor(new Color(red, green, blue));
			g.drawLine(x, y, x, y);
		}
		g.dispose();
		return img;
	}
	
	public BufferedImage invertImage(BufferedImage image) {
		BufferedImage img = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_RGB);
		Graphics g = img.getGraphics();
		g.drawImage(image, 0, 0, null);
		for(int y = 0; y < img.getHeight(); y++) for(int x = 0; x < img.getWidth(); x++) {
			int rgb = img.getRGB(x, y);
			Color color = new Color(rgb);
			color = new Color(255 - color.getRed(), 255 - color.getGreen(), 255 - color.getBlue());
			img.setRGB(x, y, color.getRGB());
		}
		return img;
	} 
	
	public Color hexToRGB(String color) {
		return new Color(Integer.valueOf(color.substring(1, 3), 16), Integer.valueOf(color.substring(3, 5), 16), Integer.valueOf(color.substring(5, 7), 16));
	}
}
