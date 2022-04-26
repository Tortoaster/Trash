import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Arrow {
	
	public enum Dir {
		X, Y;
	}
	
	private boolean visible;
	private boolean moving = true;
	
	private double x;
	private double y;
	private double xV;
	private double yV;
	private double sharpness;
	private double bounciness;
	private double weight;
	
	private int length;
	private int delay;
	
	private Arrow[] arrow;
	
	public Arrow(int x, int y, int xD, int yD, int v, int length, double weight, double sharpness, double bounciness, boolean visible, Arrow[] quiver) {
		this.x = x;
		this.y = y;
		xV = Math.cos(Math.atan2(yD - y, xD - x)) * v;
		yV = Math.sin(Math.atan2(yD - y, xD - x)) * v;
		this.length = length;
		this.weight = weight;
		this.sharpness = sharpness;
		this.bounciness = bounciness;
		this.visible = visible;
		arrow = quiver;
	}
	
	public void update() {
		if (visible && moving) {
			x += xV;
			y += yV;
			yV += 0.1 * weight;
			if (x < 0 || x > GSM.WIDTH - 1) if (Math.abs(xV) < 5 / sharpness && Math.abs(xV) > 0.5 / sharpness) {
				x = Math.max(0, Math.min(GSM.WIDTH - 1, x));
				xV *= -bounciness;
			} else {
				x = Math.max(0, Math.min(GSM.WIDTH - 1, x));
				if (moving) onHit(Dir.X);
				delay = 0;
				moving = false;
			}
			if (y < 0 || y > GSM.HEIGHT -1) if (Math.abs(yV) < 5 / sharpness && Math.abs(yV) > 0.5 / sharpness) {
				y = Math.max(0, Math.min(GSM.HEIGHT - 1, y));
				yV *= -bounciness;
			} else {
				y = Math.max(0, Math.min(GSM.HEIGHT - 1, y));
				if (moving) onHit(Dir.Y);
				delay = 0;
				moving = false;
			}
		} else if (visible && !moving) {
			if (delay <= 0) visible = false;
			delay--;
		}
	}
	
	public void onHit(Dir dir) {
		if (dir == Dir.X) {
			if (length > 0) {
				int eaters = 1;
				for (int e = 0; e < eaters; e++) for (int i = 0; i < arrow.length; i++) if (!arrow[i].isVisible()) {
					int angle = (int) Math.toDegrees(Math.atan2(yV, xV));
					arrow[i] = new Arrow((int) x, (int) y, (int) (x + Math.cos(Math.toRadians(angle)) * -10), (int) (y + Math.sin(Math.toRadians(angle)) * 10), 10, length - 5, -weight, -sharpness, 1, true, arrow);
					break;
				}
			}
		} else {
			if (length > 0) {
				int eaters = 1;
				for (int e = 0; e < eaters; e++) for (int i = 0; i < arrow.length; i++) if (!arrow[i].isVisible()) {
					int angle = (int) Math.toDegrees(Math.atan2(yV, xV));
					arrow[i] = new Arrow((int) x, (int) y, (int) (x + Math.cos(Math.toRadians(angle)) * 10), (int) (y + Math.sin(Math.toRadians(angle)) * -10), 10, length - 5, -weight, -sharpness, 1, true, arrow);
					break;
				}
			}
		}
	}
	
	public boolean isVisible() {
		return visible;
	}
	
	public void draw(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		if (visible) {
			g.setColor(new Color((int) Math.max(0, Math.min(250, 60 + 10 / sharpness * 10)), (int) Math.max(0, Math.min(250, 30 + 10 / sharpness * 5)), (int) Math.max(0, Math.min(250, 15 + 10 / sharpness * 20))));
			g2d.setStroke(new BasicStroke(2));
			g.drawLine((int) x, (int) y, (int) (x - Math.cos(Math.atan2(yV, xV)) * length), (int) (y - Math.sin(Math.atan2(yV, xV)) * length));
			g.setColor(new Color((int) Math.max(0, Math.min(250, 70 + sharpness * 10)), (int) Math.max(0, Math.min(250, 70 + sharpness * 10)), (int) Math.max(0, Math.min(250, 60 + sharpness * 10))));
			g.fillOval((int) x - 3, (int) y - 3, 6, 6);
		}
	}
}






























