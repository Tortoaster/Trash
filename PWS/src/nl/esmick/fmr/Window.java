package nl.esmick.fmr;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.core.Rect;
import org.opencv.highgui.VideoCapture;
import org.opencv.objdetect.CascadeClassifier;

import com.jhlabs.image.EdgeFilter;
import com.jhlabs.image.FourColorFilter;
import com.jhlabs.image.GaussianFilter;
import com.jhlabs.image.GrayscaleFilter;

public class Window extends JPanel implements Runnable, MouseListener, MouseMotionListener {
	
	// MOUSE SPECS
	
	private Vector held;
	private int mouseX, mouseY, set;
	private boolean pressed, right;
	
	// VARIABLES
	
	private boolean recording = true, photo = false, lines = true, looking = true, adjusting = true;
	
	private int number;
	public static int WIDTH, HEIGHT;
	public static final int RATE = 30;
	
	private static final long serialVersionUID = 1L;
	
	private CascadeClassifier eyeShape;
	private CascadeClassifier faceShape;
	private CascadeClassifier mouthShape;
	private CascadeClassifier noseShape;
	private Mat frame = new Mat();	
	private MatOfRect detections = new MatOfRect();
	private VideoCapture camera = new VideoCapture(0);
	
	private VectorSet face;
	private VectorSet leftEye;
	private VectorSet rightEye;
	private VectorSet nose;
	private VectorSet mouth;
	
	private int x, y, width, height, xMask, yMask, widthMask, heightMask;
	
	private BufferedImage original, image, mask;
	
	private JLabel status = new JLabel("Please take a picture.");
	
	// INITIALISING
	
	public Window(int num) {
		try {
			eyeShape = new CascadeClassifier(new File(Window.class.getProtectionDomain().getCodeSource().getLocation().toURI()).getPath().replace("FMR.jar", "") + "/haarcascade_eye.xml");
			faceShape = new CascadeClassifier(new File(Window.class.getProtectionDomain().getCodeSource().getLocation().toURI()).getPath().replace("FMR.jar", "") + "/haarcascade_frontalface_alt.xml");
			mouthShape = new CascadeClassifier(new File(Window.class.getProtectionDomain().getCodeSource().getLocation().toURI()).getPath().replace("FMR.jar", "") + "/haarcascade_mcs_mouth.xml");
			noseShape = new CascadeClassifier(new File(Window.class.getProtectionDomain().getCodeSource().getLocation().toURI()).getPath().replace("FMR.jar", "") + "/haarcascade_mcs_nose.xml");
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		number = num;
		Thread thread = new Thread(this);
		JFrame frame = new JFrame("Frontal Mask Reposer");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setVisible(true);
		JPanel options = new JPanel();
		options.setBackground(Color.LIGHT_GRAY);
		options.add(status);
		JButton stop = new JButton("Repose!");
		stop.setEnabled(false);
		stop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				adjusting = false;
				stop.setEnabled(false);
			}
		});
		JButton shoot = new JButton("Take picture");
		shoot.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				recording = false;
				shoot.setEnabled(false);
				stop.setEnabled(true);
			}
		});
		options.add(shoot);
		options.add(stop);
		JButton retry = new JButton("Try again");
		retry.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				recording = false;
				adjusting = false;
				looking = false;
				try {
					thread.join();
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				frame.dispose();
				new Window(number + 1);
			}
		});
		options.add(retry);
		JCheckBox guide = new JCheckBox("Show guidelines", true);
		guide.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == ItemEvent.SELECTED) {
					lines = true;
				} else {
					lines = false;
				}
			}
		});
		options.add(guide);
		frame.add(options, BorderLayout.SOUTH);
		setBackground(Color.DARK_GRAY);
		frame.add(this);
		camera.retrieve(this.frame);
		image = toBufferedImage(this.frame);
		WIDTH = image.getWidth();
		HEIGHT = image.getHeight();
		frame.setSize(WIDTH + 6, HEIGHT + options.getSize().height + 29);
		frame.setLocationRelativeTo(null);
		if(!photo) {
			BufferedImage tmp = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_3BYTE_BGR);
			try {
				image = ImageIO.read(new File("res/images/" + number + ".jpg"));
			} catch (IOException e) {
				try {
					image = ImageIO.read(new File("res/images/" + number + ".jpg"));
				} catch (IOException e1) {}
			}
			if(image.getWidth() > WIDTH) {
				Image scl = image.getScaledInstance(WIDTH, (int) ((double) WIDTH / image.getWidth() * image.getHeight()), Image.SCALE_SMOOTH);
				Graphics g = tmp.getGraphics();
				g.drawImage(scl, 0, 0, null);
				g.dispose();
			}
			if(image.getHeight() > HEIGHT) {
				Image scl = image.getScaledInstance((int) ((double) HEIGHT / image.getHeight() * image.getWidth()), HEIGHT, Image.SCALE_SMOOTH);
				Graphics g = tmp.getGraphics();
				g.drawImage(scl, 0, 0, null);
				g.dispose();
			}
			image = tmp;
		}
		addMouseListener(this);
		addMouseMotionListener(this);
		try {
			try {
				mask = ImageIO.read(new File(new File(Window.class.getProtectionDomain().getCodeSource().getLocation().toURI()).getPath().replace("FMR.jar", "") + "/mask.png"));
			} catch (URISyntaxException e1) {
				e1.printStackTrace();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		thread.start();
	}
	
	// STARTING
	
	public static void main(String[] args) {
		try {
			System.load(new File(Window.class.getProtectionDomain().getCodeSource().getLocation().toURI()).getPath().replace("FMR.jar", "") + "/opencv_java2413.dll");
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		new Window(1);
	}
	
	// DOING EVERYTHING
	
	public void run() {
		if(photo) {
			while (recording) {
				long time = System.currentTimeMillis();
				camera.retrieve(frame);
				image = toBufferedImage(frame);
				repaint();
				try {
					Thread.sleep(Math.max(0, 1000 / RATE - (System.currentTimeMillis() - time)));
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		} else {
			frame = toMat(image);
			recording = false;
		}
		original = toBufferedImage(frame);
		camera.release();
		status.setText("Finding face...");
		faceShape.detectMultiScale(frame, detections);
		if(detections.toArray().length > 0) {
			int size = 0, index = 0;
			for(int i = 0; i < detections.toArray().length; i++) {
				int current = detections.toArray()[i].width * detections.toArray()[i].height;
				if(current > size) {
					size = current;
					index = i;
				}
			}
			Rect rectangle = detections.toArray()[index];
			x = rectangle.x + rectangle.width / 16;
			y = Math.max(0, rectangle.y - rectangle.height / 5);
			width = rectangle.width - rectangle.width / 8;
			height = Math.min(rectangle.height + rectangle.height / 3, HEIGHT - Math.max(0, rectangle.y - rectangle.height / 5));
			Mat area = frame.submat(new Rect(x, y, width, height));
			face = new VectorSet(x, y, width, height, Color.RED);
			noseShape.detectMultiScale(area, detections);
			int nosePos = 0;
			int noseHeight = 0;
			for(Rect rect : detections.toArray()) {
				if(Math.abs(rect.y - height / 2) < height / 10) {
					nose = new VectorSet(rect.x + x, rect.y + y, rect.width, rect.height, Color.GREEN);
					nosePos = rect.y;
					noseHeight = rect.height;
					break;
				}
			}
			mouthShape.detectMultiScale(area, detections);
			for(Rect rect : detections.toArray()) {
				if(rect.y > nosePos + noseHeight - height / 20 && Math.abs(rect.x + rect.width / 2 - width / 2) < width / 10 && rect.y - nosePos - noseHeight < height / 10) {
					mouth = new VectorSet(rect.x + x, rect.y + y, rect.width, rect.height, Color.CYAN);
					break;
				}
			}
			eyeShape.detectMultiScale(area, detections);
			int amount = 0;
			for(Rect rect : detections.toArray()) {
				if(rect.y < nosePos) {
					if(rect.x + rect.width / 2 < width / 2 && leftEye == null) {
						leftEye = new VectorSet(rect.x + x, rect.y + y, rect.width, rect.height, Color.YELLOW);
						amount++;
					} else if(rect.x + rect.width / 2 > width / 2 && rightEye == null) {
						rightEye = new VectorSet(rect.x + x, rect.y + y, rect.width, rect.height, Color.YELLOW);
						amount++;
					}
				}
				if(amount > 1) break;
			}
			status.setText("Please adjust the keypoints");
			while(adjusting) {
				long time = System.currentTimeMillis();
				if(held != null && pressed) {
					switch(set) {
					case 0: face.setPos(held, mouseX + Math.max(0, Math.min(original.getWidth() / 2, this.x - (original.getWidth() / 2 - width) / 2)), mouseY, right);
					break;
					case 1: leftEye.setPos(held, mouseX + Math.max(0, Math.min(original.getWidth() / 2, this.x - (original.getWidth() / 2 - width) / 2)), mouseY, right);
					break;
					case 2: rightEye.setPos(held, mouseX + Math.max(0, Math.min(original.getWidth() / 2, this.x - (original.getWidth() / 2 - width) / 2)), mouseY, right);
					break;
					case 3: nose.setPos(held, mouseX + Math.max(0, Math.min(original.getWidth() / 2, this.x - (original.getWidth() / 2 - width) / 2)), mouseY, right);
					break;
					case 4: mouth.setPos(held, mouseX + Math.max(0, Math.min(original.getWidth() / 2, this.x - (original.getWidth() / 2 - width) / 2)), mouseY, right);
					break;
					}
				}
				repaint();
				try {
					Thread.sleep(Math.max(0, 1000 / RATE - (System.currentTimeMillis() - time)));
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			x = face.getX();
			y = face.getY();
			width = face.getWidth();
			height = face.getHeight();
			widthMask = width;
			heightMask = (int) ((double) width / mask.getWidth() * mask.getHeight());
			xMask = x;
			yMask = y + (height - heightMask) / 2;
			repaint();
			status.setText("Changing proportions...");
			drawSkin(2, 2, WIDTH - 4, HEIGHT - 4, original, image);
			transformImage(0, 0, WIDTH, HEIGHT, 0, (HEIGHT - (int) ((double) heightMask / height * HEIGHT)) / 2, WIDTH, (int) ((double) heightMask / height * HEIGHT), original, image);
			repaint();
			drawSkin(mouth.getX(), mouth.getY(), mouth.getWidth(), mouth.getHeight(), original, image);
			transformImage(mouth.getX(), mouth.getY(), mouth.getWidth(), mouth.getHeight(), xMask + widthMask / 2 - (int) (170.0 / mask.getWidth() * widthMask) / 2, yMask + (int) (440.0 / mask.getHeight() * heightMask), (int) (170.0 / mask.getWidth() * widthMask), (int) (70.0 / mask.getHeight() * heightMask), original, image);
			repaint();
			drawSkin(nose.getX(), nose.getY(), nose.getWidth(), nose.getHeight(), original, image);
			transformImage(nose.getX(), nose.getY(), nose.getWidth(), nose.getHeight(), xMask + widthMask / 2 - (int) (130.0 / mask.getWidth() * widthMask) / 2, yMask + heightMask - (int) (175.0 / mask.getHeight() * heightMask) - nose.getHeight(), (int) (130.0 / mask.getWidth() * widthMask), nose.getHeight(), original, image);
			repaint();
			drawSkin(leftEye.getX(), leftEye.getY(), leftEye.getWidth(), leftEye.getHeight(), original, image);
			transformImage(leftEye.getX(), leftEye.getY(), leftEye.getWidth(), leftEye.getHeight(), xMask + (int) (55.0 / mask.getWidth() * widthMask), yMask + (int) (215.0 / mask.getHeight() * heightMask), (int) (120.0 / mask.getWidth() * widthMask), (int) (65.0 / mask.getHeight() * heightMask), original, image);
			drawSkin(rightEye.getX(), rightEye.getY(), rightEye.getWidth(), rightEye.getHeight(), original, image);
			transformImage(rightEye.getX(), rightEye.getY(), rightEye.getWidth(), rightEye.getHeight(), xMask + (int) ((mask.getWidth() - 175.0) / mask.getWidth() * widthMask), yMask + (int) (215.0 / mask.getHeight() * heightMask), (int) (120.0 / mask.getWidth() * widthMask), (int) (65.0 / mask.getHeight() * heightMask), original, image);
			repaint();
			status.setText("Blurring...");
			blur(image);
			repaint();
			if(!photo) {
				status.setText("Saving.");
				File save = new File("res/edited/" + number + ".jpg");
				try {
					ImageIO.write(image, "JPG", save);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			status.setText("Done.");
		} else status.setText("No face detected.");
		while(looking) {
			long time = System.currentTimeMillis();
			repaint();
			try {
				Thread.sleep(Math.max(0, 1000 / RATE - (System.currentTimeMillis() - time)));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	// DRAWING
	
	public void paint(Graphics g) {
		super.paint(g);
		if(recording || x == 0) {
			g.drawImage(image, 0, 0, null);
		} else {
			g.drawImage(original.getSubimage(Math.max(0, Math.min(original.getWidth() / 2, x - (original.getWidth() / 2 - width) / 2)), 0, original.getWidth() / 2, original.getHeight()), 0, 0, null);
			g.drawImage(image.getSubimage(Math.max(0, Math.min(image.getWidth() / 2, x - (image.getWidth() / 2 - width) / 2)), 0, original.getWidth() / 2, image.getHeight()), image.getWidth() / 2, 0, null);
			if(lines) {
				if(face != null) g.drawImage(face.getImage().getSubimage(Math.max(0, Math.min(original.getWidth() / 2, x - (original.getWidth() / 2 - width) / 2)), 0, original.getWidth() / 2, original.getHeight()), 0, 0, null);
				if(leftEye != null) g.drawImage(leftEye.getImage().getSubimage(Math.max(0, Math.min(original.getWidth() / 2, x - (original.getWidth() / 2 - width) / 2)), 0, original.getWidth() / 2, original.getHeight()), 0, 0, null);
				if(rightEye != null) g.drawImage(rightEye.getImage().getSubimage(Math.max(0, Math.min(original.getWidth() / 2, x - (original.getWidth() / 2 - width) / 2)), 0, original.getWidth() / 2, original.getHeight()), 0, 0, null);
				if(nose != null) g.drawImage(nose.getImage().getSubimage(Math.max(0, Math.min(original.getWidth() / 2, x - (original.getWidth() / 2 - width) / 2)), 0, original.getWidth() / 2, original.getHeight()), 0, 0, null);
				if(mouth != null) g.drawImage(mouth.getImage().getSubimage(Math.max(0, Math.min(original.getWidth() / 2, x - (original.getWidth() / 2 - width) / 2)), 0, original.getWidth() / 2, original.getHeight()), 0, 0, null);
				g.drawImage(mask, x - Math.max(0, Math.min(original.getWidth() / 2, x - (original.getWidth() / 2 - width) / 2)) + WIDTH / 2, y + (height - (int) ((double) width / mask.getWidth() * mask.getHeight())) / 2, width, (int) ((double) width / mask.getWidth() * mask.getHeight()), null);
			}
		}
	}
	
	// FILTERS
	
	public void transformImage(int x, int y, int width, int height, int xDest, int yDest, int widthDest, int heightDest, BufferedImage source, BufferedImage image) {
		BufferedImage part = source.getSubimage(x, y, width, height);
		Graphics g = image.getGraphics();
		g.drawImage(part, xDest, yDest, widthDest, heightDest, null);
	}
	
	public void drawSkin(int x, int y, int width, int height, BufferedImage source, BufferedImage image) {
		Graphics g = image.getGraphics();
		BufferedImage skin = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		FourColorFilter fcf = new FourColorFilter();
		fcf.setColorNE(source.getRGB(x + width + 1, y - 1));
		fcf.setColorSE(source.getRGB(x + width + 1, y + height + 1));
		fcf.setColorSW(source.getRGB(x - 1, y + height + 1));
		fcf.setColorNW(source.getRGB(x - 1, y - 1));
		fcf.filter(skin, skin);
		g.drawImage(skin, x, y, null);
		g.dispose();
	}
	
	public BufferedImage toBufferedImage(Mat m) {
		int type = BufferedImage.TYPE_BYTE_GRAY;
		if (m.channels() > 1) {
			type = BufferedImage.TYPE_3BYTE_BGR;
		}
		int bufferSize = m.channels() * m.rows() * m.cols();
		byte[] b = new byte[bufferSize];
		m.get(0, 0, b);
		BufferedImage image = new BufferedImage(m.cols(),m.rows(), type);
		final byte[] targetPixels = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();
		System.arraycopy(b, 0, targetPixels, 0, b.length);
		return image;
	}
	
	public Mat toMat(BufferedImage image) {
		Mat mat = new Mat(image.getHeight(), image.getWidth(), CvType.CV_8UC3);
		byte[] data = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();
		mat.put(0, 0, data);
		return mat;
	}
	
	public void blur(BufferedImage image) {
		BufferedImage contours = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_RGB);
		BufferedImage mask = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_ARGB);
		EdgeFilter edge = new EdgeFilter();
		GaussianFilter blur = new GaussianFilter();
		GrayscaleFilter gray = new GrayscaleFilter();
		blur.setRadius(25);
		edge.filter(image, contours);
		blur.filter(contours, contours);
		gray.filter(contours, contours);
		Graphics g = mask.getGraphics();
		for(int y = 0; y < contours.getHeight(); y++) for(int x = 0; x < contours.getWidth(); x++) {
			Color color = new Color(image.getRGB(x, y), true);
			color = new Color(color.getRed(), color.getGreen(), color.getBlue(), (int) Math.max(0, 255 - Math.pow(new Color(contours.getRGB(x, y)).getRed(), 1.5)));
			g.setColor(color);
			g.drawLine(x, y, x, y);
		}
		g.dispose();
		blur.setRadius(10);
		blur.filter(mask, mask);
		Graphics g2 = image.getGraphics();
		g2.drawImage(mask, 0, 0, null);
		g2.dispose();
	}
	
	public void mouseClicked(MouseEvent e) {}
	
	public void mouseEntered(MouseEvent e) {}
	
	public void mouseExited(MouseEvent e) {}
	
	public void mousePressed(MouseEvent e) {
		pressed = true;
		if(e.getButton() == MouseEvent.BUTTON3) right = true;
		int x = e.getX() + Math.max(0, Math.min(original.getWidth() / 2, this.x - (original.getWidth() / 2 - width) / 2));
		int y = e.getY();
		Vector vector = null;
		for(int i = 0; i < 5; i++) {
			if(vector == null) {
				switch(i) {
				case 0: vector = face.getVector(x, y);
				break;
				case 1: vector = leftEye.getVector(x, y);
				break;
				case 2: vector = rightEye.getVector(x, y);
				break;
				case 3: vector = nose.getVector(x, y);
				break;
				case 4: vector = mouth.getVector(x, y);
				break;
				}
				set = i;
			} else break;
		}
		held = vector;
	}
	
	public void mouseReleased(MouseEvent e) {
		pressed = false;
		right = false;
	}
	
	public void mouseDragged(MouseEvent e) {
		mouseX = e.getX();
		mouseY = e.getY();
	}
	
	public void mouseMoved(MouseEvent e) {
		mouseX = e.getX();
		mouseY = e.getY();
	}
}
